package com.ae.visuavid.web.rest;

import com.ae.visuavid.domain.UserSubscriptionEntity;
import com.ae.visuavid.service.UserSubscriptionService;
import com.ae.visuavid.service.dto.*;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/page")
    public ResponseEntity<Page<UserSubscriptionDTO>> findAllByPage(Pageable pageable) {
        Page<UserSubscriptionDTO> userSubscriptionDTOS = userSubscriptionService.findAllByPage(pageable);
        return ResponseEntity.ok(userSubscriptionDTOS);
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<UserSubscriptionDTO> updateStatus(@PathVariable("id") String id, @PathVariable("status") String status) {
        return ResponseEntity.ok(userSubscriptionService.updateStatus(UUID.fromString(id), status));
    }

    @GetMapping("permission/media/{mediaId}}")
    public ResponseEntity<UserSubscriptionPermissionDTO> getSubscriberPermissions(
        @PathVariable("id") String id,
        @PathVariable("media") String mediaId
    ) {
        return ResponseEntity.ok(userSubscriptionService.setSubscriptionPermissions(UUID.fromString(id), UUID.fromString(mediaId)));
    }

    @PostMapping("/media/{id}")
    public ResponseEntity<String> updateMediaDowloadCount(@PathVariable("id") String mediaId) {
        userSubscriptionService.updateMediaDownloadCount(UUID.fromString(mediaId));
        return ResponseEntity.ok("SUCCESS");
    }
}
