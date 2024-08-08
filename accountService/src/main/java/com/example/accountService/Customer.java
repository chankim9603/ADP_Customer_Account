package com.example.accountService;


import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Table(name="CUSTOMERS")

public class Customer {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @Column(name="CUSTOMER_NAME")
    String name;
    String email;
    String password;

    public Customer(){}

    public Customer(int id, String name, String email, String password){
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    //getters and setters.

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
