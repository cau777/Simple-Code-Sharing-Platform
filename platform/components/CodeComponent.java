package platform.components;

import org.springframework.stereotype.Component;
import platform.models.CodeSnippet;
import platform.repositories.CodeSnippetsRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class CodeComponent {
    private final CodeSnippetsRepository repository;

    public CodeComponent(CodeSnippetsRepository repository) {
        this.repository = repository;
    }

    public String add(CodeSnippet snippet) {
        return repository.save(snippet).getId();
    }

    public CodeSnippet get(String index) throws NoSuchElementException {
        CodeSnippet snippet = repository.findById(index).orElseThrow();

        if (snippet.isRestrictedTime() && snippet.getSecondsLeft() == 0) {
            repository.delete(snippet);
            throw new NoSuchElementException();
        }

        if (snippet.isRestrictedViews()) {
            snippet.decreaseView();

            if (snippet.getViewsLeft() <= 0) {
                repository.delete(snippet);
            } else {
                repository.save(snippet);
            }
        }

        return snippet;
    }

    public List<CodeSnippet> getLatest() {
        return repository.findTop10ByTimeLimitAndViewsLeftOrderByUploadDateDesc(null, null);
    }

    public void remove(CodeSnippet snippet) {
        repository.delete(snippet);
    }

    public void save(CodeSnippet snippet) {
        repository.save(snippet);
    }
}
