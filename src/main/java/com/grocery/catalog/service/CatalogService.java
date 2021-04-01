package com.grocery.catalog.service;

import com.grocery.catalog.ProductRepository;
import com.grocery.catalog.model.User;
import com.grocery.catalog.model.UserListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CatalogService {

    private String url = "http://USERMICROSERVICE/user/users";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;


    public UserListResponse getAllUserFromUserService(){
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitbreaker.run(() -> new UserListResponse("Requested Process successfully",getUserlist()),throwable -> fallBack());
    }

    private List<User> getUserlist(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<User>>() {
        }).getBody();
    }

    private UserListResponse fallBack(){
        return new UserListResponse("UserService down",null);
    }

}
