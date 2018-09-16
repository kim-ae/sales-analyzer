package br.com.kimae.salesanalyzer.domain.mapper;

import br.com.kimae.salesanalyzer.domain.Record;

public interface Mapper  {

    Record map(String line);
}
