package br.com.kimae.salesanalyzer.business;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SalesmanQuantityStatistic implements Statistic {

    private static final String DEFAULT_TEXT = "Quantity of salesman: ";

    private final String value;

    public String getText(){
        return DEFAULT_TEXT;
    }
}
