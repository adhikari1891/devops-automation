package com.example.demo.controller;

import com.example.demo.jwtEntity.Product;
import com.example.demo.jwtEntity.UserInfo;
import com.example.demo.jwtEntity.AuthRequest;
import com.example.demo.jwtService.JwtService;
import com.example.demo.jwtService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private JwtService jwtService;

//    @Autowired
//    private RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

//first sign Up then you will be registered in the db then only you can get bearer token
    @PostMapping("/signUp")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/get/all")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/get/{id}")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }

    @PostMapping("/signIn")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
//
//            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUsername());
//            return JwtResponse.builder()
//                    .accessToken(jwtService.generateToken(authRequest.getUsername())).build();
//                    .token(refreshToken.getToken()).build();
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
//        return jwtService.generateToken(authRequest.getUsername());
    }

//    @PostMapping("/refreshToken")
//    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
//        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
//                .map(refreshTokenService::verifyExpiration)
//                .map(RefreshToken::getUserInfo)
//                .map(userInfo -> {
//                    String accessToken = jwtService.generateToken(userInfo.getName());
//                    return JwtResponse.builder()
//                            .accessToken(accessToken)
//                            .token(refreshTokenRequest.getToken())
//                            .build();
//                }).orElseThrow(() -> new RuntimeException(
//                        "Refresh token is not in database!"));
//    }


}

