package org.zerock.mmh.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.mmh.entity.ManufacturerInfo;
import org.zerock.mmh.entity.mService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ManufacturerInfoRepositoryTests {

    @Autowired
    private ManufacturerInfoRepository manufacturerInfoRepository;

    @Autowired
    private ManufacturerServiceRepository manufacturerServiceRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private ManufacturerProductRepository manufacturerProductRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 9).forEach(i -> {
            ManufacturerInfo manufacturerInfo = ManufacturerInfo.builder()
                    .manu_info_name("업체..." + i)
                    .manu_info_site("http://test" + i)
                    .manuMemNo("Manu" + i)
                    .build();
            System.out.println(manufacturerInfoRepository.save(manufacturerInfo));
        });
    }

    @Test
    public void testListPage() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("manuInfoNo").descending());
        Page<Object[]> result = manufacturerInfoRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }


    @Commit
    @Transactional
    @Test
    public void testDeleteManufacturerInfo() {
        String manuInfoNo = "MI00010";
        ManufacturerInfo manufacturerInfo = ManufacturerInfo.builder().manuInfoNo(manuInfoNo).build();
//        manufacturerServiceRepository.deleteByManufacturerInfo(manufacturerInfo);
        manufacturerProductRepository.deleteByManuInfoNo(manuInfoNo);
        manufacturerServiceRepository.deleteByManuInfoNo(manuInfoNo);
        manufacturerInfoRepository.delete(manufacturerInfo);

    }

    @Test
    public void testquery(){
        String manuInfoNo = "MI00009";
        List<Object[]> result = manufacturerInfoRepository.getManuInfoWithAll(manuInfoNo);

        ManufacturerInfo manufacturerInfo = (ManufacturerInfo) result.get(0)[0];
        System.out.println(manufacturerInfo);
        result.forEach(data ->{
//            System.out.println(Arrays.toString(data));
            mService service = (mService) data[1];
            System.out.println(service);
        });


    }
}
