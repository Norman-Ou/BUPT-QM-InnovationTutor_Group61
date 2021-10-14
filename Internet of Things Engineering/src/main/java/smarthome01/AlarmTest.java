package smarthome01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AlarmTest implements Runnable {

	@Override
	public void run() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Statement state = Login.conn.createStatement();
			ResultSet rs;
			boolean x=true;
			while(x) {
				String sql = "select * from alarm2 x where x.alarmTime= (select max(alarmTime) from alarm2);";
				rs = state.executeQuery(sql);
				rs.next();
				String label= rs.getString("label");
				System.out.println(label);
				if(label.equals("1")) {
					JOptionPane.showMessageDialog(null, "报警信息提示", "Waring", 2);
					System.out.println("-----------------------sql------");
					sql="INSERT INTO alarm2(alarmTime, label) VALUES(now(), 0 )";
					state.executeUpdate(sql);
					System.out.println("-----------------------sql2------");
					sql="INSERT INTO alarm(alarmTime, label) VALUES(now(), 0 )";
					state.executeUpdate(sql);
					System.out.println("-----------------------sql3------");
					sql= "select count(*) as cmdNumber from command;";
					rs = state.executeQuery(sql);
					System.out.println("-----------------------sql4------");
					rs.next();
					int cmdNumber= Integer.parseInt(rs.getString("cmdNumber"));
					cmdNumber++;
					String closeAlarm="shutdown";//关闭指令的命令
					sql="INSERT INTO command(cmd, cmdid) VALUES('"+closeAlarm+"', '"+cmdNumber+"')";
					state.executeUpdate(sql);
					System.out.println("-----------------------sql5------");
				}
				Thread.sleep(1000);
			}

		}catch(Exception e) {
			
		}
		
	}
	
}
