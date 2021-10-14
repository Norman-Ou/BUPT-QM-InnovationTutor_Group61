package smarthome01;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class chart {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

	}
	
	
	public static void createColumnChart(String dt,String val,String iid,String cid,String ts,String te) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Statement state1 = Login.conn.createStatement();

		int number1;int number2;int number3;
		
		String sql1="select count(*)\r\n"
				+ "from data d,item i,equipment e\r\n"
				+ "where  d.time between '"+ts+"' and '"+te+"' and   d.ItemID=i.ItemID and d.ItemID='"+iid+"' and i.equipmentID=e.equipmentID and e.compID='"+cid+"' and d.label=0 and d."+dt+"<"+val+"  and d."+dt+" is not null;";
		ResultSet rs1 = state1.executeQuery(sql1);
		rs1.next();
		number1=Integer.parseInt(rs1.getString("count(*)"))  ;
		
		
		String sql2="select count(*)\r\n"
				+ "from data d,item i,equipment e\r\n"
				+ "where  d.time between '"+ts+"' and '"+te+"' and  d.ItemID=i.ItemID and d.ItemID='"+iid+"' and i.equipmentID=e.equipmentID and e.compID='"+cid+"' and d.label=0 and d."+dt+"="+val+" and d."+dt+" is not null ;";
		rs1 = state1.executeQuery(sql2);
		rs1.next();
		number2=Integer.parseInt(rs1.getString("count(*)"))  ;
		
		
		String sql3="select count(*)\r\n"
				+ "from data d,item i,equipment e\r\n"
				+ "where  d.time between '"+ts+"' and '"+te+"' and d.ItemID=i.ItemID and d.ItemID='"+iid+"' and i.equipmentID=e.equipmentID and e.compID='"+cid+"' and d.label=0 and d."+dt+">"+val+"  and d."+dt+" is not null;";
		rs1 = state1.executeQuery(sql3);
		rs1.next();
		number3=Integer.parseInt(rs1.getString("count(*)"))  ;
		
		
		rs1.close();		state1.close();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.addValue(number1, "<"+val, "");
		dataset.addValue(number2, "="+val, "");
		dataset.addValue(number3, ">"+val, "");


		JFreeChart chart = ChartFactory.createBarChart("Data of "+iid, dt, "Amount",
				dataset, PlotOrientation.VERTICAL, true, true, true);

		CategoryPlot cp = chart.getCategoryPlot();
		cp.setBackgroundPaint(ChartColor.WHITE);
		cp.setRangeGridlinePaint(ChartColor.GRAY);

		 ChartFrame frame=new ChartFrame ("",chart,true);
		 frame.pack();
		 frame.setVisible(true);

	}
}



