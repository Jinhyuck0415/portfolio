package org.zerock.mmh.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.zerock.mmh.generator.IdGenerator;

@Entity
@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_service")
public class mService {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "org.zerock.mmh.generator.IdGenerator",
            parameters = {@Parameter(name = IdGenerator.METHOD, value = "SEQUENCE"),
                    @Parameter(name = IdGenerator.SEQUENCENAME, value = "service_seq"),
                    @Parameter(name = IdGenerator.PREFIX, value = "S")})
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "service_no")
    private String serviceNo;

    @Column(length = 20, nullable = false)
    private String service_name;

    @Column(length = 500, nullable = true)
    private String service_desc;

    public void changeName(String name) {
        this.service_name = name;
    }

    public void changeDesc(String desc) {
        this.service_desc = desc;
    }
}
