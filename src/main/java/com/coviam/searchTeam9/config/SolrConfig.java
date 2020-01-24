//package com.coviam.searchTeam9.config;
//
//import org.apache.solr.client.solrj.SolrClient;
//import org.apache.solr.client.solrj.impl.CloudSolrClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.solr.core.SolrTemplate;
//import org.springframework.data.solr.repository.config.EnableSolrRepositories;
//import java.util.Collections;
//@Configuration
//@EnableSolrRepositories(basePackages = "com.coviam.searchTeam9.repository")
//@ComponentScan
//public class SolrConfig {
//    @Value("${spring.data.solr.host}") String solrURL;
//    @Bean
//    public CloudSolrClient solrClient() {
//        return new CloudSolrClient.Builder(Collections.singletonList(solrURL)).build();
//    }
//    @Bean
//    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
//        return new SolrTemplate(client);
//    }
//}