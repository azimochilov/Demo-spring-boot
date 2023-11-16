package com.example.demo.service;

import com.example.demo.model.Transaction;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

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

    public List<Transaction> getAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<Transaction>> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(URL+"/test", HttpMethod.GET,entity,List.class).getBody();
    }

    public Transaction saveExch(Transaction transaction){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction,headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(URL, HttpMethod.POST,entity,Transaction.class).getBody();
    }

    public Transaction update(Transaction transaction){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction,headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(URL, HttpMethod.PUT,entity,Transaction.class).getBody();

    }
}
