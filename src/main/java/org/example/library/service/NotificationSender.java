package org.example.library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationSender {
    public void sendNotification(String title, String username) {
        //Отправка смс или письма по почте
        log.info("Уважаемый {}, верните книгу {}", username, title);
    }
}
