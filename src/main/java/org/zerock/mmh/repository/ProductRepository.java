package org.zerock.mmh.repository;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.zerock.mmh.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String>, QuerydslPredicateExecutor<Product> {

    @Query("select p, pi from Product p " +
            "left outer join ProductImage pi on pi.product = p")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select p, pi from Product p " +
            "left outer join ProductImage pi on pi.product = p " +
            "where p.product_name like %:keyword%")
    Page<Object[]> getListPage(String keyword, Pageable pageable);

    @Query("select p, pi from Product p " + "left outer join ProductImage pi on pi.product = p " +
            "where p.productNo = :productNo group by pi")
    List<Object[]> getProductWithAll(String productNo);

    @Query("select p, pi from Product p " +
            "left outer join ProductImage pi on pi.product = p " +
            "where p.productNo = :productNo")
    List<Object[]> getProductWithImagesByProductNo(@Param("productNo") String productNo);

}
