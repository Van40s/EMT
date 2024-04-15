package mk.ukim.finki.library.web;

import mk.ukim.finki.library.model.Category;
import mk.ukim.finki.library.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/categories")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public List<Category> getCategories() {
        return categoryService.listAll();
    }

    @GetMapping
    public ResponseEntity<Category> addCountry(@RequestParam String name){
        if(name == null){
            return ResponseEntity.badRequest().build();
        }

        return this.categoryService.addCategory(name)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}