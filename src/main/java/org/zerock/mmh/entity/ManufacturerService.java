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
@ToString(exclude = {"manufacturerInfo","service"})
@Table(name = "tbl_manufacturer_service")
public class ManufacturerService {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "org.zerock.mmh.generator.IdGenerator",
            parameters = {@Parameter(name = IdGenerator.METHOD, value = "SEQUENCE"),
                    @Parameter(name = IdGenerator.SEQUENCENAME, value = "manufacturer_service_seq"),
                    @Parameter(name = IdGenerator.PREFIX, value = "MS")})
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "manu_service_no")
    private String manuServiceNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manuInfoNo")
    private ManufacturerInfo manufacturerInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="serviceNo")
    private mService service;

}
