//package com.FSSE2309.backend_eshop.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class CustomAuthenticationFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        if (headerPresentInRequest(request)){
//            Authentication authentication = createCustomAutentication();
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private boolean headerPresentInRequest(HttpServletRequest request){
//        return request.getHeader("") != null;
//    }
//
//    private Authentication createCustomAutentication(){
//        return new Authentication() {
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                List<GrantedAuthority> authorities = new ArrayList<>();
//                return null;
//            }
//
//            @Override
//            public Object getCredentials() {
//                return null;
//            }
//
//            @Override
//            public Object getDetails() {
//                return null;
//            }
//
//            @Override
//            public Object getPrincipal() {
//                return null;
//            }
//
//            @Override
//            public boolean isAuthenticated() {
//                return false;
//            }
//
//            @Override
//            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//
//            }
//
//            @Override
//            public String getName() {
//                return null;
//            }
//        };
//    }
//}
