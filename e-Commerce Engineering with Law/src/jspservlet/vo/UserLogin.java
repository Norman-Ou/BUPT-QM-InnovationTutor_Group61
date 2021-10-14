package jspservlet.vo;

public class UserLogin {
	public static String homeID;
	public static String memberID;
	public static String memberName;
	public static int i=0;
	public static int alarm=0;

	public static void setHomeID(String homeIDin) {
		homeID=homeIDin;
	}
	public static void setMemberID(String memberIDin) {
		memberID=memberIDin;
	}
	public static void setMemberName(String memberNamein){
		memberName=memberNamein;
	}
}