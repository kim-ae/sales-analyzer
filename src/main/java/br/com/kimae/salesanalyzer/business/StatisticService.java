package br.com.kimae.salesanalyzer.business;

import java.util.List;

import br.com.kimae.salesanalyzer.dto.Record;

public interface StatisticService {

    Statistic calculate(final List<Record> records);
}
