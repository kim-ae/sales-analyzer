package br.com.kimae.salesanalyzer.output;

import java.util.List;

import br.com.kimae.salesanalyzer.business.Statistics;

public interface Writer {

    void write(List<Statistics> statistics);
}
