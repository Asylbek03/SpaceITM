package com.example.spaceitm.repository;

import com.example.spaceitm.domain.User;
import com.example.spaceitm.exceptions.EtAuthException;

public interface UserRepository {
    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
