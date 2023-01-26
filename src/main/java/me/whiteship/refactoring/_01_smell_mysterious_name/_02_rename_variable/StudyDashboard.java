package me.whiteship.refactoring._01_smell_mysterious_name._02_rename_variable;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudyDashboard {

    /**
     * 변수 이름 바꾸기
     * Rename Variable
     *   - 더 많이 사용되는 변수일수록 그 이름이 더 중요하다
     *     - 람다식에서 사용하는 변수 vs 함수의 매개변수
     *   - 다이나믹 타입을 지원하는 언어에서는 타입을 이름에 넣기도 한다
     *   - 여러 함수에 걸쳐 쓰이는 필드 이름에는 더 많이 고민하고 이름을 짓는다
     */

    private Set<String> usernames = new HashSet<>();

    private Set<String> reviews = new HashSet<>();

    /**
     * 스터디 리뷰 이슈에 작성되어 있는 리뷰어 목록과 리뷰를 읽어옵니다.
     * @throws IOException
     */
    private void loadReviews() throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        // 이슈의 댓글목록을 불러오긴 하지만, 리뷰를 읽어오는 함수인데 그 안에 리뷰가 아무것도 없어서 뭔가 부자연스러움 comments -> reviews, comment -> review
        List<GHIssueComment> reviews = issue.getComments();
        for (GHIssueComment review : reviews) {
            usernames.add(review.getUserName());
            this.reviews.add(review.getBody());
        }
    }

    public Set<String> getUsernames() {
        return usernames;
    }

    public Set<String> getReviews() {
        return reviews;
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.loadReviews();
        studyDashboard.getUsernames().forEach(System.out::println); // => 람다표현식을 메소드 레퍼런스로 변경함으로써 매개변수의 이름을 딱히 고민 X (인텔리제이에서는 권장하는 줄이 표시됨)
        studyDashboard.getReviews().forEach(System.out::println); // => 람다표현식을 메소드 레퍼런스로 변경함으로써 매개변수의 이름을 딱히 고민 X (인텔리제이에서는 권장하는 줄이 표시됨)
    }
}
