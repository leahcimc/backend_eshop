package com.FSSE2309.backend_eshop.api;


import com.FSSE2309.backend_eshop.config.EnvConfig;
import com.FSSE2309.backend_eshop.data.product.domainObj.ProductDetailData;
import com.FSSE2309.backend_eshop.data.product.dto.ProductListResponseDto;
import com.FSSE2309.backend_eshop.data.product.dto.SingleProductResponseDto;
import com.FSSE2309.backend_eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin("http://fsse2309-project-leahcim.s3-website-ap-southeast-1.amazonaws.com")
@CrossOrigin({EnvConfig.devEnvBaseUrl, EnvConfig.prodEnvBaseUrl})
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
