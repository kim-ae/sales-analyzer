package br.com.kimae.salesanalyzer.input;

import java.util.List;

import br.com.kimae.salesanalyzer.dto.Records;

public interface Reader {
    List<Records> read();
}
