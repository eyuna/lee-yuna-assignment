# 코딩 과제 (서버 부분)
> 주문/결제/정산 백엔드 개발 지원자 이유나

<br>

Dependencies
------------

- Java 17
- Spring Boot 3.1.0
- Lombok 1.18.26
- Thymeleaf 3.1.1.RELEASE

<br>

기능 요구 사항
------------

#### 1. 모든 문자 입력 가능

 - 메서드: getURLContents(String urlLink)
 - 설명: 입력받은 URL 의 HTML Text 를 전부 가져옵니다. 
 - StringBuilder 를 사용해서 객체 생성을 줄였고, 유효하지 않은 URL 이 입력되면 Exception 을 던집니다.
 
#### 2. 영어 및 숫자만 출력

 - 메서드: String getNumbersAndEnglish(String content)
 - 설명: 전달받은 String 의 영문자와 숫자 이외의 값을 제거합니다.
 - 정규표현식으로 String 을 필터링하였습니다.
 
#### 3. 오름차순 출력

 - 메서드: String getSortedEnglish(String content), getSortedNumbers(String content)
 - 설명: 각각 영문자, 숫자를 필터링하고 오름차순으로 정렬합니다. 
 - 영문자의 대소문자 정렬은 요구사항에 맞게 comparator 를 생성하여 Stream 을 사용해서 정렬하였습니다. 내부적으로 TimSort 를 사용해서 시간복잡도는 worst 인 경우에도 O(nlogn) 입니다.

#### 4. 영어 숫자 Mix

 - 메서드: getCrossOutput(String content)
 - 설명: 영문자와 숫자가 교차된 String 을 리턴합니다.
 - 전달받은 String 을 getSortedEnglish, getSortedNumbers 메서드를 사용해서 영문자와 숫자 String으로 분리한 뒤, 교차로 StringBuilder 에 append 합니다. 
 
#### 5. 출력 묶음 단위

 - 메서드: getQuotientAndRemainder(String content, int unit)
 - 설명: 전달받은 String 을 출력 묶음 단위로 나누어서 ParserResult 로 리턴합니다.

<br>

기타
------------
- port: 8080(default)
