package br.com.kimae.salesanalyzer.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class WorstSalesmanStatistic implements Statistic {

    private static final String DEFAULT_TEXT = "The worst salesman: ";

    private final String value;

    public String getText(){
        return DEFAULT_TEXT;
    }

}
