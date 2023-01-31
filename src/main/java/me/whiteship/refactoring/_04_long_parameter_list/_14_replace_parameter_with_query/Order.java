package me.whiteship.refactoring._04_long_parameter_list._14_replace_parameter_with_query;

public class Order {

    /**
     * 매개변수를 질의 함수로 바꾸기
     * Replace Parameter with Query
     *
     * 함수의 매개변수 목록은 함수의 다양성을 대변하며, 짧을수록 이해하기 좋다
     * 어떤 한 매개변수를 다른 매개변수를 통해 알아낼 수 있다면 "중복 매개변수"라 생각할 수 있다
     * 매개변수에 값을 전달하는 것은 "함수를 호출하는 쪽"의 책임이다. 가능하면 함수를 호출하는 쪽의 책임을 줄이고 함수 내부에서 책임지도록 노력한다
     * "임시 변수를 질의 함수로 바꾸기"와 "함수 선언 변경하기"를 통해 이 리팩토링을 적용한다
     */

    private int quantity;

    private double itemPrice;

    public Order(int quantity, double itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public double finalPrice() {
        double basePrice = this.quantity * this.itemPrice;
//        int discountLevel = this.quantity > 100 ? 2 : 1;
//        int discountLevel = disCountLevel(); // 함수추출하기로 빼내었음
        return this.discountedPrice(basePrice);
    }

    private int disCountLevel() {
        return this.quantity > 100 ? 2 : 1;
    }

    /**
     * 기본코드의 int discountLevel은 함수를 호출하는쪽에 책임이였는데 discountLevel()로 함수 추출하기를 사용한 후
     * 함수자체에 책임으로 수정
     *
     * 전달해야할 파라미터도 하나 줄은것을 볼 수 있다
     */
//    private double discountedPrice(double basePrice, int discountLevel) {
    private double discountedPrice(double basePrice) {
//        return discountLevel == 2 ? basePrice * 0.90 : basePrice * 0.95;
        return disCountLevel() == 2 ? basePrice * 0.90 : basePrice * 0.95;
    }
}
