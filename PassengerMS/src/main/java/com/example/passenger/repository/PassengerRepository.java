package com.example.passenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.passenger.entity.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {

}
