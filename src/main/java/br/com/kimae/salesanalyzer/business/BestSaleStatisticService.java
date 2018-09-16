package br.com.kimae.salesanalyzer.business;

import static br.com.kimae.salesanalyzer.dto.RecordType.SALE;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.kimae.salesanalyzer.dto.Record;
import br.com.kimae.salesanalyzer.dto.SaleRecord;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BestSaleStatisticService implements StatisticService {

    @Override
    public Statistic calculate(final List<Record> records) {
        log.info("Starting best sale statistic");

        final List<SaleRecord> sales = records.stream()
            .filter((r)->SALE.equals(r.getType()))
            .map((r)->(SaleRecord)r)
            .collect(toList());

        Comparator<SaleRecord> saleCompator = Comparator.comparing(SaleRecord::totalPrice);

        Collections.sort(sales, saleCompator.reversed());

        String value = null;

        if(isFalse(sales.isEmpty())){
            value = sales.get(0).getSaleId();
        }

        return new BestSaleStatistic(value);
    }

}
