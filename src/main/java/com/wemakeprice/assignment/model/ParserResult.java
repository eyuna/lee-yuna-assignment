package com.wemakeprice.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ParserResult {

    private String quotient;

    private String remainder;

    @Override
    public String toString() {
        return "ParserResult{" +
                "quotient='" + quotient + '\'' +
                ", remainder='" + remainder + '\'' +
                '}';
    }
}
