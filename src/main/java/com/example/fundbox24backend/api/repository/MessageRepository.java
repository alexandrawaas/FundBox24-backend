package com.example.fundbox24backend.api.repository;

import com.example.fundbox24backend.api.model.Message;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface MessageRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message>
{
}
