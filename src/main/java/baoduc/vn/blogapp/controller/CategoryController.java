package baoduc.vn.blogapp.controller;

import baoduc.vn.blogapp.playload.CategoriesDTO;
import baoduc.vn.blogapp.service.categories.CategorySevice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategorySevice categorySevice;
    @Autowired
    public CategoryController(CategorySevice categorySevice) {
        this.categorySevice = categorySevice;
    }
    @PostMapping
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CategoriesDTO> addCategory(@RequestBody CategoriesDTO categoriesDTO){
        CategoriesDTO  categoriesDTO1 = categorySevice.addCategory(categoriesDTO);
        return new ResponseEntity<>(categoriesDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriesDTO> getCategoryId(@PathVariable int id){
        CategoriesDTO categoriesDTO = categorySevice.getCategory(id);
        return new ResponseEntity<>(categoriesDTO,HttpStatus.OK);
    }
    @GetMapping
    public List<CategoriesDTO> getAllCategory(){
        List<CategoriesDTO> categoriesDTOS = categorySevice.getAllCategory();
        return categoriesDTOS;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CategoriesDTO> updateCategori(@PathVariable int id, @Valid @RequestBody CategoriesDTO categoriesDTO){
        CategoriesDTO categoriesDTO1 = categorySevice.updateCat(categoriesDTO, id);
        return new ResponseEntity<>(categoriesDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCat(@PathVariable int id){
        categorySevice.deleteCat(id);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }
}
