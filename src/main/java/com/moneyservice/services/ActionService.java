package com.moneyservice.services;

import com.moneyservice.consts.Operations;
import com.moneyservice.model.Action;
import com.moneyservice.model.repositories.ActionRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.moneyservice.consts.Constants.DATE_TIME_FORMAT;

@Service
@Slf4j
public class ActionService {

    @Autowired
    ActionRepository actionRepository;

    public DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    public void writeAction(String id, Operations actionType, Map<String, Object> actionInfo){
        Action currentAction = new Action();
        currentAction.setUserId(id);
        currentAction.setActionType(actionType);
        currentAction.setDateTime(LocalDateTime.now());
        currentAction.setActionInfo(actionInfo);
        actionRepository.save(currentAction);
    }

    public LocalDateTime parse(String dateTimeStr){
        LocalDateTime res = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        log.info("Parsing from {} to {}", dateTimeStr, res);
        return res;
    }
}
