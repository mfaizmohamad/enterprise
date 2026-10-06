package com.__eleven.enterprise.service;

import com.__eleven.enterprise.dto.CreateTestTakerRequest;
import com.__eleven.enterprise.entity.Schedule;
import com.__eleven.enterprise.entity.TestTaker;
import com.__eleven.enterprise.entity.User;
import com.__eleven.enterprise.repository.ScheduleRepository;
import com.__eleven.enterprise.repository.TestTakerRepository;
import com.__eleven.enterprise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestTakerService {

    private final TestTakerRepository testTakerRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public TestTaker createTestTaker(CreateTestTakerRequest req){

        Schedule schedule = scheduleRepository.findByName(req.getScheduleName())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Schedule not found: " + req.getScheduleName()));

        User user = userRepository.findByUsername(req.getUsername())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername(req.getUsername());
                    newUser.setFullName(req.getFullName());
                    newUser.setOrganizationId(req.getOrganizationId());
                    return userRepository.save(newUser);
                });

        if (testTakerRepository.existsByUsernameAndScheduleId(user.getId(), schedule.getId())) {
            throw new IllegalStateException(
                    "User " + user.getUsername() + " is already enrolled in " + schedule.getName());
        }

        TestTaker testTaker = new TestTaker();
        testTaker.setUsername(user.getUsername());
        testTaker.setScheduleId(schedule.getId());
        TestTaker saved = testTakerRepository.save(testTaker);

        log.info("Test Taker created: id={}, username={}", saved.getId(), saved.getUsername());

        return saved;
    }

    public List<TestTaker> getAllTestTakers() {
        return testTakerRepository.findAll();
    }

    public List<TestTaker> getBySchedule(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> {
                    log.warn("Schedule not found id={}", scheduleId);
                    return new IllegalArgumentException("Schedule not found id: " + scheduleId);
                });

        List<TestTaker> testTakers = testTakerRepository.findByScheduleId(scheduleId);
        log.info("Fetch {} by schedule {} id={}", testTakers.size(),schedule.getName(), scheduleId);
        return testTakers;
    }
}
