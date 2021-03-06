package br.com.kimae.salesanalyzer.dto.mapper;

import static br.com.kimae.salesanalyzer.dto.Regex.ALL_REGEX;
import static br.com.kimae.salesanalyzer.dto.Regex.COLUMN_SEPARATOR;
import static br.com.kimae.salesanalyzer.dto.Regex.CPF_REGEX;
import static br.com.kimae.salesanalyzer.dto.Regex.ID_REGEX;
import static br.com.kimae.salesanalyzer.dto.Regex.MONEY_REGEX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.kimae.salesanalyzer.dto.Record;
import br.com.kimae.salesanalyzer.dto.SalesmanRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SalesmanMapper implements Mapper {

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
