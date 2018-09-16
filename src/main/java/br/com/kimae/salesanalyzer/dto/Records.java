package br.com.kimae.salesanalyzer.dto;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@ToString
public class Records {

    private final String aggregationName;

    private final List<Record> records;

}
