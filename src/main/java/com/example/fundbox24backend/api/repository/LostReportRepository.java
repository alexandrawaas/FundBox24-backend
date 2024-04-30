package com.example.fundbox24backend.api.repository;

import com.example.fundbox24backend.api.model.LostReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LostReportRepository extends JpaRepository<LostReport, Long>, JpaSpecificationExecutor<LostReport>
{}
