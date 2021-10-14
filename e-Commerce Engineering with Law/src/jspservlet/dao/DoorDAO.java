package jspservlet.dao;

import jspservlet.vo.Door;
import jspservlet.vo.Humidifier;

import java.util.ArrayList;

public interface DoorDAO {
	public ArrayList<Door> allDoorid() throws Exception;
	public ArrayList<String> allDoorType() throws Exception;
	public Door queryDoor(String id) throws Exception;
	public void adddoor(Door door) throws Exception;
	public void deleteDoor(String id) throws Exception;
}
