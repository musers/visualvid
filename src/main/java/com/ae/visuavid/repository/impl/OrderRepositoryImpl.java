package com.ae.visuavid.repository.impl;

import com.ae.visuavid.repository.custom.OrderRepositoryCustom;
import com.ae.visuavid.util.ProfileUtils;
import java.time.LocalDate;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProfileUtils profileUtils;

    @Override
    public String generateOrderId() {
        String maxOrderId;
        try {
            maxOrderId = jdbcTemplate.queryForObject("select max(order_id) from vvid_order", new Object[] {}, String.class);
        } catch (EmptyResultDataAccessException e) {
            maxOrderId = null;
        }
        if (StringUtils.isBlank(maxOrderId)) {
            maxOrderId = "O000000000001";
        }
        Long num = Long.valueOf(maxOrderId.substring(maxOrderId.length() - 6));
        num++;
        return getFormattedId("O", num);
    }

    @Override
    public String generateSalesId() {
        String maxSalesId;
        try {
            maxSalesId = jdbcTemplate.queryForObject("select max(sales_id) from vvid_order", new Object[] {}, String.class);
        } catch (EmptyResultDataAccessException e) {
            maxSalesId = null;
        }
        if (StringUtils.isBlank(maxSalesId)) {
            maxSalesId = "S000000000001";
        }
        Long num = Long.valueOf(maxSalesId.substring(maxSalesId.length() - 6));
        num++;
        return getFormattedId("S", num);
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
            return jdbcTemplate.queryForObject("select order_status from vvid_order where id = ?", new Object[] { id }, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //    private String generateId(String prefix, String sequenceName) {
    //        Long num;
    //        if (profileUtils.isProfileLocal()) {
    //            String sql = "select " + sequenceName + ".NEXTVAL from dual";
    //            num = jdbcTemplate.queryForObject(sql, new Object[]{}, Long.class);
    //        } else {
    //            String sql = "select last_value from \"" + sequenceName + "\"";
    //            num = jdbcTemplate.queryForObject(sql, new Object[]{}, Long.class);
    //            num++;
    //        }
    //
    //        StringBuilder sb = getStringBuilder(prefix, num);
    //        return sb.toString();
    //    }

    private String getFormattedId(String prefix, Long num) {
        StringBuilder sb = new StringBuilder(prefix.toUpperCase());
        LocalDate currentdate = LocalDate.now();
        sb.append(currentdate.getYear()).append(currentdate.getMonthValue()).append(String.format("%06d", num));
        return sb.toString();
    }
}
