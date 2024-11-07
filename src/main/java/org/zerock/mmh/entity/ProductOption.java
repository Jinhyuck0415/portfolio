package org.zerock.mmh.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.zerock.mmh.generator.IdGenerator;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_product_option")
public class ProductOption {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "org.zerock.mmh.generator.IdGenerator",
            parameters = {@Parameter(name = IdGenerator.METHOD, value = "SEQUENCE"),
                    @Parameter(name = IdGenerator.SEQUENCENAME, value = "product_option_seq"),
                    @Parameter(name = IdGenerator.PREFIX, value = "PO")})
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "pro_option_no")
    private String proOptionNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manuProductNo")
    private ManufacturerProduct manufacturerProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "optionNo")
    private Option option;

}
