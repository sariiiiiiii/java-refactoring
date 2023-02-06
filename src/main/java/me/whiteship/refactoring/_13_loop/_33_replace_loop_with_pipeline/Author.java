package me.whiteship.refactoring._13_loop._33_replace_loop_with_pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Author {

    private String company;

    private String twitterHandle;

    public Author(String company, String twitterHandle) {
        this.company = company;
        this.twitterHandle = twitterHandle;
    }

    static public List<String> TwitterHandles(List<Author> authors, String company) {

        /**
         * 이러한 로직은 전체적으로 봤을 때 오퍼레이션 위주로 보기가 힘듬 (전체코드를 다 읽지 않는 이상)
         */

        var result = new ArrayList<String> ();
        for (Author a : authors) {
            if (a.company.equals(company)) { // stream().filter()로 변경
                var handle = a.twitterHandle; // stream().map()으로 문자열 변경
                if (handle != null) // stream().filter()로 null 체크
                    result.add(handle); // 종료 오퍼레이션 collect(Collectors.toList()) 이용
            }
        }

        return authors.stream()
                .filter(author -> author.company.equals(company)) // if문을 stream().filter(Predicate<>)로 변경
                .map(author -> author.twitterHandle) // stream().map()을 이용해서 author -> author.twitterHandle로 변경
//                .filter(twitterHandle -> twitterHandle != null)
                .filter(Objects::nonNull) // 메소드 레퍼런스 이용, map()으로 변경해온 문자열 twitterHandle이 null이 아니라면
                .collect(Collectors.toList());// 종료 오퍼레이션


    }

}
