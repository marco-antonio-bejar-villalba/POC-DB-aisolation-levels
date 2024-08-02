package org.marco.poc.pocdbisolationlevels.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.marco.poc.pocdbisolationlevels.persistence.User;
import org.marco.poc.pocdbisolationlevels.persistence.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService implements UserServicePort {

    private final UserRepository userRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public User createUser(User user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            throw new IllegalArgumentException("User id cannot be null or empty");
        }
        if (userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("User with id " + user.getId() + " already exists");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public User updateUser(User user) {

        log.info("Transaction: {}", TransactionSynchronizationManager.isActualTransactionActive());
        log.info(
                "Transaction: {}, {}",
                TransactionSynchronizationManager.getCurrentTransactionIsolationLevel(),
                Connection.TRANSACTION_SERIALIZABLE);

        if (user.getId() == null || user.getId().isEmpty()) {
            throw new IllegalArgumentException("User id cannot be null or empty");
        }
        if (!userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("User with id " + user.getId() + " does not exist");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
