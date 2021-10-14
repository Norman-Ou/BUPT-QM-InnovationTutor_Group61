package smarthome01;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.Icon;

public class CompanyFunction extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table_1;
	private JTabbedPane tabbedPane_1;
	private JPanel panel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel panel_1;
	private JTextField valField;
	private JTextField tFrom;
	private JTextField tTo;
	private final Action action = new SwingAction_1();
	public Object value;
	public String ID;//锟�?�备id,锟斤拷锟斤拷之锟斤拷sql锟斤拷锟斤拷锟斤拷锟�?
	private JPanel panel_2;
	private JButton btnNewButton;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JPanel panel_3;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JButton btnNewButton_1;
	private String companyID;
	private JLabel itemlabel;
	private JTextField itemField;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CompanyFunction frame = new CompanyFunction(companyID);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public CompanyFunction(final String companyID) throws ClassNotFoundException, SQLException {
		this.companyID = companyID;
		setTitle("Company System");
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		tabbedPane.addTab("Hardware Device Management", new ImageIcon("diannao2.png"), scrollPane, null);
		
		table_1 = new JTable();
		
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row =table_1.getSelectedRow();
				int column =table_1.getSelectedColumn();
				value = table_1.getValueAt(row, column);
				ID = (String) table_1.getValueAt(row, 0);
				table_1.getColumnName(column);

			}
		});


		table_1.setModel(new DefaultTableModel(
				CompanyInitialize.initialize(companyID)//锟斤拷锟斤拷锟皆猴拷要锟斤拷!!!!!!!!!!!

				,
			new String[] {
				"EquipmentID", "EquipmentName", "Manual", "TypeName","TypeID"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class,String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false,false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.setRowHeight(20);
		table_1.setName("");
		table_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);
		
		panel_2 = new JPanel();
		scrollPane.setRowHeaderView(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnNewButton = new JButton("Add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Addequipment addFrame = new Addequipment(companyID);
				addFrame.setVisible(true);
			}
		});
		btnNewButton.setIcon(null);
		panel_2.add(btnNewButton);
		
		btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DeleteEquipment frame = new DeleteEquipment(companyID);
				frame.setVisible(true);
			}
		});
		panel_2.add(btnNewButton_3);
		
		btnNewButton_5 = new JButton("Modify");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CompanyModify frame = new CompanyModify(companyID);
				frame.setVisible(true);
			}
		});
		panel_2.add(btnNewButton_5);
		
		btnNewButton_4 = new JButton("Refresh");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					table_1.setModel(new DefaultTableModel(
							CompanyInitialize.initialize(companyID)//锟斤拷锟斤拷锟皆猴拷要锟斤拷!!!!!!!!!!!

							,
						new String[] {
							"EquipmentID", "EquipmentName", "Manual", "TypeName","TypeID"
						}
					) {
						Class[] columnTypes = new Class[] {
							String.class, String.class, String.class, String.class,String.class
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						boolean[] columnEditables = new boolean[] {
							false, false, false, false,false
						};
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_2.add(btnNewButton_4);
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.addTab("Measurement data management", new ImageIcon("zhi2.png"), tabbedPane_1, null);
		
		panel = new JPanel();
		panel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		tabbedPane_1.addTab("Item Search", new ImageIcon("fangdajing2.png"), panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel = new JLabel("Please input itemID:");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		panel_3.add(lblNewLabel);
		
		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(10);
		
		final JComboBox comboBox_1 = new JComboBox();
		panel_3.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Brightness", "Temperature", "Decibel", "Humidity", "Distance"}));
		comboBox_1.setSelectedIndex(-1);
		
		JLabel lblNewLabel_1 = new JLabel("Time Range:   From:");
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		
		textField_1 = new JTextField();
		panel_3.add(textField_1);
		textField_1.setColumns(15);
		
		JLabel lblNewLabel_2 = new JLabel("To:  ");
		panel_3.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		
		textField_2 = new JTextField();
		panel_3.add(textField_2);
		textField_2.setColumns(15);
		
		btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//sql
				String iid =textField.getText();
				int dt = comboBox_1.getSelectedIndex();
				
				String colna="Data type";
				switch(dt) {
				case 0:
					colna="Brightness";break;
				case 1:
					colna="Temperature";break;
				case 2:
					colna="Decibel";break;
				case 3:
					colna="Humidity";break;
				case 4:
					colna="Distance";break;
				}
				
//				System.out.println(dt);
				String ts=textField_1.getText();String te=textField_2.getText();

				try {
					table.setModel(new DefaultTableModel(

							DataSearch.dataSearch(companyID,iid,dt,ts,te)
							,
							new String[] {
								"DataID", colna, "Time"
							}
						) {
							boolean[] columnEditables = new boolean[] {
								false, false, false
							};
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						});
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		panel_3.add(btnNewButton_1);
						
						scrollPane_1 = new JScrollPane();
						panel.add(scrollPane_1, BorderLayout.CENTER);
						
						table = new JTable();
						table.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null},
							},
							new String[] {
								"DataID", "Data type", "Time"
							}
						) {
							boolean[] columnEditables = new boolean[] {
								false, false, false
							};
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						});
						scrollPane_1.setViewportView(table);
		
		panel_1 = new JPanel();
		panel_1.setFont(new Font("SimSun-ExtB", Font.PLAIN, 14));
		tabbedPane_1.addTab("     Statistics", new ImageIcon("bing2.png"), panel_1, null);
		panel_1.setLayout(null);
		
		valField = new JTextField();
		valField.setBounds(437, 59, 66, 21);
		panel_1.add(valField);
		valField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Time range:    From:");
		lblNewLabel_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(256, 153, 183, 27);
		panel_1.add(lblNewLabel_4);
		
		tFrom = new JTextField();
		tFrom.setBounds(437, 158, 185, 21);
		panel_1.add(tFrom);
		tFrom.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("To:");
		lblNewLabel_5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(376, 200, 72, 32);
		panel_1.add(lblNewLabel_5);
		
		tTo = new JTextField();
		tTo.setBounds(437, 208, 185, 21);
		panel_1.add(tTo);
		tTo.setColumns(10);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Brightness", "Temperature", "Decibel", "Humidity", "Distance"}));
		comboBox.setBounds(71, 58, 125, 23);
		comboBox.setSelectedIndex(-1);
		panel_1.add(comboBox);
		
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("Determined Value");
		final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Distribution");
		
		
		final JButton btnNewButton_2 = new JButton("Confirm");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtnNewRadioButton.isSelected()) {
					String dt="";
					switch(comboBox.getSelectedIndex()) {
					 case 0: dt="brightness" ; break;
					 case 1: dt="temperature" ;  break;
					 case 2: dt="decibel" ;  break;
					 case 3: dt="humidity" ;  break;
					 case 4: dt="distance" ;  break;
					
					};
					String val=valField.getText();
					String iid=itemField.getText();
					String ts=tFrom.getText();
					String te = tTo.getText();
					try {
						chart.createColumnChart(dt,val,iid,companyID,ts,te);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}	else {
					//这里要做多种柱状图
					String iid=itemField.getText();
					String ts=tFrom.getText();
					String te = tTo.getText();
				}
					
			}
		});
//		JRadioButton rdbtnNewRadioButton = new JRadioButton("Determined Value");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtnNewRadioButton.isSelected()) {
					btnNewButton_2.setEnabled(true);
				}
			}
		});
	//	JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Distribution");
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtnNewRadioButton_1.isSelected()) {
					btnNewButton_2.setEnabled(true);
				}
			}
		});
//		JButton btnNewButton_2 = new JButton("Confirm");
		btnNewButton_2.setEnabled(false);
		
		btnNewButton_2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		btnNewButton_2.setBounds(437, 256, 103, 34);
		panel_1.add(btnNewButton_2);
		
		
		rdbtnNewRadioButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		rdbtnNewRadioButton.setForeground(new Color(0, 0, 0));
		rdbtnNewRadioButton.setBounds(240, 59, 183, 23);
		panel_1.add(rdbtnNewRadioButton);
		
//		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Distribution");
		rdbtnNewRadioButton_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		rdbtnNewRadioButton_1.setBounds(240, 102, 110, 23);
		panel_1.add(rdbtnNewRadioButton_1);
		
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		
		itemlabel = new JLabel("Item ID:");
		itemlabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		itemlabel.setBounds(256, 21, 72, 32);
		panel_1.add(itemlabel);
		
		itemField = new JTextField();
		itemField.setColumns(10);
		itemField.setBounds(437, 21, 185, 21);
		panel_1.add(itemField);
		
		Object [][] data= new Object[][] {	{new Integer(11), "syys", "说锟斤拷说锟斤拷说锟斤拷", new Integer(287)} };
		String[]  row = new String[] {"ID", "Name", "Manual", "TypeID"};
		
//		tabbedPane.addTab("Measurement data management", null, table, null);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
