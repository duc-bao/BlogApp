package baoduc.vn.blogapp.service.categories;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baoduc.vn.blogapp.dao.CategoriesRepository;
import baoduc.vn.blogapp.entity.Category;
import baoduc.vn.blogapp.exception.ResourceNotFoundException;
import baoduc.vn.blogapp.playload.CategoriesDTO;

@Service
public class CategoryServiceImpl implements CategorySevice {

    private CategoriesRepository categoriesRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoriesRepository categoriesRepository, ModelMapper modelMapper) {
        this.categoriesRepository = categoriesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoriesDTO addCategory(CategoriesDTO categoriesDTO) {

        Category category = modelMapper.map(categoriesDTO, Category.class);
        Category categoriesDTO1 = categoriesRepository.save(category);
        return modelMapper.map(categoriesDTO1, CategoriesDTO.class);
    }

    @Override
    public CategoriesDTO getCategory(int idCategory) {
        Category category = categoriesRepository
                .findById(idCategory)
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", idCategory));
        return modelMapper.map(category, CategoriesDTO.class);
    }

    @Override
    public List<CategoriesDTO> getAllCategory() {
        List<Category> categories = categoriesRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoriesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoriesDTO updateCat(CategoriesDTO categoriesDTO, int id) {
        Category category = categoriesRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        category.setName(categoriesDTO.getName());
        category.setDescription(categoriesDTO.getDescription());
        categoriesRepository.save(category);
        return modelMapper.map(category, CategoriesDTO.class);
    }

    @Override
    public void deleteCat(int id) {
        Category category = categoriesRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        categoriesRepository.delete(category);
    }
}
