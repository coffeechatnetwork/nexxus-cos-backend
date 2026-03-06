package com.nexxus.cos.api.dto.deliverable;

import com.nexxus.cos.api.dto.calendar.CalendarDto;
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
public class DeliverableDashboardDto implements Serializable {
    CalendarDto<DeliverableListItem> calendar;
    List<DeliverableSectionDto> sections;
}
