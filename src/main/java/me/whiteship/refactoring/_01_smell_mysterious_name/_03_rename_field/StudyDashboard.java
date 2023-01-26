package me.whiteship.refactoring._01_smell_mysterious_name._03_rename_field;

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
     * 필드 이름 바꾸기
     * Rename Field
     *   - Record 자료 구조의 필드 이름은 프로그램 전반에 걸쳐 참조될 수 있기 때문에 매우 중요하다
     *     - Record 자료 구조 : 특정 데이터와 관련있는 필드를 묶어놓은 자료 구조
     *     - 파이썬의 Dictionay, 또는 줄여서 dicts
     *     - C#의 Record
     *     - 자바 14 버전부터 지원 (record 키워드)
     *     - 자바에서는 Getter와 Setter 메소드 이름도 필드의 이름과 비슷하게 간주할 수 있다
     */

    // Field에서 Record 코드로 변경
    private Set<StudyReview> studyReviews = new HashSet<>(); // review의 username을 즉, 리뷰를 쓴사람을 꺼내는 것이기 때문에 usernames -> reviewers

    /**
     * 스터디 리뷰 이슈에 작성되어 있는 리뷰어 목록과 리뷰를 읽어옵니다.
     * @throws IOException
     */
    private void loadReviews() throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        List<GHIssueComment> reviews = issue.getComments();
        for (GHIssueComment review : reviews) {
            studyReviews.add(new StudyReview(review.getUserName(), review.getBody()));

            /**
             * 깃헙 라이브러리에서 getUserName()은 Deprecated 되어 있다
             * 원래는 review.getuser().getLogin()해서 가져와야 되지만 github api를 한번 더 호출해서 조금더 오래걸리기 때문에
             * 일단은 getUserName()으로 마무리
             */

        }
    }

    public Set<StudyReview> getStudyReviews() {
        return studyReviews;
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.loadReviews();
        studyDashboard.getStudyReviews().forEach(System.out::println); // record compile시 자동으로 만들어주는 StudyReview의 toString 출력
    }
}
