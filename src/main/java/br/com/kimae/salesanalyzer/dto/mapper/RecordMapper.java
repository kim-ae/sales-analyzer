package br.com.kimae.salesanalyzer.dto.mapper;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Optional;

import br.com.kimae.salesanalyzer.dto.Record;
import br.com.kimae.salesanalyzer.dto.RecordType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecordMapper implements Mapper {

    private static final Integer ID_SIZE = 3;

    @Override
    public Record map(final String line) {

        log.debug("Line {}",line);

        if (isBlank(line) || line.length() < ID_SIZE) {
            return null;
        }

        final String id = line.substring(0, ID_SIZE);
        final Optional<RecordType> type = RecordType.fromId(id);

        return type.map((t) -> t.map(line)).orElse(null);
    }
}
