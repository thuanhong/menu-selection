package com.project.coffee.Handler;

import com.google.gson.Gson;
import com.project.coffee.Model.Order;
import com.project.coffee.Utils.Constants;
import com.project.coffee.Utils.HandleInputSelection;
import com.project.coffee.Utils.UtilsHandler;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderHandler extends UtilsHandler {
    private ArrayList<Order> orderData;

    public OrderHandler() {
        this.orderData = this.convertJsonDataToArrayList(Constants.ORDER_FILE_NAME, Order.class);
    }

    public void statisticOrder(@Nullable ArrayList<Order> orderData) {
        Map<String, Integer> columns = new LinkedHashMap<String, Integer>();
        columns.put("STT", 5);
        columns.put("Mã Đơn", 40);
        columns.put("Tổng tiền", 7);
        columns.put("Ngày", 10);

        ArrayList<String> methods = new ArrayList<String>() {{
            add("getOrderId");
            add("getTotal");
            add("getDate");
        }};

        orderData = orderData != null ? orderData : this.orderData;

        this.printTable(orderData, columns, Order.class, methods);
    }

    public void saveOrder(Integer sum) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        Date date = new Date();
        Order newOrder = new Order(this.getUUIDv4(), sum, formatter.format(date));
        this.orderData.add(newOrder);

        String orderDataJsonString = new Gson().toJson(this.orderData);
        this.writeFileData(Constants.ORDER_FILE_NAME, orderDataJsonString);
    }

    public void statisticOrderByRange() {
        String startDate = HandleInputSelection.inDate(" Nhập ngày bắt đầu: ");
        String endDate = HandleInputSelection.inDate(" Nhập ngày kết thúc: ");

        ArrayList<Order> orderDataFiltered = this.orderData.stream()
                .filter(order -> {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                    try {
                        Date date1 = dateFormat.parse(startDate);
                        Date date2 = dateFormat.parse(endDate);
                        Date date3 = dateFormat.parse(order.getDate());
                        return date3.compareTo(date1) >= 0 && date3.compareTo(date2) <= 0;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toCollection(ArrayList::new));

        if (orderDataFiltered.size() == 0) {
            System.out.println("==================> Not Found <==================");
            return;
        }

        this.statisticOrder(orderDataFiltered);
    }
}
