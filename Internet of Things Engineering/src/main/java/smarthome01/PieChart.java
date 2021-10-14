package smarthome01;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart {
	public int r1,r2,r3;
	ChartPanel frame1;
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/smarthome2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "421132";
	String item,dataType;
	public PieChart(String item,String dataType){
		this.item = item;
		this.dataType = dataType;
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D(dataType + " Distribution",data,true,false,false);
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");
		NumberFormat nf = NumberFormat.getNumberInstance();
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);
		pieplot.setLabelGenerator(sp1);
		
		pieplot.setNoDataMessage("No DataMessage");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);
		
		pieplot.setIgnoreNullValues(true);
		pieplot.setIgnoreZeroValues(true);
	    frame1=new ChartPanel (chart,true);
	    chart.getTitle().setFont(new Font("Microsoft YaHei UI",Font.BOLD,20));
		PiePlot piePlot= (PiePlot) chart.getPlot();
		piePlot.setLabelFont(new Font("Microsoft YaHei UI",Font.BOLD,10));
		chart.getLegend().setItemFont(new Font("Microsoft YaHei UI",Font.BOLD,10));
	}
	
    public DefaultPieDataset getDataSet() {
    	try {
    		Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt = conn.createStatement();
			String sql1 = "select count(*) from data where itemID = '" + item + "' and " + dataType + " > 0 and " + dataType + " < 30 and label = 0;";
			ResultSet rs1 = stmt.executeQuery(sql1);
			while(rs1.next()) {
				r1 = rs1.getInt("count(*)");
			}
			String sql2 = "select count(*) from data where itemID = '" + item + "' and " + dataType + " > 30 and " + dataType + " < 60 and label = 0;";
			ResultSet rs2 = stmt.executeQuery(sql2);
			while(rs2.next()) {
				r2 = rs2.getInt("count(*)");
			}
			String sql3 = "select count(*) from data where itemID = '" + item + "' and " + dataType +" > 60 and " + dataType +" < 100 and label = 0;";
			ResultSet rs3 = stmt.executeQuery(sql3);
			while(rs3.next()) {
				r3 = rs3.getInt("count(*)");
			}
    	}
    	catch(SQLException se){
			System.out.println("SQLExpection:" + se);
		}
		catch(ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:" + e);
		}
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("0-30",r1);
        dataset.setValue("31-60",r2);
        dataset.setValue("61-100",r3);
        return dataset;
    }
    
    public ChartPanel getChartPanel(){
    	return frame1;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    	JFrame frame=new JFrame("Java Data statistics chart");
    	frame.add(new PieChart("E00006-01","decibel").getChartPanel()); 
    	frame.setBounds(200, 200, 400, 400);
    	frame.setVisible(true);
    }
}
