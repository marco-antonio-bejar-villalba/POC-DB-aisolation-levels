package org.marco.poc.pocdbaisolationlevels.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.marco.poc.pocdbaisolationlevels.persistence.User;
import org.marco.poc.pocdbaisolationlevels.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserServicePort {

    private final UserRepository userRepository;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public User createUser(User user) {
        if(user.getId() == null || user.getId().isEmpty()){
            throw new IllegalArgumentException("User id cannot be null or empty");
        }
        if(userRepository.existsById(user.getId())){
            throw new IllegalArgumentException("User with id " + user.getId() + " already exists");
        }
        User saved = userRepository.save(user);
        return saved;
    }

    @Override
    public Optional<User> getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            throw new IllegalArgumentException("User id cannot be null or empty");
        }
        if (!userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("User with id " + user.getId() + " does not exist");
        }
        User updated = userRepository.save(user);
        return updated;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
