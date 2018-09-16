package br.com.kimae.salesanalyzer.business;

import static br.com.kimae.salesanalyzer.dto.RecordType.CLIENT;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.kimae.salesanalyzer.dto.Record;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientsQuantityStatisticService implements StatisticService {

    @Override
    public Statistic calculate(final List<Record> records) {
        log.info("Starting clients quantity statistics");
        final Long clientQuantity = records.stream().filter((r)->CLIENT.equals(r.getType())).count();
        return new ClientsQuantityStatistic(clientQuantity.toString());
    }
}
