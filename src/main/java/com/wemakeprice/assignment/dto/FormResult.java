package com.wemakeprice.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FormResult {

    private String quotient;

    private String remainder;

    @Override
    public String toString() {
        return "FormResult{" +
                "quotient='" + quotient + '\'' +
                ", remainder='" + remainder + '\'' +
                '}';
    }
}
