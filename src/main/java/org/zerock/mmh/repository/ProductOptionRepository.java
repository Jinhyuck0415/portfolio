package org.zerock.mmh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mmh.entity.*;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, String> {
//    //관리 상품 정보로 찾은 서비스들을 바로 불러오기 위한 설정
//    @EntityGraph(attributePaths = {"option"}, type = EntityGraph.EntityGraphType.FETCH)
//    List<ProductOption> findByManuProduct(ManufacturerProduct manufacturerProduct);
//
//    //관리 상품 삭제시 관리 상품과 연결된 상품옵션이 지워진다. 그 때 호출하는 용도.
//    @Modifying
//    @Query("delete from ProductOption po where po.manufacturerProduct.manuProductNo =:manuProductNo ")
//    void deleteByManuInfoNo(String manuProductNo);
//
//    //옵션 삭제시 해당 관리 상품과 연결된 상품옵션이 지워진다. 그 때 호출하는 용도.
//    @Modifying
//    @Query("delete from ProductOption po where po.option.optionNo=:optionNo ")
//    void deleteByOptionNo(String optionNo);


//    //해당 업체정보번호에 해당하는 서비스 가져오기
//    @Query("select o from Option o join ProductOption po where po.manuInfoNo =:manuInfoNo and po.service.serviceNo = o.serviceNo")
//    List<Option> findByManufacturerProductNo(String manuProductNo);
//
//    //업체 정보와 서비스를 함께 받아와서 삭제하는 메소드. 웹페이지와 연결되어있다.
//    @Modifying
//    @Query("delete from ManufacturerService ms where ms.manufacturerInfo.manuInfoNo =:manuInfoNo "+
//            "and ms.service.serviceNo =:serviceNo ")
//    void deleteByManuInfoNoWithServiceNo(String manuInfoNo, String serviceNo);

}
