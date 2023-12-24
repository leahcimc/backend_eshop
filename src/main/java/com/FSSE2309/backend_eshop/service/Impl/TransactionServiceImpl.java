package com.FSSE2309.backend_eshop.service.Impl;

import com.FSSE2309.backend_eshop.data.cart.entity.CartEntity;
import com.FSSE2309.backend_eshop.data.product.entity.ProductEntity;
import com.FSSE2309.backend_eshop.data.transaction.domainObj.TransactionBillData;
import com.FSSE2309.backend_eshop.data.transaction.domainObj.TransactionDetailData;
import com.FSSE2309.backend_eshop.data.transaction.domainObj.TransactionActionStatus;
import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionEntity;
import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionProductEntity;
import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionStatus;
import com.FSSE2309.backend_eshop.data.user.domainObj.FirebaseUserData;
import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;
import com.FSSE2309.backend_eshop.exception.InvalidInputIdException;
import com.FSSE2309.backend_eshop.exception.ProductNotExistedException;
import com.FSSE2309.backend_eshop.exception.InvalidTransactionStatusException;
import com.FSSE2309.backend_eshop.exception.TransactionNotExistException;
import com.FSSE2309.backend_eshop.repository.TransactionProductRepository;
import com.FSSE2309.backend_eshop.repository.TransactionRepository;
import com.FSSE2309.backend_eshop.service.CartService;
import com.FSSE2309.backend_eshop.service.ProductService;
import com.FSSE2309.backend_eshop.service.TransactionService;
import com.FSSE2309.backend_eshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    //Attribute
    private UserService userService;
    private CartService cartService;
    private ProductService productService;
    private TransactionRepository transactionRepository;
    private TransactionProductRepository transactionProductRepository;
    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    //Constructor
    @Autowired
    public TransactionServiceImpl(UserService userService,
                                  CartService cartService,
                                  ProductService productService,
                                  TransactionRepository transactionRepository,
                                  TransactionProductRepository transactionProductRepository){
        this.userService = userService;
        this.cartService = cartService;
        this.productService = productService;
        this.transactionRepository = transactionRepository;
        this.transactionProductRepository = transactionProductRepository;
    }


    //Method from api
    @Override
    public TransactionDetailData createNewTransaction(FirebaseUserData data){
        try {
            //get cart
            UserEntity user = userService.getByFirebase(data);
            List<CartEntity> checkOutCart = cartService.getCartEntityList(user);
            checkEmptyList(checkOutCart);

            //save transaction
            TransactionEntity newTransaction = new TransactionEntity(checkOutCart, user);
            transactionRepository.save(newTransaction);

            //save transaction product
//            List<TransactionProductEntity> list = new ArrayList<>();
//            TransactionProductEntity buyProduct;
//
//            for (CartEntity item:
//                 checkOutCart) {
//                buyProduct = new TransactionProductEntity(item, newTransaction);
//                list.add(buyProduct);
//                transactionProductRepository.save(buyProduct);
//            }

            List<TransactionProductEntity> list = checkOutCart.stream()
                    .map(item -> transactionProductRepository.save(new TransactionProductEntity(item, newTransaction)))
                    .collect(Collectors.toList());

            //clear cart
//            cartService.clearUserCart(checkOutCart);
            cartService.clearUserCartByUid(user.getUid());

            return new TransactionDetailData(newTransaction, list);

        }catch(ProductNotExistedException e){
            logger.warn("Cart is empty.");
            throw e;
        }

    }

    @Override
    public TransactionDetailData getTransaction(Object tid, FirebaseUserData data){
        try {
            int tidInt = checkValidInt(tid);

            //find Transaction
            TransactionEntity desiredTransaction = findTransaction(tidInt);

            //find corresponding item
            List<TransactionProductEntity> resultList = findCorrespondingItem(tidInt);

            return new TransactionDetailData(desiredTransaction, resultList);

        }catch(NumberFormatException e){
            logger.warn("Input ID is not valid.");
            throw new InvalidInputIdException();

        }catch(TransactionNotExistException e){
            logger.warn("Transaction " + tid + " is not existed.");
            throw e;
        }
    }
    @Override
    public List<TransactionBillData> getAllTransaction(FirebaseUserData data){
        try {
            //get user
            UserEntity user = userService.getByFirebase(data);

            //find All Transaction by user
            List<TransactionEntity> resultList = findAllTransaction(user.getUid());

            return resultList.stream().map(TransactionBillData::new).collect(Collectors.toList());

        }catch(NumberFormatException e){
            logger.warn("Input ID is not valid.");
            throw new InvalidInputIdException();

        }
    }

    @Override
    public TransactionActionStatus updateStatus(Object tid, FirebaseUserData data){
        try{
            int tidInt = checkValidInt(tid);

            //find Transaction
            TransactionEntity desiredTransaction = findTransaction(tidInt);

            //change status to process
            if(desiredTransaction.getStatus() == TransactionStatus.PREPARE){

                //find corresponding item
                List<TransactionProductEntity> resultList = findCorrespondingItem(tidInt);

                //deduct stock
                for (TransactionProductEntity item:
                     resultList) {

                    ProductEntity product = productService.checkProduct(item.getPid());
                    int updatedStock = stockCheckFinal(product, item);
                    product.setStock(updatedStock);
                    productService.saveNewStock(product);

                }


                //change status
                desiredTransaction.setStatus(TransactionStatus.PROCESSING);
                transactionRepository.save(desiredTransaction);

                return TransactionActionStatus.SUCCESS;

            }else {
                throw new InvalidTransactionStatusException();
            }

        }catch(NumberFormatException e){
            logger.warn("Input ID is not valid.");
            throw new InvalidInputIdException();

        }catch(TransactionNotExistException e){
            logger.warn("Transaction " + tid + " is not existed.");
            throw e;

        }catch(ProductNotExistedException e){
            logger.warn("Some product is out of stock.");
            throw e;

        }catch(InvalidTransactionStatusException e){
            logger.warn("Transaction " + tid + " is already paid and in process.");
            return TransactionActionStatus.FAIL;
        }
    }

    @Override
    public TransactionActionStatus finishTransaction(Object tid, FirebaseUserData data){
        try{
            int tidInt = checkValidInt(tid);

            //find Transaction
            TransactionEntity desiredTransaction = findTransaction(tidInt);

            //change status to finish
            if(desiredTransaction.getStatus() == TransactionStatus.PROCESSING){
                desiredTransaction.setStatus(TransactionStatus.FINISH);
                transactionRepository.save(desiredTransaction);
                return TransactionActionStatus.SUCCESS;

            }else {
                throw new InvalidTransactionStatusException();
            }

        }catch(NumberFormatException e){
            logger.warn("Input ID is not valid.");
            throw new InvalidInputIdException();

        }catch(TransactionNotExistException e){
            logger.warn("Transaction " + tid + " is not existed.");
            throw e;

        }catch(InvalidTransactionStatusException e){
            logger.warn("Transaction " + tid + " is not in process.");
            return TransactionActionStatus.FAIL;
        }
    }

    @Override
    @Transactional
    public TransactionActionStatus deleteTransaction(Object tid, FirebaseUserData data){
        try{
            int tidInt = checkValidInt(tid);
            TransactionEntity transaction = findTransaction(tidInt);

            if(transaction.getStatus() != TransactionStatus.PREPARE){
                throw new InvalidTransactionStatusException();

            }else {
                transactionProductRepository.deleteAllByTransaction_Tid(tidInt);
                transactionRepository.deleteById(tidInt);
                return TransactionActionStatus.SUCCESS;
            }

        }catch(NumberFormatException e){
            logger.warn("Input ID is not valid.");
            throw new InvalidInputIdException();

        }catch(TransactionNotExistException e){
            logger.warn("Transaction " + tid + " is not existed.");
            throw e;

        }catch(InvalidTransactionStatusException e){
            logger.warn("Transaction " + tid + " is not in process.");
            return TransactionActionStatus.FAIL;

        }catch(EmptyResultDataAccessException e){
            logger.warn("Transaction " + tid + " is not in repository.");
            return TransactionActionStatus.FAIL;
        }
    }

    //Method for checking
    private void checkEmptyList(List<CartEntity> checkOutCart){
        if(checkOutCart.isEmpty()){
            throw new ProductNotExistedException();
        }
    }

    private int checkValidInt(Object o){
        return Integer.parseInt(o.toString());
    }

    private int stockCheckFinal(ProductEntity product, TransactionProductEntity item){
        int newStock = product.getStock() - item.getQuantity();
        if(newStock < 0){
            //renew stock in transaction_product
            item.setStock(product.getStock());
            transactionProductRepository.save(item);
            throw new ProductNotExistedException();

        }else {
            return newStock;
        }
    }

    //find transaction
    private TransactionEntity findTransaction(int tidInt){
        return transactionRepository.findById(tidInt)
                .orElseThrow(TransactionNotExistException::new);
    }

    private List<TransactionProductEntity> findCorrespondingItem(int tidInt){
        return transactionProductRepository.findAllByTransaction_Tid(tidInt);
    }

    private List<TransactionEntity> findAllTransaction(int uid){
        return transactionRepository.findAllByUser_Uid(uid);
    }
}

