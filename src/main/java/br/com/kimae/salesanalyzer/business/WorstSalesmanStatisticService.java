package br.com.kimae.salesanalyzer.business;

import static br.com.kimae.salesanalyzer.dto.RecordType.SALE;
import static br.com.kimae.salesanalyzer.dto.RecordType.SALESMAN;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.kimae.salesanalyzer.dto.Record;
import br.com.kimae.salesanalyzer.dto.SaleItem;
import br.com.kimae.salesanalyzer.dto.SaleRecord;
import br.com.kimae.salesanalyzer.dto.SalesmanRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WorstSalesmanStatisticService implements StatisticService{

    @Override
    public Statistic calculate(final List<Record> records) {
        log.info("Starting statistics for worst salesman.");
        final List<SaleRecord> sales = records
            .stream()
            .filter((r)->SALE.equals(r.getType()))
            .map((r)->(SaleRecord)r)
            .collect(toList());

        final List<SalesmanRecord> salesmen = records
            .stream()
            .filter((r)->SALESMAN.equals(r.getType()))
            .map((r)->(SalesmanRecord)r)
            .collect(toList());

        final Map<String, Double> salesmanSaleMap = sales.stream()
            .collect(toMap(SaleRecord::getSalesmanName,
                (s)->calculateItems(s.getItems()),
                (a,b)->a+b));

        final Optional<Salesman> worstSalesman = salesmen.stream()
            .map((s)->from(s, salesmanSaleMap.get(s.getName())))
            .filter((s)->nonNull(s))
            .sorted()
            .findFirst();

        return new WorstSalesmanStatistic(worstSalesman.map(Salesman::getName).orElse(null));
    }

    private Salesman from(final SalesmanRecord salesmanRecord, final Double saleVAlue){
        if(isNull(saleVAlue)){
            return null;
        }
        final Double score = saleVAlue/salesmanRecord.getSalary();
        return new Salesman(salesmanRecord.getName(), score);
    }

    private Double calculateItems(List<SaleItem> items){
        return items.stream()
            .map((i)->i.getPrice()*i.getQuantity())
            .reduce((a,b)->a+b)
            .orElse(0d);
    }

    @Getter
    @AllArgsConstructor
    static class Salesman implements Comparable<Salesman>{
        private final String name;
        private final Double score;

        @Override
        public int compareTo(final Salesman o) {
            return this.score.compareTo(o.score);
        }
    }
}
