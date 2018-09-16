package br.com.kimae.salesanalyzer.domain;

import static br.com.kimae.salesanalyzer.domain.RecordType.CLIENT;
import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor(access = PRIVATE)
@ToString
public class ClientRecord implements Record {

    private final String cnpj;
    private final String name;
    private final String businessArea;

    @Override
    public RecordType getType() {
        return CLIENT;
    }
}
