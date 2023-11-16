package com.example.demo.service;

import com.example.demo.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {
    private final String URL = "http://localhost:8081/api/transactions";
    public String getMessage(){
        RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject(URL+"/test",String.class);
        return msg;
    }

    public ResponseEntity<Transaction> save(Transaction transaction){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Transaction> transaction1 =  restTemplate.postForEntity(URL,transaction,Transaction.class);
        return transaction1;
    }
}
