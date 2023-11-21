package com.FSSE2309.backend_eshop.service.Impl;

import com.FSSE2309.backend_eshop.data.cart.domainObj.CartActionStatus;
import com.FSSE2309.backend_eshop.data.cart.domainObj.CartItemData;
import com.FSSE2309.backend_eshop.data.cart.entity.CartEntity;
import com.FSSE2309.backend_eshop.data.product.entity.ProductEntity;
import com.FSSE2309.backend_eshop.data.user.domainObj.FirebaseUserData;
import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;
import com.FSSE2309.backend_eshop.exception.InvalidInputIdException;
import com.FSSE2309.backend_eshop.exception.InvalidNumberValueException;
import com.FSSE2309.backend_eshop.exception.ProductNotExistedException;
import com.FSSE2309.backend_eshop.repository.CartRepository;
import com.FSSE2309.backend_eshop.service.CartService;
import com.FSSE2309.backend_eshop.service.ProductService;
import com.FSSE2309.backend_eshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    //Attribute
    private UserService userService;
    private ProductService productService;
    private CartRepository cartRepository;

    Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    //Constructor
    @Autowired
    public CartServiceImpl(UserService userService, ProductService productService, CartRepository cartRepository){
        this.userService = userService;
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    //Method for api
    @Override
    public CartActionStatus addCartItem(Object pid, Object quantity, FirebaseUserData data){
        try {
            int pidInt = checkValidInt(pid);
            int quantityInt = checkValidInt(quantity);

            if(!checkPositiveInt(pidInt) || !checkPositiveInt(quantityInt)){
                throw new InvalidNumberValueException();
            }


            UserEntity user = userService.getByFirebase(data);

            //check pid exist in product & enough stock
            ProductEntity product = productService.checkInputProduct(pidInt, quantityInt);

            //check uid & pid exist in cart
//            Optional<CartEntity> result = cartRepository.findByProductAndUser(product, user);
            Optional<CartEntity> result = cartRepository.findByProductIdAndUserId(product.getPid(), user.getUid());

            //add to cart
            CartEntity newCartItem = alterCart(result, product, user, quantityInt);

            return CartActionStatus.SUCCESS;

        }catch(NumberFormatException e){
            logger.warn("Input ID" + pid + " or Quantity " + quantity + " is not valid.");
            throw new InvalidInputIdException();

        }catch(InvalidNumberValueException e){
            logger.warn("Input ID" + pid + " or Quantity " + quantity + " is 0 or less than 0.");
            throw e;

        }catch(ProductNotExistedException e){
            logger.warn("Product ID: " + pid + " does not exist or Not enough stock.");
            throw e;
        }
    }

    @Override
    public List<CartItemData> getCartList(FirebaseUserData userData){
        //List<CartItemData> list = new ArrayList<>();

        UserEntity user = userService.getByFirebase(userData);

//        for (CartEntity cartItem:
//                getCartEntityList(user)) {
//            list.add(new CartItemData(cartItem));
//        }

        List<CartItemData> list = getCartEntityList(user).stream()
                .map(CartItemData::new)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public CartItemData updateCartItem(Object pid, Object quantity, FirebaseUserData data){
        try {
            int pidInt = checkValidInt(pid);
            int quantityInt = checkValidInt(quantity);

            if(!checkPositiveInt(pidInt) || !checkPositiveInt(quantityInt)){
                throw new InvalidNumberValueException();
            }

            UserEntity user = userService.getByFirebase(data);

            //check pid exist in product & enough stock
            ProductEntity product = productService.checkInputProduct(pidInt, quantityInt);

            //check uid & pid exist in cart
//            CartEntity result = cartRepository.findByProductAndUser(product, user).orElseThrow(ProductNotExistedException::new);
            CartEntity result = cartRepository.findByProductIdAndUserId(
                                    product.getPid(),
                                    user.getUid()
                                ).orElseThrow(ProductNotExistedException::new);

            //update cart quantity
            result.setQuantity(quantityInt);

//            result = cartRepository.save(result);
            updateItem(result);

            return new CartItemData(result);

        }catch(NumberFormatException e){
            logger.warn("Input ID" + pid + " or Quantity " + quantity + " is not valid.");
            throw new InvalidInputIdException();

        }catch(InvalidNumberValueException e){
            logger.warn("Input ID" + pid + " or Quantity " + quantity + " is 0 or Exceed amount in cart.");
            throw e;

        }catch(ProductNotExistedException e){
            logger.warn("Product ID: " + pid + " does not exist or Not enough stock.");
            throw e;
        }
    }

    @Override
    public CartActionStatus removeCartItem(Object pid, FirebaseUserData data){
        try{
            int pidInt = checkValidInt(pid);

            UserEntity user = userService.getByFirebase(data);

            //check uid & pid exist in cart
            ProductEntity product = productService.checkProduct(pidInt);

//            Optional<CartEntity> result = cartRepository.findByProductAndUser(product, user);
            Optional<CartEntity> result = cartRepository.findByProductIdAndUserId(
                                            product.getPid(),
                                            user.getUid()
                                         );

            if(result.isEmpty()){
                throw new ProductNotExistedException();

            }else {
//                cartRepository.delete(result.get());
                cartRepository.deleteItem(result.get().getCid());

                //refill stock
//                product.setStock(product.getStock() + result.get().getQuantity());
//                productService.saveNewStock(product);

                return CartActionStatus.SUCCESS;
            }

        }catch(NumberFormatException e){
            logger.warn("Input ID is not valid.");
            throw new InvalidInputIdException();

        }catch(ProductNotExistedException e){
            logger.warn("Product ID: " + pid + " does not exist.");
            throw e;
        }
    }

    //Method for checking

    private int checkValidInt(Object o){
        return Integer.parseInt(o.toString());
    }

    private boolean checkPositiveInt(int num){
        return num > 0;
    }

//    private ProductEntity checkInputProduct(int pidInt, int quantityInt){
//        return productRepository.findByPidAndStockGreaterThanEqual(pidInt, quantityInt)
//                .orElseThrow(ProductNotExistedException::new);
//    }

    private CartEntity alterCart(Optional<CartEntity> result, ProductEntity product, UserEntity user, int quantityInt){

        CartEntity newCartItem;

        if(result.isEmpty()){
            newCartItem = new CartEntity(product, user, quantityInt);
            addItem(newCartItem);

        } else{
            newCartItem = result.get();
            int updatedQuantity = newCartItem.getQuantity() + quantityInt;

            if(updatedQuantity < 0){
                throw new InvalidNumberValueException();
            }
            newCartItem.setQuantity(updatedQuantity);
            updateItem(newCartItem);
        }

        return newCartItem;
    }

    private void addItem(CartEntity newCartItem){
        cartRepository.saveToCart(
                newCartItem.getProduct().getPid(),
                newCartItem.getQuantity(),
                newCartItem.getUser().getUid()
        );
    }

    private void updateItem(CartEntity newCartItem){
        cartRepository.updateQuantity(
                newCartItem.getProduct().getPid(),
                newCartItem.getQuantity()
        );
    }

    @Override
    public List<CartEntity> getCartEntityList(UserEntity user){
//        return cartRepository.findAllByUser(user);
        return cartRepository.findAllByUserId(user.getUid());
    }
    @Override
    public void clearUserCart(List<CartEntity> checkOutCart){
        cartRepository.deleteAll(checkOutCart);
    }

    @Override
    public void clearUserCartByUid(int uid){
        cartRepository.deleteAllByUid(uid);
    }


}
