package br.com.kimae.salesanalyzer.dto;

public interface Record {

    String COLUMN_SEPARATOR = "ç";
    String ID_REGEX = "([0-9]{3})";
    String ALL_REGEX = "(.+)";
    RecordType getType();
}
