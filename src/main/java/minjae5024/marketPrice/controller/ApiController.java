package minjae5024.marketPrice.controller;

import minjae5024.marketPrice.dto.MarketDto;
import minjae5024.marketPrice.dto.PriceItemDto;
import minjae5024.marketPrice.repository.MarketRepository;
import minjae5024.marketPrice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final MarketRepository marketRepository;
    private final ProductService productService;

    @GetMapping("/markets")
    public ResponseEntity<List<MarketDto>> getAllMarkets() {
        return ResponseEntity.ok(
                marketRepository.findAll().stream()
                        .map(market -> new MarketDto(market.getId(), market.getMrktNm(), market.getLatitude(), market.getLongitude()))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/products")
    public ResponseEntity<List<PriceItemDto>> getProducts(
            @RequestParam("marketId") Long marketId,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "productName", required = false) String productName, 
            @RequestParam(value = "date", required = false) String date) {
        return ResponseEntity.ok(productService.getProducts(marketId, category, productName, date));
    }
}