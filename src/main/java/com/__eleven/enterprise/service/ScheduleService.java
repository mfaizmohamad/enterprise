package com.__eleven.enterprise.service;

import com.__eleven.enterprise.entity.Schedule;
import com.__eleven.enterprise.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule createSchedule(Schedule schedule){
        return scheduleRepository.save(schedule);
    }
}
