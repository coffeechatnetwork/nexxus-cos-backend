package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.api.dto.question.QuestionSummaryDto;
import com.nexxus.cos.service.entity.QuestionEntity;

import java.util.List;

public interface QuestionService extends IService<QuestionEntity> {

    QuestionEntity getByDisplayId(String displayId);

    Page<QuestionEntity> listDevChecklists(Long projectId, Long page, Long pageSize, String searchQuery);

    List<QuestionSummaryDto.PrioritySummaryItem> summary(Long projectId);
}
