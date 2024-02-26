package com.mslab.serviceproduct;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.mslab.serviceproduct.entity.Category;
import com.mslab.serviceproduct.entity.Product;
import com.mslab.serviceproduct.repository.ProductRepository;


@DataJpaTest
class ProductRepositoryMockTest {
    
    @Autowired
    private ProductRepository productRepository;

    @Test    
     void whenFindByCategory_thenResultListProduct(){
        //arrange
        Product product01=Product.builder()
        .name("computer")
        .category(Category.builder().id(1L).build())
        .description("")
        .stock(Double.parseDouble("10"))
        .price(Double.parseDouble("1240.99"))
        .status("Created")
        .createAt(new Date())
        .build();
        productRepository.save(product01);

        //act
        List<Product> founds=productRepository.findByCategory(product01.getCategory());

        //assert
        Assertions.assertThat(founds.size()).isEqualTo(3);
    } 

}
