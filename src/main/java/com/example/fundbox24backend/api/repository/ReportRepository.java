package com.example.fundbox24backend.api.repository;

import com.example.fundbox24backend.api.model.Report;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface ReportRepository extends JpaRepository<Report, Long>, JpaSpecificationExecutor<Report>
{
}
