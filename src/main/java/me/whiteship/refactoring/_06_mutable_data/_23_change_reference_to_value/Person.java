package me.whiteship.refactoring._06_mutable_data._23_change_reference_to_value;

public class Person {

    private TelephoneNumber officeTelephoneNumber;

    /**
     * TelephoneNumber의 setter를 없앴기 때문에 setter로 데이터 수정을 하는것이 아니라 새로운 value object를 생성해줘야한다
     */

    public String officeAreaCode() {
        return this.officeTelephoneNumber.areaCode();
    }

    public void officeAreaCode(String areaCode) {
        this.officeTelephoneNumber = new TelephoneNumber(areaCode, this.officeNumber());
    }

    public String officeNumber() {
        return this.officeTelephoneNumber.number();
    }

    public void officeNumber(String number) {
        this.officeTelephoneNumber = new TelephoneNumber(this.officeAreaCode(), number);
    }

}
