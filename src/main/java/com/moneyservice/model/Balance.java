package com.moneyservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("balance")
@AllArgsConstructor
@Data
public class Balance {
    @Id
    private String id;
    private Integer sum;

    @Override
    public String toString() {
        return String.format("Id %s, sum %s", id, sum);
    }

    public void addMoney(Integer sum){
        this.sum += sum;
    }

    public void getMoney(Integer sum){
        if (sum > this.sum){
            throw new RuntimeException("The current sum is greater than the available sum");
        }
        this.sum -= sum;
    }
}
