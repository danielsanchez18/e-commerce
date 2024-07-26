package com.dsac.ecommer_backend.implement;

import com.dsac.ecommer_backend.exception.ResourceFoundException;
import com.dsac.ecommer_backend.model.Role;
import com.dsac.ecommer_backend.model.User;
import com.dsac.ecommer_backend.model.UserRole;
import com.dsac.ecommer_backend.repository.RoleRepository;
import com.dsac.ecommer_backend.repository.UserRepository;
import com.dsac.ecommer_backend.service.UploadFileService;
import com.dsac.ecommer_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.module.ResolutionException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public User saveUser(User user) throws ResourceFoundException {
        User userExists = this.userRepository.findByEmail(user.getEmail());

        if (userExists != null) {
            throw new ResourceFoundException("User already exists");
        }
        else {
            Role role = roleRepository.findById(1L).orElseThrow(
                    () -> new ResolutionException("Role CLIENTE not found"));

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            user.getUserRoles().add(userRole);
            user.setRegistrationDate(LocalDateTime.now());
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
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
    public List<User> getUsersByRegisterDate(Date startDate, Date endDate, int page, int size) {
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
    public User updateUser(UUID id, User user, MultipartFile image) throws IOException {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found"));

        User userWithSameEmail = userRepository.findByEmail(user.getEmail());
        if (userWithSameEmail != null && !userWithSameEmail.getIdUser().equals(id)) {
            throw new RuntimeException("Email already in use");
        }

        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setAddress(user.getAddress());
        existingUser.setEnabled(user.isEnabled());

        if (image != null && !image.isEmpty()) {
            String imagePath = uploadFileService.copy(image, "user");
            existingUser.setProfile(imagePath);
        }

        return userRepository.save(existingUser);
    }

    @Override
    public User updateUserRole(UUID idUser, Long idRole) {
        User user = userRepository.findById(idUser).orElseThrow(
                () -> new RuntimeException("User not found"));

        Role role = roleRepository.findById(idRole).orElseThrow(
                () -> new RuntimeException("Role not found"));

        user.getUserRoles().clear();
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        user.getUserRoles().add(userRole);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}