package br.com.kimae.salesanalyzer.dto.mapper;

import static br.com.kimae.salesanalyzer.dto.RecordType.SALESMAN;
import static br.com.kimae.salesanalyzer.dto.Regex.COLUMN_SEPARATOR;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.kimae.salesanalyzer.dto.SalesmanRecord;

@RunWith(MockitoJUnitRunner.class)
public class SalesmanMapperTest {

    private static final String SALESMAN_ID = "001";

    private final SalesmanMapper mapper = new SalesmanMapper();

    private String cpf;
    private String name;
    private String salary;

    @Before
    public void setup() {
        cpf = randomNumeric(11);
        name = randomAlphabetic(50);
        salary = randomNumeric(4) + "." + randomNumeric(2);
    }

    @Test
    public void map_ok() {
        final String line = SALESMAN_ID + COLUMN_SEPARATOR + cpf + COLUMN_SEPARATOR + name + COLUMN_SEPARATOR + salary;
        SalesmanRecord record = (SalesmanRecord) mapper.map(line);

        assertNotNull(record);
        assertEquals(cpf, record.getCpf());
        assertEquals(name, record.getName());
        assertEquals(SALESMAN, record.getType());
        assertEquals(Double.valueOf(salary), record.getSalary());
    }

    @Test
    public void invalid_line1() {
        final String line = SALESMAN_ID + COLUMN_SEPARATOR + cpf + name + COLUMN_SEPARATOR + salary;

        SalesmanRecord record = (SalesmanRecord) mapper.map(line);

        assertNull(record);
    }

    @Test
    public void invalid_line_no_salary() {
        final String line = SALESMAN_ID + COLUMN_SEPARATOR + cpf + COLUMN_SEPARATOR + name + COLUMN_SEPARATOR;

        SalesmanRecord record = (SalesmanRecord) mapper.map(line);

        assertNull(record);
    }

    @Test
    public void invalid_line_no_cpf() {
        final String line = SALESMAN_ID + COLUMN_SEPARATOR + COLUMN_SEPARATOR + name + COLUMN_SEPARATOR + salary;

        SalesmanRecord record = (SalesmanRecord) mapper.map(line);

        assertNull(record);
    }

    @Test
    public void invalid_line_no_name() {
        final String line = SALESMAN_ID + COLUMN_SEPARATOR + cpf + COLUMN_SEPARATOR + COLUMN_SEPARATOR + salary;

        SalesmanRecord record = (SalesmanRecord) mapper.map(line);

        assertNull(record);
    }

    @Test
    public void invalid_line_incorrect_cpf() {
        final String line = SALESMAN_ID + COLUMN_SEPARATOR + cpf + "0" + COLUMN_SEPARATOR + COLUMN_SEPARATOR + salary;

        SalesmanRecord record = (SalesmanRecord) mapper.map(line);

        assertNull(record);
    }
}
