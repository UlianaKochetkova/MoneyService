package com.moneyservice.model;

import com.moneyservice.consts.Operations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document("transactions")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Action {
    @Id
    private String id;

    private String userId;

    private LocalDateTime dateTime;

    private Operations actionType;

    private Map<String, Object> actionInfo;
}
