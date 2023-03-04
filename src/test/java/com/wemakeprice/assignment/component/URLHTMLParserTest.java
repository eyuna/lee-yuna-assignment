package com.wemakeprice.assignment.component;

import com.wemakeprice.assignment.model.ParserResult;
import com.wemakeprice.assignment.model.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class URLHTMLParserTest {

    URLHTMLParser parser;

    String urlLink;

    Type optionId;

    int unit;

    String content;

    @BeforeEach
    void setUp() {
        parser = new URLHTMLParser();
        urlLink = "https://front.wemakeprice.com/main";
        optionId = Type.REMOVE;
        content = "aA25100-0ZJa!2";
    }

    @Test
    void 파싱_결과값은_영문자와_숫자만_포함한다() {
        unit = 1000;
        ParserResult res = parser.parse(urlLink, optionId, unit);
        assertThat(res.getQuotient(), matchesPattern("^[a-zA-Z0-9]*$"));
        assertThat(res.getRemainder(), matchesPattern("^[a-zA-Z0-9]*$"));
    }

    @Test
    void 숫자만_오름차순으로_리턴한다() {
        String res = parser.getSortedNumbers(content);
        assertThat(res, is("0001225"));
        assertThat(res, matchesPattern("^[0-9]*$"));
    }

    @Test
    void 영문자만_오름차순으로_리턴한다() {
        String res = parser.getSortedEnglish(content);
        assertThat(res, is("AaaJZ"));
        assertThat(res, matchesPattern("^[a-zA-Z]*$"));
    }

    @Test
    void 묶음단위에_의해_몫과_나머지로_나뉘어진다() {
        unit = 2;
        ParserResult res = parser.getQuotientAndRemainder(content, unit);
        assertThat(res.getQuotient().length(), is((content.length()/unit)*unit));
        assertThat(res.getRemainder().length(), is(content.length()%unit));
    }

    @Test
    void 유효하지_않은_URL은_파싱하지않는다() {
        urlLink += "abdsfdff";
        assertThrows(RuntimeException.class, () -> parser.getURLContents(urlLink));
    }
}
