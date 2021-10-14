package smarthome01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

//import org.jfree.chart.JFreeChart;

import java.awt.Component;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Adpage1 extends JFrame {
//	public static final String URL = "jdbc:mysql://localhost:3306/smarthome2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//	public static final String CLIENT = "root";
//	public static final String PSW = "421132";
	
	public Connection conn = null;
	private JPanel contentPane;
	private JTextField mnametext;
	private JTextField mIDtext;
	private JTextField newmname;
	private JTextField newphone;
	private JTextField newhome;
	private JTextField textField;
	private JTextField modmID;
	private JTextField modmname;
	private JTextField modmhome;
	private JTextField hnametext;
	private JTextField hIDtext;
	private JTextField hname;
	private JTextField haddress;
	private JTextField textField_3;
	private JTextField modhID;
	private JTextField modhname;
	private JTextField modhadd;
	private JTextField enametext;
	private JTextField eIDtext;
	private JTextField newmID;
	private JTextField hID;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField modeID;
	private JTextField modename;
	private JTextField modemanual;
	private JTextField modecomp;
	private JTextField modetype;
	private JTextField tnametext;
	private JTextField tIDtext;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField modtID;
	private JTextField modtname;
	private JTable table_1;
	private JTable table;
	private JTable table_2;
	private JTable table_3;
	private JTextField sdata1;
	private JTextField sdata2;
	private JTextField sdata3;
	private JTable table_4;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private DefaultTableModel model3;
	private DefaultTableModel model4;
	private DefaultTableModel model5;
	private JButton mapply;
	private JButton happly;
	private JButton eapply;
	private JTextField modphone;
	private JLabel result1;
	private JLabel result2;
	private JLabel result4;
	private JComboBox c1;
	private JComboBox c2;
	private JComboBox c3;
	private JComboBox c4;
	private JComboBox c5;
	private JComboBox c6;
	private JComboBox c7;
	private JComboBox c8;
	private JComboBox c9;
	private JComboBox c10;
	private JComboBox c11;
	private JComboBox c12;
	private JTextField textField_8;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField hpassword;
//	private JFreeChart chart1; 

	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Adpage1 frame = new Adpage1();
		frame.setVisible(true);
	}
	
	/**
	 * Create the frame.
	 */
	public Adpage1() throws ClassNotFoundException,SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			conn = DriverManager.getConnection(Admin.URL, Admin.CLIENT, Admin.PSW);
			System.out.println("Connection established!");
		} 
		catch (ClassNotFoundException e) { System.out.println("Exception:" + e.getMessage()); }
		catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 933, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane administrator = new JTabbedPane(JTabbedPane.LEFT);
		administrator.setBounds(5, 20, 891, 489);
		contentPane.add(administrator);
		
		JPanel member = new JPanel();
		administrator.addTab("Member Management", null, member, null);
		member.setLayout(null);
		
		JTabbedPane member_1 = new JTabbedPane(JTabbedPane.TOP);
		member_1.setBounds(0, 0, 714, 141);
		member.add(member_1);
		
		JPanel mSearch = new JPanel();
		member_1.addTab("Search", null, mSearch, null);
		mSearch.setLayout(null);
		
		JButton search1 = new JButton("Search name");
		search1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!mnametext.getText().equals("")) {
					try {
						model1.setRowCount(0);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from member where membername like '%"+mnametext.getText()+"%' and label = 0");
						while (myRs.next()) {
							model1.addRow(new String[]{myRs.getString("memberID"),myRs.getString("memberName"),myRs.getString("memberGender"),myRs.getString("memberPhone"),myRs.getString("homeID")});
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
				}
				
			}
		});
		search1.setBounds(254, 6, 150, 23);
		mSearch.add(search1);
		
		mnametext = new JTextField();
		mnametext.setBounds(80, 7, 164, 21);
		mSearch.add(mnametext);
		mnametext.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter name");
		lblNewLabel_1.setBounds(10, 10, 100, 15);
		mSearch.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter ID");
		lblNewLabel_2.setBounds(10, 38, 54, 15);
		mSearch.add(lblNewLabel_2);
		
		mIDtext = new JTextField();
		mIDtext.setBounds(80, 35, 164, 21);
		mSearch.add(mIDtext);
		mIDtext.setColumns(10);
		
		JButton search2 = new JButton("Search ID");
		search2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!mIDtext.getText().equals("")) {
					try {
						model1.setRowCount(0);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from member where memberID = '"+mIDtext.getText()+"' and label = 0");
						while (myRs.next()) {
							model1.addRow(new String[]{myRs.getString("memberID"),myRs.getString("memberName"),myRs.getString("memberGender"),myRs.getString("memberPhone"),myRs.getString("homeID")});
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
				}
			}
		});
		search2.setBounds(254, 34, 150, 23);
		mSearch.add(search2);
		
		JLabel lblNewLabel_3 = new JLabel("Show all members");
		lblNewLabel_3.setBounds(10, 75, 129, 15);
		mSearch.add(lblNewLabel_3);
		
		JButton search3 = new JButton("Show");
		search3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model1.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from member where label = 0");

					while (myRs.next()) {
						model1.addRow(new String[]{myRs.getString("memberID"),myRs.getString("memberName"),myRs.getString("memberGender"),myRs.getString("memberPhone"),myRs.getString("homeID")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		search3.setBounds(130, 71, 76, 23);
		mSearch.add(search3);
		
		JPanel mAdd = new JPanel();
		member_1.addTab("Add", null, mAdd, null);
		mAdd.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Name");
		lblNewLabel_4.setBounds(10, 10, 54, 15);
		mAdd.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Gender");
		lblNewLabel_5.setBounds(10, 35, 54, 15);
		mAdd.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Phone number");
		lblNewLabel_6.setBounds(10, 60, 87, 15);
		mAdd.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("HomeID");
		lblNewLabel_7.setBounds(520, 10, 54, 15);
		mAdd.add(lblNewLabel_7);
		
		newmname = new JTextField();
		newmname.setBounds(120, 7, 102, 21);
		mAdd.add(newmname);
		newmname.setColumns(10);
		
		final JComboBox newgender = new JComboBox();
		newgender.setModel(new DefaultComboBoxModel(new String[] {"", "M", "F"}));
		newgender.setBounds(120, 31, 102, 23);
		mAdd.add(newgender);
		
		newphone = new JTextField();
		newphone.setBounds(120, 57, 102, 21);
		mAdd.add(newphone);
		newphone.setColumns(10);
		
		newhome = new JTextField();
		newhome.setBounds(584, 7, 102, 21);
		mAdd.add(newhome);
		newhome.setColumns(10);
		
		final JLabel result = new JLabel("Please complete information!");
		result.setVisible(false);
		result.setForeground(Color.RED);
		result.setBounds(295, 35, 217, 40);
		mAdd.add(result);
		
		JButton refresh1 = new JButton("Refresh");
		refresh1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model1.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from member where label = 0");

					while (myRs.next()) {
						model1.addRow(new String[]{myRs.getString("memberID"),myRs.getString("memberName"),myRs.getString("memberGender"),myRs.getString("memberPhone"),myRs.getString("homeID")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		refresh1.setHorizontalAlignment(SwingConstants.LEADING);
		refresh1.setBounds(572, 78, 114, 23);
		mAdd.add(refresh1);
		
		JButton madd = new JButton("Add member");
		madd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(newmID.getText().equals("")||newmname.getText().equals("")||newphone.getText().equals("")||newhome.getText().equals("")||newgender.getSelectedIndex()==0) {
						result.setVisible(true);
						result.setText("Please complete information!");
					}
					else {
						
						result.setVisible(false);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from member where memberID = '"+newmID.getText()+"'");
						if(myRs.next()) {
							result.setText("ID already exists!");
							result.setVisible(true);
						}
						else {
							myRs = myStmt.executeQuery("select * from home where homeID = '"+newhome.getText()+"' and label = 0");
							if(!myRs.next()) {
								result.setText("Home does not exists!");
								result.setVisible(true);
							}
							else {
								myRs = myStmt.executeQuery("select count(*) from member where homeID = '"+newhome.getText()+"' and label = 0");
								myRs.next();
								if(myRs.getInt("count(*)")>=10) {
									result.setText("Home member exceed!");
									result.setVisible(true);
								}
								else {
									String up = "insert into member values('"+newmID.getText()+"','"+newmname.getText()+"','"+newgender.getSelectedItem().toString()+"','"+newphone.getText()+"','"+MD5Tools.stringToMD5(newmID.getText())+"','"+newhome.getText()+"','0')";
									myStmt.executeUpdate(up);
									result.setText("Successfully added!");
									result.setVisible(true);
								}
							}
						}

					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		madd.setHorizontalAlignment(SwingConstants.LEADING);
		madd.setBounds(572, 45, 114, 23);
		mAdd.add(madd);
		
		JLabel lblNewLabel_28 = new JLabel("ID");
		lblNewLabel_28.setBounds(245, 10, 54, 15);
		mAdd.add(lblNewLabel_28);
		
		newmID = new JTextField();
		newmID.setBounds(322, 7, 108, 21);
		mAdd.add(newmID);
		newmID.setColumns(10);
		
		JPanel mDelete = new JPanel();
		member_1.addTab("Delete", null, mDelete, null);
		mDelete.setLayout(null);
		
		JButton refresh2 = new JButton("Refresh");
		refresh2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model1.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from member where label = 0");

					while (myRs.next()) {
						model1.addRow(new String[]{myRs.getString("memberID"),myRs.getString("memberName"),myRs.getString("memberGender"),myRs.getString("memberPhone"),myRs.getString("homeID")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		refresh2.setBounds(550, 68, 136, 23);
		mDelete.add(refresh2);
		
		JLabel lblNewLabel_8 = new JLabel("MemberID");
		lblNewLabel_8.setBounds(10, 10, 79, 15);
		mDelete.add(lblNewLabel_8);
		
		textField = new JTextField();
		textField.setBounds(87, 7, 110, 21);
		mDelete.add(textField);
		textField.setColumns(10);
		
		JButton mdelete = new JButton("Delete");
		mdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equals("")) {
					try {
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from member where memberID = '"+textField.getText()+"' and label = 0");
						if(myRs.next()) {
							int n = JOptionPane.showConfirmDialog(null,"Whether to delete ID '"+textField.getText()+"'","Delete?",JOptionPane.YES_NO_OPTION);
							if(n==0) {
								String up = "update member set label = 1 where memberID = '"+textField.getText()+"'";
								myStmt.executeUpdate(up);
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"ID not found!");
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
				}
				
			}
		});
		mdelete.setBounds(550, 35, 136, 23);
		mDelete.add(mdelete);
		
		JPanel mModify = new JPanel();
		member_1.addTab("Modify", null, mModify, null);
		mModify.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("Please enter memberID and press modify");
		lblNewLabel_9.setBounds(10, 10, 289, 15);
		mModify.add(lblNewLabel_9);
		
		JButton refresh3 = new JButton("Refresh");
		refresh3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model1.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from member where label = 0");

					while (myRs.next()) {
						model1.addRow(new String[]{myRs.getString("memberID"),myRs.getString("memberName"),myRs.getString("memberGender"),myRs.getString("memberPhone"),myRs.getString("homeID")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		refresh3.setBounds(549, 70, 137, 23);
		mModify.add(refresh3);
		
		modmID = new JTextField();
		modmID.setBounds(277, 7, 66, 21);
		mModify.add(modmID);
		modmID.setColumns(10);
		
		final JLabel lblNewLabel_10 = new JLabel("Name");
		lblNewLabel_10.setVisible(false);
		lblNewLabel_10.setBounds(10, 35, 54, 15);
		mModify.add(lblNewLabel_10);
		
		modmname = new JTextField();
		modmname.setVisible(false);
		modmname.setBounds(60, 32, 101, 21);
		mModify.add(modmname);
		modmname.setColumns(10);
		
		final JLabel lblNewLabel_11 = new JLabel("Gender");
		lblNewLabel_11.setVisible(false);
		lblNewLabel_11.setBounds(188, 60, 54, 15);
		mModify.add(lblNewLabel_11);
		
		final JComboBox modgender = new JComboBox();
		modgender.setVisible(false);
		modgender.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		modgender.setBounds(251, 56, 92, 23);
		mModify.add(modgender);
		
		final JLabel lblNewLabel_12 = new JLabel("HomeID");
		lblNewLabel_12.setVisible(false);
		lblNewLabel_12.setBounds(10, 60, 54, 15);
		mModify.add(lblNewLabel_12);
		
		modmhome = new JTextField();
		modmhome.setVisible(false);
		modmhome.setBounds(60, 57, 101, 21);
		mModify.add(modmhome);
		modmhome.setColumns(10);
		
		final JLabel lblNewLabel_13 = new JLabel("Press apply when you finish modifying");
		lblNewLabel_13.setVisible(false);
		lblNewLabel_13.setBounds(260, 87, 289, 15);
		mModify.add(lblNewLabel_13);
				
		final JRadioButton password = new JRadioButton("Reset password");
		password.setVisible(false);
		password.setBounds(372, 56, 121, 23);
		mModify.add(password);
		
		final JLabel lblNewLabel_60 = new JLabel("Phone");
		lblNewLabel_60.setVisible(false);
		lblNewLabel_60.setBounds(10, 87, 54, 15);
		mModify.add(lblNewLabel_60);
		
		modphone = new JTextField();
		modphone.setVisible(false);
		modphone.setBounds(60, 84, 166, 21);
		mModify.add(modphone);
		modphone.setColumns(10);
		
		JButton mmod = new JButton("Modify");
		mmod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!modmID.getText().equals(""))
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from member where memberID = '"+modmID.getText()+"' and label = 0");
					if(!myRs.next()) {
						JOptionPane.showMessageDialog(null,"ID does not exist!","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					else {
						modmID.setEditable(false);
						lblNewLabel_10.setVisible(true);
						lblNewLabel_11.setVisible(true);
						lblNewLabel_12.setVisible(true);
						lblNewLabel_13.setVisible(true);
						lblNewLabel_60.setVisible(true);
						password.setSelected(false);
						modmname.setText(myRs.getString("membername"));
						modmname.setVisible(true);
						if(myRs.getString("membergender").equals("M"))
							modgender.setSelectedIndex(0);
						else
							modgender.setSelectedIndex(1);
						modgender.setVisible(true);
						modmhome.setText(myRs.getString("homeID"));
						modmhome.setVisible(true);
						password.setVisible(true);
						mapply.setVisible(true);
						modphone.setText(myRs.getString("memberphone"));
						modphone.setVisible(true);
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		mmod.setBounds(549, 6, 137, 23);
		mModify.add(mmod);
		
		mapply = new JButton("Apply");
		mapply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from home where homeID = '"+modmhome.getText()+"' and label = 0");
					if(!myRs.next())
						JOptionPane.showMessageDialog(null,"Home does not exist!","ERROR",JOptionPane.ERROR_MESSAGE);
					else {
						
						myRs = myStmt.executeQuery("select count(*) from member where homeID = '"+modmhome.getText()+"' and label = 0");
						myRs.next();
						//System.out.println(myRs.getInt("count(*)"));
						if(myRs.getInt("count(*)")>10) {
							JOptionPane.showMessageDialog(null,"Home member exceed!","ERROR",JOptionPane.ERROR_MESSAGE);
						}
						else {
							
							if(modmname.getText().equals("")||modphone.getText().equals("")||modmhome.getText().equals("")) {
								JOptionPane.showMessageDialog(null,"Please complete information!","ERROR",JOptionPane.ERROR_MESSAGE);
							}
							else {
								String up = "update member set membername = '"+modmname.getText()+"',membergender = '"+modgender.getSelectedItem().toString()+"',homeID = '"+ modmhome.getText()+"',memberPhone = '"+modphone.getText()+"' where memberID = '"+modmID.getText()+"'";
								myStmt.executeUpdate(up);
								if(password.isSelected()) {
									up = "update member set memberPWD = '"+MD5Tools.stringToMD5(modmID.getText())+"' where memberID = '"+modmID.getText()+"'";
									myStmt.executeUpdate(up);
								}
								modmID.setEditable(true);
								JOptionPane.showMessageDialog(null,"Successfully modified!","",JOptionPane.PLAIN_MESSAGE);
								lblNewLabel_10.setVisible(false);
								lblNewLabel_11.setVisible(false);
								lblNewLabel_12.setVisible(false);
								lblNewLabel_13.setVisible(false);
								lblNewLabel_60.setVisible(false);
								modmname.setVisible(false);
								modgender.setVisible(false);
								modmhome.setVisible(false);
								password.setSelected(false);
								password.setVisible(false);
								mapply.setVisible(false);
								modphone.setVisible(false);
							}
						}
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		mapply.setVisible(false);
		mapply.setBounds(549, 37, 137, 23);
		mModify.add(mapply);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 139, 689, 345);
		member.add(scrollPane);
		
		table_1 = new JTable();
		model1 = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Name", "Gender", "Phone", "HomeID"
				}
			);
		table_1.setModel(model1);
		scrollPane.setViewportView(table_1);
		
		JPanel home = new JPanel();
		administrator.addTab("Home Management", null, home, null);
		home.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 714, 135);
		home.add(tabbedPane_1);
		
		JPanel hSearch = new JPanel();
		tabbedPane_1.addTab("Search", null, hSearch, null);
		hSearch.setLayout(null);
		
		JLabel lblNewLabel_14 = new JLabel("Enter name");
		lblNewLabel_14.setBounds(10, 10, 79, 15);
		hSearch.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Enter ID");
		lblNewLabel_15.setBounds(10, 38, 79, 15);
		hSearch.add(lblNewLabel_15);
		
		hnametext = new JTextField();
		hnametext.setBounds(86, 7, 142, 21);
		hSearch.add(hnametext);
		hnametext.setColumns(10);
		
		hIDtext = new JTextField();
		hIDtext.setBounds(86, 35, 142, 21);
		hSearch.add(hIDtext);
		hIDtext.setColumns(10);
		
		JButton search4 = new JButton("Search name");
		search4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!hnametext.getText().equals("")) {
					try {
						model2.setRowCount(0);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from home where homename like '%"+hnametext.getText()+"%' and label = 0");
						while (myRs.next()) {
							model2.addRow(new String[]{myRs.getString("homeID"),myRs.getString("homeName"),myRs.getString("homeAddress")});
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
				}
			}
		});
		search4.setBounds(238, 6, 121, 23);
		hSearch.add(search4);
		
		JButton search5 = new JButton("Search ID");
		search5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!hIDtext.getText().equals("")) {
					try {
						model2.setRowCount(0);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from home where homeID = '"+hIDtext.getText()+"' and label = 0");
						while (myRs.next()) {
							model2.addRow(new String[]{myRs.getString("homeID"),myRs.getString("homeName"),myRs.getString("homeAddress")});
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
				}
			}
		});
		search5.setBounds(238, 34, 121, 23);
		hSearch.add(search5);
		
		JLabel lblNewLabel_16 = new JLabel("Show all home");
		lblNewLabel_16.setBounds(10, 75, 121, 15);
		hSearch.add(lblNewLabel_16);
		
		JButton search6 = new JButton("Show");
		search6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model2.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from home where label = 0");

					while (myRs.next()) {
						model2.addRow(new String[]{myRs.getString("homeID"),myRs.getString("homeName"),myRs.getString("homeAddress")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		search6.setBounds(106, 71, 93, 23);
		hSearch.add(search6);
		
		JPanel hAdd = new JPanel();
		tabbedPane_1.addTab("Add", null, hAdd, null);
		hAdd.setLayout(null);
		
		JLabel lblNewLabel_17 = new JLabel("Name");
		lblNewLabel_17.setBounds(10, 10, 76, 15);
		hAdd.add(lblNewLabel_17);
		
		hname = new JTextField();
		hname.setBounds(96, 7, 141, 21);
		hAdd.add(hname);
		hname.setColumns(10);
		
		JLabel lblNewLabel_18 = new JLabel("Address");
		lblNewLabel_18.setBounds(10, 41, 76, 15);
		hAdd.add(lblNewLabel_18);
		
		haddress = new JTextField();
		haddress.setBounds(96, 38, 256, 21);
		hAdd.add(haddress);
		haddress.setColumns(10);
		
		JButton refresh4 = new JButton("Refresh");
		refresh4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model2.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from home where label = 0");

					while (myRs.next()) {
						model2.addRow(new String[]{myRs.getString("homeID"),myRs.getString("homeName"),myRs.getString("homeAddress")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		refresh4.setBounds(587, 67, 112, 23);
		hAdd.add(refresh4);
		
		JButton hadd = new JButton("Add home");
		hadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(hID.getText().equals("")||hname.getText().equals("")||haddress.getText().equals("")||hpassword.getText().equals("")) {
						result1.setVisible(true);
						result1.setText("Please complete information!");
					}
					else {
						result1.setVisible(false);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from home where homeID = '"+hID.getText()+"'");
						if(myRs.next()) {
							result1.setText("ID already exists!");
							result1.setVisible(true);
						}
						else {
							String up = "insert into home values('"+hID.getText()+"','"+hname.getText()+"','"+haddress.getText()+"','0','"+MD5Tools.stringToMD5(hpassword.getText())+"')";
							myStmt.executeUpdate(up);
							result1.setText("Successfully added!");
							result1.setVisible(true);
						}
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
				
			}
		});
		hadd.setBounds(587, 37, 112, 23);
		hAdd.add(hadd);
		
		result1 = new JLabel("");
		result1.setForeground(Color.RED);
		result1.setBounds(247, 69, 298, 15);
		hAdd.add(result1);
		
		JLabel lblNewLabel_29 = new JLabel("ID");
		lblNewLabel_29.setBounds(298, 10, 54, 15);
		hAdd.add(lblNewLabel_29);
		
		hID = new JTextField();
		hID.setBounds(362, 7, 66, 21);
		hAdd.add(hID);
		hID.setColumns(10);
		
		JLabel lblNewLabel_36 = new JLabel("Password");
		lblNewLabel_36.setBounds(10, 71, 58, 15);
		hAdd.add(lblNewLabel_36);
		
		hpassword = new JTextField();
		hpassword.setBounds(96, 68, 141, 21);
		hAdd.add(hpassword);
		hpassword.setColumns(10);
		
		JPanel hDelete = new JPanel();
		tabbedPane_1.addTab("Delete", null, hDelete, null);
		hDelete.setLayout(null);
		
		JLabel lblNewLabel_19 = new JLabel("HomeID");
		lblNewLabel_19.setBounds(10, 10, 54, 15);
		hDelete.add(lblNewLabel_19);
		
		textField_3 = new JTextField();
		textField_3.setBounds(74, 10, 84, 21);
		hDelete.add(textField_3);
		textField_3.setColumns(10);
		
		JButton refresh5 = new JButton("Refresh");
		refresh5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model2.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from home where label = 0");

					while (myRs.next()) {
						model2.addRow(new String[]{myRs.getString("homeID"),myRs.getString("homeName"),myRs.getString("homeAddress")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		refresh5.setBounds(571, 72, 128, 23);
		hDelete.add(refresh5);
		
		JButton hdelete = new JButton("Delete");
		hdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField_3.getText().equals("")) {
					try {
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from home where homeID = '"+textField_3.getText()+"' and label = 0");
						if(myRs.next()) {
							int n = JOptionPane.showConfirmDialog(null,"Whether to delete ID '"+textField_3.getText()+"'","Delete?",JOptionPane.YES_NO_OPTION);
							if(n==0) {
								String up = "update home set label = 1 where homeID = '"+textField_3.getText()+"'";
								myStmt.executeUpdate(up);
								up = "update member set label = 1 where homeID = '"+textField_3.getText()+"'";
								myStmt.executeUpdate(up);
								up = "update item set label = 1 where homeID = '"+textField_3.getText()+"'";
								myStmt.executeUpdate(up);
								up = "update alarm set label = 1 where homeID = '"+textField_3.getText()+"'";
								myStmt.executeUpdate(up);
								up = "update data set label = 1 where itemID in (select itemID from item where homeID = '"+textField_3.getText()+"')";
								myStmt.executeUpdate(up);
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"ID not found!");
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
				}
			}
		});
		hdelete.setBounds(571, 39, 128, 23);
		hDelete.add(hdelete);
		
		JPanel hModify = new JPanel();
		tabbedPane_1.addTab("Modify", null, hModify, null);
		hModify.setLayout(null);
		
		JLabel lblNewLabel_21 = new JLabel("Please enter homeID and press modify");
		lblNewLabel_21.setBounds(10, 10, 248, 15);
		hModify.add(lblNewLabel_21);
		
		modhID = new JTextField();
		modhID.setBounds(268, 7, 79, 21);
		hModify.add(modhID);
		modhID.setColumns(10);
		
		JButton refresh6 = new JButton("Refresh");
		refresh6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model2.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from home where label = 0");

					while (myRs.next()) {
						model2.addRow(new String[]{myRs.getString("homeID"),myRs.getString("homeName"),myRs.getString("homeAddress")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		refresh6.setBounds(577, 74, 122, 23);
		hModify.add(refresh6);
		
		final JLabel lblNewLabel_22 = new JLabel("Name");
		lblNewLabel_22.setVisible(false);
		lblNewLabel_22.setBounds(10, 45, 54, 15);
		hModify.add(lblNewLabel_22);
		
		final JLabel lblNewLabel_23 = new JLabel("Address");
		lblNewLabel_23.setVisible(false);
		lblNewLabel_23.setBounds(10, 78, 54, 15);
		hModify.add(lblNewLabel_23);
		
		modhname = new JTextField();
		modhname.setVisible(false);
		modhname.setBounds(85, 42, 115, 21);
		hModify.add(modhname);
		modhname.setColumns(10);
		
		modhadd = new JTextField();
		modhadd.setVisible(false);
		modhadd.setBounds(85, 75, 248, 21);
		hModify.add(modhadd);
		modhadd.setColumns(10);
		
		final JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("reset password");
		rdbtnNewRadioButton_2.setVisible(false);
		rdbtnNewRadioButton_2.setBounds(266, 41, 127, 23);
		hModify.add(rdbtnNewRadioButton_2);
		
		final JLabel lblNewLabel_24 = new JLabel("Press apply when you finish modifying");
		lblNewLabel_24.setVisible(false);
		lblNewLabel_24.setBounds(343, 78, 289, 15);
		hModify.add(lblNewLabel_24);
		
		JButton hmod = new JButton("Modify");
		hmod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!modhID.getText().equals(""))
					try {
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from home where homeID = '"+modhID.getText()+"' and label = 0");
						if(!myRs.next()) {
							JOptionPane.showMessageDialog(null,"ID does not exist!","ERROR",JOptionPane.ERROR_MESSAGE);
						}
						else {
							modhID.setEditable(false);
							lblNewLabel_22.setVisible(true);
							lblNewLabel_23.setVisible(true);
							lblNewLabel_24.setVisible(true);
							rdbtnNewRadioButton_2.setVisible(true);
							modhname.setVisible(true);
							happly.setVisible(true);
							modhadd.setVisible(true);
							modhname.setText(myRs.getString("homeName"));
							modhadd.setText(myRs.getString("homeAddress"));
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
			}
		});
		hmod.setBounds(577, 6, 122, 23);
		hModify.add(hmod);
		
		happly = new JButton("Apply");
		happly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(modhname.getText().equals("")||modhadd.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"Please complete information!","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					else {
						Statement myStmt = conn.createStatement();
						//ResultSet myRs = myStmt.executeQuery("select * from home where homeID = '"+modmhome.getText()+"' and label = 0");
						String up = "update home set homename = '"+modhname.getText()+"',homeaddress = '"+ modhadd.getText()+"' where homeID = '"+modhID.getText()+"'";
						myStmt.executeUpdate(up);
						if(rdbtnNewRadioButton_2.isSelected()) {
							up = "update home set homePWD = '"+MD5Tools.stringToMD5(modhID.getText())+"' where homeID = '"+modhID.getText()+"'";
							myStmt.executeUpdate(up);
						}
						modhID.setEditable(true);
						JOptionPane.showMessageDialog(null,"Successfully modified!","",JOptionPane.PLAIN_MESSAGE);
						lblNewLabel_22.setVisible(false);
						lblNewLabel_23.setVisible(false);
						lblNewLabel_24.setVisible(false);
						rdbtnNewRadioButton_2.setVisible(false);
						rdbtnNewRadioButton_2.setSelected(false);
						modhname.setVisible(false);
						happly.setVisible(false);
						modhadd.setVisible(false);
					}
					
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		happly.setVisible(false);
		happly.setBounds(577, 41, 122, 23);
		hModify.add(happly);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 133, 688, 351);
		home.add(scrollPane_1);
		
		table = new JTable();
		model2 = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"HomeID", "HomeName", "Address"
				}
			);
		table.setModel(model2);
		scrollPane_1.setViewportView(table);
		
		JPanel equipment = new JPanel();
		administrator.addTab("Hardware Device Management", null, equipment, null);
		equipment.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 714, 187);
		equipment.add(tabbedPane);
		
		JPanel eSearch = new JPanel();
		tabbedPane.addTab("Search", null, eSearch, null);
		eSearch.setLayout(null);
		
		JLabel lblNewLabel_25 = new JLabel("Enter name");
		lblNewLabel_25.setBounds(10, 10, 102, 15);
		eSearch.add(lblNewLabel_25);
		
		JLabel lblNewLabel_26 = new JLabel("Enter ID");
		lblNewLabel_26.setBounds(10, 38, 102, 15);
		eSearch.add(lblNewLabel_26);
		
		enametext = new JTextField();
		enametext.setBounds(89, 7, 143, 21);
		eSearch.add(enametext);
		enametext.setColumns(10);
		
		eIDtext = new JTextField();
		eIDtext.setBounds(89, 35, 143, 21);
		eSearch.add(eIDtext);
		eIDtext.setColumns(10);
		
		JButton search7 = new JButton("Search name");
		search7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model3.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from equipment where equipmentname like '%"+enametext.getText()+"%' and label = 0");
					while (myRs.next()) {
						model3.addRow(new String[]{myRs.getString("equipmentID"),myRs.getString("equipmentName"),myRs.getString("equipmentManual"),myRs.getString("compID"),myRs.getString("typeID"),myRs.getString("equipImage")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		search7.setBounds(255, 6, 121, 23);
		eSearch.add(search7);
		
		JButton search8 = new JButton("Search ID");
		search8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!eIDtext.getText().equals("")) {
					try {
						model3.setRowCount(0);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from equipment where equipmentID = '"+eIDtext.getText()+"' and label = 0");
						while (myRs.next()) {
							model3.addRow(new String[]{myRs.getString("equipmentID"),myRs.getString("equipmentName"),myRs.getString("equipmentManual"),myRs.getString("compID"),myRs.getString("typeID"),myRs.getString("equipImage")});
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
				}
			}
		});
		search8.setBounds(255, 34, 121, 23);
		eSearch.add(search8);
		
		JLabel lblNewLabel_27 = new JLabel("Show all equipments");
		lblNewLabel_27.setBounds(10, 75, 143, 15);
		eSearch.add(lblNewLabel_27);
		
		JButton search9 = new JButton("Show");
		search9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model3.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from equipment where label = 0");

					while (myRs.next()) {
						model3.addRow(new String[]{myRs.getString("equipmentID"),myRs.getString("equipmentName"),myRs.getString("equipmentManual"),myRs.getString("compID"),myRs.getString("typeID"),myRs.getString("equipImage")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		search9.setBounds(140, 71, 77, 23);
		eSearch.add(search9);
		
		JPanel eAdd = new JPanel();
		tabbedPane.addTab("Add", null, eAdd, null);
		eAdd.setLayout(null);
		
		JLabel lblNewLabel_30 = new JLabel("Name");
		lblNewLabel_30.setBounds(10, 10, 54, 15);
		eAdd.add(lblNewLabel_30);
		
		JLabel lblNewLabel_31 = new JLabel("Manual");
		lblNewLabel_31.setBounds(10, 60, 54, 15);
		eAdd.add(lblNewLabel_31);
		
		textField_1 = new JTextField();
		textField_1.setBounds(74, 7, 144, 21);
		eAdd.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(74, 57, 506, 21);
		eAdd.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_34 = new JLabel("CompID");
		lblNewLabel_34.setBounds(10, 35, 54, 15);
		eAdd.add(lblNewLabel_34);
		
		textField_9 = new JTextField();
		textField_9.setBounds(74, 32, 87, 21);
		eAdd.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_35 = new JLabel("TypeID");
		lblNewLabel_35.setBounds(445, 35, 54, 15);
		eAdd.add(lblNewLabel_35);
		
		textField_10 = new JTextField();
		textField_10.setBounds(501, 32, 79, 21);
		eAdd.add(textField_10);
		textField_10.setColumns(10);
		
		JButton refresh7 = new JButton("Refresh");
		refresh7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model3.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from equipment where label = 0");

					while (myRs.next()) {
						model3.addRow(new String[]{myRs.getString("equipmentID"),myRs.getString("equipmentName"),myRs.getString("equipmentManual"),myRs.getString("compID"),myRs.getString("typeID"),myRs.getString("equipImage")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		refresh7.setBounds(577, 125, 123, 23);
		eAdd.add(refresh7);
		
		JButton eadd = new JButton("Add device");
		eadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_1.getText().equals("")||textField_2.getText().equals("")||textField_9.getText().equals("")||textField_1.getText().equals("")) {
						result2.setVisible(true);
						result2.setText("Please complete information!");
					}
					else {
						
						result2.setVisible(false);
						Statement myStmt = conn.createStatement();
						//����equipmentID
						ResultSet myRs = myStmt.executeQuery("select count(*) from equipment");
						myRs.next();
						int addid = myRs.getInt("count(*)") + 1;
						String eID = ""+addid;
						int idlen = eID.length();
						for(int i=0 ; i<5-idlen ; i++) {
							eID = "0" + eID;
						}
						eID = "E" + eID;
						
						myRs = myStmt.executeQuery("select * from company where compID = '"+textField_9.getText()+"' and label = 0");
						if(!myRs.next()) {
							result2.setText("Company does not exists!");
							result2.setVisible(true);
						}
						else {
							myRs = myStmt.executeQuery("select * from equipmenttype where typeID = '"+textField_10.getText()+"' and label = 0");
							if(!myRs.next()) {
								result2.setText("Equipment type does not exists!");
								result2.setVisible(true);
							}
							else {
								String pic = "";
								if(textField_10.getText().equals("T00001"))
									pic = "light";
								else if(textField_10.getText().equals("T00002"))
									pic = "humidifier";
								else if(textField_10.getText().equals("T00003"))
									pic = "door";
								else if(textField_10.getText().equals("T00004"))
									pic = "window";
								pic = "./assets/images/" + pic + "/";
								myRs = myStmt.executeQuery("select count(*) from equipment where typeID = '"+textField_10.getText()+"'");
								myRs.next();
								int picid = myRs.getInt("count(*)") + 1;
								pic = pic + picid + ".jpg";
								
								String up = "insert into equipment values('"+eID+"','"+textField_1.getText()+"','"+textField_2.getText()+"','"+textField_9.getText()+"','"+textField_10.getText()+"','0','"+pic+"')";
								myStmt.executeUpdate(up);
								result2.setText("Successfully added!");
								result2.setVisible(true);
							}
						}
						
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		eadd.setBounds(578, 92, 122, 23);
		eAdd.add(eadd);
		
		result2 = new JLabel("");
		result2.setForeground(Color.RED);
		result2.setBounds(10, 110, 490, 15);
		eAdd.add(result2);
		
		JPanel eDelete = new JPanel();
		tabbedPane.addTab("Delete", null, eDelete, null);
		eDelete.setLayout(null);
		
		JLabel lblNewLabel_37 = new JLabel("EquipmentID");
		lblNewLabel_37.setBounds(10, 10, 110, 15);
		eDelete.add(lblNewLabel_37);
		
		textField_11 = new JTextField();
		textField_11.setBounds(115, 7, 100, 21);
		eDelete.add(textField_11);
		textField_11.setColumns(10);
		
		JButton refresh8 = new JButton("Refresh");
		refresh8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model3.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from equipment where label = 0");

					while (myRs.next()) {
						model3.addRow(new String[]{myRs.getString("equipmentID"),myRs.getString("equipmentName"),myRs.getString("equipmentManual"),myRs.getString("compID"),myRs.getString("typeID"),myRs.getString("equipImage")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		refresh8.setBounds(581, 125, 118, 23);
		eDelete.add(refresh8);
		
		JButton edelete = new JButton("Delete");
		edelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from equipment where equipmentID = '"+textField_11.getText()+"' and label = 0");
					if(myRs.next()) {
						int n = JOptionPane.showConfirmDialog(null,"Whether to delete ID '"+textField_11.getText()+"'","Delete?",JOptionPane.YES_NO_OPTION);
						if(n==0) {
							String up = "update equipment set label = 1 where equipmentID = '"+textField_11.getText()+"'";
							myStmt.executeUpdate(up);
							up = "update item set label = 1 where itemID = '"+textField_11.getText()+"'";
							myStmt.executeUpdate(up);
							up = "update data set label = 1 where itemID in (select itemID from item where equipmentID = '"+textField_11.getText()+"')";
							myStmt.executeUpdate(up);
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"ID not found!");
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		edelete.setBounds(581, 92, 118, 23);
		eDelete.add(edelete);
		
		JPanel eModify = new JPanel();
		tabbedPane.addTab("Modify", null, eModify, null);
		eModify.setLayout(null);
		
		JLabel lblNewLabel_38 = new JLabel("Please enter EquipmentID and press modify");
		lblNewLabel_38.setBounds(10, 10, 284, 15);
		eModify.add(lblNewLabel_38);
		
		modeID = new JTextField();
		modeID.setBounds(279, 7, 87, 21);
		eModify.add(modeID);
		modeID.setColumns(10);
		
		JButton refresh9 = new JButton("Refresh");
		refresh9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model3.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from equipment where label = 0");

					while (myRs.next()) {
						model3.addRow(new String[]{myRs.getString("equipmentID"),myRs.getString("equipmentName"),myRs.getString("equipmentManual"),myRs.getString("compID"),myRs.getString("typeID"),myRs.getString("equipImage")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		refresh9.setBounds(586, 126, 113, 23);
		eModify.add(refresh9);
		
		
		
		final JLabel lblNewLabel_39 = new JLabel("Name");
		lblNewLabel_39.setVisible(false);
		lblNewLabel_39.setBounds(10, 35, 54, 15);
		eModify.add(lblNewLabel_39);
		
		final JLabel lblNewLabel_40 = new JLabel("Manual");
		lblNewLabel_40.setVisible(false);
		lblNewLabel_40.setBounds(10, 64, 54, 15);
		eModify.add(lblNewLabel_40);
		
		modename = new JTextField();
		modename.setVisible(false);
		modename.setBounds(74, 35, 196, 21);
		eModify.add(modename);
		modename.setColumns(10);
		
		modemanual = new JTextField();
		modemanual.setVisible(false);
		modemanual.setBounds(74, 63, 469, 21);
		eModify.add(modemanual);
		modemanual.setColumns(10);
		
		final JLabel lblNewLabel_42 = new JLabel("CompID");
		lblNewLabel_42.setVisible(false);
		lblNewLabel_42.setBounds(279, 38, 54, 15);
		eModify.add(lblNewLabel_42);
		
		modecomp = new JTextField();
		modecomp.setVisible(false);
		modecomp.setBounds(322, 35, 87, 21);
		eModify.add(modecomp);
		modecomp.setColumns(10);
		
		final JLabel lblNewLabel_43 = new JLabel("TypeID");
		lblNewLabel_43.setVisible(false);
		lblNewLabel_43.setBounds(411, 38, 54, 15);
		eModify.add(lblNewLabel_43);
		
		modetype = new JTextField();
		modetype.setVisible(false);
		modetype.setBounds(456, 35, 87, 21);
		eModify.add(modetype);
		modetype.setColumns(10);
		
		final JLabel lblNewLabel_44 = new JLabel("Press apply when you finish modifying");
		lblNewLabel_44.setVisible(false);
		lblNewLabel_44.setBounds(10, 130, 321, 15);
		eModify.add(lblNewLabel_44);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 185, 687, 299);
		equipment.add(scrollPane_2);
		
		table_2 = new JTable();
		model3 = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "Name", "Manual", "CompID", "TypeID", "Picture"
				}
			);
		table_2.setModel(model3);
		table_2.getColumnModel().getColumn(5).setPreferredWidth(102);
		scrollPane_2.setViewportView(table_2);
		
		JButton emodify = new JButton("Modify");
		emodify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from equipment where equipmentID = '"+modeID.getText()+"' and label = 0");
					if(!myRs.next()) {
						JOptionPane.showMessageDialog(null,"ID does not exist!","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					else {
						modeID.setEditable(false);
						lblNewLabel_39.setVisible(true);
						lblNewLabel_40.setVisible(true);
						lblNewLabel_42.setVisible(true);
						lblNewLabel_43.setVisible(true);
						lblNewLabel_44.setVisible(true);
						modename.setVisible(true);
						eapply.setVisible(true);
						modemanual.setVisible(true);
						modecomp.setVisible(true);
						modetype.setVisible(true);
						modename.setText(myRs.getString("equipmentName"));
						modemanual.setText(myRs.getString("equipmentManual"));
						modecomp.setText(myRs.getString("compID"));
						modetype.setText(myRs.getString("typeID"));
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		emodify.setBounds(586, 60, 113, 23);
		eModify.add(emodify);
		
		eapply = new JButton("Apply");
		eapply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from company where compID = '"+modecomp.getText()+"' and label = 0");
					if(!myRs.next())
						JOptionPane.showMessageDialog(null,"Company does not exist!","ERROR",JOptionPane.ERROR_MESSAGE);
					else {
						myRs = myStmt.executeQuery("select * from equipmenttype where typeID = '"+modetype.getText()+"' and label = 0");
						if(!myRs.next()) {
							JOptionPane.showMessageDialog(null,"Equipment type does not exist!","ERROR",JOptionPane.ERROR_MESSAGE);
						}
						else {
							if(modename.getText().equals("")||modemanual.getText().equals("")||modecomp.getText().equals("")||modetype.getText().equals("")) {
								JOptionPane.showMessageDialog(null,"Please complete information!","ERROR",JOptionPane.ERROR_MESSAGE);
							}
							else {
								String up = "update equipment set equipmentname = '"+modename.getText()+"',equipmentmanual = '"+modemanual.getText()+"',compID = '"+ modecomp.getText()+"',typeID = '"+modetype.getText()+"' where equipmentID = '"+modeID.getText()+"'";
								myStmt.executeUpdate(up);
								modeID.setEditable(true);
								JOptionPane.showMessageDialog(null,"Successfully modified!","",JOptionPane.PLAIN_MESSAGE);
								lblNewLabel_39.setVisible(false);
								lblNewLabel_40.setVisible(false);
								lblNewLabel_42.setVisible(false);
								lblNewLabel_43.setVisible(false);
								lblNewLabel_44.setVisible(false);
								modename.setVisible(false);
								eapply.setVisible(false);
								modemanual.setVisible(false);
								modecomp.setVisible(false);
								modetype.setVisible(false);
							}
						}
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		eapply.setVisible(false);
		eapply.setBounds(586, 93, 113, 23);
		eModify.add(eapply);
		
		JPanel type = new JPanel();
		administrator.addTab("Equipment Type Management", null, type, null);
		type.setLayout(null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(0, 0, 714, 131);
		type.add(tabbedPane_2);
		
		JPanel tSearch = new JPanel();
		tabbedPane_2.addTab("Search", null, tSearch, null);
		tSearch.setLayout(null);
		
		JLabel lblNewLabel_45 = new JLabel("Enter name");
		lblNewLabel_45.setBounds(10, 10, 82, 15);
		tSearch.add(lblNewLabel_45);
		
		tnametext = new JTextField();
		tnametext.setBounds(86, 7, 134, 21);
		tSearch.add(tnametext);
		tnametext.setColumns(10);
		
		JButton search10 = new JButton("Search name");
		search10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tnametext.getText().equals("")) {
					try {
						model4.setRowCount(0);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from equipmenttype where typename like '%"+tnametext.getText()+"%' and label = 0");
						while (myRs.next()) {
							model4.addRow(new String[]{myRs.getString("typeID"),myRs.getString("typeName")});
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
				}
			}
		});
		search10.setBounds(230, 6, 107, 23);
		tSearch.add(search10);
		
		JLabel lblNewLabel_46 = new JLabel("Enter ID");
		lblNewLabel_46.setBounds(10, 38, 65, 15);
		tSearch.add(lblNewLabel_46);
		
		tIDtext = new JTextField();
		tIDtext.setBounds(86, 35, 134, 21);
		tSearch.add(tIDtext);
		tIDtext.setColumns(10);
		
		JButton search11 = new JButton("Search ID");
		search11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tIDtext.getText().equals("")) {
					try {
						model4.setRowCount(0);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from equipmenttype where typeID = '"+tIDtext.getText()+"' and label = 0");
						while (myRs.next()) {
							model4.addRow(new String[]{myRs.getString("typeID"),myRs.getString("typeName")});
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
				}
			}
		});
		search11.setBounds(230, 34, 107, 23);
		tSearch.add(search11);
		
		JLabel lblNewLabel_47 = new JLabel("Show all types");
		lblNewLabel_47.setBounds(10, 75, 98, 15);
		tSearch.add(lblNewLabel_47);
		
		JButton search12 = new JButton("Show");
		search12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model4.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from equipmenttype where label = 0");

					while (myRs.next()) {
						model4.addRow(new String[]{myRs.getString("typeID"),myRs.getString("typeName")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		search12.setBounds(127, 71, 82, 23);
		tSearch.add(search12);
		
		JPanel tAdd = new JPanel();
		tabbedPane_2.addTab("Add", null, tAdd, null);
		tAdd.setLayout(null);
		
		JLabel lblNewLabel_48 = new JLabel("Name");
		lblNewLabel_48.setBounds(10, 10, 54, 15);
		tAdd.add(lblNewLabel_48);
		
		JLabel lblNewLabel_49 = new JLabel("ID");
		lblNewLabel_49.setBounds(10, 35, 54, 15);
		tAdd.add(lblNewLabel_49);
		
		textField_4 = new JTextField();
		textField_4.setBounds(74, 7, 268, 21);
		tAdd.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(74, 32, 66, 21);
		tAdd.add(textField_5);
		textField_5.setColumns(10);
		
		JButton refresh10 = new JButton("Refresh");
		refresh10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model4.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from equipmenttype where label = 0");

					while (myRs.next()) {
						model4.addRow(new String[]{myRs.getString("typeID"),myRs.getString("typeName")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		refresh10.setBounds(593, 71, 106, 23);
		tAdd.add(refresh10);
		
		JButton tadd = new JButton("Add");
		tadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_4.getText().equals("")||textField_5.getText().equals("")) {
						result4.setVisible(true);
						result4.setText("Please complete information!");
					}
					else {
						result4.setVisible(false);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from equipmenttype where typeID = '"+textField_5.getText()+"'");
						if(myRs.next()) {
							result4.setText("ID already exists!");
							result4.setVisible(true);
						}
						else {
							String up = "insert into equipmenttype values('"+textField_5.getText()+"','"+textField_4.getText()+"','','0')";
							myStmt.executeUpdate(up);
							result4.setText("Successfully added!");
							result4.setVisible(true);
						}
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		tadd.setBounds(593, 38, 106, 23);
		tAdd.add(tadd);
		
		result4 = new JLabel("");
		result4.setForeground(Color.RED);
		result4.setBounds(10, 60, 309, 15);
		tAdd.add(result4);
		
		JPanel tDelete = new JPanel();
		tabbedPane_2.addTab("Delete", null, tDelete, null);
		tDelete.setLayout(null);
		
		JLabel lblNewLabel_50 = new JLabel("TypeID");
		lblNewLabel_50.setBounds(10, 10, 82, 15);
		tDelete.add(lblNewLabel_50);
		
		textField_6 = new JTextField();
		textField_6.setBounds(116, 7, 66, 21);
		tDelete.add(textField_6);
		textField_6.setColumns(10);
		
		JButton refresh11 = new JButton("Refresh");
		refresh11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model4.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from equipmenttype where label = 0");

					while (myRs.next()) {
						model4.addRow(new String[]{myRs.getString("typeID"),myRs.getString("typeName")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		refresh11.setBounds(593, 71, 106, 23);
		tDelete.add(refresh11);
		
		JButton tdelete = new JButton("Delete");
		tdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField_6.getText().equals("")) {
					try {
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from equipmenttype where typeID = '"+textField_6.getText()+"' and label = 0");
						if(myRs.next()) {
							int n = JOptionPane.showConfirmDialog(null,"Whether to delete ID '"+textField_6.getText()+"'","Delete?",JOptionPane.YES_NO_OPTION);
							if(n==0) {
								String up = "update equipmenttype set label = 1 where typeID = '"+textField_6.getText()+"'";
								myStmt.executeUpdate(up);
								up = "update equipment set label = 1 where typeID = '"+textField_6.getText()+"'";
								myStmt.executeUpdate(up);
								up = "update item set label = 1 where equipmentID in (select equipmentID from equipment where typeID = '"+textField_6.getText()+"')";
								myStmt.executeUpdate(up);
								up = "update data set label = 1 where itemID in (select itemID from item i,equipment e, equipmenttype t where i.equipmentID = e.equipmentID and e.typeID = t.typeID and t.typeID = '"+textField_6.getText()+"')";
								myStmt.executeUpdate(up);
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"ID not found!");
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
				}
			}
		});
		tdelete.setBounds(593, 38, 106, 23);
		tDelete.add(tdelete);
		
		JLabel lblNewLabel_51 = new JLabel("");
		lblNewLabel_51.setBounds(10, 57, 405, 15);
		tDelete.add(lblNewLabel_51);
		
		JPanel tModify = new JPanel();
		tabbedPane_2.addTab("Modify", null, tModify, null);
		tModify.setLayout(null);
		
		JLabel lblNewLabel_52 = new JLabel("Please enter EquipmentID and press modify");
		lblNewLabel_52.setBounds(10, 10, 275, 15);
		tModify.add(lblNewLabel_52);
		
		modtID = new JTextField();
		modtID.setBounds(284, 7, 66, 21);
		tModify.add(modtID);
		modtID.setColumns(10);
		
		final JLabel lblNewLabel_53 = new JLabel("Name");
		lblNewLabel_53.setVisible(false);
		lblNewLabel_53.setBounds(10, 35, 54, 15);
		tModify.add(lblNewLabel_53);
		
		modtname = new JTextField();
		modtname.setVisible(false);
		modtname.setBounds(74, 35, 177, 21);
		tModify.add(modtname);
		modtname.setColumns(10);
		
		final JLabel lblNewLabel_54 = new JLabel("Press apply when you finish modifying");
		lblNewLabel_54.setVisible(false);
		lblNewLabel_54.setBounds(10, 72, 286, 15);
		tModify.add(lblNewLabel_54);
		
		JButton refresh12 = new JButton("Refresh");
		refresh12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model4.setRowCount(0);
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from equipmenttype where label = 0");

					while (myRs.next()) {
						model4.addRow(new String[]{myRs.getString("typeID"),myRs.getString("typeName")});
					}
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		refresh12.setBounds(606, 71, 93, 23);
		tModify.add(refresh12);
		
		final JButton tapply = new JButton("Apply");
		tapply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(modtname.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"Please complete information!","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					else {
						Statement myStmt = conn.createStatement();
						//ResultSet myRs = myStmt.executeQuery("select * from home where homeID = '"+modmhome.getText()+"' and label = 0");
						String up = "update equipmenttype set typename = '"+modtname.getText()+"' where typeID = '"+modtID.getText()+"'";
						myStmt.executeUpdate(up);
						modtID.setEditable(true);
						JOptionPane.showMessageDialog(null,"Successfully modified!","",JOptionPane.PLAIN_MESSAGE);
						lblNewLabel_53.setVisible(false);
						lblNewLabel_54.setVisible(false);
						modtname.setVisible(false);
						tapply.setVisible(false);
					}
					
				}
				catch (SQLException se) {
					System.out.println("SQLException:" + se.getMessage());
				}
			}
		});
		tapply.setVisible(false);
		tapply.setBounds(606, 38, 93, 23);
		tModify.add(tapply);
		
		JButton tmodify = new JButton("Modify");
		tmodify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!modtID.getText().equals(""))
					try {
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from equipmenttype where typeID = '"+modtID.getText()+"' and label = 0");
						if(!myRs.next()) {
							JOptionPane.showMessageDialog(null,"ID does not exist!","ERROR",JOptionPane.ERROR_MESSAGE);
						}
						else {
							modtID.setEditable(false);
							lblNewLabel_53.setVisible(true);
							lblNewLabel_54.setVisible(true);
							modtname.setVisible(true);
							tapply.setVisible(true);
							modtname.setText(myRs.getString("typeName"));
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
			}
			
		});
		tmodify.setBounds(606, 6, 93, 23);
		tModify.add(tmodify);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 129, 686, 355);
		type.add(scrollPane_3);
		
		table_3 = new JTable();
		model4 = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"TypeID", "TypeName"
				}
			);
		table_3.setModel(model4);
		table_3.getColumnModel().getColumn(1).setPreferredWidth(193);
		scrollPane_3.setViewportView(table_3);
		
		JPanel data = new JPanel();
		administrator.addTab("Metrical Data Management", null, data, null);
		data.setLayout(null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setBounds(0, 0, 714, 407);
		data.add(tabbedPane_3);
		
		JPanel search = new JPanel();
		tabbedPane_3.addTab("Search", null, search, null);
		search.setLayout(null);
		
		JLabel lblNewLabel_57 = new JLabel("ItemID");
		lblNewLabel_57.setBounds(10, 10, 103, 15);
		search.add(lblNewLabel_57);
		
		sdata1 = new JTextField();
		sdata1.setBounds(80, 7, 81, 21);
		search.add(sdata1);
		sdata1.setColumns(10);
		
		JButton sbut1 = new JButton("Search item");
		sbut1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sdata1.getText().equals(""))
					try {
						model5.setRowCount(0);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from data where itemID = '"+sdata1.getText()+"' and label = 0 and time between '"+time1()+"' and '"+time2()+"'");
						ResultSetMetaData md = myRs.getMetaData();
						while (myRs.next()) {
							int i,j=4;
							for(i=4;i<=9;i++)
								if(myRs.getString(i)!=null)
									j=i;
							model5.addRow(new String[]{myRs.getString("dataID"),myRs.getString("itemID"),myRs.getString("time"),md.getColumnName(j)+":"+myRs.getString(j)});
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
			}
		});
		sbut1.setBounds(179, 6, 147, 23);
		search.add(sbut1);
		
		JLabel lblNewLabel_58 = new JLabel("TypeID");
		lblNewLabel_58.setBounds(10, 35, 54, 15);
		search.add(lblNewLabel_58);
		
		sdata2 = new JTextField();
		sdata2.setBounds(80, 32, 81, 21);
		search.add(sdata2);
		sdata2.setColumns(10);
		
		JButton sbut2 = new JButton("Search type");
		sbut2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sdata2.getText().equals(""))
					try {
						model5.setRowCount(0);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from data where itemID in (select itemID from item i,equipment e, equipmenttype t where i.equipmentID = e.equipmentID and e.typeID = t.typeID and t.typeID = '"+sdata2.getText()+"') and label = 0 and time between '"+time1()+"' and '"+time2()+"'");
						ResultSetMetaData md = myRs.getMetaData();
						while (myRs.next()) {
							int i,j=4;
							for(i=4;i<=9;i++)
								if(myRs.getString(i)!=null)
									j=i;
							model5.addRow(new String[]{myRs.getString("dataID"),myRs.getString("itemID"),myRs.getString("time"),md.getColumnName(j)+":"+myRs.getString(j)});
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
			}
		});
		sbut2.setBounds(179, 31, 147, 23);
		search.add(sbut2);
		
		JLabel lblNewLabel_59 = new JLabel("HomeID");
		lblNewLabel_59.setBounds(10, 60, 54, 15);
		search.add(lblNewLabel_59);
		
		sdata3 = new JTextField();
		sdata3.setBounds(80, 57, 81, 21);
		search.add(sdata3);
		sdata3.setColumns(10);
		
		JButton sbut3 = new JButton("Search home");
		sbut3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sdata3.getText().equals(""))
					try {
						model5.setRowCount(0);
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("select * from data where itemID in (select itemID from item i,home h where i.homeID = h.homeID and h.homeID = '"+sdata3.getText()+"') and label = 0 and time between '"+time1()+"' and '"+time2()+"'");
						ResultSetMetaData md = myRs.getMetaData();
						while (myRs.next()) {
							int i,j=4;
							for(i=4;i<=9;i++)
								if(myRs.getString(i)!=null)
									j=i;
							model5.addRow(new String[]{myRs.getString("dataID"),myRs.getString("itemID"),myRs.getString("time"),md.getColumnName(j)+":"+myRs.getString(j)});
						}
					}
					catch (SQLException se) {
						System.out.println("SQLException:" + se.getMessage());
					}
			}
		});
		sbut3.setBounds(179, 56, 147, 23);
		search.add(sbut3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 86, 683, 292);
		search.add(scrollPane_4);
		
		table_4 = new JTable();
		model5 = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"DataID", "ItemID", "Time", "Value"
				}
			);
		table_4.setModel(model5);
		table_4.getColumnModel().getColumn(2).setPreferredWidth(151);
		table_4.getColumnModel().getColumn(3).setPreferredWidth(133);
		scrollPane_4.setViewportView(table_4);
		
		JPanel statistics = new JPanel();
		tabbedPane_3.addTab("Statistics", null, statistics, null);
		statistics.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Statistic value", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 709, 116);
		panel.setLayout(null);
		statistics.add(panel);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Line chart");
		rdbtnNewRadioButton_1.setBounds(6, 47, 121, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		JLabel itemLabel_1 = new JLabel("ItemID");
		itemLabel_1.setBounds(148, 51, 54, 15);
		panel.add(itemLabel_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(218, 48, 119, 21);
		panel.add(textField_8);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"brightness", "temperature", "decibel", "humidity"}));
		comboBox_1.setBounds(374, 47, 102, 23);
		panel.add(comboBox_1);
		
		JButton btnItem_1 = new JButton("Search item");
		btnItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String a= textField_8.getText();
				String b=(String) comboBox_1.getSelectedItem();
				JFrame frame=new JFrame("Java Data statistics chart");
				frame.getContentPane().add(new TimeSeriesChart(a,b).getChartPanel()); 
				frame.setBounds(200, 200, 400, 400);
				frame.setVisible(true);	
			}
		});
		btnItem_1.setBounds(531, 48, 147, 23);
		panel.add(btnItem_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Distribution", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 115, 709, 145);
		statistics.add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Pie chart");
		rdbtnNewRadioButton.setBounds(6, 66, 121, 23);
		panel_1.add(rdbtnNewRadioButton);
		
		JLabel itemLabel = new JLabel("ItemID");
		itemLabel.setBounds(148, 70, 54, 15);
		panel_1.add(itemLabel);
		
		final JTextField itemField = new JTextField();
		itemField.setBounds(218, 67, 119, 21);
		panel_1.add(itemField);
		itemField.setColumns(10);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(374, 66, 102, 23);
		panel_1.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"brightness", "temperature", "decibel", "humidity"}));
		
		JButton btnItem = new JButton("Search item");
		btnItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String a= itemField.getText();
				String b=(String) comboBox.getSelectedItem();
				JFrame frame=new JFrame("Java Data statistics chart");
				frame.getContentPane().add(new PieChart(a,b).getChartPanel()); 
				frame.setBounds(200, 200, 400, 400);
				frame.setVisible(true);	
				
			}
		});
		btnItem.setBounds(531, 67, 147, 23);
		panel_1.add(btnItem);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Tabulate home data", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(0, 259, 709, 157);
		statistics.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbtnBarChart = new JRadioButton("Bar chart");
		rdbtnBarChart.setBounds(6, 51, 121, 23);
		panel_2.add(rdbtnBarChart);
		
		JLabel itemLabel_2 = new JLabel("ItemID");
		itemLabel_2.setBounds(148, 27, 54, 15);
		panel_2.add(itemLabel_2);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(218, 24, 121, 21);
		panel_2.add(textField_12);
		
		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"brightness", "temperature", "decibel", "humidity"}));
		comboBox_2.setBounds(374, 51, 102, 23);
		panel_2.add(comboBox_2);
		
		JButton btnItem_2 = new JButton("Search item");
		btnItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String a= textField_12.getText();
				String b=(String) comboBox_2.getSelectedItem();
				int c =Integer.parseInt(textField_13.getText());
				JFrame frame=new JFrame("Java Data statistics chart");
				frame.getContentPane().add(new BarChart1(a,b,c).getChartPanel()); 
				frame.setBounds(200, 200, 400, 400);
				frame.setVisible(true);	
				
				
			}
		});
		btnItem_2.setBounds(531, 52, 147, 23);
		panel_2.add(btnItem_2);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(218, 69, 121, 21);
		panel_2.add(textField_13);
		
		JLabel itemLabel_2_1 = new JLabel("Data");
		itemLabel_2_1.setBounds(148, 72, 54, 15);
		panel_2.add(itemLabel_2_1);
		
		c1 = new JComboBox();
		c1.setModel(new DefaultComboBoxModel(new String[] {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
		c1.setBounds(106, 417, 60, 23);
		data.add(c1);
		
		c2 = new JComboBox();
		c2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		c2.setBounds(176, 417, 56, 23);
		data.add(c2);
		
		c3 = new JComboBox();
		c3.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		c3.setBounds(242, 417, 56, 23);
		data.add(c3);
		
		c7 = new JComboBox();
		c7.setModel(new DefaultComboBoxModel(new String[] {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
		c7.setSelectedIndex(11);
		c7.setBounds(355, 417, 60, 23);
		data.add(c7);
		
		c8 = new JComboBox();
		c8.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		c8.setSelectedIndex(11);
		c8.setBounds(425, 417, 56, 23);
		data.add(c8);
		
		c9 = new JComboBox();
		c9.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		c9.setSelectedIndex(30);
		c9.setBounds(491, 417, 56, 23);
		data.add(c9);
		
		c4 = new JComboBox();
		c4.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		c4.setBounds(106, 450, 60, 23);
		data.add(c4);
		
		c5 = new JComboBox();
		c5.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		c5.setBounds(176, 450, 56, 23);
		data.add(c5);
		
		c6 = new JComboBox();
		c6.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		c6.setBounds(242, 450, 56, 23);
		data.add(c6);
		
		c10 = new JComboBox();
		c10.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		c10.setSelectedIndex(23);
		c10.setBounds(355, 451, 60, 23);
		data.add(c10);
		
		c11 = new JComboBox();
		c11.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		c11.setSelectedIndex(59);
		c11.setBounds(425, 450, 56, 23);
		data.add(c11);
		
		c12 = new JComboBox();
		c12.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		c12.setSelectedIndex(59);
		c12.setBounds(491, 450, 56, 23);
		data.add(c12);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Time frame", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(0, 404, 714, 80);
		data.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_56 = new JLabel("to");
		lblNewLabel_56.setBounds(312, 31, 37, 15);
		panel_3.add(lblNewLabel_56);
		
		JLabel lblNewLabel = new JLabel("Hi Administrator!");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(316, 0, 313, 15);
		contentPane.add(lblNewLabel);
	}
	
	public String time1() {
		return c1.getSelectedItem().toString()+"-"+c2.getSelectedItem().toString()+"-"+c3.getSelectedItem().toString()+" "+c4.getSelectedItem().toString()+":"+c5.getSelectedItem().toString()+":"+c6.getSelectedItem().toString();
	}
	public String time2() {
		return c7.getSelectedItem().toString()+"-"+c8.getSelectedItem().toString()+"-"+c9.getSelectedItem().toString()+" "+c10.getSelectedItem().toString()+":"+c11.getSelectedItem().toString()+":"+c12.getSelectedItem().toString();
	}
}
