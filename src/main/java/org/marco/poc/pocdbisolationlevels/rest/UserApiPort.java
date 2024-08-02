package org.marco.poc.pocdbisolationlevels.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
public interface UserApiPort {

    @PostMapping
    ResponseEntity<UserRequest> createUser(@RequestBody UserRequest userRequest);

    @PutMapping
    ResponseEntity<UserRequest> updateUser(@RequestBody UserRequest userRequest, @RequestHeader("traceId") String traceId);

    @GetMapping("/{id}")
    ResponseEntity<UserRequest> getUserById(@PathVariable String id);

    @GetMapping
    ResponseEntity<List<UserRequest>> getAll();
}
