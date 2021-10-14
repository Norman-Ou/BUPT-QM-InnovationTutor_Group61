package smarthome01;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart1 {
	ChartPanel frame1;
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/smarthome2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "421132";
	String item,dataType;
	int value,large,equal,small;
	public  BarChart1(String item,String dataType,int value){
		this.item = item;
		this.dataType = dataType;
		this.value = value;
		CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(
        					dataType + "-Number Chart", // ͼ�����
                            dataType, // Ŀ¼�����ʾ��ǩ
                            "Number", // ��ֵ�����ʾ��ǩ
                            dataset, // ���ݼ�
                            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                            true,           // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
                            false,          // �Ƿ����ɹ���
                            false           // �Ƿ�����URL����
                            );
        CategoryPlot plot=chart.getCategoryPlot();
        CategoryAxis domainAxis=plot.getDomainAxis();         
         domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         
         domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  
         ValueAxis rangeAxis=plot.getRangeAxis();
         rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
          chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
          chart.getTitle().setFont(new Font("����",Font.BOLD,20));
          
         frame1=new ChartPanel(chart,true);        
         
	}
	   private CategoryDataset getDataSet() {
	    	try {
	    		Class.forName(JDBC_DRIVER);
				Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt = conn.createStatement();
				String sql1 = "select count(*) from data where itemID = '" + item + "' and " + dataType + " > " + value + " and label = 0;";
				ResultSet rs1 = stmt.executeQuery(sql1);
				while(rs1.next()) {
					large = rs1.getInt("count(*)");
				}

				String sql2 = "select count(*) from data where itemID = '" + item + "' and " + dataType + " = " + value + " and label = 0;";
				ResultSet rs2 = stmt.executeQuery(sql2);
				while(rs2.next()) {
					equal = rs2.getInt("count(*)");
				}
				
				String sql3 = "select count(*) from data where itemID = '" + item + "' and " + dataType + " < " + value + " and label = 0;";
				ResultSet rs3 = stmt.executeQuery(sql3);
				while(rs3.next()) {
					small = rs3.getInt("count(*)");
				}
	    	}
	    	catch(SQLException se){
				System.out.println("SQLExpection:" + se);
			}
			catch(ClassNotFoundException e) {
				System.out.println("ClassNotFoundException:" + e);
			}
	       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	       dataset.addValue(large, "larger than " + value, "larger than " + value);
	       dataset.addValue(equal, "equal to " + value, "equal to " + value);
	       dataset.addValue(small, "smaller than  " + value, "smaller than  " + value);
	       return dataset;
}

	public ChartPanel getChartPanel(){
		return frame1;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		JFrame frame=new JFrame("Java Data statistics chart");
		frame.add(new BarChart1("E00006-01","decibel",33).getChartPanel()); 
		frame.setBounds(200, 200, 400, 400);
		frame.setVisible(true);	
	}
	
}

