package br.com.kimae.salesanalyzer.job;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.kimae.salesanalyzer.business.CalculateStatistics;
import br.com.kimae.salesanalyzer.business.Statistic;
import br.com.kimae.salesanalyzer.business.Statistics;
import br.com.kimae.salesanalyzer.dto.Records;
import br.com.kimae.salesanalyzer.input.Reader;
import br.com.kimae.salesanalyzer.output.Writer;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzeDataJobTest {

    @InjectMocks
    private AnalyzeDataJob job;

    @Mock
    private Reader reader;

    @Mock
    private Writer writer;

    @Mock
    private CalculateStatistics calculateStatistics;

    @Test
    public void analyze(){
        List<Records> records = Arrays.asList(Records.builder().build());
        when(reader.read()).thenReturn(records);
        Statistics statistics = Statistics.builder().build();
        when(calculateStatistics.calculate(records.get(0))).thenReturn(statistics);

        job.analyze();

        verify(reader).read();
        verify(calculateStatistics).calculate(records.get(0));
        ArgumentCaptor<List> listArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(writer).write(listArgumentCaptor.capture());

        List<Statistics> statisticsList = listArgumentCaptor.getValue();

        assertEquals(statistics, statisticsList.get(0));
    }

}
