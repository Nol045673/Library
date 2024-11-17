package org.example.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.dto.RequestDto;
import org.example.library.entity.LibraryUser;
import org.example.library.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @GetMapping("/users/{fullname}")
    public LibraryUser getBooks(@PathVariable String fullname) {
        return userService.findByFullName(fullname);
    }

    @PostMapping("/upload")
    public void uploadData(@RequestBody @Valid RequestDto requestDto) {
        log.info("Received data: {}", requestDto);
        userService.saveData(requestDto);
    }
}
