package smarthome01;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class CompanyModify extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private String cid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompanyModify frame = new CompanyModify("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CompanyModify(String str) {
		setResizable(false);
		cid=str;
		final JLabel lblCanNotFind = new JLabel("Can not find the equipmentID or typeID");
		lblCanNotFind.setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 412, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please input equipmentID:");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 27, 207, 28);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(197, 33, 139, 21);
		panel.add(textField);
		textField.setColumns(10);

		
		JLabel lblEquipmentiName = new JLabel("Equipment name:");
		lblEquipmentiName.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblEquipmentiName.setBounds(10, 75, 207, 28);
		panel.add(lblEquipmentiName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(197, 81, 139, 21);

		panel.add(textField_1);
		
		JLabel lblEquipmentiManual = new JLabel("Equipment manual:");
		lblEquipmentiManual.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblEquipmentiManual.setBounds(10, 125, 207, 28);
		panel.add(lblEquipmentiManual);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(197, 131, 139, 21);

		panel.add(textField_2);
		
		JLabel lblTypeid = new JLabel("TypeID:");
		lblTypeid.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblTypeid.setBounds(10, 175, 207, 28);
		panel.add(lblTypeid);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(197, 181, 139, 21);

		panel.add(textField_3);
		
		JButton btnNewButton = new JButton("Modify");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//sql
				String eid = textField.getText() ;
				String ena = textField_1.getText();
				String ema = textField_2.getText();
				String tid = textField_3.getText();
				try {
					if(CompanyInitialize.ModifyEquipment(cid,eid,ena,ema,tid)) {
						lblCanNotFind.setText("Modify successfully");
						lblCanNotFind.setVisible(true);
						//修改完成
					}else {
						lblCanNotFind.setText("Can not find the equipmentID or typeID");
						lblCanNotFind.setVisible(true);
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		btnNewButton.setBounds(146, 252, 105, 28);
		panel.add(btnNewButton);
		
//		JLabel lblCanNotFind = new JLabel("Can not find the equipmentID or typeID");
		lblCanNotFind.setForeground(Color.RED);
		lblCanNotFind.setHorizontalAlignment(SwingConstants.CENTER);
		lblCanNotFind.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblCanNotFind.setBounds(32, 213, 326, 28);
		panel.add(lblCanNotFind);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("beijing1.jpg"));
		lblNewLabel_1.setBounds(0, 0, 396, 300);
		panel.add(lblNewLabel_1);
	}
}
