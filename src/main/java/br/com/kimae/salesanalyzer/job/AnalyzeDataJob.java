package br.com.kimae.salesanalyzer.job;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.kimae.salesanalyzer.business.CalculateStatistics;
import br.com.kimae.salesanalyzer.business.Statistics;
import br.com.kimae.salesanalyzer.dto.Records;
import br.com.kimae.salesanalyzer.input.Reader;
import br.com.kimae.salesanalyzer.output.Writer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AnalyzeDataJob {

    @Autowired
    @Qualifier("fileReader")
    private Reader reader;

    @Autowired
    @Qualifier("fileWriter")
    private Writer writer;

    @Autowired
    private CalculateStatistics calculateStatistics;


    @Scheduled(fixedDelay = 10000L)
    void analyze(){
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
