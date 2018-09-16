package br.com.kimae.salesanalyzer;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.kimae.salesanalyzer.business.CalculateStatistics;
import br.com.kimae.salesanalyzer.business.Statistics;
import br.com.kimae.salesanalyzer.domain.Records;
import br.com.kimae.salesanalyzer.input.Reader;
import br.com.kimae.salesanalyzer.output.Writer;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class SalesAnalyzerApplication {

    @Autowired
    @Qualifier("fileReader")
    private Reader reader;

    @Autowired
    @Qualifier("fileWriter")
    private Writer writer;

    @Autowired
    private CalculateStatistics calculateStatistics;

    public static void main(String[] args) {
        SpringApplication.run(SalesAnalyzerApplication.class, args);
    }

    @Scheduled(fixedDelay = 10000L)
    void readDirectory(){
        log.info("Starting job");

        List<Records> records = reader.read();

        log.debug("Records: {}", records);

        List<Statistics> statistics = records.stream()
            .map(calculateStatistics::calculate)
            .collect(toList());

        log.debug("Statistics: {}", statistics);

        writer.write(statistics);
        log.info("Ending Job");
    }

}
