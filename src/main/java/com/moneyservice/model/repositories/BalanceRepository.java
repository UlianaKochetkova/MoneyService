package com.moneyservice.model.repositories;

import com.moneyservice.model.Balance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BalanceRepository extends MongoRepository<Balance, String> {
    @Query("{_id:'?0'}")
    Balance findBalanceById(String id);
}
