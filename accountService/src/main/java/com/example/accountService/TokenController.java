package com.example.accountService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class TokenController {

    public static Token customerToken;

    // @GetMapping("/token")
    // public String getAll() {
	// 	return "jwt-fake-token-asdfasdfasfa".toString();
	// }
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Token Management!";
    }

    @PostMapping("/token")
    public ResponseEntity<?> createTokenForCustomer(@RequestBody Customer customer){
        String username = customer.getName();
        String password = customer.getPassword();
        System.out.println(username);
        if (username != null && username.length() > 0 && password != null && password.length() > 0 && checkPassword(username, password)) {
            System.out.println("Here!!!!!!!!!!!");
			Token token = createToken();
			ResponseEntity<Object> response = ResponseEntity.ok(token);
			return response;			
		}
		// bad request
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		

    }

    private boolean checkPassword(String username, String password){
        System.out.println("check Password");
        Customer cust = getCustomerWithNameFromCustomerAPI(username);
        if(cust != null && cust.getName().equals(username) && cust.getPassword().equals(password)) {
			return true;				
		}		
		return false;

    }

    private Customer getCustomerWithNameFromCustomerAPI(String username){
        System.out.println("getCustomerWithNameFromCustomerAPI");
        try {
            URL url = new URL("http://localhost:8080/api/customers/customers/name/" + username);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			Token token = createToken();
			conn.setRequestProperty("authorization", "Bearer " + token.getToken());

			if (conn.getResponseCode() != 200) {
				return null;
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output = "";
				String out = "";
				while ((out = br.readLine()) != null) {
					output += out;
				}
				conn.disconnect();
				return getCustomerObj(output);
			}
        }catch (MalformedURLException e) {
			e.printStackTrace();
			return null;

		} catch (java.io.IOException e) {
			e.printStackTrace();
			return null;
		}

    }

    public static Token createToken() {

    	String token_string = JWTHelper.createToken();
    	
    	return new Token(token_string);
    }

    public static Customer getCustomerObj(String json_string){
		
        // parsing file "JSONExample.json" 
        JSONObject jobj = new org.json.JSONObject(json_string); 
          
        // getting firstName and lastName 
        int id = (int) jobj.get("id");
        String name = (String) jobj.get("name"); 
        String email = (String) jobj.get("email"); 
        String password = (String) jobj.get("password"); 
		
		// create customer object
		Customer cust = new Customer();
		cust.setName(name);
		cust.setId(id);
		cust.setEmail(email);
		cust.setPassword(password);
		return cust;
	}

}
