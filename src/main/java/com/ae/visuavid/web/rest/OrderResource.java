package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.OrderService;
import com.ae.visuavid.service.RazorPayService;
import com.ae.visuavid.service.dto.OrderDTO;
import com.ae.visuavid.service.dto.OrderRequestDTO;
import com.ae.visuavid.service.dto.PaymentOrderDTO;
import com.ae.visuavid.service.dto.RazorPayResponseDTO;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OrderResource {
    private static final Logger log = LoggerFactory.getLogger(OrderResource.class);

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected RazorPayService razorPayService;

    @PostMapping("/order")
    public ResponseEntity<PaymentOrderDTO> create(@Valid @RequestBody OrderRequestDTO orderRequest) {
        List<OrderDTO> orders = orderService.create(orderRequest);
        PaymentOrderDTO paymentOrder = razorPayService.createPaymentOrder(orders);
        orderService.updatePaymentOrder(orders, paymentOrder);
        return new ResponseEntity<>(paymentOrder, HttpStatus.CREATED);
    }

    @PutMapping("/order")
    public ResponseEntity<OrderDTO> update(@Valid @RequestBody OrderDTO orderDTO) {
        if (orderDTO.getId() != null) {
            return new ResponseEntity<>(orderService.create(orderDTO), HttpStatus.ACCEPTED);
        } else {
            throw new ApiRuntimeException("cannot update order without Id");
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable("id") String id) {
        OrderDTO orderDTO = orderService.findOrderById(UUID.fromString(id));
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> findOrders() {
        List<OrderDTO> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PostMapping("/order/updaterazorpaytransaction")
    public void updateRazorPayTransaction(@Valid @RequestBody RazorPayResponseDTO razorPayResponse) {
        orderService.updateRazorPayTransaction(razorPayResponse);
    }
}
