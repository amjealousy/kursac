package org.main.dto;

import lombok.*;
import org.main.entity.Account;
import org.main.entity.Credential;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountData implements Mapper<AccountData, Account>{
    public Integer id;
    public String uuid;
    public String type;
    public String currencies;
    public String client;

    @Override
    public AccountData toDTO(Account obj) {
        String concat = obj.getClient().getFirst_name() +" "+ obj.getClient().getLast_name() +" "+  obj.getClient().getPatronymic_name();
        return AccountData.builder().
                    uuid(obj.getAcc_number().toString()).
                    type(obj.getType_of_account().name()).currencies(obj.getCurrencies()).client(concat).id(obj.getClient().getId()).
                    build();

    }

    @Override
    public Account toEntity(AccountData mapper) {

        return null;
    }

    @Override
    public Account fromList(List<String> list) {
        return null;
    }


}
