package me.whiteship.refactoring._02_duplicated_code._06_pull_up_method;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ReviewerDashboard extends Dashboard {

    /**
     * printReviewers의 구현들을 printUsernames로 함수 추출하고 매개변수로 int eventId 추가
     * @throws IOException
     */

    // pull up method로 올리기
    // Pull members up
    public void printReviewers() throws IOException {
        // Get GitHub issue to check homework
//        super.printUsernames(30);
        printUsernames(30); // static import
    }

}
