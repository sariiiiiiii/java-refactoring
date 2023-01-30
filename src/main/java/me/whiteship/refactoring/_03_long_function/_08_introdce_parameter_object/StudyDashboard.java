package me.whiteship.refactoring._03_long_function._08_introdce_parameter_object;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudyDashboard {

    private final int totalNumberOfEvents;

    public StudyDashboard(int totalNumberOfEvents) {
        this.totalNumberOfEvents = totalNumberOfEvents;
    }

    /**
     * 매개변수 객체 만들기
     * Introduce Parameter Object
     *
     * 같은 매개변수들이 여러 메소드에 걸쳐 나타난다면 그 매개변수들을 묶은 자료 구조를 만들 수 있다
     * 그렇게 만든 자료구조는:
     *   - 해당 데이터간의 관계를 보다 명시적으로 나타낼 수 있다
     *   - 함수에 전달할 매개변수 개수를 줄일 수 있다
     *   - 도메인을 이해하는데 중요한 역할을 하는 클래스로 발전할 수도 있다
     *
     * ※ 핵심내용
     *   - int totalNumberOfEvents 라는 변수를 메소드에서 파라미터로 너무 많이 넘겨주고 있다
     *   - 그래서 StudyDashboard의 필드로 준다음 StudyDashboard 객체 생성시 totalNumberOfEvents의 값을 넘겨주는 생성자로 생성을 한 다음
     *   - 그 필드의 값을 가지고 메소드에서 활용 (파라미터로 totalNumberOfEvents의 값을 계속 넘겨주고 있었는데 생략할 수 있어서 파라미터 갯수를 줄일 수 있었음)
     */

    public static void main(String[] args) throws IOException, InterruptedException {
        StudyDashboard studyDashboard = new StudyDashboard(15);
        studyDashboard.print();
    }

    private void print() throws IOException, InterruptedException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        List<Participant> participants = new CopyOnWriteArrayList<>();

//        int totalNumberOfEvents = 15; totalNumberOfEvent를 field로 생성
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
                            String username = comment.getUserName();
                            boolean isNewUser = participants.stream().noneMatch(p -> p.username().equals(username));
                            Participant participant = null;
                            if (isNewUser) {
                                participant = new Participant(username);
                                participants.add(participant);
                            } else {
                                participant = participants.stream().filter(p -> p.username().equals(username)).findFirst().orElseThrow();
                            }

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

        try (FileWriter fileWriter = new FileWriter("participants.md");
             PrintWriter writer = new PrintWriter(fileWriter)) {
            participants.sort(Comparator.comparing(Participant::username));

//            writer.print(header(totalNumberOfEvents, participants.size()));
            writer.print(header(participants.size()));

            participants.forEach(p -> {
                String markdownForHomework = getMarkdownForParticipant(p);
                writer.print(markdownForHomework);
            });
        }
    }

    private double getRate(Participant p) {
        long count = p.homework().values().stream()
                .filter(v -> v == true)
                .count();
        double rate = count * 100 / this.totalNumberOfEvents;
        return rate;
    }

//    private String getMarkdownForParticipant(int totalNumberOfEvents, Participant p) {
//    필드로 바꾼 변수값을 파라미터로 넘기는것이 아니라 객체의 접근해서 그 값을 사용
    private String getMarkdownForParticipant(Participant p) {
        return String.format("| %s %s | %.2f%% |\n", p.username(), checkMark(p), getRate(p));
    }

    /**
     * | 참여자 (420) | 1주차 | 2주차 | 3주차 | 참석율 |
     * | --- | --- | --- | --- | --- |
     */
    // 필드로 바꾼 변수값을 파라미터로 넘기는것이 아니라 객체의 접근해서 그 값을 사용
//    private String header(int totalNumberOfEvents, int totalNumberOfParticipants) {
    private String header(int totalNumberOfParticipants) {
        StringBuilder header = new StringBuilder(String.format("| 참여자 (%d) |", totalNumberOfParticipants));

        for (int index = 1; index <= this.totalNumberOfEvents; index++) {
            header.append(String.format(" %d주차 |", index));
        }
        header.append(" 참석율 |\n");

        header.append("| --- ".repeat(Math.max(0, this.totalNumberOfEvents + 2)));
        header.append("|\n");

        return header.toString();
    }

    /**
     * |:white_check_mark:|:white_check_mark:|:white_check_mark:|:x:|
     */
    private String checkMark(Participant p) {
        StringBuilder line = new StringBuilder();
        for (int i = 1 ; i <= this.totalNumberOfEvents ; i++) {
            if(p.homework().containsKey(i) && p.homework().get(i)) {
                line.append("|:white_check_mark:");
            } else {
                line.append("|:x:");
            }
        }
        return line.toString();
    }
}
