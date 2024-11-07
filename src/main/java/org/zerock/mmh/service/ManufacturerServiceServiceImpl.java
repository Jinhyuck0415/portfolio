package org.zerock.mmh.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.mmh.entity.mService;
import org.zerock.mmh.entity.ManufacturerInfo;
import org.zerock.mmh.entity.ManufacturerService;
import org.zerock.mmh.repository.ManufacturerServiceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ManufacturerServiceServiceImpl implements ManufacturerServiceService {

    private final ManufacturerServiceRepository repository;

    @Override
    public String register(String manuInfoNo, String serviceNo) {
        ManufacturerInfo manufacturerInfo = ManufacturerInfo.builder().manuInfoNo(manuInfoNo).build();

        ManufacturerService manufacturerService = ManufacturerService.builder()
                .manufacturerInfo(manufacturerInfo)
                .service(mService.builder().serviceNo(serviceNo).build())
                .build();
        repository.save(manufacturerService);

        return manufacturerService.getManuServiceNo();
    }

    @Transactional
    @Override
    public void remove(String manuInfoNo, String serviceNo) {
        repository.deleteByManuInfoNoWithServiceNo(manuInfoNo, serviceNo);
    }

}
