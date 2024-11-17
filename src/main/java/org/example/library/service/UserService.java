package org.example.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.dto.DataDto;
import org.example.library.dto.RequestDto;
import org.example.library.entity.Book;
import org.example.library.entity.LibraryUser;
import org.example.library.mapper.BookMapper;
import org.example.library.mapper.UserMapper;
import org.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    public LibraryUser findByFullName(String fullName) {
        return userRepository.findByFullName(fullName);
    }

    @Transactional
    public void saveData(RequestDto requestDto) {
        if (requestDto == null || requestDto.getData() == null) {
            log.warn("Data is null");
            return;
        }
        Map<String, LibraryUser> userMap = requestDto.getData().stream()
                .map(this::mapToUserWithBooks)
                .collect(Collectors.toMap(
                        LibraryUser::getUsername,
                        user -> user,
                        (existingData, newData) -> {
                            existingData.getBooks().addAll(newData.getBooks());
                            return existingData;
                        }
                ));
        userRepository.saveAll(userMap.values());
    }

    private LibraryUser mapToUserWithBooks(DataDto dataDto) {
        LibraryUser user = userMapper.toLibraryUser(dataDto);
        Book book = bookMapper.toBook(dataDto);
        book.setUser(user);
        user.getBooks().add(book);
        return user;
    }
}
