package com.juanc.services;

import com.juanc.models.Card;
import com.juanc.repositories.ICardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl {
    @Autowired
    private final ICardRepository iCardRepository;
    public Card saveCard(Card card){
        return this.iCardRepository.save(card);
    }
    public List<Card> readCards(){
        return this.iCardRepository.findAll();
    }
    public void deleteCardById(Long id){
        this.iCardRepository.deleteById(id);
    }
}
