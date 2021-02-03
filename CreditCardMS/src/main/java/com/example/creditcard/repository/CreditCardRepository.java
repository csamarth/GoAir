package com.example.creditcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.creditcard.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {

}
