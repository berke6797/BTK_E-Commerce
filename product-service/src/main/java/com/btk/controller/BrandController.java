package com.btk.controller;

import com.btk.entity.Brand;
import com.btk.service.BrandService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

import static com.btk.constant.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BRAND)
public class BrandController {

    private final BrandService brandService;

    @PostMapping(SAVE_BRAND)
    public ResponseEntity<Boolean> save(String brandName){
        return ResponseEntity.ok(brandService.save(brandName));
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<Brand>> getAll(){
        return ResponseEntity.ok(brandService.findAll());
    }
}
