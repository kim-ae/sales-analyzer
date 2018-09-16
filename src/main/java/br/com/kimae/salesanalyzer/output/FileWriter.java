package br.com.kimae.salesanalyzer.output;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.kimae.salesanalyzer.business.Statistics;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileWriter implements Writer {

    private static final String DEFAULT_PATH = System.getProperty("user.home");
    private static final String DEFAULT_DATA_IN_PATH = "data" + File.separator + "out" + File.separator;
    private static final String DEFAULT_EXTENSION = ".done.dat";
    private static final String DEFAULT_CHARTSET = "UTF-8";

    @Value("${salesanalyzer.base.path:#{null}}")
    private Optional<String> basePath;

    @Value("${salesanalyzer.data.out.path:#{null}}")
    private Optional<String> dataInPath;


    @Override
    public void write(final List<Statistics> statistics) {
        String path = basePath.orElse(DEFAULT_PATH) + File.separator + dataInPath.orElse(DEFAULT_DATA_IN_PATH);
        log.info("Writing the path: {}", path);
        statistics.forEach((s) -> writeTo(s, path));
    }

    @SneakyThrows
    private void writeTo(Statistics statistics, final String path) {
        final String outputPath = path + statistics.getName() + DEFAULT_EXTENSION;
        try (PrintWriter output = new PrintWriter(outputPath, DEFAULT_CHARTSET)) {
            statistics.getStatistics().forEach((s) -> output.println(s.getText() + s.getValue()));
        }
    }

}
