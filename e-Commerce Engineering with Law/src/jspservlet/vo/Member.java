package jspservlet.vo;

public class Member {
    private String memberID = null;
    private String memberName = null;
    private String memberGender = null;
    private String memberPhone = null;
    private String homeID = null;
    private String memberPWD = null;
    private int label = 0;

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getHomeID() {
        return homeID;
    }

    public void setHomeID(String string) {
        this.homeID = string;
    }

    public String getMemberPWD() {
        return memberPWD;
    }

    public void setMemberPWD(String memberPWD) {
        this.memberPWD = memberPWD;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }
}
