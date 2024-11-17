package org.example.library.service;

import org.example.library.dto.DataDto;
import org.example.library.dto.RequestDto;
import org.example.library.entity.Book;
import org.example.library.entity.LibraryUser;
import org.example.library.mapper.BookMapperImpl;
import org.example.library.mapper.UserMapperImpl;
import org.example.library.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig(classes = {UserService.class, UserMapperImpl.class, BookMapperImpl.class})
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @Captor
    private ArgumentCaptor<Collection<LibraryUser>> userCaptor;

    @Test
    void findByFullName() {
        LibraryUser user = new LibraryUser();
        String fullName = "test";
        Mockito.when(userRepository.findByFullName(any())).thenReturn(user);

        LibraryUser result = userService.findByFullName(fullName);

        Assertions.assertEquals(user, result);
        verify(userRepository).findByFullName(fullName);
    }

    @Test
    void saveData() {
        String username = "username";
        String bookName = "bookName";
        String bookName2 = "bookName2";
        RequestDto requestDto = new RequestDto();
        requestDto.setData(List.of(getDataDto(username, bookName), getDataDto(username, bookName2)));
        LibraryUser lUser = new LibraryUser();
        lUser.setUsername(username);
        lUser.setActive(true);
        Book book = new Book();
        book.setTitle(bookName);

        assertDoesNotThrow(() -> userService.saveData(requestDto));

        verify(userRepository).saveAll(userCaptor.capture());
        Collection<LibraryUser> users = userCaptor.getValue();
        Assertions.assertEquals(1, users.size());
        Assertions.assertTrue(users.stream().allMatch(user -> user.getUsername().equals(username)));
    }

    @Test
    void saveDataIfDataIsNull() {
        RequestDto requestDto = new RequestDto();

        assertDoesNotThrow(() -> userService.saveData(requestDto));

        verify(userRepository, Mockito.never()).saveAll(any());
    }

    private DataDto getDataDto(String username, String bookName) {
        DataDto dataDto = new DataDto();
        dataDto.setUsername(username);
        dataDto.setUserActive(true);
        dataDto.setBookName(bookName);
        return dataDto;
    }
}