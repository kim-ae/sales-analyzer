package br.com.kimae.salesanalyzer.business;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientsQuantityStatistic implements Statistic {

    private static final String DEFAULT_TEXT = "Clients quantity: ";

    private final String value;

    public String getText(){
        return DEFAULT_TEXT;
    }
}
