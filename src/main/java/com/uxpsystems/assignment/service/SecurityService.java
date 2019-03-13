package com.uxpsystems.assignment.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}