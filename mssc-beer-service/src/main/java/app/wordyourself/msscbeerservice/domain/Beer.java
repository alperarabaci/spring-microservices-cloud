package app.wordyourself.msscbeerservice.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * alper - 05/08/2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length=36, columnDefinition = "varchar(36)", nullable = false, updatable = false)
    UUID id;
    @Version
    Long version;

    @CreationTimestamp
    @Column(updatable = false)
    Timestamp createdDate;
    @UpdateTimestamp
    Timestamp lastModifiedDate;
    String beerName;
    String beerStyle;
    @Column(unique = true)
    String upc;
    BigDecimal price;

    Integer minOnHand;
    Integer quantityToBrew;

}
