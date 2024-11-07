package org.zerock.mmh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.mmh.entity.ManufacturerInfo;
import org.zerock.mmh.entity.ManufacturerProduct;
import org.zerock.mmh.entity.Product;
import org.zerock.mmh.entity.mService;

import java.util.List;

public interface ManufacturerProductRepository extends JpaRepository<ManufacturerProduct, String>, QuerydslPredicateExecutor<ManufacturerProduct> {

    @EntityGraph(attributePaths = {"product", "manufacturerInfo"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ManufacturerProduct> findByProduct(Product product);

    @EntityGraph(attributePaths = {"product", "manufacturerInfo"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ManufacturerProduct> findByManufacturerInfo(ManufacturerInfo manufacturerInfo);


    @Modifying
    @Query("delete from ManufacturerProduct mp where mp.manufacturerInfo.manuInfoNo =:manuInfoNo")
    void deleteByManuInfoNo(String manuInfoNo);

    @Modifying
    @Query("delete from ManufacturerProduct mp where mp.product.productNo =:productNo")
    void deleteByProductNo(String productNo);


    //업체별 상품 리스트
    @Query("select mi, p, mp.manuProductPrice from ManufacturerProduct mp " +
            "left outer join ManufacturerInfo mi on mi = mp.manufacturerInfo " +
            "left outer join Product p on p = mp.product "
    )
    Page<Object[]> getListPage(Pageable pageable);

    //업체 정보와 상품 번호를 함께 받아와서 삭제하는 메소드. 웹페이지와 연결되어있다.
    @Modifying
    @Query("delete from ManufacturerProduct mp where mp.manufacturerInfo.manuInfoNo =:manuInfoNo "+
            "and mp.product.productNo =:productNo ")
    void deleteByManuInfoNoWithProductNo(String manuInfoNo, String productNo);

}
