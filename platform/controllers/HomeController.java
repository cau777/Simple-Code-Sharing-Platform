package platform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.components.CodeComponent;
import platform.models.CodeSnippet;

import java.util.NoSuchElementException;

@Controller
public class HomeController {
    private final CodeComponent codeComponent;

    public HomeController(CodeComponent codeComponent) {
        this.codeComponent = codeComponent;
    }

    @GetMapping(value = "/code/{id}", produces = {"text/html"})
    public String codeById(@PathVariable String id, Model model) {
        try {
            CodeSnippet snippet = codeComponent.get(id);
            model.addAttribute("code", snippet);
            return "code";
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/code/new", produces = {"text/html"})
    public String newCode(Model model) {
        return "newCode";
    }

    @GetMapping(value = "code/latest", produces = {"text/html"})
    public String latestCodes(Model model) {
        model.addAttribute("codes", codeComponent.getLatest());
        return "latestCodes";
    }
}
