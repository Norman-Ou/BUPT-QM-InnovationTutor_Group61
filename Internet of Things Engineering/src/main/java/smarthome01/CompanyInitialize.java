package smarthome01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyInitialize {
	private String companyID;
	
	public static Object[][] initialize(String cid) throws ClassNotFoundException, SQLException {
		//1.��������
		//2.���ؾ���������
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//3.��������
		
		final String URL = "jdbc:mysql://localhost:3306/smarthome2?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
		final String USERNAME = "root";
		final String PASSWORD = "421132";
		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

		//4.����sql��ִ����ɾ�ġ�������
		Statement state = conn.createStatement();

		
		//��ʱ������Ҫ��!!!!!!!!!!!!
		String sql0= "select count(*) \r\n"
				+ "from equipment a, equipmenttype b\r\n"
				+ "where a.typeID=b.typeID and compID='"+cid+"' and a.label = 0;";
		
		ResultSet rs0 = state.executeQuery(sql0);
		rs0.next();//����Ҫ�����α�����Ųһλ!!!!!!!!!!!!!!!!!!!
		String number =rs0.getString("count(*)");
		int num = Integer.parseInt(number);

		String sql4= "select equipmentID,equipmentName,equipmentManual,typeName,b.typeID \r\n"
				+ "from equipment a, equipmenttype b\r\n"
				+ "where a.typeID=b.typeID and compID='"+cid+"' and a.label =0;";
		ResultSet rs = state.executeQuery(sql4);
		Object [][] xx=new Object [num][];
		int i;
		for(i=0;rs.next();i++) {
			String equipmentID = rs.getString("equipmentID");
			String equipmentName = rs.getString("equipmentName");
			String equipmentManual = rs.getString("equipmentManual");
			String typeName = rs.getString("typeName");
			String typeID = rs.getString("typeID");
			xx[i] = new Object[5];//Ϊ��i+1�з���5��!!!!!!!!!!!!!!!
			xx[i][0]=equipmentID;
			xx[i][1]=equipmentName;
			xx[i][2]=equipmentManual;
			xx[i][3]=typeName;
			xx[i][4]=typeID;
		}
		
		
		rs.close();
		state.close();
		conn.close();
		
		return xx;
	}
	
	public static boolean addEquipment(boolean a,String eid,String ena,String ema,String tid,String tna,String tin,String cid) throws ClassNotFoundException, SQLException {
		//1.��������
		//2.���ؾ���������
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//3.��������
		
		final String URL = "jdbc:mysql://localhost:3306/smarthome2?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
		final String USERNAME = "root";
		final String PASSWORD = "421132";
		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

		//4.����sql��ִ����ɾ�ġ�������
		Statement state = conn.createStatement();
		boolean tag =false;
		//�ȼ���Ƿ����ظ���
		String sql = "select equipmentID from equipment";
		ResultSet rs = state.executeQuery(sql);
		while(rs.next()) {
			if(eid.equals(rs.getString("equipmentID"))) {tag=true;/*���ظ�*/break;}
		}
		sql = "select typeID from equipmenttype";
		rs = state.executeQuery(sql);
		while(rs.next()) {
			if(tid.equals(rs.getString("typeID"))&&a) {tag=true;/*���ظ�*/break;}
		}
		if (a&&!tag) {
			//aΪtrue ˵�����µ�type
//			System.out.println("��type");
			sql="INSERT INTO equipmenttype VALUES ('"+tid+"','"+tna+"','"+tin+"',0);";
			state.executeUpdate(sql);
			sql="INSERT INTO equipment (equipmentID,equipmentName,equipmentManual,compID,typeID,label,equipIamge) VALUES ('"+eid+"','"+ena+"','"+ema+"','"+cid+"','"+tid+"',0,'0');";
			state.executeUpdate(sql);
		}else if (!a&&!tag) {
			//aΪfalse ˵�������е�type
//			System.out.println("��type");
			sql="INSERT INTO equipment (equipmentID,equipmentName,equipmentManual,compID,typeID,label,equipIamge) VALUES ('"+eid+"','"+ena+"','"+ema+"','"+cid+"','"+tid+"',0,'0');";
			state.executeUpdate(sql);
		}
		
		
//		rs.close();
		state.close();
		conn.close();
		if(tag) return false;//���ʧ��
		else return true;//��ӳɹ�
		
	}

	public static boolean deleteEquipment(String cid,String eid) throws ClassNotFoundException, SQLException {
		//1.��������
		//2.���ؾ���������
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//3.��������
		
		final String URL = "jdbc:mysql://localhost:3306/smarthome2?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
		final String USERNAME = "root";
		final String PASSWORD = "421132";
		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

		//4.����sql��ִ����ɾ�ġ�������
		Statement state = conn.createStatement();

		
//		cid ="C00003";//��ʱ������Ҫ��!!!!!!!!!!!!
		String sql= " select equipmentID from equipment where equipmentID ='"+eid+"'and label=0;";
		
		ResultSet rs = state.executeQuery(sql);

		if(rs.next()!=false) {
			if(rs.getString("equipmentID").equals(eid)) {
				sql="UPDATE equipment SET label=1 WHERE equipmentID ='"+eid+"';";
				state.executeUpdate(sql);
				rs.close();
				state.close();
				conn.close();
				return true;//ɾ���ɹ�
			}else {
				rs.close();
				state.close();
				conn.close();
				return false; //û�ҵ�
			}
		}else {
			rs.close();
			state.close();
			conn.close();
			return false; //û�ҵ�
		}
		
	}
	
	public static boolean ModifyEquipment(String cid,String eid,String ena,String ema,String tid) throws ClassNotFoundException, SQLException {
		//1.��������
		//2.���ؾ���������
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//3.��������
		
		final String URL = "jdbc:mysql://localhost:3306/smarthome2?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
		final String USERNAME = "root";
		final String PASSWORD = "421132";
		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

		//4.����sql��ִ����ɾ�ġ�������
		Statement state = conn.createStatement();
		Statement state2 = conn.createStatement();
		
//		cid ="C00003";//��ʱ������Ҫ��!!!!!!!!!!!!
		String sql= " select equipmentID from equipment where equipmentID ='"+eid+"'and label=0;";
		
		ResultSet rs = state.executeQuery(sql);//�ȼ��eid��û���ҵ�
		
		
		String sql2= " select typeID from equipmenttype where typeID ='"+tid+"'and label=0;";
		ResultSet rs2 = state2.executeQuery(sql2);
	
		if(rs.next()!=false&&rs.getString("equipmentID").equals(eid)) {
			if(rs2.next()!=false&&rs2.getString("typeID").equals(tid)) {
				sql="UPDATE equipment SET equipmentName='"+ena+"',equipmentManual='"+ema+"' ,typeID='"+tid+"'WHERE equipmentID ='"+eid+"';";
				state.executeUpdate(sql);
				rs.close();
				rs2.close();
				state.close();
				state2.close();
				conn.close();
				return true;//ɾ���ɹ�
			}else {
				rs.close();
				rs2.close();
				state.close();
				state2.close();
				conn.close();
				return false; //û�ҵ�
			}
			
		}else {
			rs.close();
			state.close();
			conn.close();
			return false; //û�ҵ�
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		CompanyInitialize.initialize("C00003");
	}
}
