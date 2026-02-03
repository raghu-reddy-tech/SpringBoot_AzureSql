package com.cloudshop.service;

import com.cloudshop.persistance.DTO.OrderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Service
public class OrderFunctionClient {

    private final RestTemplate restTemplate = new RestTemplate();
    Logger logger = LogManager.getLogger(OrderFunctionClient.class);

    @Value("${azure.function.url}")
    private String functionUrl;

    @Value("${azure.function.key}")
    private String functionKey;

    public void triggerFunctionAppOrderProcessing(OrderDTO orderDTO) {

        URI uri = UriComponentsBuilder
                .fromUriString(functionUrl)
                .queryParam("orderId", orderDTO.getId())
                .queryParam("status", orderDTO.getStatus())
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
       // headers.add("x-functions-key", functionKey);

        HttpEntity<OrderDTO> entity = new HttpEntity<>(orderDTO, headers);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(uri, entity, String.class);
        logger.info("response body "+stringResponseEntity.getBody());
    }
}

