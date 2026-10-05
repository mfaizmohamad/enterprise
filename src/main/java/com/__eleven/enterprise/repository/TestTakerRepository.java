package com.__eleven.enterprise.repository;

import com.__eleven.enterprise.entity.TestTaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestTakerRepository extends JpaRepository<TestTaker, Long> {

    List<TestTaker> findByScheduleId(Long scheduleId);

    List<TestTaker> findByUsername(Long username);

    boolean existsByUsernameAndScheduleId(Long userId, Long scheduleId);

}
