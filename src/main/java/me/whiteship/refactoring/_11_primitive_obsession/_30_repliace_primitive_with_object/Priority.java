package me.whiteship.refactoring._11_primitive_obsession._30_repliace_primitive_with_object;

import java.util.List;

public class Priority {

    /**
     * Order에서 primitive type인 priority를 클래스로 생성
     *
     * 이렇게 했을 경우 해당 로직들도 클래스 내부에서 활용할 수 있기 때문에 유지보수 하고 해당 기능들을 아 이 객체의 이런기능들이구나 라는것을 한눈에 보기 좋음
     */

    private String value;

    // 허용가능한 값들을 확인
    private List<String> legalValues = List.of("low", "normal", "high", "rush");

    public Priority(String value) {
        if (legalValues.contains(value)) {
            this.value = value; // 허용가능한 값 설정
        } else {
            throw new IllegalArgumentException("illegal value for priority " + value);
        }
    }

    @Override
    public String toString() {
        return this.value;
    }

    private int index() {
        return this.legalValues.indexOf(this.value);
    }

    public boolean higherThan(Priority other) {
        return this.index() > other.index();
    }

}
