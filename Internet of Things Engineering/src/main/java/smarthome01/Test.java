package smarthome01;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Test{
	JFrame f;
	JPanel jp1,jp2;
	JLabel jlab1,jlab2;
	JTabbedPane jtbp;
	
	Test()
	{
		f=new JFrame("ѡ�������");
		f.setTitle("Memeber Management");
		f.setBounds(700, 100, 500, 500);
		jp1=new JPanel();//���1(ѡ�1)
		jp2=new JPanel();//���2(ѡ�2)
		
		jlab1=new JLabel("ѡ�A");
		jlab2=new JLabel("ѡ�B");
		
		JLabel Label1 = new JLabel("Search by the memberID: ");
		Label1.setFont(new Font("Calibri", Font.PLAIN, 20));
		Label1.setBounds(33, 83, 320, 26);

		jtbp=new JTabbedPane();//����Ĭ�ϵ�ѡ����
		
		jp1.setLayout(new BorderLayout());//����ΪBorderLayout���ֹ�����
		jp2.setLayout(new BorderLayout());
		jp1.add(Label1);
		jp2.add(jlab2);
		jtbp.addTab("ѡ�A", jp1);//���ѡ���ѡ����
		jtbp.addTab("ѡ�2", jp2);
		
		f.setContentPane(jtbp);
		f.setSize(400, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public static void main(String []args)
	{
		Test t = new Test();
//		t.displayWindow();
	}
}
