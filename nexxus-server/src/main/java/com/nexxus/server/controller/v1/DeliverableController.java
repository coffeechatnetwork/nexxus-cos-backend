package com.nexxus.server.controller.v1;

import com.nexxus.cos.api.DeliverableApi;
import com.nexxus.cos.api.dto.deliverable.CreateDeliverableRequest;
import com.nexxus.cos.api.dto.deliverable.DeliverableDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/deliverables")
@RequiredArgsConstructor
public class DeliverableController {

    private final DeliverableApi deliverableApi;

    @PostMapping("")
    public DeliverableDto create(@RequestBody @Valid CreateDeliverableRequest req) {
        log.info("create deliverable req: {}", req);
        return deliverableApi.createDeliverable(req);
    }
}
