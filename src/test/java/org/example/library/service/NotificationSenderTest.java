package org.example.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = NotificationSender.class)
class NotificationSenderTest {
    @Autowired
    NotificationSender notificationSender;

    @Test
    void sendNotification() {
        String title = "title";
        String username = "username";

        Assertions.assertDoesNotThrow(() -> notificationSender.sendNotification(title, username));
    }
}