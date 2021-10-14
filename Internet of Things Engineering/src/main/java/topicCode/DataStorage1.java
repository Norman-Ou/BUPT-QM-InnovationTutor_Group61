package topicCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import smarthome01.Login;

public class DataStorage1 {

	public static void dataStorage(String[] totalData,int index) throws ClassNotFoundException, SQLException {
		//1.导入驱动
		//2.加载具体驱动类

		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		Statement state2 = Login.conn.createStatement();
		String sql2= " select count(*) as number from data;";
		ResultSet rs2 = state2.executeQuery(sql2);//检查data表中有多少条数据
		rs2.next();
		String dataNumberstr= rs2.getString("number");
		rs2.close();
		state2.close();//关闭第二条指令
		int dataNumber = Integer.parseInt(dataNumberstr)+1;//这里加一是为了直接让datanumber等于接下来要插入的id

		int dataNumberCopy=dataNumber;
		int i=0;int remainder=1;//取整,这里设为1是为了能进循环正常操作
		for(;remainder>=1;i++) {
			remainder = dataNumber/10;
			dataNumber/=10;
		}//通过这个循环来知道现在的条数是几位数，之后方便往前面加0
		String zero="";
		while(i!=5) {
			zero=zero+"0";
			i++;
		}
		zero=zero+dataNumberCopy;//这里注意是copy，因为之前的datanumber已经被除完了
		String id="D"+zero;

		Statement state3 = Login.conn.createStatement();
		String sql3="";

		//1.
//		switch(index) {
//		case 1:/*灯brightness*/											  /*color*/

			sql3= " INSERT INTO data(dataID, itemID, time,brightness,label,color) VALUES ('"+id+"', 'E00001-01', now(),"+totalData[0]/*!!!!!!!!!!*/+",0,"+totalData[1]+")";							
			state3.executeUpdate(sql3);

//			break;
//		case 2:/*距离传感器distance*/

			
			dataNumber=dataNumberCopy+1;
			dataNumberCopy=dataNumber;
			i=0;remainder=1;//取整,这里设为1是为了能进循环正常操作

			for(;remainder>=1;i++) {
				remainder = dataNumber/10;
				dataNumber/=10;
			}//通过这个循环来知道现在的条数是几位数，之后方便往前面加0
			zero="";
			while(i!=5) {
				zero=zero+"0";
				i++;
			}
			zero=zero+dataNumberCopy;//这里注意是copy，因为之前的datanumber已经被除完了
			id="D"+zero;
			
			
			
			
			sql3= " INSERT INTO data(dataID, itemID, time,distance,label) VALUES ('"+id+"', 'E00002-01', now(),"+totalData[2]+",0)";
			state3.executeUpdate(sql3);

//			break;
//		case 3:/*温湿度传感器temperature humidity*/
			
			
			dataNumber=dataNumberCopy+1;
			dataNumberCopy=dataNumber;
			i=0;remainder=1;//取整,这里设为1是为了能进循环正常操作

			for(;remainder>=1;i++) {
				remainder = dataNumber/10;
				dataNumber/=10;
			}//通过这个循环来知道现在的条数是几位数，之后方便往前面加0
			zero="";
			while(i!=5) {
				zero=zero+"0";
				i++;
			}
			zero=zero+dataNumberCopy;//这里注意是copy，因为之前的datanumber已经被除完了
			id="D"+zero;
			
			
			sql3= " INSERT INTO data(dataID, itemID, time,temperature,humidity,label) VALUES ('"+id+"', 'E00003-01', now(),"+totalData[3]+","+totalData[4]+",0)";
			state3.executeUpdate(sql3);

//			break;
//		case 4:/*声音传感器decibel*/

			
			dataNumber=dataNumberCopy+1;
			dataNumberCopy=dataNumber;
			i=0;remainder=1;//取整,这里设为1是为了能进循环正常操作

			for(;remainder>=1;i++) {
				remainder = dataNumber/10;
				dataNumber/=10;
			}//通过这个循环来知道现在的条数是几位数，之后方便往前面加0
			zero="";
			while(i!=5) {
				zero=zero+"0";
				i++;
			}
			zero=zero+dataNumberCopy;//这里注意是copy，因为之前的datanumber已经被除完了
			id="D"+zero;
			
			
			
			sql3= " INSERT INTO data(dataID, itemID, time,decibel,label) VALUES ('"+id+"', 'E00004-01', now(),"+totalData[5]+",0)";
			state3.executeUpdate(sql3);
//			break;
//		case 5:
			/*报警信息*/
//			idnew = "D"+dataNumberCopy+4; 
//			if(totalData[0].equals("1"))
				sql3="INSERT INTO alarm(alarmTime, label) VALUES(now(), "+totalData[6]+" )";
				state3.executeUpdate(sql3);
				sql3= "INSERT INTO alarm2(alarmTime, label) VALUES(now(), "+totalData[6]+" )";
//				Statement state4 = Login.conn.createStatement();
				state3.executeUpdate(sql3);
				state3.close();
//			break;
//		}

		state3.close();//第3条指令关闭
//		Login.conn.close();
		
		
		
	}
}


//totalData[0]="E00001-01";
		//3.建立链接

//		final String URL = "jdbc:mysql://localhost:3306/smarthome2?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
//		final String USERNAME = "root";
//		final String PASSWORD = "421132";
//		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

		//4.发送sql，执行增删改。。。查
//		Statement state = conn.createStatement();
//
//
//		String sql= " select b.typeID from item a,equipment b,equipmenttype c\r\n"
//				+ "where a.ItemID="+"'E00001-01'"/*totalData[0]这里目前只测试灯*/+" and a.equipmentID=b.equipmentID and b.typeID = c.typeID;";
////		System.out.println(sql);
//		ResultSet rs = state.executeQuery(sql);//先通过totalData[0]检查数据类型
//		rs.next();
//		String typeID= rs.getString("typeID");
////		System.out.println(typeID);
//		rs.close();
//		state.close();//关闭第一条指令的结果集和state
//		int index = Character.getNumericValue(typeID.charAt(5));//取出类型T00002中的第6个char，并转换为int，进行switch判断
