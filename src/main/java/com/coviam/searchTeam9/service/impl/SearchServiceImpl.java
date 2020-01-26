package com.coviam.searchTeam9.service.impl;

import com.coviam.searchTeam9.document.Product;
import com.coviam.searchTeam9.dto.ProductInput;
import com.coviam.searchTeam9.repository.SearchRepository;
import com.coviam.searchTeam9.service.SearchService;
import com.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchRepository searchRepository;

    @Resource
    SolrTemplate solrTemplate;
//
//    @Override
//    public List<Product> searchByProductName(String productName) {
//        return searchRepository.findByProductName(productName);
//    }

    //    public List<Product> searchByCategory(String category){
//        return searchRepository.findByCategory(category);
//    }
//
//    public List<Product> searchByDescription(String description){
//        return searchRepository.findByName(description);
//    }
    public void addProducts(ProductInput productInput) {
        Product product = new Product();
        product.setProductId(productInput.getProductId());
        product.setProductName(productInput.getProductName());
        product.setDescription(productInput.getDescription());
        StringJoiner str = new StringJoiner(", ");
        for (Map.Entry<String, String> entry : productInput.getAttributes().entrySet()) {
            str.add(entry.getKey() + " " + entry.getValue());
        }
        product.setSearchFields(str.toString());
        product.setCategoryName(productInput.getCategoryName());
        product.setProductRating(String.valueOf(productInput.getProductRating()));
        product.setPrice(String.valueOf(productInput.getPrice()));
        product.setUrl1(productInput.getUrl1());
        product.setUrl2(productInput.getUrl2());
        product.setUrl3(productInput.getUrl3());
        searchRepository.save(product);

    }

//    @Override
//    public List<Product> searchByCategory(String categoryName) {
//        return searchRepository.findByCategoryName(categoryName);
//    }

    public List<Product> sortByPrice(){
        Iterable<Product> iterable=searchRepository.findAll(Sort.by(Sort.Direction.ASC,"price"));
        ArrayList<Product> arrayList=new ArrayList<>();
        if(iterable != null) {
            for(Product x: iterable) {
                arrayList.add(x);
            }
        }
        return arrayList;
    }

    public List<Product> sortByRating(){
        Iterable<Product> iterable=searchRepository.findAll(Sort.by(Sort.Direction.DESC,"productRating"));
        ArrayList<Product> arrayList=new ArrayList<>();
        if(iterable != null) {
            for(Product x: iterable) {
                arrayList.add(x);
            }
        }
        return arrayList;
    }

    public List<Product> searchIn(String searchTerm) {

        SimpleQuery simpleQuery = new SimpleQuery(searchTerm);

        simpleQuery.setRequestHandler("/browse");

        List<Product> results= solrTemplate.query("search",simpleQuery, Product.class).getContent();

        return results;
    }

//    private Criteria createSearchConditions(String[] words) {
//        Criteria conditions = null;
//
//        for (String word: words) {
//            if (conditions == null) {
//                conditions = new Criteria("productName").contains(word)
//                        .or(new Criteria("description").contains(word));
//            }
//            else {
//                conditions = conditions.or(new Criteria("productName").contains(word))
//                        .or(new Criteria("description").contains(word));
//            }
//        }
//
//        return conditions;
//    }


//    @Override
//    public List<Product> searchByCustomQuery(String searchTerm) {
//        return searchRepository.findByCustomQuery(searchTerm);
//    }

}
