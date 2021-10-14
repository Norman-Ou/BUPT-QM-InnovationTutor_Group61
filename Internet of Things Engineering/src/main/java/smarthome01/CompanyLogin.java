package smarthome01;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;

public class CompanyLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompanyLogin frame = new CompanyLogin();
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
	public CompanyLogin() {
		setResizable(false);
		setTitle("Company Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 506, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CompanyID:");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(93, 34, 91, 29);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(194, 33, 207, 32);
		contentPane.add(textField);
		textField.setColumns(25);
		
		JLabel lblNewLabel_1 = new JLabel("PassWord:");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(93, 98, 101, 23);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(194, 174, 131, 38);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(194, 94, 207, 32);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("ID or password is wrong, please try again!");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(94, 135, 307, 29);
		contentPane.add(lblNewLabel_2);
	}
}
