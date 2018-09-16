package br.com.kimae.salesanalyzer.dto;

import static br.com.kimae.salesanalyzer.dto.RecordType.CLIENT;
import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor(access = PRIVATE)
@ToString
@Getter
public class ClientRecord implements Record {

    private final String cnpj;
    private final String name;
    private final String businessArea;

    @Override
    public RecordType getType() {
        return CLIENT;
    }
}
