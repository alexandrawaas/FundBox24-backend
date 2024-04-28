package com.example.fundbox24backend.api.repository;

import com.example.fundbox24backend.api.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChatRepository extends JpaRepository<Chat, Long>, JpaSpecificationExecutor<Chat>
{
}
