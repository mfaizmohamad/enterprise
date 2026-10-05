package com.__eleven.enterprise.controller;

import com.__eleven.enterprise.dto.CreateTestTakerRequest;
import com.__eleven.enterprise.entity.TestTaker;
import com.__eleven.enterprise.service.TestTakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test-takers")
@RequiredArgsConstructor
public class TestTakerController {

    private final TestTakerService testTakerService;

    @PostMapping("/create")
    public ResponseEntity<TestTaker>  createTestTaker (@RequestBody CreateTestTakerRequest req){
        TestTaker createdTestTaker = testTakerService.createTestTaker(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTestTaker);
    }

    @GetMapping("/by-schedule/{scheduleId}")
    public  ResponseEntity<List<TestTaker>> fetchBySchedule(@PathVariable Long scheduleId){
     return ResponseEntity.ok(testTakerService.getBySchedule(scheduleId));
    }
}
