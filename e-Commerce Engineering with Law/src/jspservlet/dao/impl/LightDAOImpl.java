package jspservlet.dao.impl;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jspservlet.dao.LightDAO;
import jspservlet.db.DBConnect;
import jspservlet.vo.Light;
import jspservlet.vo.UserLogin;

public class LightDAOImpl implements LightDAO {

	public ArrayList<Light> allLightid() throws Exception {
		ArrayList<Light> lightinfo = new ArrayList<Light>();
		String sql = "select * from item i,equipment e,equipmenttype t where i.equipmentID=e.equipmentID and e.typeID=t.typeID and t.typeID='T00001' and homeID='"+UserLogin.homeID+"';";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{    
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
            	Light Alight=new Light();
            	Alight.setId(rs.getString("ItemID"));
            	Alight.setType(rs.getString("equipmentName"));
            	Alight.setImg(rs.getString("equipImage"));
                Alight.setName(rs.getString("name"));
                Alight.setPosition(rs.getString("position"));
                String sql2="select brightness from data where itemID='"+rs.getString("ItemID")+"' and time=(select MAX(time) from data where itemID='"+rs.getString("ItemID")+"')";
                pstmt = dbc.getConnection().prepareStatement(sql2) ;
                ResultSet rs2 = pstmt.executeQuery();
                while(rs2.next()) {
                    Alight.setState(rs2.getString("brightness"));
                }
            	lightinfo.add(Alight);
            }   
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{ 
            dbc.close() ;   
        }   
		return lightinfo;
	}

    public ArrayList<String> allLightType(){
        ArrayList<String> lighttype = new ArrayList<String>();
        String sql = "select * from equipment e,equipmenttype t where e.typeID=t.typeID and t.typeID='T00001';";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                lighttype.add(rs.getString("equipmentName"));
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            dbc.close() ;
        }
        return lighttype;
    }

	public void addlight(Light light) {
        String sql3 = "select equipmentID from equipment where equipmentName='"+light.getType()+"';";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;
        String eid = null;
        String did = null;
        try{    
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql3) ;
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                eid=rs.getString("equipmentID");
            }
            String sql1 = "select RIGHT(MAX(RIGHT(ItemID,2))+101,2) as ItemID from item where LEFT(ItemID,6)='"+eid+"';";
            pstmt = dbc.getConnection().prepareStatement(sql1) ;
            rs = pstmt.executeQuery();
            while(rs.next()){
                String num=rs.getString("ItemID");
                if (num==null){
                    num="01";
                }
                String id=eid+"-"+num;
                light.setId(id);
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String time=df.format(new Date());
            String sql2 = "insert into item(ItemID,equipmentID,homeID,purchaseDate,label,name,position) VALUES('"+light.getId()+"','"+eid+"','"+UserLogin.homeID+"','"+time+"',0,'"+light.getName()+"','"+light.getPosition()+"');";
            pstmt = dbc.getConnection().prepareStatement(sql2) ;
            pstmt.execute();
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time=df.format(new Date());
            String sql5 = "select RIGHT(MAX(RIGHT(dataID,5))+100001,5) as dataID from data";
            pstmt = dbc.getConnection().prepareStatement(sql5) ;
            rs = pstmt.executeQuery();
            while(rs.next()){
                did=rs.getString("dataID");
                if (did == null){
                    did="00001";
                }
                did="D"+did;
            }
            String sql4 = "insert into data(dataID,itemID,time,brightness,label) VALUES('"+did+"','"+light.getId()+"','"+time+"',0,0);";
            pstmt = dbc.getConnection().prepareStatement(sql4) ;
            pstmt.execute();
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{ 
            dbc.close() ;   
        }   
	}
	
	public Light queryLight(String id){
		Light Alight=new Light();
		String sql = "select * from item i,equipment e,equipmenttype t,data d where i.equipmentID=e.equipmentID and e.typeID=t.typeID and i.ItemID=d.itemID and i.ItemID='"+id+"' and homeID='"+UserLogin.homeID+"' and d.time=(select MAX(time) from data where itemID='"+id+"')";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
            	Alight.setId(rs.getString("ItemID"));
            	Alight.setType(rs.getString("equipmentID"));
            	Alight.setImg(rs.getString("equipImage"));
            	Alight.setState(rs.getString("brightness"));
            }
            rs.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());   
        }finally{ 
            dbc.close() ;   
        }   
		return Alight;
	}
	
	public void changeLight(String id,String State) {
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{    
            dbc = new DBConnect() ;
            String sql1 = "select RIGHT(MAX(RIGHT(dataID,5))+100001,5) as dataID from data where itemID='"+id+"';";
            pstmt = dbc.getConnection().prepareStatement(sql1) ;
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String dataID="D"+rs.getString("dataID");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time=df.format(new Date());
                String sql = "insert into data(dataID,itemID,time,brightness,label) VALUES('"+dataID+"',''"+id+"','"+time+"','"+State+"',0);";
                pstmt = dbc.getConnection().prepareStatement(sql) ;
                pstmt.execute();
            }
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{ 
            dbc.close() ;   
        }   
	}
	
	public void deleteLight(String id) {
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
		String sql = "delete from item where ItemID="+id+" and homeID="+UserLogin.homeID;
        try{    
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ;   
            pstmt.execute();
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{ 
            dbc.close() ;   
        }  
	}
}
