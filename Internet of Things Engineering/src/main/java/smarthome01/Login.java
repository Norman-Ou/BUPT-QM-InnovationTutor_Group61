package smarthome01;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import topicCode.PubCommand;
import topicCode.SubData1;
import topicCode.SubData2;
import topicCode.SubData3;
import topicCode.SubData4;
import topicCode.SubData5;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
public class Login extends JFrame  implements ActionListener  {
	
	public static 	Connection conn;

//	public static 	Connection conn2;
//	public static 	Connection conn3;
//	public static 	Connection conn4;
//	public static 	Connection conn5;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton compButton,AdminButton;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		 try {
				UIManager.setLookAndFeel(new NimbusLookAndFeel());
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		
		final String URL = "jdbc:mysql://localhost:3306/smarthome2?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
		final String USERNAME = "root";
		final String PASSWORD = "421132";
		conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//		conn2 = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//		conn3 = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//		conn4 = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//		conn5 = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		Login frame = new Login();
		frame.setVisible(true);
//		Thread sub1 = new Thread(new SubData1());
//		sub1.start();
		Thread ala = new Thread(new AlarmTest());
		ala.start();
		Thread sub2 = new Thread(new SubData2());
		sub2.start();
//		Thread sub3 = new Thread(new SubData3());
//		sub3.start();
//		Thread sub4 = new Thread(new SubData4());
//		sub4.start();
//		Thread sub5 = new Thread(new SubData5());
//		sub5.start();
		Thread pub = new Thread(new PubCommand());
		pub.start();
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Smart Home Login");
		titleLabel.setForeground(SystemColor.textHighlight);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		titleLabel.setBounds(119, 30, 257, 39);
		contentPane.add(titleLabel);
		
		JLabel IDLabel = new JLabel("ID:");
		IDLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		IDLabel.setBounds(95, 80, 91, 29);
		contentPane.add(IDLabel);
		
		textField = new JTextField();
		textField.setBounds(196, 79, 207, 32);
		contentPane.add(textField);
		textField.setColumns(25);
		
		JLabel passwordLabel = new JLabel("PassWord:");
		passwordLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		passwordLabel.setBounds(95, 144, 101, 23);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(196, 140, 207, 32);
		contentPane.add(passwordField);
		passwordField.setColumns(25);
		
		compButton = new JButton("Company Login");
		compButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		compButton.setBounds(48, 205, 175, 38);
		compButton.addActionListener(this);
		contentPane.add(compButton);
		
		AdminButton = new JButton("Administrator Login");
		AdminButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		AdminButton.setBounds(271, 205, 175, 38);
		AdminButton.addActionListener(this);
		contentPane.add(AdminButton);

	}

	public void actionPerformed(ActionEvent event){
		String ID = textField.getText();
		String PWD = String.valueOf( passwordField.getPassword());
		String MD5 = MD5Tools.stringToMD5(PWD);
		String buttonName = event.getActionCommand();
		String sql = null;
		ResultSet rs = null;
		if(ID.equals("") || PWD.equals("")) {
			JOptionPane.showMessageDialog(this,"Please complete the information!","Warning",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try {
				Connection conn = DBConnection.conn();
				Statement stmt = conn.createStatement();
				
				if(buttonName.equals("Company Login")) {
					sql = "SELECT * FROM company where compID = '" + ID +"';";
					rs = stmt.executeQuery(sql);
					if(rs.next()){
		                String rPWD = rs.getString("compPWD");
		                if(MD5.equals(rPWD)) {
		                	JOptionPane.showMessageDialog(this,"Successfully Login!","Success",JOptionPane.INFORMATION_MESSAGE);
//		                	this.setVisible(false);
							CompanyFunction anotherFrame = new CompanyFunction(ID);
							anotherFrame.setVisible(true);
		                }
		                else
		                {
		                	JOptionPane.showMessageDialog(this,"ID or password is wrong, please try again!","Wrong Message",JOptionPane.ERROR_MESSAGE);
		                	textField.setText("");
		                	passwordField.setText("");
		                }
		            }	
					else
		            {
						JOptionPane.showMessageDialog(this,"Nonexistent ID!","Error",JOptionPane.ERROR_MESSAGE);
						textField.setText("");
	                	passwordField.setText("");
		            }
				}
				else
				{
					sql = "SELECT * FROM administrator where adminID = '" + ID +"';";
					rs = stmt.executeQuery(sql);
					if(rs.next()){
		                String rPWD = rs.getString("adminPWD");
		                if(MD5.equals(rPWD)) {
		                	JOptionPane.showMessageDialog(this,"Successfully Login!","Success",JOptionPane.INFORMATION_MESSAGE);
//		                	System.exit(0);
		                	Adpage1 frame = new Adpage1();
		            		frame.setVisible(true);
		                	
		                }
		                else
		                {
		                	JOptionPane.showMessageDialog(this,"ID or password is wrong, please try again!","Wrong Message",JOptionPane.ERROR_MESSAGE);
		                	textField.setText("");
		                	passwordField.setText("");
		                }
		            }
					else
		            {
						JOptionPane.showMessageDialog(this,"Nonexistent ID!","Error",JOptionPane.ERROR_MESSAGE);
						textField.setText("");
	                	passwordField.setText("");
		            }
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
		}
	}
}
