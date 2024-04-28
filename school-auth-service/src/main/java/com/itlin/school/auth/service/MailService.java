package com.itlin.school.auth.service;

public interface MailService {
    void sendSimpleMail(String to, String subject, String content);
}
