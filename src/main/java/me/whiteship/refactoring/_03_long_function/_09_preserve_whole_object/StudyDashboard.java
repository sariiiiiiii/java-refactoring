package me.whiteship.refactoring._03_long_function._09_preserve_whole_object;

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

    /**
     * 객체 통째로 넘기기
     * Preserve Whole Object
     *
     * 어떠 한 레코드에서 구할 수 있는 여러 값들을 함수에 전달하는 경우, 해당 매개변수를 레코드 하나로 교체할 수 있다
     * 매개변수 목록을 줄일 수 있다 (향후에 추가할지도 모를 매개변수까지도...)
     * 이 기술을 적용하기 전에 의존성을 고려해야 한다
     * 어쩌면 해당 메소드의 위치가 적절하지 않을 수도 있다 (기능 편애 "Feature Envy"냄새에 해당한다)
     *
     * ※ 핵심내용
     * getRate()의 필요한 함수는 Participant 객체가 가지고 있다 그래서 getRate() 함수를 participant 객체의 메소드로 추출하고
     * 그의 해당하는 participant의 필드외의 파라미터를 따로 받음
     * 하지만 그 파라미터는 StudyDashboard의 값이여서 파라미터로 StudyDashboard가 파라미터로 넘겨오기 때문에 this.totalNumberEvents의 필드만 파라미터로 넘김
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

        for (int index = 1; index <= totalNumberOfEvents; index++) {
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

            writer.print(header(participants.size()));

            participants.forEach(p -> {
                String markdownForHomework = getMarkdownForParticipant(p);
                writer.print(markdownForHomework);
            });
        }
    }

    private String getMarkdownForParticipant(Participant participant) {
        return String.format("| %s %s | %.2f%% |\n", participant.username(),
                checkMark(participant, this.totalNumberOfEvents),
                participant.getRate(this.totalNumberOfEvents)); // getRate() 함수를 Participant의 함수로 추출
    }

    /**
     * | 참여자 (420) | 1주차 | 2주차 | 3주차 | 참석율 |
     * | --- | --- | --- | --- | --- |
     */
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
    private String checkMark(Participant participant, int totalEvents) {
        StringBuilder line = new StringBuilder();
        for (int i = 1; i <= totalEvents; i++) {
            if (participant.homework().containsKey(i) && participant.homework().get(i)) {
                line.append("|:white_check_mark:");
            } else {
                line.append("|:x:");
            }
        }
        return line.toString();
    }
}
