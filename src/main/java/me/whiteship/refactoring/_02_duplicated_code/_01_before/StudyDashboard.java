package me.whiteship.refactoring._02_duplicated_code._01_before;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StudyDashboard {

    /**
     * 중복코드
     * Duplicated Code
     * 
     * 중복코드의 단점
     *   - 비슷한지, 완전히 동일한 코드인지 주의 깊게 봐야한다
     *   - 코드를 변경할 때, 동일한 모든 곳의 코드를 변경해야 한다
     * 사용할 수 있는 리팩토링 기술
     *   - 동일한 코드를 여러 메소드에서 사용하는 경우, 함수 추출하기
     *   - 코드가 비슷하게 생겼지만 완전히 같지는 않은 경우, 코드 분리하기
     *   - 여러 하위 클래스에 동일한 코드가 있다면, 메소드 올리기
     */

    private void printParticipants(int eventId) throws IOException {
        // Get github issue to check homework
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);

        // Get participants
        Set<String> participants = new HashSet<>();
        issue.getComments().forEach(c -> participants.add(c.getUserName()));

        // Print participants
        participants.forEach(System.out::println);
    }

    private void printReviewers() throws IOException {
        // Get github issue to check homework
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        // Get reviewers
        Set<String> reviewers = new HashSet<>();
        issue.getComments().forEach(c -> reviewers.add(c.getUserName()));

        // Print reviewers
        reviewers.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.printReviewers();
        studyDashboard.printParticipants(15);

    }

}
