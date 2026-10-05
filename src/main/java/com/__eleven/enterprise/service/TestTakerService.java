package com.__eleven.enterprise.service;

import com.__eleven.enterprise.dto.CreateTestTakerRequest;
import com.__eleven.enterprise.entity.Schedule;
import com.__eleven.enterprise.entity.TestTaker;
import com.__eleven.enterprise.entity.User;
import com.__eleven.enterprise.repository.ScheduleRepository;
import com.__eleven.enterprise.repository.TestTakerRepository;
import com.__eleven.enterprise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestTakerService {

    private final TestTakerRepository testTakerRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public TestTaker createTestTaker(CreateTestTakerRequest req){

        // 1. Resolve the schedule by name → ID
        Schedule schedule = scheduleRepository.findByName(req.getScheduleName())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Schedule not found: " + req.getScheduleName()));

        // 2. Create the user (or reuse an existing one)
        User user = userRepository.findByUsername(req.getUsername())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername(req.getUsername());
                    newUser.setFullName(req.getFullName());
                    newUser.setOrganizationId(req.getOrganizationId());
                    return userRepository.save(newUser);
                });

        // 3. Prevent duplicate enrollment
        if (testTakerRepository.existsByUsernameAndScheduleId(user.getId(), schedule.getId())) {
            throw new IllegalStateException(
                    "User " + user.getUsername() + " is already enrolled in " + schedule.getName());
        }

        // 4. Create the test_taker row
        TestTaker testTaker = new TestTaker();
        testTaker.setUsername(user.getUsername());
        testTaker.setScheduleId(schedule.getId());
        return testTakerRepository.save(testTaker);
    }

    public List<TestTaker> getAllTestTakers() {
        return testTakerRepository.findAll();
    }

    public List<TestTaker> getBySchedule(Long scheduleId) {
        return testTakerRepository.findByScheduleId(scheduleId);
    }
}
