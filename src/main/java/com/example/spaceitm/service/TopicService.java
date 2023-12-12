package com.example.spaceitm.service;

import com.example.spaceitm.model.Comment;
import com.example.spaceitm.model.Topic;
import com.example.spaceitm.repositories.CommentRepository;
import com.example.spaceitm.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    public List<Topic> searchTopicsByTitle(String title) {
        return topicRepository.findByTitleContaining(title);
    }

    public void saveTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic getTopicById(Long id) {
        return topicRepository.findById(id).orElseThrow(() -> new RuntimeException("Topic not found"));

    }

    public static List<Topic> getAllTopics(TopicRepository topicRepository) {
        return topicRepository.findAll();
    }



    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByTopicId(Long topicId) {
        return commentRepository.findByTopicId(topicId);
    }

    public void saveComment(Comment comment, Long topicId, Topic topic_id) {
        commentRepository.save(comment);
    }


}
