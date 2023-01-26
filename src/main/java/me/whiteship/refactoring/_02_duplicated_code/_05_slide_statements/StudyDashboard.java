package me.whiteship.refactoring._02_duplicated_code._05_slide_statements;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StudyDashboard {

    /**
     * 코드 정리하기
     * Slide Statements
     *   - 관련있는 코드끼리 묶여있어야 코드를 더 쉽게 이해할 수 있다
     *   - 함수에서 사용할 변수를 상단에 미리 정의하기 보다는, 해당 변수를 사용하는 코드 바로 위에 선언하자
     *   - 관련있는 코드끼리 묶은 다음, 함수 추출하기 (Extract Function)를 사용해서 더 깔끔하게 분리할 수도 있다
     */

    private void printParticipants(int eventId) throws IOException {
        // Get github issue to check homework
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);

        // Get participants
        Set<String> participants = new HashSet<>(); // 변수는 쓰기 직전에 선언을 해놓자 상단에 몰아넣으면 알아보기가 힘듬
        issue.getComments().forEach(c -> participants.add(c.getUserName()));

        // Print participants
        participants.forEach(System.out::println);
    }

    private void printReviewers() throws IOException {
        // Get github issue to check homework
//        Set<String> reviewers = new HashSet<>(); 변수를 상단에서 선언을 하게 됐을 경우 printParticipants와 중복 코드였던것을 알아볼수가 있었을까
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        // Get reviewers
        Set<String> reviewers = new HashSet<>(); // 변수는 쓰기 직전에 선언을 해놓자 상단에 몰아넣으면 알아보기가 힘듬 또, 중복코드(Extract Function)을 추출하기에도 굉장히 편리
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
