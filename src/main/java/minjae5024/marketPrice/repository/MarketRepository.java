package minjae5024.marketPrice.repository;

import minjae5024.marketPrice.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MarketRepository extends JpaRepository<Market, Long> {
    Optional<Market> findById(Long id);
}