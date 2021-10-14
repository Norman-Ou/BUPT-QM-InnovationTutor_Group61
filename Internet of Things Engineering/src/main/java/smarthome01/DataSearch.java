package smarthome01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;

public class DataSearch {
	public static Object[][] dataSearch(String cid,String iid,int dt,String ts,String te) throws ClassNotFoundException, SQLException {
		//1.��������
		//2.���ؾ���������
//		cid ="C00003";//��ʱ������Ҫ��!!!!!!!!!!!!
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//3.��������
		
		final String URL = "jdbc:mysql://localhost:3306/smarthome2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		final String USERNAME = "root";
		final String PASSWORD = "421132";
		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

		//4.����sql��ִ����ɾ�ġ�������
		Statement state = conn.createStatement();
		Statement state2 = conn.createStatement();
		Statement state3 = conn.createStatement();
		String sql= " select itemID from item where itemID ='"+iid+"'and label=0;";
		String sql2;
		String sql3;
		String datat="";
		String colna="";
		switch(dt) {
		case 0:
			datat="lightdata";
			colna="brightness";
			break;
		case 1:
			datat="temperaturedata";
			colna="temperature";
			break;
		case 2:
			datat="soundsenordata";
			colna="decibel";
			break;
		case 3:
			datat="humiditydata";
			colna="humidity";
			break;
		case 4:
			datat="doordata";
			colna="distance";
			break;
		case 5:
			datat="irsensordata";
			colna="someone";
			break;
		}
		ResultSet rs = state.executeQuery(sql);//�ȼ��eid��û���ҵ�
		rs.next();
		if(rs.getString("itemID").equals(iid)) {
			rs.close();
			state.close();//

			sql2="select d.dataID, d.itemID, d.time, d."+colna+" from data d,item i ,equipment e  where  time between '"+ts+"' and '"+te+"' and d.itemID=i.itemID and i.equipmentID = e.equipmentID and e.compID= '"+cid+"' and d.itemID='"+iid+"' and d.label=0;";
			ResultSet rs2 = state2.executeQuery(sql2);
			

			sql3="select count(*) from data d,item i ,equipment e  where time between '"+ts+"' and '"+te+"' and d.itemID=i.itemID and i.equipmentID = e.equipmentID and e.compID= '"+cid+"' and d.itemID='"+iid+"' and d.label=0;";
			ResultSet rs3 = state3.executeQuery(sql3);
			rs3.next();
			System.out.println(rs3.getString("count(*)")); 
			int num=Integer.parseInt(rs3.getString("count(*)")) ;

			Object [][] xx=new Object [num][];
			int i;
			for(i=0;rs2.next();i++) {
				String did=rs2.getString(1);
				String data=rs2.getString(4);
				String tm=rs2.getString(3);
				String i4=rs2.getString(2);
				xx[i] = new Object[4];//Ϊ��i+1�з���4��!!!!!!!!!!!!!!!
				xx[i][0]=did;
				xx[i][1]=data;
				xx[i][2]=tm;
				xx[i][3]=i4;
			}
			
			rs.close();rs2.close();rs3.close();
			state.close();state2.close();state3.close();
			conn.close();
			return xx;
		
		} else {
			//û�ҵ����item
			CanNotFindItem dialog = new CanNotFindItem();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			rs.close();
			state.close();
			Object [][] xx=new Object [4][];
			xx[0]=new Object[4];
			xx[0][0]="";
			xx[0][1]="";
			xx[0][2]="";
			xx[0][3]="";
			rs.close();
			state.close();
			conn.close();
			return xx;

		}
			
		
	}
}
