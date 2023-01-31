package me.whiteship.refactoring._04_long_parameter_list._16_combine_functinos_into_class;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudyDashboard {

    /**
     * 여러 함수를 클래스로 묶기
     * Combine Functions into Class
     *
     * 비슷한 매개변수 목록을 여러 함수에서 사용하고 있다면 해당 메소드를 모아서 클래스를 만들 수 있다
     * 클래스 내부로 메소드를 옮기고, 데이터를 필드로 만들면 메소드에 전달해야 하는 매개변수 목록도 줄일 수 있다
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

        /**
         * 밑 코드를 빼어낼게 될 경우 Participant라는 객체와 StudyDashboard의 totalNumberOfEvenets 필드를 필요로 하고 있다
         * totalNumberOfEvenets 필드를 Participant 객체의 필드로 넘겨주고 해당 메소드를 객체에서 사용하게 될 경우
         * 파라미터도 줄일 수 있고 필드에서 사용하기 때문에 무슨일을 하는것인지 좀더 직관적으로 알 수 있다
         */

        new StudyPrinter(this.totalNumberOfEvents, participants).print();

//        try (FileWriter fileWriter = new FileWriter("participants.md");
//             PrintWriter writer = new PrintWriter(fileWriter)) {
//            participants.sort(Comparator.comparing(Participant::username));
//
//            writer.print(header(participants.size()));
//
//            participants.forEach(p -> {
//                String markdownForHomework = getMarkdownForParticipant(p.username(), p.homework());
//                writer.print(markdownForHomework);
//            });
//        }
    }


//    private String getMarkdownForParticipant(String username, Map<Integer, Boolean> homework) {
//        return String.format("| %s %s | %.2f%% |\n", username, checkMark(homework), getRate(homework));
//    }

    /**
     * |:white_check_mark:|:white_check_mark:|:white_check_mark:|:x:|
     */
//    private String checkMark(Map<Integer, Boolean> homework) {
//        StringBuilder line = new StringBuilder();
//        for (int i = 1 ; i <= this.totalNumberOfEvents ; i++) {
//            if(homework.containsKey(i) && homework.get(i)) {
//                line.append("|:white_check_mark:");
//            } else {
//                line.append("|:x:");
//            }
//        }
//        return line.toString();
//    }

//    private double getRate(Map<Integer, Boolean> homework) {
//        long count = homework.values().stream()
//                .filter(v -> v == true)
//                .count();
//        return (double) (count * 100 / this.totalNumberOfEvents);
//    }

    /**
     * | 참여자 (420) | 1주차 | 2주차 | 3주차 | 참석율 |
     * | --- | --- | --- | --- | --- |
     */
//    private String header(int totalNumberOfParticipants) {
//        StringBuilder header = new StringBuilder(String.format("| 참여자 (%d) |", totalNumberOfParticipants));
//
//        for (int index = 1; index <= this.totalNumberOfEvents; index++) {
//            header.append(String.format(" %d주차 |", index));
//        }
//        header.append(" 참석율 |\n");
//
//        header.append("| --- ".repeat(Math.max(0, this.totalNumberOfEvents + 2)));
//        header.append("|\n");
//
//        return header.toString();
//    }







}
