package me.whiteship.refactoring._06_mutable_data._23_change_reference_to_value;

import java.util.Objects;

public class TelephoneNumber {

    /**
     * 현재 TelephoneNumber class의 코드는 record TelephoneNumber(String areaCode, String number)의 코드임 record로 바꾸고 다 지우면 똑같음
     */

    /**
     * TelephoneNumber를 값 객체로 setter를 삭제해주고 생성자를 생성해주고 필드는 final로
     * 주의해야할 점 : equals와 hashcode를 만들어줘야됨
     */

    private final String areaCode;

    private final String number;

    public TelephoneNumber(String areaCode, String number) {
        this.areaCode = areaCode;
        this.number = number;
    }

    /**
     * 같은값을 가지고 있는 객체라면 같은 value라는것을 검증
     * equals를 구현을 안하면 단순히 ==로 구분하는것이기 때문에 equals를 구현해주자
     * hashcode같은 경우는 equals와 같이 구현해야 하는 함수중 하나 why ? collection hash를 검증하는 set같은곳에 들어갈때는 값이 같으면 같아야되고 값이 다르면 달라야 되기 때문에 hashcode도 구현
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TelephoneNumber that)) return false;
        return Objects.equals(areaCode, that.areaCode) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, number);
    }

    public String areaCode() {
        return areaCode;
    }

//    public void areaCode(String areaCode) {
//        this.areaCode = areaCode;
//    }

    public String number() {
        return number;
    }

//    public void number(String number) {
//        this.number = number;
//    }
}
