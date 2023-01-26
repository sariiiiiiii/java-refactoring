package me.whiteship.refactoring._01_smell_mysterious_name._01_change_method_declaration;

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
     * 함수 선언 변경하기
     * Change Function Declaration
     *   - 함수 이름 변경하기, 메소드 이름 변경하기, 매개변수 추가하기, 매개변수 제거하기, 시그니처 변경하기
     *
     * 1. 좋은 이름을 가진 함수는 함수가 어떻게 구현되었는지 코드를 보지 않아도 이름만 보고도 이해할 수 있다
     * 2. 좋은 이름을 찾아내는 방법? 함수에 주석을 작성한 다음, 주석을 함수 이름으로 만들어 본다
     * 3. 함수의 매개변수는
     *   - 함수 내부의 문맥을 결정한다 (예, 전화번호 포매팅 함수)
     *   - 의존성을 결정한다 (예, Payment 만기일 계산 함수)
     */

    private Set<String> usernames = new HashSet<>();

    private Set<String> reviews = new HashSet<>();

    /**
     * (주석 달아보기)
     * 스터디 리뷰이슈에 작성되어 있는 리뷰어 목록과 리뷰를 읽어옵니다.
     * studyReviews -> loadReviews
     */
    private void loadReviews() throws IOException {
        /**
         * 어떠한 목록을 가져와야 될지 이미 알고 있기 때문에
         * 호출한 쪽에서 선언하여 파라미터로 넘겨주는 것이 아니라
         * 해당 메소드에서 선언하여 활용
         */
        
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);
        
        List<GHIssueComment> comments = issue.getComments();
        for (GHIssueComment comment : comments) {
            usernames.add(comment.getUserName());
            reviews.add(comment.getBody());
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
        studyDashboard.getUsernames().forEach(System.out::println);
        studyDashboard.getReviews().forEach(System.out::println);
    }
}
