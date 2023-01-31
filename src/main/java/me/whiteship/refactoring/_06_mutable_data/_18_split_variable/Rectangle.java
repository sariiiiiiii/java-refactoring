package me.whiteship.refactoring._06_mutable_data._18_split_variable;

public class Rectangle {

    private double perimeter;
    private double area;

    public void updateGeometry(double height, double width) {

        /**
         * 필드의 perimeter와 area 필드값을 하나의 변수로 값을 할당받아 넣어주는것이 아니라
         * 각각의 맞는 변수로 쪼개자
         * 해당 메소드에서는 값이 변할일이 없으니 final 변수로 할당해주자
         */

//        double temp = 2 * (height + width);
        final double perimeter = 2 * (height + width);
        System.out.println("Perimeter: " + perimeter);
        this.perimeter = perimeter;

//        perimeter = height * width; // 변수값 재할당 (변수를 재사용하지 말자)
        final double area = height * width; // 변수 쪼개기
        System.out.println("Area: " + area);
        this.area = area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getArea() {
        return area;
    }
}
