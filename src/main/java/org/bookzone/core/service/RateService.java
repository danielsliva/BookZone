package org.bookzone.core.service;

import org.bookzone.core.model.Comment;
import org.bookzone.core.model.Rate;
import org.bookzone.core.repository.CommentRepository;
import org.bookzone.core.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RateService {

    @Autowired
    private RateRepository repository;

    public Rate save(Rate rate){
        return repository.save(rate);
    }

    public List<Rate> findAllRates(){
        List<Rate> rates = new ArrayList<>();
        for(Rate rate: repository.findAll()){
            rates.add(rate);
        }
        return rates;
    }
}
