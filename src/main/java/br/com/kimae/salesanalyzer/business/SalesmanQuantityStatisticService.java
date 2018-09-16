package br.com.kimae.salesanalyzer.business;

import static br.com.kimae.salesanalyzer.dto.RecordType.SALESMAN;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.kimae.salesanalyzer.dto.Record;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SalesmanQuantityStatisticService implements StatisticService{

    @Override
    public Statistic calculate(final List<Record> records) {
        log.info("Starting salesman quantity statistics");
        final Long salesmanQuantity = records.stream()
            .filter((r)->SALESMAN.equals(r.getType()))
            .count();
        return new SalesmanQuantityStatistic(salesmanQuantity.toString());
    }
}
