package org.zerock.mmh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.mmh.entity.ManufacturerInfo;

import java.util.List;

public interface ManufacturerInfoRepository extends JpaRepository<ManufacturerInfo, String>, QuerydslPredicateExecutor<ManufacturerInfo> {
//
//    @Query("select m, count(distinct s) from ManufacturerInfo m " +
//            "left outer join ManufacturerService s on s.manufacturerInfo = m group by m")
//    Page<Object[]> getListPage(Pageable pageable);
//
//
//    @Query("select m, count(distinct s) from ManufacturerInfo m " +
//            "left outer join ManufacturerService s on s.manufacturerInfo = m " +
//            "where m.manu_info_name like %:keyword% group by m")
//    Page<Object[]> getListPage(String keyword, Pageable pageable);


//    @Query("select m, s from ManufacturerInfo m "+
//            "left outer join ManufacturerService s on s.manufacturerInfo = m")
//    Page<Object[]> getListPage(Pageable pageable);


    @Query("select m, s from ManufacturerInfo m " +
            "left outer join ManufacturerService ms on ms.manufacturerInfo = m " +
            "left outer join mService s on s.serviceNo = ms.service.serviceNo "
            + "group by m "
    )
    Page<Object[]> getListPage(Pageable pageable);


    @Query("select m, s from ManufacturerInfo m " +
            "left outer join ManufacturerService ms on ms.manufacturerInfo = m " +
            "left outer join mService s on s.serviceNo = ms.service.serviceNo " +
            "where m.manu_info_name like %:keyword% "
            + "group by m "
    )
    Page<Object[]> getListPage(String keyword, Pageable pageable);


    //여기서 반환한 값을 read에 쓸 것이다.
    @Query("select mi, s from ManufacturerInfo mi " +
            "left outer join ManufacturerService ms on ms.manufacturerInfo = mi " +
            "left outer join mService s on s.serviceNo = ms.service.serviceNo "+
            "where mi.manuInfoNo =:manuInfoNo")
    List<Object[]> getManuInfoWithAll(String manuInfoNo);

}
