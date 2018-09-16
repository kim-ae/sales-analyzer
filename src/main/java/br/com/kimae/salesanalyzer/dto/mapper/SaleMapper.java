package br.com.kimae.salesanalyzer.dto.mapper;

import static br.com.kimae.salesanalyzer.dto.Regex.ALL_REGEX;
import static br.com.kimae.salesanalyzer.dto.Regex.COLUMN_SEPARATOR;
import static br.com.kimae.salesanalyzer.dto.Regex.ID_REGEX;
import static br.com.kimae.salesanalyzer.dto.Regex.ITEMS_REGEX;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.kimae.salesanalyzer.dto.Record;
import br.com.kimae.salesanalyzer.dto.SaleItem;
import br.com.kimae.salesanalyzer.dto.SaleRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaleMapper implements Mapper {

    private static final String ITEMS_SEPARATOR = ",";
    private static final Pattern PATTERN = Pattern.compile(
        ID_REGEX + COLUMN_SEPARATOR + ALL_REGEX + COLUMN_SEPARATOR + ITEMS_REGEX + COLUMN_SEPARATOR + ALL_REGEX);

    private final SaleItemMapper saleItemMapper = new SaleItemMapper();

    public SaleMapper() {
        log.debug("Pattern: {}", PATTERN.toString());
    }

    @Override
    public Record map(final String line) {
        log.debug("Trying to map {} with SaleMapper", line);

        Matcher matcher = PATTERN.matcher(line);
        Record record = null;

        if (matcher.matches()) {
            record = SaleRecord.builder()
                .saleId(matcher.group(2))
                .items(mapItems(matcher.group(3)))
                .salesmanName(matcher.group(4))
                .build();
        }

        return record;
    }

    private List<SaleItem> mapItems(String items) {
        return Arrays.stream(items.split(ITEMS_SEPARATOR))
            .map(saleItemMapper::map)
            .collect(toList());
    }
}
