package com.example.spaceitm.controller;

import com.example.spaceitm.model.Comment;
import com.example.spaceitm.model.Topic;
import com.example.spaceitm.model.User;
import com.example.spaceitm.repositories.CommentRepository;
import com.example.spaceitm.repositories.TopicRepository;
import com.example.spaceitm.repositories.UserRepository;
import com.example.spaceitm.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/create-topic")
    public String createTopic(Model model) {
        model.addAttribute("topic", new Topic());
        return "create-topic";
    }

    @PostMapping("/create-topic")
    public String createTopic(@ModelAttribute Topic topic, Principal principal) {
        User author = userRepository.findByEmail(principal.getName());
        topic.setAuthor(author);
        topicService.createTopic(topic);
        return "redirect:/topics";
    }

    @GetMapping("/topics")
    public String getAllTopics(Model model) {
        model.addAttribute("topics", topicRepository.findAll());
        return "topics";
    }


    @PostMapping("/topic/{id}/comment")
    public String createComment(@PathVariable Long id, @RequestParam String content, Principal principal) {
        User author = userRepository.findByEmail(principal.getName());
        Topic topic_id = topicRepository.getReferenceById(id);
        Comment comment = new Comment(content, author, topic_id);
        comment.setCreatedDate(new Date());
        topicService.saveComment(comment, id, topic_id);
        return "redirect:/topic/" + id;
    }

    @GetMapping("/topic/{id}")
    public String getTopicById(Model model, @PathVariable Long id, @RequestParam(required = false) Long commentId) {
        Topic topic = topicService.getTopicById(id);
        model.addAttribute("topic", topic);
        if (commentId != null) {
            Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
            model.addAttribute("newComment", comment);
        }
        model.addAttribute("comments", commentRepository.findByTopicId(id));
        return "topic";
    }


}
