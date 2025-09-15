package minjae5024.marketPrice.config;

import minjae5024.marketPrice.entity.Market;
import minjae5024.marketPrice.entity.ProductCode;
import minjae5024.marketPrice.repository.MarketRepository;
import minjae5024.marketPrice.repository.ProductCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final MarketRepository marketRepository;
    private final ProductCodeRepository productCodeRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeMarketData();
        initializeProductCodeData();
    }

    private void initializeMarketData() {
        if (marketRepository.count() == 0) {
            log.info("Initializing market data...");
            List<Market> marketsToSave = List.of(
                    new Market("0110253", "서울 양곡도매", 37.4586, 127.0366),
                    new Market("0210022", "부산 부전", 35.1583, 129.0596),
                    new Market("0220023", "대구 서문", 35.8718, 128.5759),
                    new Market("0240121", "광주 양동", 35.1544, 126.8913),
                    new Market("0250114", "대전 인동", 36.3213, 127.4419),
                    new Market("0210042", "부산 엄궁도매", 35.1323, 128.9772),
                    new Market("0220021", "대구 북부도매", 35.9238, 128.5348),
                    new Market("0250113", "대전 오정도매", 36.3562, 127.4093),
                    new Market("0110211", "서울 가락도매", 37.4947, 127.1143),
                    new Market("0240123", "광주 서부도매", 35.1539, 126.8623),
                    new Market("0250112", "대전 역전", 36.3316, 127.4341)
            );
            marketRepository.saveAll(marketsToSave);
            log.info("Saved {} markets to the database.", marketsToSave.size());
        }
    }

    private void initializeProductCodeData() {
        if (productCodeRepository.count() == 0) {
            log.info("Initializing product code data...");
            Map<String, String> productMap = Map.ofEntries(
                    Map.entry("111", "쌀"), Map.entry("112", "찹쌀"), Map.entry("141", "콩"), Map.entry("142", "팥"),
                    Map.entry("143", "녹두"), Map.entry("144", "메밀"), Map.entry("151", "고구마"), Map.entry("152", "감자"),
                    Map.entry("211", "배추"), Map.entry("212", "양배추"), Map.entry("213", "시금치"), Map.entry("214", "상추"),
                    Map.entry("215", "얼갈이배추"), Map.entry("221", "수박"), Map.entry("222", "참외"), Map.entry("223", "오이"),
                    Map.entry("224", "호박"), Map.entry("225", "토마토"), Map.entry("231", "무"), Map.entry("232", "당근"),
                    Map.entry("233", "열무"), Map.entry("241", "건고추"), Map.entry("242", "풋고추"), Map.entry("243", "붉은고추"),
                    Map.entry("245", "양파"), Map.entry("246", "파"), Map.entry("247", "생강"), Map.entry("252", "미나리"),
                    Map.entry("253", "깻잎"), Map.entry("255", "피망"), Map.entry("256", "파프리카"), Map.entry("257", "멜론"),
                    Map.entry("258", "깐마늘(국산)"), Map.entry("279", "알배기배추"), Map.entry("280", "브로콜리"),
                    Map.entry("312", "참깨"), Map.entry("313", "들깨"), Map.entry("314", "땅콩"), Map.entry("315", "느타리버섯"),
                    Map.entry("316", "팽이버섯"), Map.entry("317", "새송이버섯"), Map.entry("411", "사과"), Map.entry("412", "배"),
                    Map.entry("418", "바나나"), Map.entry("419", "참다래"), Map.entry("420", "파인애플"), Map.entry("421", "오렌지"),
                    Map.entry("422", "방울토마토"), Map.entry("424", "레몬"), Map.entry("425", "체리"), Map.entry("428", "망고"),
                    Map.entry("611", "고등어"), Map.entry("613", "갈치"), Map.entry("615", "명태"), Map.entry("616", "삼치"),
                    Map.entry("619", "물오징어"), Map.entry("638", "마른멸치"), Map.entry("639", "북어"), Map.entry("640", "마른오징어"),
                    Map.entry("641", "김"), Map.entry("642", "마른미역"), Map.entry("653", "전복"), Map.entry("654", "새우"),
                    Map.entry("658", "홍합"), Map.entry("660", "건다시마")
            );

            List<ProductCode> productCodes = productMap.entrySet().stream()
                    .map(entry -> new ProductCode(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());

            productCodeRepository.saveAll(productCodes);
            log.info("Saved {} product codes to the database.", productCodes.size());
        }
    }
}