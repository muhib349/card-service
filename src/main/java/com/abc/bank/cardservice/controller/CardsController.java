package com.abc.bank.cardservice.controller;

import com.abc.bank.cardservice.config.CardServiceConfig;
import com.abc.bank.cardservice.model.Cards;
import com.abc.bank.cardservice.model.ConfigProperties;
import com.abc.bank.cardservice.model.Customer;
import com.abc.bank.cardservice.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardServiceConfig serviceConfig;
    @PostMapping("/myCards")
    public List<Cards> getCardDetails(@RequestBody Customer customer) {
        return cardsRepository.findByCustomerId(customer.getCustomerId());
    }

    @GetMapping("/cards/properties")
    public String getConfigProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        ConfigProperties properties = new ConfigProperties(
                serviceConfig.getMsg(),
                serviceConfig.getBuildVersion(),
                serviceConfig.getMailDetails(),
                serviceConfig.getActiveBranches()
        );
        return ow.writeValueAsString(properties);
    }
}
