package com.mslab.shoppingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mslab.shoppingservice.model.Customer;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "customer-service", path = "/customers")
public interface CustomerClient {

    @GetMapping(value = "/{id}")
    @CircuitBreaker(name="getCustomerCircuitBreaker", fallbackMethod = "getCustomerFallback")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);

    default ResponseEntity<Customer> getCustomerFallback(Exception exception) {
        Customer customer = Customer.builder()
                .firstName("none")
                .lastName("none")
                .email("none")
                .photoUrl("none").build();
        return ResponseEntity.ok(customer);
    }
}