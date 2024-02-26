package com.mslab.shoppingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mslab.shoppingservice.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {
    
}
