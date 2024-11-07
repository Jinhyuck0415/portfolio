package org.zerock.mmh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.mmh.entity.ManufacturerInfo;
import org.zerock.mmh.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ManufacturerProductDTO {
    private String manuProductNo;
    private ManufacturerInfo manufacturerInfo;
    private Product product;

    @Builder.Default
    private List<OptionDTO> optionDTOList = new ArrayList<>();
}
