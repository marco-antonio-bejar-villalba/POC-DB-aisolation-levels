package org.marco.poc.pocdbisolationlevels.rest;

import org.marco.poc.pocdbisolationlevels.persistence.User;

public interface UserMapper {

    static UserRequest toUserRequest(User user) {
        return UserRequest.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    static User toUser(UserRequest userRequest) {
        return User.builder()
                .id(userRequest.getId())
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .role(userRequest.getRole())
                .build();
    }
}
