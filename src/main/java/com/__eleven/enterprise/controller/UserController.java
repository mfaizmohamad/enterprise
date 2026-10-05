package com.__eleven.enterprise.controller;

import com.__eleven.enterprise.entity.Organization;
import com.__eleven.enterprise.entity.Schedule;
import com.__eleven.enterprise.entity.User;
import com.__eleven.enterprise.service.OrganizationService;
import com.__eleven.enterprise.service.ScheduleService;
import com.__eleven.enterprise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OrganizationService organizationService;
    private final ScheduleService scheduleService;

    @PostMapping("/add-user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/user-list")
    public ResponseEntity<List<User>> getALlUser(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create-org")
    public ResponseEntity<Organization> createOrg(@RequestBody Organization organization){
        Organization createdOrganization = organizationService.createOrganization(organization);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrganization);
    }

    @PostMapping("/create-schedule")
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule){
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
    }
}
