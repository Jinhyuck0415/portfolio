package org.zerock.mmh.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.zerock.mmh.entity.ManufacturerInfo;
import org.zerock.mmh.entity.ManufacturerService;
import org.zerock.mmh.entity.mService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ManufacturerServiceRepositoryTests {
    @Autowired
    private ManufacturerServiceRepository repository;

    @Test
    public void insertServiceToManufacturer() {
//        IntStream.rangeClosed(1,10).forEach(i->{
        //업체정보 번호
        String manuInfoNo = "MI00002";
        ManufacturerInfo manufacturerInfo = ManufacturerInfo.builder().manuInfoNo(manuInfoNo).build();


        //서비스 번호
        String serviceNo = "S00002";
        ManufacturerService manufacturerService = ManufacturerService.builder()
                .manufacturerInfo(manufacturerInfo)
                .service(mService.builder().serviceNo(serviceNo).build())
                .build();
        repository.save(manufacturerService);
//        });
    }

    @Test
    public void testGetManufacturerServices() {
        ManufacturerInfo manufacturerInfo = ManufacturerInfo.builder().manuInfoNo("MI00009").build();
        List<ManufacturerService> result = repository.findByManufacturerInfo(manufacturerInfo);
        result.forEach(manufacturerService -> {
                    System.out.println(manufacturerService.getManuServiceNo());
                    System.out.println(manufacturerService.getManufacturerInfo().getManuInfoNo());
                    System.out.println(manufacturerService.getService().getServiceNo());
                    System.out.println(manufacturerService.getService().getService_name());
                    System.out.println(manufacturerService.getService().getService_desc());
                    System.out.println("--------------------------------");
                }

        );
    }



}
