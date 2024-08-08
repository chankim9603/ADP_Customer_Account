package com.example.accountService;



import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

import com.example.customer_management_service.CustomerRepository;
import com.example.customer_management_service.Customer;
import com.example.accountService.JWTHelper;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    CustomerRepository CustRepo;

    public static JWTHelper userToken;

    @GetMapping("/account/{username}")
    public Customer getAccountID(@PathVariable String userName) {
        return CustRepo.findById(userName);
    } 

    
}
