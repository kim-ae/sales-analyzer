package br.com.kimae.salesanalyzer.dto.mapper;

import br.com.kimae.salesanalyzer.dto.Record;

public interface Mapper  {

    Record map(String line);
}
