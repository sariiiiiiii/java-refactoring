package me.whiteship.refactoring._03_long_function._09_preserve_whole_object;

import java.util.HashMap;
import java.util.Map;

public record Participant(String username, Map<Integer, Boolean> homework) {
    public Participant(String username) {
        this(username, new HashMap<>());
    }

    public void setHomeworkDone(int index) {
        this.homework.put(index, true);
    }


    /**
     * 적용하고 나서 또는, 적용할 때에 고민해볼 것이 있다
     * 각각의 함수가 Participant의 의존하는것이 맞나 아니면 고치기 전이였던 HashMap을 보고 있는것이 맞는가를 한번 고민해보는 것이 맞다
     * 이 함수를 다른 도메인에도 적용할 것인가 아니면 그럴 계획이 있는가를 고민
     * 만약 다른곳에서도 사용할 것이면 차라리 HashMap을 적용하는것이 맞겠다 라는 의존성에 대한 판단을 고민해보자 !
     * <p>
     * 그 후, 고민해볼것이 있다면 과연 이 메소드의 위치가 적절한것인가 고민
     * getRate()는 참석률을 계산하는 함수이다
     * 그에대한 정보는 Participant에 들어가있다 하지만 totalNumberOfEvents는 Participant에 있는 정보가 아니다
     *
     * @param studyDashboard
     */

    /**
     * getRate()라는 메소드가 StudyDashboard에 의존하는것이 맞는가?
     */
    double getRate(int totalNumberOfEvents) {
        long count = homework().values().stream()
                .filter(v -> v == true)
                .count();
        return (double) (count * 100 / totalNumberOfEvents);
    }
}
