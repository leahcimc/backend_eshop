package com.FSSE2309.backend_eshop.api;


import com.FSSE2309.backend_eshop.data.product.domainObj.ProductDetailData;
import com.FSSE2309.backend_eshop.data.product.dto.ProductListResponseDto;
import com.FSSE2309.backend_eshop.data.product.dto.SingleProductResponseDto;
import com.FSSE2309.backend_eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/product")
public class ProductAPI {

    //Attribute
    private ProductService productService;

    @Autowired
    //Constructor
    public ProductAPI(ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductListResponseDto> getAllProduct(){
        List<ProductListResponseDto> list = new ArrayList<>();

        for (ProductDetailData data:
             productService.getProductList()) {
            list.add(new ProductListResponseDto(data));
        }

        return list;
    }

    @GetMapping("/{id}")
    public SingleProductResponseDto getByPid(@PathVariable (name = "id") Object pid){
        ProductDetailData data = productService.getById(pid);

        return new SingleProductResponseDto(data);
    }

}
