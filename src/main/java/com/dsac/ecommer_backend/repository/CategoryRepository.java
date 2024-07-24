package com.dsac.ecommer_backend.repository;

import com.dsac.ecommer_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "CALL sp_find_category_by_name(:name)", nativeQuery = true)
    Category findCategoryByName(@Param("name") String name);

    @Query(value = "CALL sp_search_category_by_name(:name, :page, :size)", nativeQuery = true)
    List<Category> searchCategoryByName(@Param("name") String name, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_find_popular_categories(:page, :size)", nativeQuery = true)
    List<Category> findPopularCategories(@Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_find_unpopular_categories(:page, :size)", nativeQuery = true)
    List<Category> findUnPopularCategories(@Param("page") int page, @Param("size") int size);

}