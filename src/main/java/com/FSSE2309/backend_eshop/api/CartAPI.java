package com.FSSE2309.backend_eshop.api;

import com.FSSE2309.backend_eshop.config.EnvConfig;
import com.FSSE2309.backend_eshop.data.cart.domainObj.CartItemData;
import com.FSSE2309.backend_eshop.data.cart.dto.CartActionResponseDto;
import com.FSSE2309.backend_eshop.data.cart.dto.CartItemResponseDto;
import com.FSSE2309.backend_eshop.data.user.domainObj.FirebaseUserData;
import com.FSSE2309.backend_eshop.service.CartService;
import com.FSSE2309.backend_eshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin("http://fsse2309-project-leahcim.s3-website-ap-southeast-1.amazonaws.com")
@CrossOrigin({EnvConfig.devEnvBaseUrl, EnvConfig.prodEnvBaseUrl, EnvConfig.prodEnvBaseUrl1, EnvConfig.prodEnvBaseUrl2, EnvConfig.prodEnvBaseUrl3})
@RestController
@RequestMapping("/cart")
public class CartAPI {
//aa
    //Attribute
    private CartService cartService;

    //Constructor
    @Autowired
    public CartAPI(CartService cartService){
        this.cartService = cartService;
    }

    @PutMapping("/{pid}/{quantity}")
    public CartActionResponseDto addCartItem(JwtAuthenticationToken token,
                                             @PathVariable Object pid,
                                             @PathVariable Object quantity){

        return new CartActionResponseDto(
                cartService.addCartItem(
                        pid, quantity, JwtUtil.getFirebaseUser(token)
                )
        );
    }

    @GetMapping()
    public List<CartItemResponseDto> getUserCart(JwtAuthenticationToken token){
        FirebaseUserData userData = JwtUtil.getFirebaseUser(token);

        List<CartItemResponseDto> list = new ArrayList<>();

        for (CartItemData data:
             cartService.getCartList(userData)) {
            list.add(new CartItemResponseDto(data));
        }

//        cartService.getCartList(userData).stream().map(
//                (data) -> list.add(new CartItemResponseDto(data))
//        );

        return list;
    }

    @PatchMapping("/{pid}/{quantity}")
    public CartItemResponseDto updateCart(JwtAuthenticationToken token,
                                          @PathVariable Object pid,
                                          @PathVariable Object quantity){
        return new CartItemResponseDto(
                cartService.updateCartItem(
                        pid, quantity, JwtUtil.getFirebaseUser(token)
                )
        );
    }

    @DeleteMapping("/{pid}")
    public CartActionResponseDto removeCartItem(JwtAuthenticationToken token,
                                                @PathVariable Object pid){
        return new CartActionResponseDto(
                cartService.removeCartItem(
                        pid, JwtUtil.getFirebaseUser(token)
                )
        );
    }
}
