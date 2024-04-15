package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> listAll();

    Optional<Category> addCategory(String name);

}
