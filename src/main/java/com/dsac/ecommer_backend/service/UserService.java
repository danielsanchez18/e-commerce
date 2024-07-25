package com.dsac.ecommer_backend.service;

import com.dsac.ecommer_backend.exception.ResourceFoundException;
import com.dsac.ecommer_backend.model.Product;
import com.dsac.ecommer_backend.model.User;
import com.dsac.ecommer_backend.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserService {

    User saveUser(User user, Set<UserRole> userRoles) throws ResourceFoundException;

    User getUserById(UUID id);

    User getUserByUsername(String username);

    Long totalUsers();

    Page<User> getAllUsers(Pageable pageable);

    User getUserByEmail(String email);

    User lastUser();

    List<User> getUsersByName(String name, int page, int size);

    List<User> getUsersByRegisterDate(String startDate, String endDate, int page, int size);

    List<User> getUsersByRole(String role, int page, int size);

    List<User> getUsersByEnabled(int page, int size);

    List<User> getUsersByUnEnabled(int page, int size);

    List<User> getTopBuyers(int page, int size);

    User updateUser(UUID id, User user);

    User updateUserRole(UUID id, String roleName);

    void deleteUser(UUID id);

}