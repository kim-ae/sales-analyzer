package br.com.kimae.salesanalyzer.domain.mapper;

import static br.com.kimae.salesanalyzer.domain.Record.ALL_REGEX;
import static br.com.kimae.salesanalyzer.domain.Record.COLUMN_SEPARATOR;
import static br.com.kimae.salesanalyzer.domain.Record.ID_REGEX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.kimae.salesanalyzer.domain.Record;
import br.com.kimae.salesanalyzer.domain.SalesmanRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SalesmanMapper implements Mapper {

    private static final String CPF_REGEX = "(\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2})";
    private static final String MONEY_REGEX = "(\\d+\\.?\\d{2})";
    private static final Pattern PATTERN = Pattern.compile(
        ID_REGEX + COLUMN_SEPARATOR + CPF_REGEX + COLUMN_SEPARATOR + ALL_REGEX + COLUMN_SEPARATOR + MONEY_REGEX);

    public SalesmanMapper(){
        log.debug("Pattern: {}",PATTERN.toString());
    }

    @Override
    public Record map(final String line) {
        log.debug("Trying to map {} with SalesmanMapper",line);
        Matcher matcher = PATTERN.matcher(line);
        Record record = null;

        if(matcher.matches()){
            record = SalesmanRecord.builder()
                .cpf(matcher.group(2))
                .name(matcher.group(3))
                .salary(Double.valueOf(matcher.group(4)))
                .build();
        }else{
            log.info("Error on line: {}", line);
        }

        return record;
    }
}
