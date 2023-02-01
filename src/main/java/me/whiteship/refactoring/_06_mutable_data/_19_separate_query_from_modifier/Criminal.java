package me.whiteship.refactoring._06_mutable_data._19_separate_query_from_modifier;

import java.util.List;

public class Criminal {

    /**
     * 단순 조회와 사이드이팩트 함수를 나누자
     */
    
    // 사이드이팩트 로직
    public void alertForMiscreant(List<Person> people) {

        /**
         * people을 찾는일을 findMiscreant()에서 똑같이 하고 있다
         */

        if (findMiscreant(people).isBlank()) {
            setOffAlarms();
        }

//        for (Person p : people) {
//            if (p.getName().equals("Don")) {
//                setOffAlarms();
//            }
//
//            if (p.getName().equals("John")) {
//                setOffAlarms();
//            }
//        }

    }

    // 단순 조회 로직
    public String findMiscreant(List<Person> people) {
        for (Person p : people) {
            if (p.getName().equals("Don")) {
                return "Don";
            }

            if (p.getName().equals("John")) {
                return "John";
            }
        }

        return "";
    }

    private void setOffAlarms() {
        System.out.println("set off alarm");
    }
}
