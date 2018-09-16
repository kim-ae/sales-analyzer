package br.com.kimae.salesanalyzer.business;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor(access = PRIVATE)
@Builder
@Getter
@ToString
public class Statistics {

    private final String name;
    private final List<Statistic> statistics;

}
