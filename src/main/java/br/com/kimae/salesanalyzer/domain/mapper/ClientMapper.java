package br.com.kimae.salesanalyzer.domain.mapper;

import static br.com.kimae.salesanalyzer.domain.Record.ALL_REGEX;
import static br.com.kimae.salesanalyzer.domain.Record.COLUMN_SEPARATOR;
import static br.com.kimae.salesanalyzer.domain.Record.ID_REGEX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.kimae.salesanalyzer.domain.ClientRecord;
import br.com.kimae.salesanalyzer.domain.Record;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientMapper implements Mapper {

    private static final String CNPJ_REGEX = "(\\d{2}\\.?\\d{3}\\.?\\d{3}\\/?\\d{4}\\-?\\d{2})";
    private static final Pattern CLIENT_PATTERN = Pattern.compile(
        ID_REGEX + COLUMN_SEPARATOR + CNPJ_REGEX + COLUMN_SEPARATOR + ALL_REGEX + COLUMN_SEPARATOR + ALL_REGEX);

    public ClientMapper(){
        log.debug("Pattern: {}", CLIENT_PATTERN.toString());
    }

    @Override
    public Record map(final String line) {
        log.debug("Trying to map {} with ClientMapper", line);
        Matcher clientMatcher = CLIENT_PATTERN.matcher(line);
        Record record = null;

        if (clientMatcher.matches()) {
            record = ClientRecord.builder()
                .cnpj(clientMatcher.group(2))
                .name(clientMatcher.group(3))
                .businessArea(clientMatcher.group(4))
                .build();
        } else {
            log.info("Error on line: {}", line);
        }

        return record;
    }
}
