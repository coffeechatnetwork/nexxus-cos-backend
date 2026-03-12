package com.nexxus.cos.service.api;

import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.SectionName;
import com.nexxus.common.enums.cos.deliverable.DeliverableStatus;
import com.nexxus.cos.api.DeliverableApi;
import com.nexxus.cos.api.dto.calendar.CalendarDto;
import com.nexxus.cos.api.dto.calendar.CalendarEventDto;
import com.nexxus.cos.api.dto.deliverable.CreateDeliverableRequest;
import com.nexxus.cos.api.dto.deliverable.DeliverableDashboardDto;
import com.nexxus.cos.api.dto.deliverable.DeliverableDashboardRequest;
import com.nexxus.cos.api.dto.deliverable.DeliverableDto;
import com.nexxus.cos.api.dto.deliverable.DeliverableListItem;
import com.nexxus.cos.api.dto.deliverable.DeliverableSectionDto;
import com.nexxus.cos.api.dto.deliverable.EditDeliverableRequest;
import com.nexxus.cos.service.api.converter.DeliverableConverter;
import com.nexxus.cos.service.entity.DeliverableEntity;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.DeliverableService;
import com.nexxus.cos.service.service.UserService;
import com.nexxus.cos.service.service.query.DeliverableQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliverableApiImpl implements DeliverableApi {

    private final UserService userService;
    private final DeliverableService deliverableService;
    private final DeliverableConverter deliverableConverter;

    @Override
    public DeliverableDto createDeliverable(Long projectId, CreateDeliverableRequest req) {
        // get orgId from context
        AccountInfo accountInfo = AccountInfoContext.get();
        Long orgId = accountInfo.getOrgId();

        // check the duplicate
        DeliverableEntity deliverableEntity = deliverableService.getByProjectIdAndTitle(req.getProjectId(), req.getTitle());
        if (deliverableEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("deliverable already exist in this project"));
        }

        // check the assignee
        UUID assigneeId = req.getAssignee();
        UserEntity assignee = userService.getByAccountId(assigneeId);
        if (assignee == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("assignee not found."));
        }

        DeliverableEntity newDeliverable = DeliverableEntity.builder()
                .orgId(orgId)
                .projectId(req.getProjectId())
                .displayId(UUID.randomUUID().toString())
                .title(req.getTitle())
                .shortDesc(req.getShortDesc())
                .longDesc(req.getLongDesc())
                .assignee(req.getAssignee())
                .deadline(req.getDeadline())
                .status(DeliverableStatus.NOT_YET_STARTED)
                .attachments(req.getAttachments())
                .build();
        deliverableService.save(newDeliverable);
        return deliverableConverter.toDeliverableDto(newDeliverable);
    }

    @Override
    public DeliverableDto getByDisplayId(Long projectId, String displayId) {
        DeliverableEntity deliverableEntity = deliverableService.getByDisplayId(displayId);
        if (deliverableEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("deliverable not found"));
        }
        return deliverableConverter.toDeliverableDto(deliverableEntity);
    }

    @Override
    public DeliverableDto edit(Long projectId, String displayId, EditDeliverableRequest req) {
        DeliverableEntity deliverableEntity = deliverableService.getByDisplayId(displayId);
        if (deliverableEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("deliverable not found"));
        }
        deliverableEntity.setAttachments(req.getAttachments());
        deliverableEntity.setParticipants(req.getParticipants());
        deliverableEntity.setStatus(req.getStatus());
        deliverableEntity.setRelatedTasks(req.getRelatedTasks());
        deliverableService.updateById(deliverableEntity);
        return deliverableConverter.toDeliverableDto(deliverableEntity);
    }

    @Override
    public PageResult<DeliverableListItem> list(Long projectId, Long page, Long pageSize) {
        DeliverableQuery query = DeliverableQuery.builder()
                .projectId(projectId).page(page).pageSize(pageSize)
                .build();
        var entityPage = deliverableService.listDeliverables(query);

        List<DeliverableListItem> items = entityPage.getRecords().stream()
                .map(deliverableConverter::toDeliverableListItem)
                .collect(Collectors.toList());

        return PageResult.<DeliverableListItem>builder()
                .records(items)
                .total(entityPage.getTotal())
                .pageSize(entityPage.getSize())
                .page(entityPage.getCurrent())
                .build();
    }

    @Override
    public DeliverableDashboardDto dashboard(Long projectId, DeliverableDashboardRequest req) {
        List<DeliverableEntity> deliverableEntities = deliverableService.getByProjectIdAndDate(
                req.getProjectId(), req.getStartDate(), req.getEndDate());

        List<DeliverableListItem> deliverableListItems = deliverableEntities.stream()
                .map(deliverableConverter::toDeliverableListItem)
                .toList();

        List<CalendarEventDto<DeliverableListItem>> calendarEvents = deliverableListItems.stream()
                .filter(item -> item.getDeadline() != null)
                .map(item -> CalendarEventDto.<DeliverableListItem>builder()
                        .date(item.getDeadline())
                        .event(item)
                        .build())
                .collect(Collectors.toList());

        CalendarDto<DeliverableListItem> calendar = CalendarDto.<DeliverableListItem>builder()
                .events(calendarEvents)
                .build();

        Instant now = Instant.now();
        Map<SectionName, List<DeliverableListItem>> sectionMap = new HashMap<>();
        sectionMap.put(SectionName.OVERDUE, new ArrayList<>());
        sectionMap.put(SectionName.NEXT_7_DAYS, new ArrayList<>());
        sectionMap.put(SectionName.NEXT_30_DAYS, new ArrayList<>());
        sectionMap.put(SectionName.LATER, new ArrayList<>());

        for (DeliverableListItem item : deliverableListItems) {
            if (item.getDeadline() == null) {
                sectionMap.get(SectionName.LATER).add(item);
                continue;
            }

            if (item.getDaysToDeadline() != null) {
                int days = item.getDaysToDeadline();
                if (days < 0) {
                    sectionMap.get(SectionName.OVERDUE).add(item);
                } else if (days <= 7) {
                    sectionMap.get(SectionName.NEXT_7_DAYS).add(item);
                } else if (days <= 30) {
                    sectionMap.get(SectionName.NEXT_30_DAYS).add(item);
                } else {
                    sectionMap.get(SectionName.LATER).add(item);
                }
            } else {
                sectionMap.get(SectionName.LATER).add(item);
            }
        }

        List<DeliverableSectionDto> sections = sectionMap.entrySet().stream()
                .map(entry -> DeliverableSectionDto.builder()
                        .sectionName(entry.getKey())
                        .count(entry.getValue().size())
                        .items(entry.getValue())
                        .build())
                .collect(Collectors.toList());

        return DeliverableDashboardDto.builder()
                .calendar(calendar)
                .sections(sections)
                .build();
    }
}
