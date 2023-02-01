package me.whiteship.refactoring._06_mutable_data._21_replace_derived_variable_with_query;

public class Discount {

    // 기존의 데이터에서 파생되어 온 데이터라는것을 알수 있음 this.baseTotal - this.discount
    // 차라리 해당 변수를 제거하고 getDiscountedTotal()에서 this.baseTotal - this.discount를 해주자
    private double discountedTotal;
    private double discount;

    private double baseTotal;

    public Discount(double baseTotal) {
        this.baseTotal = baseTotal;
    }

    public double getDiscountedTotal() {
//        return this.discountedTotal;
//        return this.baseTotal - this.discount; // 이런식으로 기존 필드의 값을 계산해서 return값을 보낼수 있고
        return calculatedDiscountedTotal(); // 메소드를 만들어서 활용할 수 있음
    }

    private double calculatedDiscountedTotal() {
        return this.baseTotal - this.discount;
    }

    public void setDiscount(double number) {
        this.discount = number;
//        this.discountedTotal = this.baseTotal - this.discount;
    }
}
