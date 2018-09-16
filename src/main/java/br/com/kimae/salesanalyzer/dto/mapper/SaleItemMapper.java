package br.com.kimae.salesanalyzer.dto.mapper;

import static br.com.kimae.salesanalyzer.dto.Record.ALL_REGEX;
import static br.com.kimae.salesanalyzer.dto.Record.COLUMN_SEPARATOR;
import static br.com.kimae.salesanalyzer.dto.Record.ID_REGEX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.kimae.salesanalyzer.dto.Record;
import br.com.kimae.salesanalyzer.dto.SaleItem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaleItemMapper {


    private static final String ITEM_SEPARATOR = "\\-";
    private static final Pattern PATTERN = Pattern.compile(ALL_REGEX + ITEM_SEPARATOR + ALL_REGEX + ITEM_SEPARATOR + ALL_REGEX);

    public SaleItemMapper() {
        log.debug("Pattern: {}", PATTERN.toString());
    }

    public SaleItem map(final String line) {
        Matcher matcher = PATTERN.matcher(line);
        SaleItem saleItem = null;
        if(matcher.matches()){
            saleItem = SaleItem.builder()
                .id(matcher.group(1))
                .quantity(Integer.valueOf(matcher.group(2)))
                .price(Double.valueOf(matcher.group(3)))
                .build();
        }
        return saleItem;
    }
}
