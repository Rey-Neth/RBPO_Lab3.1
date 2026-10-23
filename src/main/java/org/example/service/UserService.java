package org.example.service;

import org.example.exception.BadRequestException;
import org.example.exception.NotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public User create(User user) {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new BadRequestException("Username is required");
        }
        user.setId(null);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User update(Long id, User updates) {
        User existing = findById(id);
        if (updates.getUsername() != null && !updates.getUsername().isBlank()) {
            existing.setUsername(updates.getUsername());
        }
        if (updates.getEmail() != null) {
            existing.setEmail(updates.getEmail());
        }
        // createdAt is not updatable; server-managed
        return userRepository.save(existing);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
