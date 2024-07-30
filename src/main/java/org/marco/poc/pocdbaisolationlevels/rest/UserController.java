package org.marco.poc.pocdbaisolationlevels.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.marco.poc.pocdbaisolationlevels.LogUtils;
import org.marco.poc.pocdbaisolationlevels.service.UserServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController implements UserApiPort {

    private final UserServicePort userServicePort;

    @Override
    public ResponseEntity<UserRequest> createUser(UserRequest userRequest) {
        log.info("Creating user: {}", LogUtils.asJsonString(userRequest));
        UserRequest response = UserMapper.toUserRequest(userServicePort.createUser(UserMapper.toUser(userRequest)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<UserRequest> updateUser(UserRequest userRequest) {
        log.info("Updating user: {}", LogUtils.asJsonString(userRequest));
        UserRequest response = UserMapper.toUserRequest(userServicePort.updateUser(UserMapper.toUser(userRequest)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<UserRequest> getUserById(String id) {
        log.info("Getting user by id: {}", id);
        return ResponseEntity.ok(UserMapper.toUserRequest(userServicePort.getUserById(id).orElse(null)));
    }

    @Override
    public ResponseEntity<List<UserRequest>> getAll() {
        log.info("Getting all users");
        return ResponseEntity.ok(userServicePort.getAllUsers().stream().map(UserMapper::toUserRequest).collect(Collectors.toList()));
    }
}
