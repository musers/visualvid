package com.ae.visuavid.repository.impl;

import com.ae.visuavid.repository.custom.OrderRepositoryCustom;
import com.ae.visuavid.util.ProfileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.UUID;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProfileUtils profileUtils;

    @Override
    public String generateOrderId() {
        return generateId("O", "ORDER_ID_SEQ");
    }

    @Override
    public String generateSalesId() {
        return generateId("S", "SALES_ID_SEQ");
    }

    @Override
    public void updateRazorPayOrderId(UUID id, String paymentOrderId, String orderStatus) {
        String updateQuery = "update vvid_order set razor_pay_order_id = ?,order_status = ?  where id = ?";
        jdbcTemplate.update(updateQuery, paymentOrderId, orderStatus, id);
    }

    @Override
    public void updateRazorPayPaymentIdAndSalesId(UUID id, String razorpayPaymentId, String orderStatus) {
        String salesId = generateSalesId();
        String updateQuery = "update vvid_order set razor_pay_payment_id = ?,order_status = ?, sales_id = ?  where id = ?";
        jdbcTemplate.update(updateQuery, razorpayPaymentId, orderStatus, salesId, id);
    }

    @Override
    public String getOrderStatus(UUID id) {
        try {
            return jdbcTemplate.queryForObject("select order_status from vvid_order where id = ?", new Object[]{id}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    private String generateId(String prefix, String sequenceName) {
        Long num;
        if (profileUtils.isProfileLocal()) {
            String sql = "select " + sequenceName + ".NEXTVAL from dual";
            num = jdbcTemplate.queryForObject(sql, new Object[]{}, Long.class);
        } else {
            String sql = "select last_value from \"" + sequenceName + "\"";
            num = jdbcTemplate.queryForObject(sql, new Object[]{}, Long.class);
        }

        StringBuilder sb = new StringBuilder(prefix.toUpperCase());
        LocalDate currentdate = LocalDate.now();
        sb.append(currentdate.getYear()).
            append(currentdate.getMonthValue()).
            append(String.format("%06d", num + 1));
        return sb.toString();
    }
}
