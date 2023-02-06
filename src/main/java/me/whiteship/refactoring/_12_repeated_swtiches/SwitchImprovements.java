package me.whiteship.refactoring._12_repeated_swtiches;

public class SwitchImprovements {

    /**
     * 기존의 switch문은 break;를 걸어줘야만 해당 case에서 끝이나지 break;를 안걸어주게 될 경우 로직이 끝까지 진행된다
     * 자바 14버전 이전에는 switch문을 사용했었는데 14버전 이후로는 switch expression을 사용할 수 있다
     * switch문(switch statement)과 switch expression은 다르다
     */

    // switch문 (switch statement)
    public int vacationHoursSwitchStatement(String type) {
        int result;
        switch (type) {
            case "full-time": result = 120; break;
            case "part-time": result = 80; break;
            case "temporal": result = 32; break;
            default: result = 0;
        }
        return result;
    }

    // switch문 (switch expression)
    public int vacationHoursSwitchExpression(String type) {

        /**
         * 화살표 (->) 하고 값을 주면 break; 같은 제어를 안넣어줘도 된다
         * 물론 화살표를 안쓰고 특정 연산식의 값을 넣어주고 싶으면 yield를 넣어주면 됨 (근데 굳이 번거롭게 할 필요가 없음)
         * 여러줄을 쓰게 될경우는 -> { 내용 } 을 해주고 마지막에 yield를 써서 값을 넘겨주면 됨
         */

        return switch (type) {
            case "full-time" -> 120;
//            case "full-time": yield 120;
            case "part-time" -> 80;
            case "temporal" -> 32;
            default -> 0;
        };
    }

}
