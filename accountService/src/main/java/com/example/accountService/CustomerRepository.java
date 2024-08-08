package com.example.accountService;

import org.springframework.data.jpa.reository.JpaRepository;
public class CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByUserName(String userName){}
}
