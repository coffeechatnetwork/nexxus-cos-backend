package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.cos.service.entity.RiskLogEntity;
import com.nexxus.cos.service.mapper.RiskLogMapper;
import com.nexxus.cos.service.service.RiskLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiskLogServiceImpl extends ServiceImpl<RiskLogMapper, RiskLogEntity> implements RiskLogService {
}
