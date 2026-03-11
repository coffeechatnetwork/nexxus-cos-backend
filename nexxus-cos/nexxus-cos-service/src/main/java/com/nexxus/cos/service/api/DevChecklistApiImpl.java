package com.nexxus.cos.service.api;

import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.checklist.DevChecklistCategory;
import com.nexxus.common.enums.cos.checklist.DevChecklistStatus;
import com.nexxus.cos.api.DevChecklistApi;
import com.nexxus.cos.api.dto.checklist.CreateDevChecklistRequest;
import com.nexxus.cos.api.dto.checklist.DevChecklistDto;
import com.nexxus.cos.api.dto.checklist.DevChecklistListItem;
import com.nexxus.cos.api.dto.checklist.DevChecklistSummaryDto;
import com.nexxus.cos.api.dto.checklist.DevChecklistSummaryRequest;
import com.nexxus.cos.api.dto.checklist.EditDevChecklistRequest;
import com.nexxus.cos.service.api.converter.DevChecklistConverter;
import com.nexxus.cos.service.entity.DevChecklistEntity;
import com.nexxus.cos.service.service.DevChecklistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DevChecklistApiImpl implements DevChecklistApi {

    private final DevChecklistService devChecklistService;
    private final DevChecklistConverter devChecklistConverter;

    @Override
    public DevChecklistDto createDevChecklist(Long projectId, CreateDevChecklistRequest req) {
        AccountInfo accountInfo = AccountInfoContext.get();
        Long orgId = accountInfo.getOrgId();

        DevChecklistEntity devChecklistEntity = devChecklistService.getByProjectIdAndTitle(req.getProjectId(), req.getTitle());
        if (devChecklistEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("checklist with this title already exist"));
        }
        DevChecklistEntity newEntity = DevChecklistEntity.builder()
                .orgId(orgId)
                .projectId(req.getProjectId())
                .displayId(UUID.randomUUID().toString())
                .title(req.getTitle())
                .description(req.getDescription())
                .category(req.getCategory())
                .status(DevChecklistStatus.IN_PROGRESS)
                .attachments(req.getAttachments())
                .build();
        devChecklistService.save(newEntity);
        return devChecklistConverter.toDevChecklistDto(newEntity);
    }

    @Override
    public DevChecklistDto getByDisplayId(Long projectId, String displayId) {
        DevChecklistEntity devChecklist = devChecklistService.getByDisplayId(displayId);
        if (devChecklist == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("devChecklist not found"));
        }
        return devChecklistConverter.toDevChecklistDto(devChecklist);
    }

    @Override
    public DevChecklistDto edit(Long projectId, String displayId, EditDevChecklistRequest req) {
        DevChecklistEntity devChecklist = devChecklistService.getByDisplayId(displayId);
        if (devChecklist == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("devChecklist not found"));
        }
        devChecklist.setStatus(req.getStatus());
        devChecklist.setAttachments(req.getAttachments());
        devChecklistService.updateById(devChecklist);
        return devChecklistConverter.toDevChecklistDto(devChecklist);
    }

    @Override
    public PageResult<DevChecklistListItem> listDevChecklists(Long projectId, Long page, Long pageSize, DevChecklistCategory category) {
        var pageResult = devChecklistService.listDevChecklists(projectId, page, pageSize, category);
        List<DevChecklistListItem> items = pageResult.getRecords().stream()
                .map(devChecklistConverter::toDevChecklistListItem)
                .collect(Collectors.toList());
        return PageResult.<DevChecklistListItem>builder()
                .records(items)
                .total(pageResult.getTotal())
                .pageSize(pageResult.getSize())
                .page(pageResult.getCurrent())
                .build();
    }

    @Override
    public DevChecklistSummaryDto summary(Long projectId, DevChecklistSummaryRequest req) {
        var categories = devChecklistService.summary(req.getProjectId());
        return DevChecklistSummaryDto.builder()
                .categories(categories)
                .build();
    }
}
