package mk.ukim.finki.library.service.implementation;

import mk.ukim.finki.library.model.Category;
import mk.ukim.finki.library.repository.CategoryRepository;
import mk.ukim.finki.library.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> addCategory(String name) {
        Category country = new Category(name);

        return Optional.of(categoryRepository.save(country));
    }
}
