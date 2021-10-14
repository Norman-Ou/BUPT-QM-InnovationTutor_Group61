package jspservlet.dao;

import java.util.ArrayList;

import jspservlet.vo.Humidifier;
import jspservlet.vo.Light;

public interface HumidifierDAO {
	public ArrayList<Humidifier> allHumidifierid() throws Exception;
	public ArrayList<String> allHumidifierType() throws Exception;
	public Humidifier queryHumidifier(String id) throws Exception;
	public void addhumidifier(Humidifier humidifier) throws Exception;
	public void deleteHumidifier(String id) throws Exception;
}
