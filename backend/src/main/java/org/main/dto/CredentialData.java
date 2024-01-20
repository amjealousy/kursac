package org.main.dto;

import org.main.entity.Credential;

import java.util.List;

public record CredentialData() implements Mapper<CredentialData, Credential>{

    @Override
    public CredentialData toDTO(Credential obj) {

        return null;
    }

    @Override
    public Credential toEntity(CredentialData mapper) {
        return null;
    }

    @Override
    public Credential fromList(List<String> list) {
        return null;
    }
}
