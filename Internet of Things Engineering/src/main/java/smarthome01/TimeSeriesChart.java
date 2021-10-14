package smarthome01;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class TimeSeriesChart {
	ChartPanel frame1;
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/smarthome2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "421132";
	String item,dataType;
	public TimeSeriesChart(String item,String dataType){
		this.item = item;
		this.dataType = dataType;
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(dataType + "-Date Chart", "Date",dataType,xydataset,true, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("MM-dd"));
        frame1=new ChartPanel(jfreechart,true);
        dateaxis.setLabelFont(new Font("Microsoft YaHei UI",Font.BOLD,14));         
        dateaxis.setTickLabelFont(new Font("Microsoft YaHei UI",Font.BOLD,12));  
        ValueAxis rangeAxis=xyplot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("Microsoft YaHei UI",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("Microsoft YaHei UI",Font.BOLD,20));

	} 
	private XYDataset createDataset() {
		TimeSeries timeseries = new TimeSeries(dataType);
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt = conn.createStatement();
			String sql = "select time," + dataType + " from data where itemID = '" + item + "' and label = 0;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String date = rs.getString("time");
				int year = Integer.parseInt(date.substring(0,4));
				int month = Integer.parseInt(date.substring(5,7));
				int day = Integer.parseInt(date.substring(8,10));
				timeseries.add(new Day(day,month,year),rs.getDouble(dataType));
				System.out.println("time: " + rs.getString("time") + dataType + " : " + rs.getDouble(dataType)
									+ "year: " + year + "month: " + month + "day: " + day);
			}
			rs.close();
            stmt.close();
            conn.close();
		}
		catch(SQLException se){
			System.out.println("SQLExpection:" + se);
		}
		catch(ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:" + e);
		}
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);
        return timeseriescollection;
    }
	public ChartPanel getChartPanel(){
		return frame1;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		JFrame frame=new JFrame("Java Data statistics chart");
		frame.add(new TimeSeriesChart("E00002-01","temperature").getChartPanel()); 
		frame.setBounds(200, 200, 400, 400);
		frame.setVisible(true);	
	}
}
