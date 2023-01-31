package me.whiteship.refactoring._06_mutable_data._18_split_variable;

public class Haggis {

    private double primaryForce;
    private double secondaryForce;
    private double mass;
    private int delay;

    public Haggis(double primaryForce, double secondaryForce, double mass, int delay) {
        this.primaryForce = primaryForce;
        this.secondaryForce = secondaryForce;
        this.mass = mass;
        this.delay = delay;
    }

    public double distanceTravelled(int time) {

        /**
         * 해당메소드의 double result 변수는 값을 재할당해도 타당하다
         * why ? return 값을 result로 계속 축적해서 계산을 해야 하기 때문에
         * 
         * 해당메소드에서 바뀌지 않는 변수는 값을 명확하게 해주기 위해 final 상수 활용
         */

        double result;
        final double primaryAcceleration = primaryForce / mass;
        int primaryTime = Math.min(time, delay);
        result = 0.5 * primaryAcceleration * primaryTime * primaryTime;

        int secondaryTime = time - delay;
        if (secondaryTime > 0) {
            final double primaryVelocity = primaryAcceleration * delay;
//            primaryAcceleration = (primaryForce + secondaryForce) / mass; // 두번째 가속도를 구할때 첫번째 가속도의 값을 재할당 하는것이 아니라 새로운 변수로 할당
            final double secondaryAcceleration = (primaryForce + secondaryForce) / mass;
            result += primaryVelocity * secondaryTime + 0.5 * secondaryAcceleration * secondaryTime + secondaryTime;
        }

        return result;
    }
}
