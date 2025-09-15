package minjae5024.marketPrice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductCode {

    @Id
    private String prdlstCd; 

    private String prdlstNm; 

    public ProductCode(String prdlstCd, String prdlstNm) {
        this.prdlstCd = prdlstCd;
        this.prdlstNm = prdlstNm;
    }
}