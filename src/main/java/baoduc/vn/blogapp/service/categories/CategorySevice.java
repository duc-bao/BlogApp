package baoduc.vn.blogapp.service.categories;

import java.util.List;

import baoduc.vn.blogapp.playload.CategoriesDTO;

public interface CategorySevice {
    public CategoriesDTO addCategory(CategoriesDTO categoriesDTO);

    public CategoriesDTO getCategory(int idCategory);

    public List<CategoriesDTO> getAllCategory();

    public CategoriesDTO updateCat(CategoriesDTO categoriesDTO, int id);

    public void deleteCat(int id);
}
