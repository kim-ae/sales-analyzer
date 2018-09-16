package br.com.kimae.salesanalyzer.business;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BestSaleStatistic implements Statistic {

    private static final String DEFAULT_TEXT = "The best sale: ";

    private final String value;

    public String getText(){
        return DEFAULT_TEXT;
    }
}
