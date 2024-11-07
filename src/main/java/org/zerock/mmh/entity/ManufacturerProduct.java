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
@Table(name = "tbl_manufacturer_product")
public class ManufacturerProduct {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "org.zerock.mmh.generator.IdGenerator",
            parameters = {@Parameter(name = IdGenerator.METHOD, value = "SEQUENCE"),
                    @Parameter(name = IdGenerator.SEQUENCENAME, value = "manufacturer_product_seq"),
                    @Parameter(name = IdGenerator.PREFIX, value = "MP")})
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "manu_product_no")
    private String manuProductNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manuInfoNo")
    private ManufacturerInfo manufacturerInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productNo")
    private Product product;

    @Column(nullable = true, name = "manu_product_price")
    private int manuProductPrice;


}
