package com.wemakeprice.assignment.component;

import com.wemakeprice.assignment.model.ParserResult;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

@Slf4j
@Component
public class URLHTMLParser {

    public ParserResult parse(String urlLink, String type, int unit) {
        String content = getURLContents(urlLink);
        if(type.equals("remove")) content = removeTag(content);
        content = getNumbersAndEnglish(content);
        content = getCrossOutput(content);
        return getQuotientAndRemainder(content, unit);
    }

    public String getURLContents(String urlLink){
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(urlLink);
            URLConnection urlConnection = url.openConnection();
            if (urlConnection instanceof HttpURLConnection connection) {
                BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = buff.readLine()) != null) {
                    content.append(line);
                }
                buff.close();
            }
        } catch (IOException ex) {
            throw new RuntimeException("INVALID URL.");
        }
        return content.toString();
    }

    public String removeTag(String content) {
        return content.replaceAll("<[^>]*>", "");
    }

    public String getNumbersAndEnglish(String content) {
        return content.replaceAll("[^a-zA-Z0-9]", "");
    }

    public String[] getSortedEnglish(String content) {
        String engStr = content.replaceAll("[^a-zA-Z]", "");
        String[] engArr = engStr.split("");

        Arrays.sort(engArr, (o1, o2) -> {
            if (o1.compareToIgnoreCase(o2) == 0) return o1.compareTo(o2);
            else return o1.compareToIgnoreCase(o2);
        });
        return engArr;
    }

    public String[] getSortedNumbers(String content) {
        String numStr = content.replaceAll("[^0-9]", "");
        String[] numArr = numStr.split("");

        Arrays.sort(numArr);
        return numArr;
    }

    public String getCrossOutput(String content) {
        String[] engArr = getSortedEnglish(content);
        String[] numArr = getSortedNumbers(content);

        int engArrLen = engArr.length;
        int numArrLen = numArr.length;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.max(engArrLen, numArrLen); ++i) {
            if (i < engArrLen) sb.append(engArr[i]);
            if (i < numArrLen) sb.append(numArr[i]);
        }
        return sb.toString();
    }

    public ParserResult getQuotientAndRemainder(String content, int unit) {
        int contentLen = content.length();
        int remainder = contentLen % unit;
        return new ParserResult(content.substring(0, contentLen - remainder), content.substring(contentLen - remainder, contentLen));
    }
}
