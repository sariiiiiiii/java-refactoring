package me.whiteship.refactoring._06_mutable_data._21_replace_derived_variable_with_query;

import java.util.ArrayList;
import java.util.List;

public class ProductionPlan {

//    private double production;
    private List<Double> adjustments = new ArrayList<>();

    // production 필드는 adjustments의 합계를 구하는 것이기 때문에 this.production += adjustment 처럼 미리 계산을 할 필요가 없다
    public void applyAdjustment(double adjustment) {
        this.adjustments.add(adjustment);
//        this.production += adjustment;
    }

    public double getProduction() {
//        assert this.production == calculatedProduction();
//        return calculatedProduction();
        return this.adjustments.stream().reduce((double) 0, Double::sum);
    }

    // stream()을 이용한 합계 구하기식
    private double calculatedProduction() {
//        return this.adjustments.stream().reduce(0, (a, b) -> a + b);
        return this.adjustments.stream().mapToDouble(Double::valueOf).sum();
//        return this.adjustments.stream().reduce((double) 0, Double::sum);
    }
}
