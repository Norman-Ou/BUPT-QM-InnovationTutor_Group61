package smarthome01;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.ImageIcon;

public class DeleteEquipment extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String companyID="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEquipment frame = new DeleteEquipment("");
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
	public DeleteEquipment(String str) {
		setResizable(false);
		companyID=str;
		final JLabel lblNewLabel_1 = new JLabel("The ID is not exists.");
		lblNewLabel_1.setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 497, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please input equipmentID:");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 29, 188, 45);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(208, 43, 112, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String eid=textField.getText();

				try {
					if(CompanyInitialize.deleteEquipment(companyID, eid)) {
						lblNewLabel_1.setText("Delete successfully");
						lblNewLabel_1.setVisible(true);
					}else {lblNewLabel_1.setText("The ID is not exists.");
					lblNewLabel_1.setVisible(true);}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(208, 121, 112, 30);
		panel.add(btnNewButton);
		
		
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(200, 85, 211, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("beijing1.jpg"));
		lblNewLabel_2.setBounds(0, 0, 481, 205);
		panel.add(lblNewLabel_2);
	}

}
