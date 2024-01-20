package org.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.main.entity.Account;
import org.main.entity.Credit;

import java.util.List;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditData implements Mapper<CreditData, Credit>{
    private UUID key_id;
    private UUID accountCredit;
    private Integer type;
    private Double value;



    @Override
    public CreditData toDTO(Credit obj) {
        return CreditData.builder().key_id(obj.getKey_id()).accountCredit(obj.getAccountCredit().getAcc_number())
                .type(obj.getType().getId()).value(obj.getValue())
                .build();
    }

    @Override
    public Credit toEntity(CreditData mapper) {
        return null;
    }

    @Override
    public Credit fromList(List<String> list) {
        return null;
    }
}
