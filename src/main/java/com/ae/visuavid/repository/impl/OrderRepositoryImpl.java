package com.ae.visuavid.repository.impl;

import com.ae.visuavid.repository.custom.OrderRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.UUID;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public String generateOrderId() {
        return generateId("O", "ORDER_ID_SEQ");
    }

    @Override
    public String generateSalesId() {
        return generateId("S", "SALES_ID_SEQ");
    }

    @Override
    public void updatePaymentOrderId(UUID id, String paymentOrderId, String orderStatus) {
        String updateQuery = "update vvid_order set payment_order_id = ?,order_status = ?  where id = ?";
        jdbcTemplate.update(updateQuery, paymentOrderId, orderStatus, id);
    }


    private String generateId(String prefix, String sequenceName) {
        String sql = "select " + sequenceName + ".NEXTVAL from dual";
        Long num = jdbcTemplate.queryForObject(sql, new Object[]{}, Long.class);
        StringBuilder sb = new StringBuilder(prefix.toUpperCase());
        LocalDate currentdate = LocalDate.now();
        sb.append(currentdate.getYear()).
            append(currentdate.getMonthValue()).
            append(String.format("%06d", num));
        return sb.toString();

    }
}
