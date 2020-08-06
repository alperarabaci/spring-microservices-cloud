package app.wordyourself.msscbrewery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * alper - 06/08/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length=36, columnDefinition = "varchar", nullable = false, updatable = false)
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
    Long upc;
    BigDecimal price;

    Integer minOnHand;
    Integer quantityToBrew;
}
