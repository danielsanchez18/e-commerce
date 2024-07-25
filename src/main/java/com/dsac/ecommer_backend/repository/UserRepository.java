package com.dsac.ecommer_backend.repository;

import com.dsac.ecommer_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "CALL sp_find_user_by_email(:email)", nativeQuery = true)
    User findByEmail(@Param("email") String email);

    @Query(value = "CALL sp_find_user_by_name(:name, :page, :size)", nativeQuery = true)
    List<User> searchUserByName(@Param("name") String name, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_find_users_by_register_date(:startDate, :endDate, :page, :size)", nativeQuery = true)
    List<User> searchUserByRegisterDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_find_last_user()", nativeQuery = true)
    User lastUser();

    @Query(value = "CALL sp_find_users_by_role(:role, :page, :size)", nativeQuery = true)
    List<User> findUserByRole(@Param("role") String role, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_find_users_by_enabled(:enabled, :page, :size)", nativeQuery = true)
    List<User> findUserByEnabled(@Param("enabled") boolean b, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_find_top_buyers(:page, :size)", nativeQuery = true)
    List<User> findTopBuyers(@Param("page") int page, @Param("size") int size);

}
