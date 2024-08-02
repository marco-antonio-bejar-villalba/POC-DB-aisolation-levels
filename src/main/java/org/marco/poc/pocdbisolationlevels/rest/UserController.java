package org.marco.poc.pocdbisolationlevels.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.marco.poc.pocdbisolationlevels.LogUtils;
import org.marco.poc.pocdbisolationlevels.persistence.User;
import org.marco.poc.pocdbisolationlevels.service.UserServicePort;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController implements UserApiPort {

    private final UserServicePort userServicePort;

    @Override
    public ResponseEntity<UserRequest> createUser(UserRequest userRequest) {
        log.info("Creating user: {}", LogUtils.asJsonString(userRequest));
        UserRequest response =
                UserMapper.toUserRequest(
                        userServicePort.createUser(UserMapper.toUser(userRequest)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<UserRequest> updateUser(UserRequest userRequest, String traceId) {
        log.info("traceId: {}", traceId);
        MDC.put("traceId", traceId);
        log.info("Updating user: {}", LogUtils.asJsonString(userRequest));
        UserRequest response =
                UserMapper.toUserRequest(
                        userServicePort.updateUser(UserMapper.toUser(userRequest)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<UserRequest> getUserById(String id) {
        log.info("Getting user by id: {}", id);
        Optional<User> user = userServicePort.getUserById(id);
        return user.map(value -> ResponseEntity.ok(UserMapper.toUserRequest(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<UserRequest>> getAll() {
        log.info("Getting all users");
        return ResponseEntity.ok(
                userServicePort.getAllUsers().stream()
                        .map(UserMapper::toUserRequest)
                        .collect(Collectors.toList()));
    }
}


