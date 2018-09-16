package br.com.kimae.salesanalyzer.input;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.kimae.salesanalyzer.dto.Record;
import br.com.kimae.salesanalyzer.dto.Records;
import br.com.kimae.salesanalyzer.dto.mapper.Mapper;
import br.com.kimae.salesanalyzer.dto.mapper.RecordMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileReader implements Reader {

    private static final String DEFAULT_PATH = System.getProperty("user.home");
    private static final String DEFAULT_DATA_IN_PATH = "data" + File.separator + "in" + File.separator;
    private static final String DEFAILT_EXTENSION = ".dat";
    private static final String DEFAULT_DATA_READ_PATH = "read" + File.separator;

    @Value("${salesanalyzer.base.path:#{null}}")
    private Optional<String> basePath;

    @Value("${salesanalyzer.data.in.path:#{null}}")
    private Optional<String> dataInPath;

    private final Mapper mapper = new RecordMapper();

    @Override
    @SneakyThrows //TODO: TREAT EXCEPTONS
    public List<Records> read() {
        String path = basePath.orElse(DEFAULT_PATH) + File.separator + dataInPath.orElse(DEFAULT_DATA_IN_PATH);
        log.info("Reading the path: {}", path);
        List<Records> records;
        try (Stream<Path> paths = Files.walk(Paths.get(path),1)) {
            records = paths
                .peek((p)-> log.debug(p.toString()))
                .filter(Files::isRegularFile)
                .filter((p)->p.toString().endsWith(DEFAILT_EXTENSION))
                .map(this::readLines)
                .collect(toList());
        }

        try (Stream<Path> paths = Files.walk(Paths.get(path),1)) {
            paths.filter(Files::isRegularFile)
                .filter((p)->p.toString().endsWith(DEFAILT_EXTENSION))
                .peek((p)-> log.debug("moving: {}", p.toString()))
                .forEach((p)->move(p, path));
        }

        return records;
    }

    private void move(Path filePath, String basePath){
        final Path newPath = Paths.get(basePath+DEFAULT_DATA_READ_PATH+filePath.getFileName().toString());
        try{
            Files.move(filePath, newPath);
        } catch (IOException ex){
            log.warn("Problem moving file {}",filePath.toString());
        }

    }

    @SneakyThrows
    private Records readLines(Path path){
        final String fileName = getFileName(path);

        log.info("Reading file: {}", fileName);
        List<Record> records;

        try (Stream<String> stream = Files.lines(path)) {
            records = stream.map((l)->mapper.map(l)).filter((r)->nonNull(r)).collect(toList());
        }

        return Records.builder()
            .aggregationName(fileName)
            .records(records)
            .build();
    }

    private String getFileName(Path filePath){
        final String fileName = filePath.toFile().getName();
        return fileName.substring(0, fileName.indexOf("."));
    }
}
