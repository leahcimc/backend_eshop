package com.FSSE2309.backend_eshop.service.Impl;

import com.FSSE2309.backend_eshop.data.product.domainObj.ProductDetailData;
import com.FSSE2309.backend_eshop.data.product.entity.ProductEntity;
import com.FSSE2309.backend_eshop.exception.InvalidInputIdException;
import com.FSSE2309.backend_eshop.exception.ProductNotExistedException;
import com.FSSE2309.backend_eshop.repository.ProductRepository;
import com.FSSE2309.backend_eshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {
    //Attribute
    private ProductRepository productRepository;
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    //Constructor
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    //Method from API
    @Override
    public List<ProductDetailData> getProductList(){
//        List<ProductDetailData> list = new ArrayList<>();
//
//        for (ProductEntity entity:
//                productRepository.findAll()) {
//            list.add(new ProductDetailData(entity));
//        }

        List<ProductDetailData> list = StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(ProductDetailData::new)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public ProductDetailData getById(Object pid){
        try {
            int productId = checkValidId(pid);

            ProductEntity entity = productRepository.findById(productId).
                    orElseThrow(ProductNotExistedException::new);

            return new ProductDetailData(entity);

        }catch(NumberFormatException e){
            logger.warn("Input \"" + pid + "\" is not an integer.");
            throw new InvalidInputIdException();

//        }catch(InvalidInputIdException e){
//            logger.warn("Input ID: " + pid + " is not valid.");
//            throw e;

        }catch(ProductNotExistedException e){
            logger.warn("Product ID: " + pid + " does not exist.");
            throw e;
        }

    }

    //Method for checking

    private int checkValidId(Object pid){
        try{
            return Integer.parseInt(pid.toString());
        }catch(NumberFormatException e){
            throw e;
        }
    }

    //Method for Other Service

    //check pid exist in product & enough stock
    @Override
    public ProductEntity checkInputProduct(int pidInt, int quantityInt){
        return productRepository.findByPidAndStockGreaterThanEqual(pidInt, quantityInt)
                .orElseThrow(ProductNotExistedException::new);
    }

    //check pid exist
    @Override
    public ProductEntity checkProduct(int pidInt){
        return productRepository.findById(pidInt).orElseThrow(ProductNotExistedException::new);
    }

    @Override
    public void saveNewStock(ProductEntity product){
        productRepository.save(product);
    }
}
