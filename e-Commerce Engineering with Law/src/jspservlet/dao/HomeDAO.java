package jspservlet.dao;

import jspservlet.vo.Home;

public interface HomeDAO {

	//query home information
	public Home showHome(String homeID) throws Exception;

	//change user's home PWD
	public int Userhomechange(Home home, String newPWD) throws Exception;

	//change home information
	public int create(Home home) throws Exception;

	//change to another home
	public int anohomechange(Home home) throws Exception;

	//register a home
	public String register(String name, String adress, String password) throws Exception;
}