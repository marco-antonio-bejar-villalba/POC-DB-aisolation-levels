package org.marco.poc.pocdbaisolationlevels.service;

import org.marco.poc.pocdbaisolationlevels.persistence.User;

import java.util.List;
import java.util.Optional;

public interface UserServicePort {

    User createUser(User user);

    Optional<User> getUserById(String id);

    User updateUser(User user);

    List<User> getAllUsers();

}
