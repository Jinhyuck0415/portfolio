package org.zerock.mmh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ManufacturerInfoDTO {
    private String manuInfoNo;
    private String manu_info_name;
    private String manu_info_site;
    private String manuMemNo;

    //서비스를 출력하기 위함.
    @Builder.Default
    private List<ServiceDTO> serviceDTOList = new ArrayList<>();

}
