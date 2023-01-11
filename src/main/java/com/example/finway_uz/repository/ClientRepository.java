package com.example.finway_uz.repository;

import com.example.finway_uz.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByChatId(String chatId);
}
