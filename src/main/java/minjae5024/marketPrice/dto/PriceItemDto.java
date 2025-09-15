package minjae5024.marketPrice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceItemDto {
    @JsonProperty("PRDLST_NM")
    private String prdlstNm;
    @JsonProperty("SPCIES_NM")
    private String spciesNm;
    @JsonProperty("GRAD_NM")
    private String gradNm;
    @JsonProperty("EXAMIN_UNIT")
    private String examinUnit;
    @JsonProperty("AMT")
    private String amt;
}