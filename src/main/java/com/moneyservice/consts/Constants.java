package com.moneyservice.consts;

import com.google.common.collect.ImmutableMap;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Constants {
    public static ImmutableMap<Operations, List<String>> requestInfo = ImmutableMap.<Operations, List<String>>builder()
            .put(Operations.ADD, List.of("id", "sum", "comment"))
            .put(Operations.GET, List.of("id", "sum", "comment"))
            .put(Operations.SEND, List.of("sender_id", "receiver_id", "sum", "comment"))
            .put(Operations.BALANCE, List.of("id"))
            .put(Operations.ACTIONS, List.of("id", "date_from", "date_to"))
            .build();

    public static ImmutableMap<String, Object> balanceInfo = ImmutableMap.<String, Object>builder()
            .put("comment", "GET_BALANCE")
            .build();

    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
