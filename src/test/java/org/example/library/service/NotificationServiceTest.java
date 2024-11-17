package org.example.library.service;

import org.example.library.entity.Book;
import org.example.library.entity.LibraryUser;
import org.example.library.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringJUnitConfig(classes = NotificationService.class)
@TestPropertySource(properties = "notification.schedule.days=20")
class NotificationServiceTest {
    @Autowired
    private NotificationService notificationService;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private NotificationSender notificationSender;

    @Test
    void sendNotification() {
        String username = "user";
        String title = "title";
        LibraryUser user = new LibraryUser();
        user.setUsername(username);
        Book book = new Book();
        book.setTitle(title);
        book.setUser(user);
        Mockito.when(bookRepository.findByDateBefore(any())).thenReturn(List.of(book));

        notificationService.sendNotification();

        Assertions.assertNotNull(notificationSender);
        Mockito.verify(bookRepository).findByDateBefore(any());
        Mockito.verify(notificationSender).sendNotification(title, username);
    }
}