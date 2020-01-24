package com.coviam.searchTeam9.controller;

import com.coviam.searchTeam9.document.InputDTO;
import com.coviam.searchTeam9.document.Product;
import com.coviam.searchTeam9.dto.ProductDTO;
import com.coviam.searchTeam9.dto.ProductInput;
import com.coviam.searchTeam9.service.SearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "*")
public class searchController {

    @Autowired
    SearchService searchService;

    @PostMapping(path = "/addProduct")
    public void addProduct(/*@RequestBody  List<ProductInput> productInputs*/) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ProductInput[]> response = restTemplate.getForEntity("http://10.177.68.150:8082/product/get", ProductInput[].class);
        List<ProductInput> productInputs1 = Arrays.asList(response.getBody());
        for (ProductInput x : productInputs1) {
            searchService.addProducts(x);
        }
    }

    @GetMapping(path = "/bySearchURL/{name}")
    public ResponseEntity<List<ProductDTO>> getProductsBySearchURL(@PathVariable String name) {
        List<ProductDTO> list = new LinkedList<ProductDTO>();
        for (Product p : searchService.searchIn(name)) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(p, productDTO);
            list.add(productDTO);
        }
        return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.CREATED);
    }

    @PostMapping(path = "/bySearch")
    public ResponseEntity<List<ProductDTO>> getProductsBySearch(@RequestBody InputDTO inputDTO) {
        List<ProductDTO> list = new LinkedList<ProductDTO>();
        String name=inputDTO.getInputData();
        for (Product p : searchService.searchIn(name)) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(p, productDTO);
            list.add(productDTO);
        }
        return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.CREATED);
    }


}
