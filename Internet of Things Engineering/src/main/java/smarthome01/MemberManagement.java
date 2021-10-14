package smarthome01;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;

public class MemberManagement extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MemberManagement frame = new MemberManagement();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MemberManagement() {
		setTitle("Memeber Management");
		setBounds(700, 100, 500, 500);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane();
//		tabbedPane.setBounds(5, 5, 475, 455);
		contentPane.add(tabbedPane);
//		
		JPanel mSearch = new JPanel();
		mSearch.setBounds(15,15,475,455);
		JLabel Label1 = new JLabel("Search by the memberID: ");
		Label1.setFont(new Font("Calibri", Font.PLAIN, 20));
		Label1.setBounds(33, 83, 320, 26);
		mSearch.add(Label1);
		
//		tabbedPane.add(mSearch);
		tabbedPane.addTab("Search", mSearch);
//		mSearch.setLayout(null);
//		
//		JLabel titleLabel = new JLabel("Search members in smart home");
//		titleLabel.setForeground(SystemColor.textHighlight);
//		titleLabel.setFont(new Font("Calibri", Font.BOLD, 20));
//		titleLabel.setBounds(33, 22, 320, 26);
//		mSearch.add(titleLabel);
//		

//		
//		JRadioButton rdbtnNewRadioButton = new JRadioButton("memberID:");
//		rdbtnNewRadioButton.setFont(new Font("Calibri", Font.PLAIN, 20));
//		rdbtnNewRadioButton.setBounds(29, 129, 155, 23);
//		mSearch.add(rdbtnNewRadioButton);
//		
//		JRadioButton rdbtnMembername = new JRadioButton("memberName:");
//		rdbtnMembername.setFont(new Font("Calibri", Font.PLAIN, 20));
//		rdbtnMembername.setBounds(29, 239, 155, 23);
//		mSearch.add(rdbtnMembername);
//		
//		JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("All members: ");
//		rdbtnNewRadioButton_1_1.setFont(new Font("Calibri", Font.PLAIN, 20));
//		rdbtnNewRadioButton_1_1.setBounds(29, 341, 155, 23);
//		mSearch.add(rdbtnNewRadioButton_1_1);
//		
//		JLabel lblSearchByThe = new JLabel("Search by the memberName: ");
//		lblSearchByThe.setFont(new Font("Calibri", Font.PLAIN, 20));
//		lblSearchByThe.setBounds(33, 191, 320, 26);
//		mSearch.add(lblSearchByThe);
//		
//		JLabel Label1_1_1 = new JLabel("Search all the members: ");
//		Label1_1_1.setFont(new Font("Calibri", Font.PLAIN, 20));
//		Label1_1_1.setBounds(33, 297, 320, 26);
//		mSearch.add(Label1_1_1);
//		
//		textField = new JTextField();
//		textField.setBounds(178, 129, 175, 23);
//		mSearch.add(textField);
//		textField.setColumns(10);
//		
//		textField_1 = new JTextField();
//		textField_1.setColumns(10);
//		textField_1.setBounds(178, 239, 175, 23);
//		mSearch.add(textField_1);
//		
//		textField_2 = new JTextField();
//		textField_2.setColumns(10);
//		textField_2.setBounds(178, 341, 175, 23);
//		mSearch.add(textField_2);
//		
//		JButton btnNewButton = new JButton("Search");
//		btnNewButton.setBounds(367, 129, 93, 23);
//		mSearch.add(btnNewButton);
//		
//		JButton btnNewButton_1 = new JButton("Search");
//		btnNewButton_1.setBounds(367, 239, 93, 23);
//		mSearch.add(btnNewButton_1);
//		
//		JButton btnNewButton_2 = new JButton("Search");
//		btnNewButton_2.setBounds(367, 341, 93, 23);
//		mSearch.add(btnNewButton_2);
		
	}
}
