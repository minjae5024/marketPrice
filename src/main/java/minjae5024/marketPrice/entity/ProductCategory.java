package minjae5024.marketPrice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductCategory {
    FOOD_CROPS("100", "식량작물"),
    VEGETABLES("200", "채소류"),
    FRUITS("400", "과일류"),
    LIVESTOCK("500", "축산물"),
    FISHERY("600", "수산물");

    private final String apiCode;
    private final String description;
}