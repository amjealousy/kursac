package org.main.dto;

import org.main.entity.Account;

import java.util.List;


public interface Mapper<T,F> {
    public T toDTO(F obj);
    public F toEntity(T mapper);
    public F fromList(List<String> list);

}
