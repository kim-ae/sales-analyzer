package br.com.kimae.salesanalyzer.dto.mapper;

import static br.com.kimae.salesanalyzer.dto.Regex.COLUMN_SEPARATOR;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.kimae.salesanalyzer.dto.ClientRecord;
import br.com.kimae.salesanalyzer.dto.Record;
import br.com.kimae.salesanalyzer.dto.SaleRecord;
import br.com.kimae.salesanalyzer.dto.SalesmanRecord;

@RunWith(MockitoJUnitRunner.class)
public class RecordMapperTest {

    private static final String CLIENT_ID = "002";
    private static final String SALESMAN_ID = "001";
    private static final String SALE_ID = "003";

    private final RecordMapper mapper = new RecordMapper();

    @Test
    public void client_ok(){
        final String line = CLIENT_ID + COLUMN_SEPARATOR + randomNumeric(14) + COLUMN_SEPARATOR +  randomAlphabetic(50) + COLUMN_SEPARATOR + randomAlphabetic(100);
        final Record record = mapper.map(line);

        assertTrue(record instanceof ClientRecord);
    }

    @Test
    public void salesman_ok(){
        final String line = SALESMAN_ID + COLUMN_SEPARATOR + randomNumeric(11) + COLUMN_SEPARATOR +  randomAlphabetic(50) + COLUMN_SEPARATOR + randomNumeric(4) + "." + randomNumeric(2);
        final Record record = mapper.map(line);

        assertTrue(record instanceof SalesmanRecord);
    }

    @Test
    public void sale_ok(){
        final String line = SALE_ID + COLUMN_SEPARATOR + randomNumeric(6) + COLUMN_SEPARATOR +  "["+ getItem()+"]" + COLUMN_SEPARATOR + randomAlphabetic(50);
        final Record record = mapper.map(line);

        assertTrue(record instanceof SaleRecord);
    }

    @Test
    public void invalid_id(){
        final String line = "010"+ COLUMN_SEPARATOR + randomNumeric(6) + COLUMN_SEPARATOR +  "["+ getItem()+"]" + COLUMN_SEPARATOR + randomAlphabetic(50);
        final Record record = mapper.map(line);

        assertNull(record);
    }

    private String getItem(){
        return randomNumeric(6) + "-" + randomNumeric(9) + "-"+randomNumeric(9) + "."+randomNumeric(2);
    }
}
