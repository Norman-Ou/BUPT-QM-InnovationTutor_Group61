package jspservlet.dao;

import java.util.ArrayList;

import jspservlet.vo.Humidifier;
import jspservlet.vo.Window;

public interface WindowDAO {
	public ArrayList<Window> allWindowid() throws Exception;
	public ArrayList<String> allWindowType() throws Exception;
	public Window queryWindow(String id) throws Exception;
	public void addwindow(Window window) throws Exception;
	public void deleteWindow(String id) throws Exception;
}
