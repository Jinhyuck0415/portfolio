package org.zerock.mmh.service;

import org.zerock.mmh.dto.ServiceDTO;
import org.zerock.mmh.entity.mService;

import java.util.List;

public interface ManufacturerServiceService {

    //등록
    String register(String manuInfoNo, String serviceNo);

    //삭제
    void remove(String manuInfoNo, String serviceNo);


}
