package com.example.spaceitm.repositories;

import com.example.spaceitm.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByTitleContaining(String title);
}
