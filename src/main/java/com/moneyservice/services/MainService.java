package com.moneyservice.services;

import com.moneyservice.consts.Constants;
import com.moneyservice.consts.Operations;
import com.moneyservice.model.Action;
import com.moneyservice.model.Balance;
import com.moneyservice.model.repositories.ActionRepository;
import com.moneyservice.model.repositories.BalanceRepository;
import com.moneyservice.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.moneyservice.consts.Constants.requestInfo;

@Service
@Slf4j
public class MainService {
    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    ValidationService validationService;

    @Autowired
    ActionService actionService;

    @Autowired
    ActionRepository actionRepository;

    public BaseResponse addMoney(Map<String, Object> request){
        try {
            validationService.validate(request, requestInfo.get(Operations.ADD));

            Balance balance = balanceRepository.findBalanceById((String) request.get("id"));
            balance.addMoney((Integer) request.get("sum"));
            balanceRepository.save(balance);
            actionService.writeAction((String) request.get("id"), Operations.ADD, request);
            return BaseResponse.buildOkResponse();
        }
        catch (Exception e){
            return new BaseResponse("S-401", e.getMessage());
        }
    }

    public BaseResponse getMoney(Map<String, Object> request){
        try {
            validationService.validate(request, requestInfo.get(Operations.GET));

            Balance balance = balanceRepository.findBalanceById((String) request.get("id"));
            balance.getMoney((Integer) request.get("sum"));
            balanceRepository.save(balance);
            actionService.writeAction((String) request.get("id"), Operations.GET, request);
            return BaseResponse.buildOkResponse();
        }
        catch (Exception e){
            return new BaseResponse("S-402", e.getMessage());
        }
    }

    public BaseResponse sendMoney(Map<String, Object> request){
        try {
            validationService.validate(request, requestInfo.get(Operations.SEND));

            Balance senderBalance = balanceRepository.findBalanceById((String) request.get("sender_id"));
            Balance receiverBalance = balanceRepository.findBalanceById((String) request.get("receiver_id"));
            senderBalance.getMoney((Integer) request.get("sum"));
            receiverBalance.addMoney((Integer) request.get("sum"));
            actionService.writeAction((String) request.get("sender_id"), Operations.GET, request);
            actionService.writeAction((String) request.get("receiver_id"), Operations.ADD, request);
            return BaseResponse.buildOkResponse();
        }
        catch (Exception e){
            return new BaseResponse("S-403", e.getMessage());
        }
    }

    public Map<String, Object> getBalance(Map<String, Object> request){
        Map<String, Object> result = new HashMap<>();
        try {
            validationService.validate(request, requestInfo.get(Operations.BALANCE));

            Balance balance = balanceRepository.findBalanceById((String) request.get("id"));
            result.put("sum", balance.getSum());

            actionService.writeAction((String) request.get("id"), Operations.BALANCE, Constants.balanceInfo);
            result.put("response", BaseResponse.buildOkResponse());
        }
        catch (Exception e){
            result.put("sum", 0);
            result.put("response", new BaseResponse("S-404", e.getMessage()));
        }
        return result;
    }

    public Map<String, Object> getActions(Map<String, Object> request){
        Map<String, Object> result = new HashMap<>();
        try {
            validationService.validate(request, requestInfo.get(Operations.ACTIONS));

            List<Action> actions = actionRepository.findActionsByUserIdAndDateTimeBetween((String) request.get("id"),
                    actionService.parse((String) request.get("date_from")),
                    actionService.parse((String) request.get("date_to")));
            result.put("response", BaseResponse.buildOkResponse());
            result.put("actions", actions);
        }
        catch (Exception e){
            result.put("response", new BaseResponse("S-405", e.getMessage()) );
            result.put("actions", List.of());
        }
        return result;
    }
}
