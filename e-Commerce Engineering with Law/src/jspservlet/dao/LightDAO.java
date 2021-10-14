package jspservlet.dao;

import java.util.ArrayList;

import jspservlet.vo.Light;

public interface LightDAO {
	public ArrayList<Light> allLightid() throws Exception;
	public ArrayList<String> allLightType() throws Exception;
	public Light queryLight(String id) throws Exception;
	public void changeLight(String id,String State) throws Exception;
	public void deleteLight(String id) throws Exception;
	public void addlight(Light light) throws Exception;
}
