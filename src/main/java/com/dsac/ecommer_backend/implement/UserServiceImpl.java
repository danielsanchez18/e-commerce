package com.dsac.ecommer_backend.implement;

import com.dsac.ecommer_backend.exception.ResourceFoundException;
import com.dsac.ecommer_backend.model.User;
import com.dsac.ecommer_backend.model.UserRole;
import com.dsac.ecommer_backend.repository.RoleRepository;
import com.dsac.ecommer_backend.repository.UserRepository;
import com.dsac.ecommer_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User saveUser(User user, Set<UserRole> userRoles) throws ResourceFoundException {
        User userExists = this.userRepository.findByUsername(user.getUsername());

        if (userExists != null) {
            throw new ResourceFoundException("User already exists");
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            userExists = userRepository.save(user);
        }
        return userExists;
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Long totalUsers() {
        return userRepository.count();
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User lastUser() {
        return userRepository.lastUser();
    }

    @Override
    public List<User> getUsersByName(String name, int page, int size) {
        return userRepository.searchUserByName(name, page, size);
    }

    @Override
    public List<User> getUsersByRegisterDate(String startDate, String endDate, int page, int size) {
        return userRepository.searchUserByRegisterDate(startDate, endDate, page, size);
    }

    @Override
    public List<User> getUsersByRole(String role, int page, int size) {
        return userRepository.findUserByRole(role, page, size);
    }

    @Override
    public List<User> getUsersByEnabled(int page, int size) {
        return userRepository.findUserByEnabled(true, page, size);
    }

    @Override
    public List<User> getUsersByUnEnabled(int page, int size) {
        return userRepository.findUserByEnabled(false, page, size);
    }

    @Override
    public List<User> getTopBuyers(int page, int size) {
        return userRepository.findTopBuyers(page, size);
    }

    @Override
    public User updateUser(UUID id, User user) {
        return null;
    }

    @Override
    public User updateUserRole(UUID id, String roleName) {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
