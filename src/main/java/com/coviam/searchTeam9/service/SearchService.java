package com.coviam.searchTeam9.service;

import com.coviam.searchTeam9.document.Product;
import com.coviam.searchTeam9.dto.ProductInput;
//import com.coviam.searchTeam9.dto.ProductDTO;

import java.util.List;

public interface SearchService {
//    public List<Product> searchByName(String name);
//    public List<Product> searchByCategory(String category);
//    public List<Product> searchByDescription(String description);
//    public List<Product> searchByProductName(String name);
    public void addProducts(ProductInput productInput);
    public List<Product> searchIn(String searchTerm) ;
//    public List<Product> searchByCategory(String categoryName) ;
//    public List<Product> searchByCustomQuery(String searchTerm) ;
}
