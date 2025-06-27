package com.upgrade.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upgrade.backend.dto.DailyProgressResponse;
import com.upgrade.backend.dto.UserStatsResponse;
import com.upgrade.backend.model.User;
import com.upgrade.backend.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<UserStatsResponse> getUserStats(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserStats(id));
    }

    @GetMapping("/{id}/progress-history")
    public ResponseEntity<List<DailyProgressResponse>> getUserProgressHistory(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserProgressHistory(id));
    }
}