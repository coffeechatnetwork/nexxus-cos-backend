package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionResponseConverter {

    private final UserService userService;
    private final UserConverter userConverter;


}
