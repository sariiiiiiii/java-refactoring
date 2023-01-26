package me.whiteship.refactoring._01_smell_mysterious_name._03_rename_field;

public record StudyReview(String reviewer, String review) {

    /**
     * record
     * record 매개변수로 들어온 값의 생성자를 만들어줌
     * getter의 역할을 해주는 메소드를 만들어주고
     * hashCodeEquals와 toString을 만들어줌
     */

}
