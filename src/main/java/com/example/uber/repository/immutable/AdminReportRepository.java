package com.example.uber.repository.immutable;

import com.example.uber.model.immutable.AdminReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface AdminReportRepository extends JpaRepository<AdminReport, UUID> {
}
