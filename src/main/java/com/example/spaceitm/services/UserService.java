package com.example.spaceitm.services;

import com.example.spaceitm.domain.User;
import com.example.spaceitm.exceptions.EtAuthException;

import javax.naming.AuthenticationException;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

}
