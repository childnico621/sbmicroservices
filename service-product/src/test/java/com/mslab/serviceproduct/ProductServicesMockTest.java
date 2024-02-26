package com.mslab.serviceproduct;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.mslab.serviceproduct.entity.Category;
import com.mslab.serviceproduct.entity.Product;
import com.mslab.serviceproduct.repository.ProductRepository;
import com.mslab.serviceproduct.service.ProductService;
import com.mslab.serviceproduct.service.ProductServiceImpl;

@SpringBootTest
public class ProductServicesMockTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);
        Product computer = Product.builder()
        .id(1L)
        .name("computer")
        .category(Category.builder().id(1L).build())
        .price(Double.parseDouble("12.5"))
        .stock(Double.parseDouble("5"))
        .build();

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer)).thenReturn(computer);
    }

    @Test
    void whenValidGetId_thenReturnProduct(){
        Product found = productService.getProduct(1L);

        Assertions.assertThat(found.getName()).isEqualTo("computer");
    }

    @Test
    void whenValidUpdatesStock_thenReturnNewStock(){
        Product newStock = productService.updateStock(1L, Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);

    }
}
