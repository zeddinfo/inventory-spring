package com.inventory.app.repository;

import com.inventory.app.models.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
