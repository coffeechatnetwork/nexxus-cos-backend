package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.common.enums.cos.question.QuestionStatus;
import com.nexxus.cos.api.dto.question.QuestionSummaryDto;
import com.nexxus.cos.service.entity.QuestionEntity;
import com.nexxus.cos.service.mapper.QuestionMapper;
import com.nexxus.cos.service.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, QuestionEntity> implements QuestionService {

    private final QuestionMapper questionMapper;

    @Override
    public QuestionEntity getByDisplayId(String displayId) {
        LambdaQueryWrapper<QuestionEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(QuestionEntity::getDisplayId, displayId);
        return getOne(wrapper);
    }

    @Override
    public Page<QuestionEntity> listDevChecklists(Long projectId, Long page, Long pageSize, String searchQuery) {
        Page<QuestionEntity> pageParam = new Page<>(page, pageSize);
        LambdaQueryWrapper<QuestionEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(QuestionEntity::getProjectId, projectId);
        if (StringUtils.isNotBlank(searchQuery)) {
            queryWrapper.like(QuestionEntity::getContent, searchQuery);
        }
        queryWrapper.orderByDesc(QuestionEntity::getId);
        return questionMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public List<QuestionSummaryDto.PrioritySummaryItem> summary(Long projectId) {
        LambdaQueryWrapper<QuestionEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(QuestionEntity::getProjectId, projectId);
        wrapper.isNotNull(QuestionEntity::getPriority);
        List<QuestionEntity> allChecklists = list(wrapper);

        return allChecklists.stream()
                .collect(Collectors.groupingBy(QuestionEntity::getPriority))
                .entrySet().stream()
                .map(entry -> {
                    Map<QuestionStatus, Integer> statusCount = entry.getValue().stream()
                            .collect(Collectors.groupingBy(
                                    QuestionEntity::getStatus,
                                    Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                            ));
                    for (QuestionStatus status : QuestionStatus.values()) {
                        statusCount.putIfAbsent(status, 0);
                    }
                    return QuestionSummaryDto.PrioritySummaryItem.builder()
                            .priority(entry.getKey())
                            .statusCount(statusCount)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
