package com.nexxus.server.controller.v1;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.DeliverableApi;
import com.nexxus.cos.api.dto.deliverable.CreateDeliverableRequest;
import com.nexxus.cos.api.dto.deliverable.DeliverableDashboardDto;
import com.nexxus.cos.api.dto.deliverable.DeliverableDashboardRequest;
import com.nexxus.cos.api.dto.deliverable.DeliverableDto;
import com.nexxus.cos.api.dto.deliverable.DeliverableListItem;
import com.nexxus.cos.api.dto.deliverable.EditDeliverableRequest;
import com.nexxus.cos.api.dto.deliverable.ListDeliverableRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/projects/{projectId}/deliverables")
@RequiredArgsConstructor
public class DeliverableController {

    private final DeliverableApi deliverableApi;

    @PostMapping("")
    public DeliverableDto create(@PathVariable Long projectId, @RequestBody @Valid CreateDeliverableRequest req) {
        log.info("create deliverable req: {}", req);
        return deliverableApi.createDeliverable(projectId, req);
    }

    @GetMapping("/{displayId}")
    public DeliverableDto detail(@PathVariable Long projectId, @PathVariable String displayId) {
        return deliverableApi.getByDisplayId(projectId, displayId);
    }

    @PostMapping("/{displayId}/edit")
    public DeliverableDto edit(@PathVariable Long projectId, @PathVariable String displayId, @RequestBody @Valid EditDeliverableRequest req) {
        log.info("edit deliverable req: {}", req);
        return deliverableApi.edit(projectId, displayId, req);
    }

    @PostMapping("/list")
    public PageResult<DeliverableListItem> list(@PathVariable Long projectId, @RequestBody @Valid ListDeliverableRequest req) {
        log.info("list deliverable req: {}", req);
        return deliverableApi.list(projectId, req.getPage(), req.getPageSize());
    }

    @PostMapping("/dashboard")
    public DeliverableDashboardDto dashboard(@PathVariable Long projectId, @RequestBody @Valid DeliverableDashboardRequest req) {
        return deliverableApi.dashboard(projectId, req);
    }
}
