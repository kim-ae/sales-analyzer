package br.com.kimae.salesanalyzer.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kimae.salesanalyzer.domain.Records;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CalculateStatistics {

    @Autowired
    private List<StatisticService> statisticServices;

    public Statistics calculate(Records records){
        List<Statistic> statistics = statisticServices.stream()
            .map((s)->s.calculate(records.getRecords()))
            .collect(Collectors.toList());

        return Statistics.builder()
            .name(records.getAggregationName())
            .statistics(statistics)
            .build();
    }
}
