package com.FSSE2309.backend_eshop.service;

import com.FSSE2309.backend_eshop.data.product.domainObj.ProductDetailData;
import com.FSSE2309.backend_eshop.data.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    //Method from API
    List<ProductDetailData> getProductList();

    ProductDetailData getById(Object pid);

    //check pid exist in product & enough stock
    ProductEntity checkInputProduct(int pidInt, int quantityInt);

    //check pid exist
    ProductEntity checkProduct(int pidInt);

    void saveNewStock(ProductEntity product);
}
