package com.example.uber.repository.immutable;

import com.example.uber.model.immutable.PassengerReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface PassengerReportRepository extends JpaRepository<PassengerReport, UUID> {

    List<PassengerReport> findByPassengerId(UUID passengerId);
}
