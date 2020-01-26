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

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.Collections;
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
        ResponseEntity<ProductInput[]> response = restTemplate.getForEntity("http://10.177.68.16:8082/product/get", ProductInput[].class);
        List<ProductInput> productInputs1 = Arrays.asList(response.getBody());
        for (ProductInput x : productInputs1) {
            searchService.addProducts(x);
        }
    }

    @PostMapping(path = "/bySearch")
    public ResponseEntity<List<ProductDTO>> getProductsBySearch(@RequestBody InputDTO inputDTO) {
        System.out.println();
        List<ProductDTO> list = new LinkedList<ProductDTO>();
        System.out.println();
        String name = inputDTO.getInputData();
        for (Product p : searchService.searchIn(name)) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(p, productDTO);
            list.add(productDTO);
        }

        Collections.sort(list, (a, b) -> a.getPrice().compareTo(b.getPrice()));
        return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.CREATED);
    }

    @GetMapping(path = "/get/{hello}")
    public String hello(@PathVariable(name = "hello") String hello) {
        System.out.println(hello+"port:8002");
        return hello;
    }

}
