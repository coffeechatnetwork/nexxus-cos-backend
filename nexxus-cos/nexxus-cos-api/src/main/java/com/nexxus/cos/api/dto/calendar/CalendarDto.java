package com.nexxus.cos.api.dto.calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDto<T> implements Serializable {
    List<CalendarEventDto<T>> events;
}
