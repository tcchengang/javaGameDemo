package cn.day1;

import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;
/**
 * 
 * @author chen
 *
 */
public class FiestFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//实例化一个窗体
		JFrame jf=new JFrame();
		//设置参数
//		jf.setBounds(100, 100,1024,768);
		jf.setSize(1024,666);
		jf.setTitle("game");
		jf.setLocationRelativeTo(null);
		//实现默认关闭
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//让窗口不能改变大小
		//jf.setResizable(false);
		jf.addWindowListener(new WindowAdapter() {
			//当窗口正在关闭的时候会自动执行该方法
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				//string只有在点确定才会赋值给它
				String string=JOptionPane.showInputDialog("hello");
				JOptionPane.showMessageDialog(null, "hello2");
				int num = JOptionPane.showConfirmDialog(null, "退出吗？","退出",JOptionPane.YES_NO_OPTION);
				if(num==JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		Gamepanel gp=new Gamepanel();
		jf.add(gp);
		//启动线程
		Thread paneltThread= new Thread(gp);
		paneltThread.start();
		//画板键盘监听器
		jf.addKeyListener(gp);
		gp.addKeyListener(gp);
		//画板鼠标监听器
		jf.addMouseListener(gp);
		gp.addMouseListener(gp);
		//窗体可见,先写然后插孔前面的
		jf.setVisible(true);
		
	}

}
