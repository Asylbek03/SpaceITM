package com.example.spaceitm.controller;

import com.example.spaceitm.model.Comment;
import com.example.spaceitm.model.Topic;
import com.example.spaceitm.repositories.CommentRepository;
import com.example.spaceitm.repositories.TopicRepository;
import com.example.spaceitm.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ForumController {
    @Autowired
    private TopicRepository topicRepository;


    @GetMapping("/forum")
    public String getForumPage(Model model) {
        List<Topic> topics = topicRepository.findAll();
        model.addAttribute("topics", topics);

        return "forum";
    }

}