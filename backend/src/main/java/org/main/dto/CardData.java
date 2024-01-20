package org.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.main.entity.Card;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardData implements Mapper<CardData, Card>{
    public String number;
    public String account;
    public Double balance;
    public String code;
    public Instant creating;
    public Instant closing;
    @Override
    public CardData toDTO(Card obj) {
        return CardData.builder().number(obj.getNumber()).account(String.valueOf(obj.getAccount().getAcc_number()))
                .balance(obj.getBalance()).code(obj.getCode()).creating(obj.getCreation_date()).closing(obj.getClosing_date())
                .build();
    }

    @Override
    public Card toEntity(CardData mapper) {
        return null;
    }

    @Override
    public Card fromList(List<String> list) {
        return null;
    }
}
