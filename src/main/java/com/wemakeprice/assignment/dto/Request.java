package com.wemakeprice.assignment.dto;

import com.wemakeprice.assignment.model.Type;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Request {

    @NotBlank(message = "URL 은 필수 입력 값입니다.")
    private String urlLink;

    private Type optionId;

    @NotNull(message = "출력 단위 묶음은 필수 입력 값입니다.")
    @Min(value=1, message="1 이상의 값을 입력해주세요.")
    private Integer unit;

    @Override
    public String toString() {
        return "Request{" +
                "urlLink='" + urlLink + '\'' +
                ", optionId='" + optionId + '\'' +
                ", unit=" + unit +
                '}';
    }
}
