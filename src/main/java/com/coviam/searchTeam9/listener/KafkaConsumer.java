package com.coviam.searchTeam9.listener;


import com.coviam.searchTeam9.document.Product;
import com.coviam.searchTeam9.dto.ProductInput;
import com.coviam.searchTeam9.service.SearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {
    @Autowired
    SearchService searchService;

    public KafkaConsumer() {
    }

    @KafkaListener(topics = "team9demo", groupId = "demo_team9", containerFactory = "userkafkaListenerContainerFactoryDemo")
    public void consumeUserJSON2(Product product) {
        ProductInput productInput = new ProductInput();
        BeanUtils.copyProperties(product, productInput);
        searchService.addProducts(productInput);
        System.out.println("consume Demo name ->" + product.toString());
    }
}