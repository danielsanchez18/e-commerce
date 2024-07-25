package com.dsac.ecommer_backend.repository;

import com.dsac.ecommer_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "CALL sp_find_product_by_name(:name_product)", nativeQuery = true)
    Product findProductByName(@Param("name_product") String name);

    @Query(value = "CALL sp_search_products_by_name(:p_name, :p_page, :p_size)", nativeQuery = true)
    List<Product> searchProductsByName(@Param("p_name") String nameProduct, @Param("p_page") int page, @Param("p_size") int size);

    @Query(value = "CALL sp_find_products_by_enabled(true, :p_page, :p_size)", nativeQuery = true)
    List<Product> findEnabledProducts(@Param("p_page") int page, @Param("p_size") int size);

    @Query(value = "CALL sp_find_products_by_enabled(false, :p_page, :p_size)", nativeQuery = true)
    List<Product> findDisabledProducts(@Param("p_page") int page, @Param("p_size") int size);

    @Query(value = "CALL sp_find_products_by_category(:p_category, :p_page, :p_size)", nativeQuery = true)
    List<Product> findProductsByCategory(@Param("p_category") String category, @Param("p_page") int page, @Param("p_size") int size);

    @Query(value = "CALL sp_find_enabled_products_by_category(:p_category, :p_page, :p_size)", nativeQuery = true)
    List<Product> findEnabledProductsByCategory(@Param("p_category") String category, @Param("p_page") int page, @Param("p_size") int size);

    @Query(value = "CALL sp_find_top_selling_products(:page, :size)", nativeQuery = true)
    List<Product> findTopSellingProducts(@Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_find_top_buying_products(:page, :size)", nativeQuery = true)
    List<Product> findTopBuyingProducts(@Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_find_top_buyer_product(:userId)", nativeQuery = true)
    Product findTopBuyerProduct(@Param("userId") UUID idUser);

}