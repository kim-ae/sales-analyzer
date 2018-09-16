package br.com.kimae.salesanalyzer.domain;


import static br.com.kimae.salesanalyzer.domain.RecordType.SALE;
import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor(access = PRIVATE)
@ToString
@Getter
public class SaleRecord implements Record {

    private final String saleId;
    private final List<SaleItem> items;
    private final String salesmanName;

    @Override
    public RecordType getType() {
        return SALE;
    }

    public Double totalPrice(){
        return items.stream()
            .map((i)->i.getQuantity()*i.getPrice())
            .reduce((a,b)->a+b)
            .orElse(null);
    }
}
