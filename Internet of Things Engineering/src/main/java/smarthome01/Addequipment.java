package smarthome01;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;

public class Addequipment extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_5;
	private String cid="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Addequipment frame = new Addequipment();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public Addequipment(String str) {
		cid=str;
		final JLabel lblNewLabel_5 = new JLabel("The equipment or type already existed!");
		final JButton btnNewButton = new JButton("Confirm");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 823, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EquipmentID:");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(56, 23, 114, 37);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(180, 33, 218, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("EquipmentName:");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(30, 70, 128, 37);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 80, 218, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Manual:");
		lblNewLabel_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(90, 125, 102, 15);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(179, 124, 219, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("New type");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_3.setEditable(false);
				textField_4.setEditable(true);
				textField_5.setEditable(true);
				textField_6.setEditable(true);
				btnNewButton.setEnabled(true);
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		rdbtnNewRadioButton.setBounds(433, 30, 121, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Existed type");
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_3.setEditable(true);
				textField_4.setEditable(false);
				textField_5.setEditable(false);
				textField_6.setEditable(false);
				btnNewButton.setEnabled(true);
			}
		});
		rdbtnNewRadioButton_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		rdbtnNewRadioButton_1.setBounds(433, 177, 121, 23);
		panel.add(rdbtnNewRadioButton_1);
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(627, 180, 135, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Type ID:");
		lblNewLabel_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(563, 171, 54, 34);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Type name:");
		lblNewLabel_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(537, 79, 114, 19);
		panel.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(627, 80, 135, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		
		btnNewButton.setEnabled(false);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnNewButton.isEnabled()) {
					
					
					boolean a =rdbtnNewRadioButton.isSelected();
					String eid=textField.getText();
					String ena=textField_1.getText();
					String ema=textField_2.getText();
					String tidn=textField_5.getText();
					String tide=textField_3.getText();
					String tna=textField_4.getText();
					String tin=textField_6.getText();
					//ע������֮��Ҫ��!!!!!!!!!!!!!!!!!!!
					boolean tag=false;//���ڼ���ʽ�Ƿ���ȷ
					
					
					
					
					if(eid.length()==6&&eid.charAt(0)=='E') {	//���ȼ��eid�Ƿ��ʽ��ȷ
						if(a==true) {//
							if(tidn.length()==6&&tidn.charAt(0)=='T') {
								try {
									if(CompanyInitialize.addEquipment(a,eid,ena,ema,tidn,tna,tin,cid)) {
										lblNewLabel_5.setText("Successfully adding!");
										lblNewLabel_5.setVisible(true);//��ӳɹ�
									}
										
									else {
										lblNewLabel_5.setText("The equipment or type already existed!");
										lblNewLabel_5.setVisible(true);
									}//���ʧ��
									
								} catch (ClassNotFoundException e1) {e1.printStackTrace();} 
								  catch (SQLException e1) {e1.printStackTrace();}
							}
							else {
								lblNewLabel_5.setText("The typeID should be Txxxxx");
								lblNewLabel_5.setVisible(true);
							}
							
						} else {
							if(tide.length()==6&&tide.charAt(0)=='T') {
								try {
									if(CompanyInitialize.addEquipment(a,eid,ena,ema,tide,"","",cid)) {
										lblNewLabel_5.setText("Successfully adding!");
										lblNewLabel_5.setVisible(true);//��ӳɹ�
									}
										
									else {
										lblNewLabel_5.setText("The equipment or type already existed!");
										lblNewLabel_5.setVisible(true);
										}//���ʧ��
								} catch (ClassNotFoundException e1) {e1.printStackTrace();} 
								  catch (SQLException e1) {e1.printStackTrace();}
							}
							else {
								lblNewLabel_5.setText("The typeID should be Txxxxx");
								lblNewLabel_5.setVisible(true);
							}
						}				
					}else {
						System.out.println(111);
						lblNewLabel_5.setText("The equipmentID should be Exxxxx");
						lblNewLabel_5.setVisible(true);
					}
				}
			}
		});
		
		btnNewButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		btnNewButton.setBounds(309, 246, 218, 34);
		panel.add(btnNewButton);
		
		
		lblNewLabel_5.setVisible(false);
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(90, 175, 281, 27);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_4_1 = new JLabel("Type introduction:");
		lblNewLabel_4_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel_4_1.setBounds(492, 123, 164, 19);
		panel.add(lblNewLabel_4_1);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(627, 124, 135, 21);
		panel.add(textField_6);
		
		JLabel lblNewLabel_3_2 = new JLabel("Type ID:");
		lblNewLabel_3_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(563, 26, 54, 34);
		panel.add(lblNewLabel_3_2);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(627, 33, 135, 21);
		panel.add(textField_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("beijing1.jpg"));
		lblNewLabel_6.setBounds(0, 0, 807, 332);
		panel.add(lblNewLabel_6);
	}
}
