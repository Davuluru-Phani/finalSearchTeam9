package com.coviam.searchTeam9.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

//import javax.persistence.Id;

@Getter
@Setter
@SolrDocument(collection = "search")
public class Product {

    @Id
    @Indexed(name = "productId", type = "string")
    private String productId;


    @Indexed(name = "productName", type = "string")
    private String productName;

    @Indexed(name = "description", type = "string")
    private String description;

    @Indexed(name = "searchFields", type = "String")
    private String searchFields;

    @Indexed(name = "categoryName", type = "string")
    private String categoryName;

    @Indexed(name = "productRating", type = "string")
    private String productRating;

    @Indexed(name = "price", type = "string")
    private String price;

    @Indexed(name = "url1", type = "string")
    private String url1;

    @Indexed(name = "url2", type = "string")
    private String url2;

    @Indexed(name = "url3", type = "string")
    private String url3;

    //q
    //q cache-search
    //fq
    //filter and search , min max fq sq
//    private Map<String, String> attributes; parse

}
