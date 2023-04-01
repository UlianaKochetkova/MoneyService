package com.moneyservice.model.repositories;

import com.moneyservice.model.Action;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ActionRepository extends MongoRepository<Action, String> {
    List<Action> findActionsByUserIdAndDateTimeBetween(String id, LocalDateTime dateFrom, LocalDateTime dateTo);
}
