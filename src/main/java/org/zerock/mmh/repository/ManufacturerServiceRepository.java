package org.zerock.mmh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mmh.entity.ManufacturerInfo;
import org.zerock.mmh.entity.ManufacturerService;
import org.zerock.mmh.entity.mService;

import java.util.List;

public interface ManufacturerServiceRepository extends JpaRepository<ManufacturerService, String> {
    //업체 정보로 찾은 서비스들을 바로 불러오기 위한 설정
    @EntityGraph(attributePaths = {"service"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ManufacturerService> findByManufacturerInfo(ManufacturerInfo manufacturerInfo);

    //업체 정보 삭제시 업체 정보와 연결된 업체서비스가 지워진다. 그 때 호출하는 용도.
    @Modifying
    @Query("delete from ManufacturerService ms where ms.manufacturerInfo.manuInfoNo =:manuInfoNo ")
    void deleteByManuInfoNo(String manuInfoNo);

    //서비스 삭제시 해당 서비스와 연결된 업체서비스가 지워진다. 그 때 호출하는 용도.
    @Modifying
    @Query("delete from ManufacturerService ms where ms.service.serviceNo=:serviceNo ")
    void deleteByServiceNo(String serviceNo);


    //해당 업체정보번호에 해당하는 서비스 가져오기
    @Query("select s from mService s join ManufacturerService ms where ms.manufacturerInfo.manuInfoNo =:manuInfoNo and ms.service.serviceNo = s.serviceNo")
    List<mService> findByManufacturerInfoNo(String manuInfoNo);

    //업체 정보와 서비스를 함께 받아와서 삭제하는 메소드. 웹페이지와 연결되어있다.
    @Modifying
    @Query("delete from ManufacturerService ms where ms.manufacturerInfo.manuInfoNo =:manuInfoNo "+
            "and ms.service.serviceNo =:serviceNo ")
    void deleteByManuInfoNoWithServiceNo(String manuInfoNo, String serviceNo);
}
