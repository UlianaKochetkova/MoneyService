package com.moneyservice;

import com.moneyservice.model.BaseResponse;
import com.moneyservice.services.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class MainController {
    @Autowired
    MainService mainService;

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World test another one 2";
    }

    /**
     * Зачисление средств
     */
    @GetMapping("/add")
    @ResponseBody
    public BaseResponse addMoney(@RequestBody Map<String, Object> request){
        log.info("/add: {}", request);
        return mainService.addMoney(request);
    }

    /**
     * Списание средств
     */
    @GetMapping("/get")
    public BaseResponse getMoney(@RequestBody Map<String, Object> request){
        log.info("/get: {}", request);
        return mainService.getMoney(request);
    }

    /**
     * Отправка средств
     */
    @GetMapping("/send")
    public BaseResponse sendMoney(@RequestBody Map<String, Object> request){
        log.info("/send: {}", request);
        return mainService.sendMoney(request);
    }

    /**
     * Информация о балансе
     */
    @GetMapping("/balance")
    public Map<String, Object> getBalance(@RequestBody Map<String, Object> request){
        log.info("/balance: {}", request);
        return mainService.getBalance(request);
    }

    /**
     * Информация о действиях пользователя
     */
    @GetMapping("/actions")
    public Map<String, Object> getActions(@RequestBody Map<String, Object> request){
        log.info("/actions: {}", request);
        return mainService.getActions(request);
    }
}
