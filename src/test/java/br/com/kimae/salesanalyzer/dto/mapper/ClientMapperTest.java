package br.com.kimae.salesanalyzer.dto.mapper;

import static br.com.kimae.salesanalyzer.dto.RecordType.CLIENT;
import static br.com.kimae.salesanalyzer.dto.Regex.COLUMN_SEPARATOR;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.kimae.salesanalyzer.dto.ClientRecord;
import br.com.kimae.salesanalyzer.dto.RecordType;

@RunWith(MockitoJUnitRunner.class)
public class ClientMapperTest {

    private static final String CLINET_ID = "002";

    private final ClientMapper mapper = new ClientMapper();

    private String cnpj;
    private String name;
    private String businessArea;

    @Before
    public void setup(){
        cnpj = randomNumeric(14);
        name = randomAlphabetic(50);
        businessArea = randomAlphabetic(100);
    }

    @Test
    public void map_ok(){
        final String line = CLINET_ID + COLUMN_SEPARATOR + cnpj + COLUMN_SEPARATOR + name + COLUMN_SEPARATOR + businessArea;

        ClientRecord record = (ClientRecord) mapper.map(line);

        assertNotNull(record);
        assertEquals(cnpj, record.getCnpj());
        assertEquals(name, record.getName());
        assertEquals(CLIENT, record.getType());
        assertEquals(businessArea, record.getBusinessArea());
    }

    @Test
    public void invalid_line1(){
        final String line = CLINET_ID + COLUMN_SEPARATOR + cnpj + name + COLUMN_SEPARATOR + businessArea;

        ClientRecord record = (ClientRecord) mapper.map(line);

        assertNull(record);
    }

    @Test
    public void invalid_line_no_business_area(){
        final String line = CLINET_ID + COLUMN_SEPARATOR + cnpj + COLUMN_SEPARATOR + name + COLUMN_SEPARATOR;

        ClientRecord record = (ClientRecord) mapper.map(line);

        assertNull(record);
    }

    @Test
    public void invalid_line_no_CNPJ(){
        final String line = CLINET_ID + COLUMN_SEPARATOR + COLUMN_SEPARATOR + name + COLUMN_SEPARATOR + businessArea;

        ClientRecord record = (ClientRecord) mapper.map(line);

        assertNull(record);
    }

    @Test
    public void invalid_line_no_name(){
        final String line = CLINET_ID + COLUMN_SEPARATOR + cnpj + COLUMN_SEPARATOR  + COLUMN_SEPARATOR + businessArea;

        ClientRecord record = (ClientRecord) mapper.map(line);

        assertNull(record);
    }

    @Test
    public void invalid_line_incorrect_cnpj(){
        final String line = CLINET_ID + COLUMN_SEPARATOR + cnpj + "0" + COLUMN_SEPARATOR  + COLUMN_SEPARATOR + businessArea;

        ClientRecord record = (ClientRecord) mapper.map(line);

        assertNull(record);
    }
}
