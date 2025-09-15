package minjae5024.marketPrice.repository;

import minjae5024.marketPrice.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<Post> search(Long marketId, String searchType, String keyword, Pageable pageable);
}
