package br.com.kimae.salesanalyzer.dto;

import static br.com.kimae.salesanalyzer.dto.RecordType.SALESMAN;
import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor(access = PRIVATE)
@Getter
@ToString
public class SalesmanRecord implements Record{

    private final String name;
    private final String cpf;
    private final Double salary;

    @Override
    public RecordType getType() {
        return SALESMAN;
    }
}
