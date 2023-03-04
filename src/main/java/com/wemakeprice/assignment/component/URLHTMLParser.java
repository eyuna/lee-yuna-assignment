package com.wemakeprice.assignment.component;

import com.wemakeprice.assignment.model.ParserResult;
import com.wemakeprice.assignment.model.Type;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class URLHTMLParser {

    public ParserResult parse(String urlLink, Type type, int unit) {
        String content = getURLContents(urlLink);
        if(type == Type.REMOVE) content = removeTag(content);
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

    public String getSortedEnglish(String content) {
        String engStr = content.replaceAll("[^a-zA-Z]", "");
        String[] engArr = engStr.split("");

        return Arrays
                .stream(engArr).sorted((o1, o2) -> {
                    if (o1.compareToIgnoreCase(o2) == 0) return o1.compareTo(o2);
                    else return o1.compareToIgnoreCase(o2);})
                .collect(Collectors.joining());
    }

    public String getSortedNumbers(String content) {
        String numStr = content.replaceAll("[^0-9]", "");
        String[] numArr = numStr.split("");

        return Arrays.stream(numArr).sorted().collect(Collectors.joining());
    }

    public String getCrossOutput(String content) {
        String engStr = getSortedEnglish(content);
        String numStr = getSortedNumbers(content);

        int engStrLen = engStr.length();
        int numStrLen = numStr.length();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.max(engStrLen, numStrLen); ++i) {
            if (i < engStrLen) sb.append(engStr.charAt(i));
            if (i < numStrLen) sb.append(numStr.charAt(i));
        }
        return sb.toString();
    }

    public ParserResult getQuotientAndRemainder(String content, int unit) {
        int contentLen = content.length();
        int remainder = contentLen % unit;
        return new ParserResult(content.substring(0, contentLen - remainder), content.substring(contentLen - remainder, contentLen));
    }
}
