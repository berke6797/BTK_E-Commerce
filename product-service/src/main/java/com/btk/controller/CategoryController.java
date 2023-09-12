package com.btk.controller;

import com.btk.dto.request.CategoryUpdateRequestDto;
import com.btk.entity.Category;
import com.btk.service.CategoryService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.List;

import static com.btk.constant.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CATEGORY)
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(SAVE_CATEGORY)
    public ResponseEntity<Boolean> save(String categoryName, String token) {
        return ResponseEntity.ok(categoryService.save(categoryName, token));
    }

    @PutMapping(UPDATE_CATEGORY + "/{token}")
    public ResponseEntity<Boolean> updateCategory(@RequestBody @Valid CategoryUpdateRequestDto dto, @PathVariable String token) {
        return ResponseEntity.ok(categoryService.updateCategory(dto, token));
    }


    @GetMapping(GET_ALL)
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }
}
