package br.com.kimae.salesanalyzer.dto;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor(access = PRIVATE)
@Getter
@ToString
public class SaleItem {

    private final String id;
    private final Integer quantity;
    private final Double price;

}
