package me.whiteship.refactoring._03_long_function._11_decompose_conditional;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudyDashboard {

    /**
     * 조건문 분해하기
     * Decompose Conditional
     *
     * 여러 조건에 따라 달라지는 코드를 작성하다보면 종종 긴 함수가 만들어지는 것을 목격할 수 있다
     * "조건"과 "액션" 모두 "의도"를 표현해야 한다
     * 기술적으로는 "함수 추출하기"와 동일한 리팩토링이지만 의도만 다를 뿐이다
     */

    private final int totalNumberOfEvents;

    public StudyDashboard(int totalNumberOfEvents) {
        this.totalNumberOfEvents = totalNumberOfEvents;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        StudyDashboard studyDashboard = new StudyDashboard(15);
        studyDashboard.print();
    }

    private void print() throws IOException, InterruptedException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        List<Participant> participants = new CopyOnWriteArrayList<>();

        ExecutorService service = Executors.newFixedThreadPool(8);
        CountDownLatch latch = new CountDownLatch(totalNumberOfEvents);

        for (int index = 1 ; index <= totalNumberOfEvents ; index++) {
            int eventId = index;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        GHIssue issue = repository.getIssue(eventId);
                        List<GHIssueComment> comments = issue.getComments();

                        for (GHIssueComment comment : comments) {
                            Participant participant = findParticipant(comment.getUserName(), participants);
                            participant.setHomeworkDone(eventId);
                        }

                        latch.countDown();
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            });
        }

        latch.await();
        service.shutdown();

        new StudyPrinter(this.totalNumberOfEvents, participants).execute();
    }

    // 조건문 분해하기
    private Participant findParticipant(String username, List<Participant> participants) {

        /**
         * 조건문을 decompose(분해하면)하면 코드를 더 간추릴 수 있다 (해당 메소드에서는 삼항연산자 활용) -> 깔끔해짐
         * 근데 보면 파라미터로 받은 username과 participants가 반복적으로 오고가고 하고 있다 이 부분도 리팩토링으로 더 짧게 만들 수 있다
         */

        return isNewParticipant(username, participants) ? createNewParticipant(username, participants) : findExistingParticipant(username, participants);
//        Participant participant;
//        if (isNewParticipant(username, participants)) { // 조건문에 의미를 부여 (새로운 참가자인 것인가)
//            participant = createNewParticipant(username, participants); // 맞다면 새로 만들고
//        } else {
//            participant = findExistingParticipant(username, participants); // 새로운 참가자가 아니라면 찾아주고
//        }
//
//        return participant;
    }

    private static boolean isNewParticipant(String username, List<Participant> participants) {
        return participants.stream().noneMatch(p -> p.username().equals(username));
    }

    private static Participant createNewParticipant(String username, List<Participant> participants) {
        Participant participant;
        participant = new Participant(username);
        participants.add(participant);
        return participant;
    }

    private static Participant findExistingParticipant(String username, List<Participant> participants) {
        Participant participant;
        participant = participants.stream().filter(p -> p.username().equals(username)).findFirst().orElseThrow();
        return participant;
    }

}
