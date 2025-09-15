package minjae5024.marketPrice.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceApiResponseDto {
    private Map<String, GridResult> gridResult = new HashMap<>();

    @JsonAnySetter
    public void setGridResult(String key, GridResult value) {
        gridResult.put(key, value);
    }

    public GridResult getResultData() {
        return gridResult.values().stream().findFirst().orElse(null);
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GridResult {
        private List<PriceItem> row;
        private ResultInfo result;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResultInfo {
        private String code;
        private String message;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PriceItem {
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
}