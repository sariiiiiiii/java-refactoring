package me.whiteship.refactoring._02_duplicated_code._04_extract_function;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StudyDashboard {

    /**
     * 함수 추출하기
     * Extract Function
     *   - "의도"와 "구현" 분리하기
     *   - 무슨 일을 하는 코드인지 알아내려고 노력해야 하는 코드라면 해당 코드를 함수로 본리하고 함수 이름으로 "무슨 일을 하는지" 표현할 수 있다
     *   - 한줄 짜리 메소드도 괜찮은가? (의도를 잘 드러낼 수 있다면 한줄짜리 코드도 괜찮다)
     *   - 거대한 함수 안에 들어있는 주석은 추출한 함수를 찾는데 있어서 좋은 단서가 될 수 있다
     */

    // 구현 함수 (한눈에 봤을 때 한번에 알아 볼 수 없기 때문에 의도를 분리하자)
    private void printParticipants(int eventId) throws IOException {
        // Get github issue to check homework
        GHIssue issue = getGhIssue(eventId);

        // Get participants
        Set<String> participants = getUsernames(issue);

        // Print participants
        print(participants);
    }

    private void printReviewers() throws IOException {
        // Get github issue to check reviews
        GHIssue issue = getGhIssue(30);

        // Get reviewers
        Set<String> reviewers = getUsernames(issue);

        // Print reviewers
        print(reviewers);
    }

    /**
     * 공통된 함수 분리 ↓
     */

    // Get github issue to check 의도를 분리한 메소드
    private static GHIssue getGhIssue(int eventId) throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);
        return issue;
    }

    // Get participants 분리
    private static Set<String> getUsernames(GHIssue issue) throws IOException {
        // 해당 메소드에서는 participants, reviewers를 뽑는 메소드 즉, username을 뽑는 메소드 이기 때문에 participants, reviewers -> usernames로 변경
        Set<String> usernames = new HashSet<>();
        issue.getComments().forEach(c -> usernames.add(c.getUserName()));
        return usernames;
    }

    // Print participants, reviewers 분리
    private static void print(Set<String> usernames) {
        usernames.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.printReviewers();
        System.out.println("==========================================================");
        studyDashboard.printParticipants(15);
    }

}
