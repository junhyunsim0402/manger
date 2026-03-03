package example.controller;

import example.model.dao.CategoryDao;
import example.model.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired CategoryDao categoryDao;

    @GetMapping("/category")
    public List<CategoryDto> findAll(){
        List<CategoryDto> result=categoryDao.findAll();
        return result;
    }

    @PostMapping("/category")
    public boolean add(@RequestBody CategoryDto categoryDto){
        boolean result=categoryDao.add(categoryDto);
        return result;
    }

    @PutMapping("/category")
    public boolean update(@RequestBody CategoryDto categoryDto){
        boolean result= categoryDao.update(categoryDto);
        return result;
    }
    @DeleteMapping("/category")
    public boolean delete(@RequestParam int dept_key){
        boolean result= categoryDao.delete(dept_key);
        return result;
    }

}
