package platform.repositories;

import org.springframework.data.repository.CrudRepository;
import platform.models.CodeSnippet;

import java.util.List;

public interface CodeSnippetsRepository extends CrudRepository<CodeSnippet, String> {
    List<CodeSnippet> findTop10ByTimeLimitAndViewsLeftOrderByUploadDateDesc(Long timeLimit, Long restrictedViews);
}
