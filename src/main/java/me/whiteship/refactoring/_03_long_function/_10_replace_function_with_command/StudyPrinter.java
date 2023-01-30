package me.whiteship.refactoring._03_long_function._10_replace_function_with_command;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

public class StudyPrinter {

    private int totalNumberOfEvents;

    private List<Participant> participants;

    public StudyPrinter(int totalNumberOfEvents, List<Participant> participants) {
        this.totalNumberOfEvents = totalNumberOfEvents;
        this.participants = participants;
    }

    /**
     * StudyDashboard의 마크다운 생성하는 로직을 StudyPrinter 클래스를 만들어 execute 함수로 추출했고
     * execute 함수에서 호출하는 메소드들을 StudyPrinter 클래스로 같이 옮겨줌
     * 옮겨주고 보니까 StudyDashboard의 totalnumberOfEvents 필드값이 필요하기 때문에 그 필드를 StudyPrinter의 필드로 생성하고
     * List<Participant> 도 보니까 execute의 파라미터로 받아오는것이 아니라 필드로 만들고 totalNumberOfEvents와 List<Participant>를 생성자로 받는다
     *
     * 이렇게 하면 StudyPrinter는 하나의 독립적인 커멘드를 표현하는 클래스가 되었음
     */

    public void execute() throws IOException {
        try (FileWriter fileWriter = new FileWriter("participants.md");
             PrintWriter writer = new PrintWriter(fileWriter)) {
            this.participants.sort(Comparator.comparing(Participant::username));

            writer.print(header(this.participants.size()));

            this.participants.forEach(p -> {
                String markdownForHomework = getMarkdownForParticipant(p);
                writer.print(markdownForHomework);
            });
        }
    }

    private String getMarkdownForParticipant(Participant p) {
        return String.format("| %s %s | %.2f%% |\n", p.username(), checkMark(p, this.totalNumberOfEvents),
                p.getRate(this.totalNumberOfEvents));
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
    private String checkMark(Participant p, int totalEvents) {
        StringBuilder line = new StringBuilder();
        for (int i = 1 ; i <= totalEvents ; i++) {
            if(p.homework().containsKey(i) && p.homework().get(i)) {
                line.append("|:white_check_mark:");
            } else {
                line.append("|:x:");
            }
        }
        return line.toString();
    }

}
