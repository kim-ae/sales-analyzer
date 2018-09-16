package br.com.kimae.salesanalyzer.domain;


import static lombok.AccessLevel.PRIVATE;

import java.util.Arrays;
import java.util.Optional;

import br.com.kimae.salesanalyzer.domain.mapper.ClientMapper;
import br.com.kimae.salesanalyzer.domain.mapper.Mapper;
import br.com.kimae.salesanalyzer.domain.mapper.SaleMapper;
import br.com.kimae.salesanalyzer.domain.mapper.SalesmanMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = PRIVATE)
public enum RecordType {
    CLIENT("002", new ClientMapper()),
    SALE("003", new SaleMapper()),
    SALESMAN("001", new SalesmanMapper());

    private final String id;
    private final Mapper mapper;

    public static Optional<RecordType> fromId(String id) {
        return Arrays.stream(RecordType.values())
            .filter((rt) -> rt.id.equals(id))
            .findFirst();
    }

    public Record map(String line){
        return mapper.map(line);
    }
}
