package minjae5024.marketPrice.repository;

import minjae5024.marketPrice.entity.ProductCode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductCodeRepository extends JpaRepository<ProductCode, String> {
    List<ProductCode> findByPrdlstNmContaining(String productName);
}