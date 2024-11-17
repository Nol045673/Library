package org.example.library.service;

import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class NotificationService {
    @Value("${notification.schedule.days}")
    private int days;
    private final BookRepository bookRepository;
    private final NotificationSender notificationSender;

    @Scheduled(cron = "${notification.schedule.cron}")
    @SchedulerLock(name = "sendNotificationScheduler")
    public void sendNotification() {bookRepository.findByDateBefore(LocalDate.now().minusDays(days))
                .forEach(book -> notificationSender.sendNotification(book.getTitle(), book.getUser().getUsername()));
    }
}
