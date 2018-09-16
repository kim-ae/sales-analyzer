package br.com.kimae.salesanalyzer.dto;

public interface Regex {

    String COLUMN_SEPARATOR = "ç";
    String ID_REGEX = "([0-9]{3})";
    String ALL_REGEX = "(.+)";
    String CNPJ_REGEX = "(\\d{2}\\.?\\d{3}\\.?\\d{3}\\/?\\d{4}\\-?\\d{2})";
    String ITEMS_REGEX = "\\[(.*)\\]";
    String CPF_REGEX = "(\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2})";
    String MONEY_REGEX = "(\\d+\\.?\\d{2})";

}
