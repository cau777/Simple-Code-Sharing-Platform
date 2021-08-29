package platform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.components.CodeComponent;
import platform.models.CodeSnippet;

import java.util.Map;
import java.util.NoSuchElementException;

@Controller
@ResponseBody
public class ApiController {

    private final CodeComponent codeComponent;

    public ApiController(CodeComponent codeComponent) {
        this.codeComponent = codeComponent;
    }

    @PostMapping(value = "/api/code/new", produces = {"application/json"})
    public Object postNewCode(@RequestBody Map<String, String> data) {
        String codeText = data.get("code");
        long viewSeconds = Long.parseLong(data.get("time"));
        long viewNumber = Long.parseLong(data.get("views"));

        CodeSnippet snippet = new CodeSnippet(codeText, viewSeconds, viewNumber);

        String id = codeComponent.add(snippet);
        return Map.of("id", String.valueOf(id));
    }

    @GetMapping(value = "/api/code/{id}", produces = {"application/json"})
    public Object getCode(@PathVariable String id) {
        try {
            CodeSnippet snippet = codeComponent.get(id);
            return snippet;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return e;
        }
    }

    @GetMapping(value = "/api/code/latest", produces = {"application/json"})
    public Object getLatestCodes() {
        return codeComponent.getLatest();
    }
}
