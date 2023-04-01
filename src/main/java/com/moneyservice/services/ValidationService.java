package com.moneyservice.services;

import com.moneyservice.model.repositories.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ValidationService {
    @Autowired
    private BalanceRepository balanceRepository;

    public void validate(Map<String, Object> request, List<String> fields){
        containsFields(request, fields);
        exists(request);
    }

    private void containsFields(Map<String, Object> request, List<String> fields){
        if (!request.keySet().containsAll(fields)){
            throw new RuntimeException("The request does not contain required fields");
        }
    }

    private void exists(Map<String, Object> request){
        if (request.containsKey("id") && !balanceRepository.existsById((String) request.get("id"))){
            throw new RuntimeException("Id is not found in database");
        }
        else if (request.containsKey("sender_id")){
            if (!balanceRepository.existsById((String) request.get("sender_id")))
                throw new RuntimeException("Sender_id is not found in database");
            if (!balanceRepository.existsById((String) request.get("receiver_id")))
                throw new RuntimeException("Receiver_id is not found in database");
        }
    }
}
