package smarthome01;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Member{
	JFrame f;
	JPanel jp1,jp2;
	JLabel jlab1,jlab2;
	JTabbedPane jtbp;
	
	Member() 
	{
		f=new JFrame("ѡ�������");
		jp1=new JPanel();//���1(ѡ�1)
		jp2=new JPanel();//���2(ѡ�2)
		
		jlab1=new JLabel("ѡ�A");
		jlab2=new JLabel("ѡ�B");
		
		jtbp=new JTabbedPane();//����Ĭ�ϵ�ѡ����
	}
	
	public void displayWindow()
	{
		jp1.setLayout(new BorderLayout());//����ΪBorderLayout���ֹ�����
		jp2.setLayout(new BorderLayout());
		jp1.add(jlab1);
		jp2.add(jlab2);
		jtbp.addTab("ѡ�A", jp1);//���ѡ���ѡ����
		jtbp.addTab("ѡ�2", jp2);
		
		f.setContentPane(jtbp);
		f.setSize(400, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		Member frame = new Member();
		frame.displayWindow();
	}
}
