package com.example.fundbox24backend.api.repository;


import com.example.fundbox24backend.api.model.FoundReport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FoundReportRepository extends JpaRepository<FoundReport, Long>, JpaSpecificationExecutor<FoundReport>
{}
