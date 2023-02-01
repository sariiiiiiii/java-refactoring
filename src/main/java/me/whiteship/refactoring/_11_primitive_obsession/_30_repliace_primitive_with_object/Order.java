package me.whiteship.refactoring._11_primitive_obsession._30_repliace_primitive_with_object;

public class Order {

    /**
     * 기본형을 객체로 바꾸기
     * Replace Primitive with Object
     *
     * 개발 초기에는 기본형 (숫자 또는 문자열)으로 표현한 데이터가 나중에는 해당 데이터와 관련있는 다양한 기능을 필요로 하는 경우가 발생한다
     *   - 예) 문자열로 표현하던 전화번호의 지역 코드가 필요하거나 다양한 포맷을 지원하는 경우
     *   - 예) 숫자로 표현하던 온도의 단위 (화씨, 섭씨)를 변환하는 경우
     * 기본형을 사용한 데이터를 감싸 줄 클래스를 만들면, 필요한 기능을 추가할 수 있다
     */

//    private String priority; // 주문의 우선순위

    private Priority priority;


    // 그래서 Priority 클래스를 생성한것을 받도록 한다
    // 그러나 여기에도 오점이 있음 Priority도 문자를 받기 때문에 안전이 보장이 되지않음
    // 이럴때는 허용가능한 값들을 확인하고 설정하는 방법이 있다 (Prioirity 참조)
    public Order(Priority priority) {
        this.priority = priority;
    }


    // priority는 String이므로 아무값이나 들어올 수 있다 이런 경우를 type의 안전이 보장되지 않는다라고 한다
    public Order(String prioirtyValue) {
//        this.prioirtyValue = prioirtyValue;
        this(new Priority(prioirtyValue)); // 기존의 생성자를 유지하는 방법
    }

    public Priority getPriority() {
        return priority;
    }

}
