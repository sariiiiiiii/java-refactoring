package me.whiteship.refactoring._03_long_function._13_replace_conditional_with_polymorphism;

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
     * 조건문을 다형성으로 바꾸기
     * Replace Conditional with Polymorphism
     *
     * - 여러 타입에 따라 각기 다른 로직으로 처리해야 하는 경우에 다형성을 적용해서 조건문을 보다 명확하게 분리할 수 있다.
     *   (예, 책, 음악, 음식 등...) 반복되는 swtich문을 각기 다른 클래스를 만들어 제거할 수 있다
     * - 공통으로 사용되는 로직은 상위클래스에 두고 달라지는 부분만 하위클래스에 둠으로써, 달라지는 부분만 강조할 수 있다
     * - 모든 조건문을 다형성으로 바꿔야 하는 것은 아니다
     */

    private final int totalNumberOfEvents;
    private final List<Participant> participants;

    public StudyDashboard(int totalNumberOfEvents) {
        this.totalNumberOfEvents = totalNumberOfEvents;
        participants = new CopyOnWriteArrayList<>();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        StudyDashboard studyDashboard = new StudyDashboard(15);
        studyDashboard.print();
    }

    /**
     * 분기별로 로직이 짜여져있는것이 아닌 type에 따라 상속받은 하위클래스의 메소드 실행
     * 하지만 분기처리가 없어진게 아니라 type이 나뉘어지는 분기처리는 언제나 존재 (동적으로 실행되는 로직을 나누고 싶을때 팩토리 메소드 활용 !)
     */
    private void print() throws IOException, InterruptedException {
        checkGithubIssues(getGhRepository());
//        new StudyPrinter(this.totalNumberOfEvents, this.participants, PrinterMode.MARKDOWN).execute();
        new CvsPrinter(this.totalNumberOfEvents, this.participants).execute();
        new ConsolePrinter(this.totalNumberOfEvents, this.participants).execute();
        new MarkdownPrinter(this.totalNumberOfEvents, this.participants).execute();
    }

    private GHRepository getGhRepository() throws IOException {
        GitHub gitHub = GitHub.connect();
        return gitHub.getRepository("whiteship/live-study");
    }

    private void checkGithubIssues(GHRepository repository) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(8);
        CountDownLatch latch = new CountDownLatch(totalNumberOfEvents);

        for (int index = 1 ; index <= totalNumberOfEvents ; index++) {
            int eventId = index;
            service.execute(() -> {
                try {
                    GHIssue issue = repository.getIssue(eventId);
                    checkHomework(issue.getComments(), participants, eventId);
                    latch.countDown();
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
            });
        }

        latch.await();
        service.shutdown();
    }

    private void checkHomework(List<GHIssueComment> comments, List<Participant> participants, int eventId) {
        for (GHIssueComment comment : comments) {
            Participant participant = findParticipant(comment.getUserName(), participants);
            participant.setHomeworkDone(eventId);
        }
    }

    private Participant findParticipant(String username, List<Participant> participants) {
        return isNewParticipant(username, participants) ?
                createNewParticipant(username, participants) :
                findExistingParticipant(username, participants);
    }

    private Participant findExistingParticipant(String username, List<Participant> participants) {
        Participant participant;
        participant = participants.stream().filter(p -> p.username().equals(username)).findFirst().orElseThrow();
        return participant;
    }

    private Participant createNewParticipant(String username, List<Participant> participants) {
        Participant participant;
        participant = new Participant(username);
        participants.add(participant);
        return participant;
    }

    private boolean isNewParticipant(String username, List<Participant> participants) {
        return participants.stream().noneMatch(p -> p.username().equals(username));
    }

}
