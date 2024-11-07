package org.zerock.mmh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionDTO {
    private String optionNo;
    private String option_name;
    //    private String option_thumbnail;
    private String option_desc;

}
