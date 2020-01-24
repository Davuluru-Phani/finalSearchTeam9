package com.coviam.searchTeam9.repository;

import com.coviam.searchTeam9.document.Product;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends SolrCrudRepository<Product,String> {
//    public List<Product> findByName(String name);
//    public List<Product> findByCategory(String category);
//    public List<Product> findByDescription(String description);

//    public List<Product> findByName(String name);
//    public List<Product> findByCategory(String category);

//    @Query("name:*?0*")
//    public List<Product> findByProductName(String name);
//    @Query("name:?0*")
//    public List<Product> findByCustomQuery(String searchTerm);
//    @Query("categoryName:*?0*")
//    public List<Product> findByCategoryName(String category);
}
