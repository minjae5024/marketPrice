package minjae5024.marketPrice.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseDto<T> {
    private Map<String, GridResult<T>> gridResult = new HashMap<>();

    @JsonAnySetter
    public void setGridResult(String key, GridResult<T> value) {
        gridResult.put(key, value);
    }

    public GridResult<T> getResultData() {
        return gridResult.values().stream().findFirst().orElse(null);
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GridResult<T> {
        private List<T> row;
        private ResultInfo result;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResultInfo {
        private String code;
        private String message;
    }
}