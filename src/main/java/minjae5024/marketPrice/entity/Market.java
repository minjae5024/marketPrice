package minjae5024.marketPrice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String mrktCd;

    @Column(nullable = false)
    private String mrktNm;

    private Double latitude;

    private Double longitude;

    public Market(String mrktCd, String mrktNm, Double latitude, Double longitude) {
        this.mrktCd = mrktCd;
        this.mrktNm = mrktNm;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}