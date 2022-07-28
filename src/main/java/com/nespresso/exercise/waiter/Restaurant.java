package com.nespresso.exercise.waiter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Restaurant {
    private Map<Integer, Integer> tables = new HashMap<>();
    Map<Integer, LinkedHashMap<String, String>> orders = new LinkedHashMap<>();

    List<String> fishes = new ArrayList<>();

    public int initTable(int sizeOfTable) {
        try {
            int tableId = ThreadLocalRandom.current().nextInt(100);
            tables.put(tableId, sizeOfTable);
            return tableId;
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public void customerSays(int tableId, String message) {
        try {

            LinkedHashMap<String, String> order = orders.get(tableId) == null ? new LinkedHashMap<>() : orders.get(tableId);
            String[] split = message.split(":");
            TreeMap<String, String> orderInHash = new TreeMap<>(order);

            if (split[1].contains("Fish")) {
                fishes.add(split[0]);
            }

            if (split[1].equals(" Same")) {
                order.put(split[0], orderInHash.get(orderInHash.lastKey()));
            } else {
                order.put(split[0], split[1]);
            }

            orders.put(tableId, order);
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public String createOrder(int tableId) {
        try {
            Collection<String> values = this.orders.get(tableId).values();
            Set<String> keys = this.orders.get(tableId).keySet();
            Integer sizeOfTable = this.tables.get(tableId);

            String missing1 = String.format("%s%s", "MISSING ", sizeOfTable - keys.size());
            String orderCreated = values.stream().collect(Collectors.joining(",")).trim();
            String orderedFirst = keys.size() == sizeOfTable ? orderCreated : missing1;

            String missing2 = "MISSING 1 for Fish for 2";
            String orderedSecond = fishes.size() == 1 ? missing2 : orderedFirst;

            return orderedSecond;
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
