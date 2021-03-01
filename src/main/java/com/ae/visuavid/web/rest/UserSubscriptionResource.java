package com.ae.visuavid.web.rest;

import com.ae.visuavid.domain.UserSubscriptionEntity;
import com.ae.visuavid.service.UserSubscriptionService;
import com.ae.visuavid.service.dto.OrderDTO;
import com.ae.visuavid.service.dto.RazorPayResponseDTO;
import com.ae.visuavid.service.dto.UserSubscriptionDTO;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/subscription")
public class UserSubscriptionResource {
    private static final Logger logger = LoggerFactory.getLogger(UserSubscriptionResource.class);

    @Autowired
    UserSubscriptionService userSubscriptionService;

    @PostMapping("/new")
    public ResponseEntity<UserSubscriptionDTO> create(@Valid @RequestBody UserSubscriptionDTO dto) {
        return new ResponseEntity<UserSubscriptionDTO>(userSubscriptionService.createUserSubscription(dto), HttpStatus.OK);
    }

    @PostMapping("/updaterazorpaytransaction")
    public UserSubscriptionDTO updateRazorPayTransaction(@Valid @RequestBody RazorPayResponseDTO razorPayResponse) {
        return userSubscriptionService.updateRazorPayTransaction(razorPayResponse);
    }

    @GetMapping("/search-by-username/{userName}")
    public ResponseEntity<List<UserSubscriptionDTO>> searchByUserName(@PathVariable("userName") String userName) {
        List<UserSubscriptionDTO> userSubscriptionDTOS = userSubscriptionService.searchByUserName(userName);
        return ResponseEntity.ok(userSubscriptionDTOS);
    }
}
