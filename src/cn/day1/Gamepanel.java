package cn.day1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.tools.Tool;

/**
 * 本类文件表示游戏的界面
 * 取余数是一个很有效的方法，一般用于增加然后又变为0的情况
 * @author chen
 * ctrl+/ 批量注释
 *java一段一般为前闭后开string.substring(0,6)//0-5，6取不到
 *strin.substring(7)//7-最后
 *鼠标点击事件不等于按下+释放 ，而是同一个点的按下+释放
 *选中代码alt，直接选中代码整体移动
 *鼠标的接口有俩个，一个是mouselisten（按下松开）和mousemotionlisten（拖拽，移动）一定要把它添加进窗口和画板
 */

public class Gamepanel extends JPanel implements Runnable ,KeyListener, MouseListener{
	//李家村市场相关图片
	Scene ljcMScene;
	AWangShen ljcMAws;
	AZWJ ljcMAzwj;
	WCS ljcMWcs;
	Chicken ljcMChicken;
	Skip ljcMSkip;
	//李家村相关图片
	Scene ljcScene;
	AWangShen ljcAws;
	AZWJ ljcAzwj;
	WCS ljcWcs;
	Chicken ljcChicken;
	Skip ljcSkip;
	//对话框
	Image dialogBox;
	int diagX;
	int diagY;
	String[] diagMessages;
	int diagIndex;
	boolean isDiagShow;
	//李逍遥图片集
	LXY lxy;
	//定义显示的场景以及图片
	Scene scene;
	AWangShen aws;
	AZWJ azwj;
	WCS wcs;
	Chicken chicken;
	Skip skip;
	public  Gamepanel() {
		
		String string1=JOptionPane.showInputDialog("用户名");
		String string2=JOptionPane.showInputDialog("密码");
		//将素材文件夹放在过程下
		try {
			//绝对路径：以盘符（C:）或者从根目录开始（/）
			//相对路径：从工程文件后面的路径开始书写
			
			dialogBox=ImageIO.read(new File("素材/对话框/0.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//初始化ljcM的场景
		int ljcMX=-250;
		int ljcMY=-250;
		BufferedImage dataMap;
		try {
			Image sceneImage=ImageIO.read(new File("素材/李家村市场/0.png"));
			dataMap = ImageIO.read(new File("素材/李家村市场/RedMap.png"));
			ljcMScene=new Scene(sceneImage,dataMap, 1,ljcMX,ljcMY,-65536);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene=ljcMScene;
		ljcMAws= new AWangShen(880-scene.x, 270-scene.y);		
		ljcMAzwj =new AZWJ(730-scene.x, 30-scene.y);
		ljcMWcs=new WCS(1060-scene.x,210-scene.y);
		ljcMChicken=new Chicken(700-scene.x, 100-ljcMScene.y);
		ljcMSkip=new Skip(0-ljcMScene.x, 340-ljcMScene.y);
		isDiagShow=false;
		diagIndex=0;
		lxy =new LXY(430-scene.x,250-scene.y);
		//初始化人物及场景
		aws=ljcMAws;
		azwj=ljcMAzwj;
		wcs=ljcMWcs;
		skip=ljcMSkip;
		chicken=ljcMChicken;
	}
	public void run(){
		while(true){
			aws.addIndex();
			azwj.addIndex();
			chicken.addIndex();
			wcs.addIndex();
			skip.addIndex();
			try {
				Thread.sleep(130);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
	
		}
	}
	public void paint(Graphics g){
		//这种方法不是很好，其中的两个数字是图片左上角的x，y的坐标
/*		g.drawImage(Toolkit.getDefaultToolkit().getImage(
				Gamepanel.class.getResource("0.png")),-250,-250,this);
*/
		//清空先前内容
		super.paint(g);

		//代码不写死，改变游戏窗口后也可自动变化
		scene.x=(this.getWidth()-lxy.width)/2-lxy.x;
		scene.y=(this.getHeight()-lxy.height)/2-lxy.y;
		if(scene.y>=0){
			scene.y=0;
		}else if(scene.y<=this.getHeight()-scene.height){
			scene.y=this.getHeight()-scene.height;
		}
		if(scene.x>=0){
			scene.x=0;
		}else if (scene.x<=this.getWidth()-scene.width) {
			scene.x=this.getWidth()-scene.width;
		}
		g.drawImage(scene.sceneImage, scene.x, scene.y, this);
		g.drawImage(aws.images[aws.index], aws.x+scene.x, aws.y+scene.y, this);
		g.drawImage(azwj.images[azwj.index], azwj.x+scene.x, azwj.y+scene.y, this);
		g.drawImage(chicken.images[chicken.index], chicken.x+scene.x, chicken.y+scene.y, this);
		g.drawImage(wcs.images[wcs.index], wcs.x+scene.x, wcs.y+scene.y, this);
		g.drawImage(skip.images[skip.index], skip.x+scene.x, skip.y+scene.y, this);
		g.drawImage(lxy.nowImage,lxy.x+scene.x, lxy.y+scene.y, this);
		//this.get必须要有窗体的情况下才可以用，所以不能放在构造方法里面
		diagX=(this.getWidth()-dialogBox.getWidth(null))/2;
		diagY=(this.getHeight()-dialogBox.getHeight(null));
		if(isDiagShow){
			g.drawImage(dialogBox, diagX, diagY, this);
			g.setColor(Color.black);
			g.setFont(new Font("微软雅黑", Font.BOLD, 30));
			String string=diagMessages[diagIndex];
			int stringStartIndex=0;
			int stringEndIndex=15;
			int count=1;
			while(string.length()>=1){
				if(count<4){
					if(string.length()>15){
						g.drawString(string.substring(stringStartIndex,stringEndIndex), diagX+180, diagY+40+40*count);
						count+=1;
						string=string.substring(stringEndIndex);
					}else {
						g.drawString(string, diagX+180, diagY+20+40*count);
						string="";
					}	
				}
				else {		
					diagIndex--;
					count=1;
					String s1=diagMessages[diagIndex];
					diagMessages[diagIndex]=string;
					repaint();
					diagMessages[diagIndex]=s1;
				}
						
			}
		}

		}
	//键盘监听器实现方法，  source-->override/implement methods
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode=e.getKeyCode();
//		System.out.println(keyCode);
		//左=37，上=38，右=39.下=40，w=87，s=83, A=65,d=68
		if (isDiagShow) {
			if (keyCode==KeyEvent.VK_SPACE) {
				diagIndex++;
				if (diagIndex>=diagMessages.length) {
					diagIndex=0;
					isDiagShow=false;
				}
			}
		}else {
			if(keyCode==KeyEvent.VK_UP){
				lxy.moveUP(scene);
				repaint();
			}else if(keyCode==KeyEvent.VK_DOWN){
				lxy.moveDown(scene);
				repaint();
			}else if(keyCode==KeyEvent.VK_LEFT){
				lxy.moveLeft(scene);
				repaint();
			}else if(keyCode==KeyEvent.VK_RIGHT){
				lxy.moveRight(scene);
				repaint();
			}else if(keyCode==KeyEvent.VK_SPACE){
				//判断主角是否走到配角前,
				int x=lxy.x;
				int y=lxy.y+lxy.height;
				if(aws.isDiagShow(x, y, lxy.dir)){
					//开启聊天
					isDiagShow=true;		
					diagMessages =aws.toLxyMessages;
					repaint();
				}
//				int x4=wcsX;
//				int y4=wcsY+wcsiImages[wcsIndex].getHeight(null);
//				int x3=x4+20;
//				int y3=y4-20;
//				int x5=lxy.x+lxyImage.getWidth(null);
//				int y5=lxy.y+lxyImage.getHeight(null);		
//				if(lxyDir==KeyEvent.VK_RIGHT&&x5>x4-10&&x5<x3+20&&y5>y3&&y5<y4+30){
//					isDiagShow=true;
//					diagMessages =new String[]{"h","w","3","4"};
//					repaint();
//				}
				if(azwj.isDiagShow(x, y, lxy.dir)){
					diagMessages = azwj.toLxyMessages;
					isDiagShow=true;
					repaint();
				}
				
		}else if (keyCode==KeyEvent.VK_J) {
			if(lxy.y>0)
				lxy.y-=lxy.speed;
		}else if(keyCode==KeyEvent.VK_2){
			int ljcX=-400;
			int ljcY=-400;
			BufferedImage dataMap;
			try {
				Image sceneImage=ImageIO.read(new File("素材/李家村/0.png"));
				dataMap = ImageIO.read(new File("素材/李家村/RedMap.png"));
				ljcScene=new Scene(sceneImage,dataMap, 1,ljcX,ljcY,-521461);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			scene=ljcScene;
			ljcAws= new AWangShen(400-scene.x, 100-scene.y);		
			ljcAzwj =new AZWJ(100-scene.x, 100-scene.y);
			ljcWcs=new WCS(250-scene.x,150-scene.y);
			ljcChicken=new Chicken(60-scene.x, 140-scene.y);
			ljcSkip=new Skip(800-scene.x, 450-scene.y);
			isDiagShow=false;
			diagIndex=0;
			lxy =new LXY(430-scene.x,250-scene.y);
			//初始化人物及场景
			aws=ljcAws;
			azwj=ljcAzwj;
			wcs=ljcWcs;
			skip=ljcSkip;
			chicken=ljcChicken;
			repaint();
		}else if(keyCode==KeyEvent.VK_1){
			scene=ljcMScene;
			aws=ljcMAws;
			azwj=ljcMAzwj;
			wcs=ljcMWcs;
			skip=ljcMSkip;
			chicken=ljcMChicken;
			lxy.x=430-scene.x;
			lxy.y=250-scene.y;
			repaint();
		}

		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == e.BUTTON1) {
			int mouseX = e.getX() - scene.x- lxy.width / 2;
			int mouseY = e.getY() - scene.y- lxy.height;
			System.out.println(mouseX+" "+mouseY);
			while (mouseX % lxy.speed != 0) {
				mouseX++;
			}
			while (mouseY % lxy.speed != 0) {
				mouseY++;
			}
			System.out.println(mouseX+" "+mouseY);
			final int x = mouseX ;
			final int y = mouseY ;
			if (scene.isStop(mouseX+lxy.width / 2, mouseY+lxy.height)) {
				isDiagShow = true;
				String[] a = new String[] { "这个点不能走" };
				diagMessages = a;
			} else {
				new Thread() {
					@Override
					public void run() {
						int count = 0;
						while (lxy.x != x && lxy.y != y && count < 8) {
							while (lxy.x < x) {
								if (lxy.moveRight(scene))
									try {
										repaint();
										Thread.sleep(50);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								else {
									break;
								}
							}
							while (lxy.x > x) {
								if (lxy.moveLeft(scene))
									try {
										repaint();
										Thread.sleep(50);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								else {
									break;
								}
							}
							while (lxy.y < y) {
								if (lxy.moveDown(scene))
									try {
										repaint();
										Thread.sleep(50);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								else {
									break;
								}
							}
							while (lxy.y > y) {
								if (lxy.moveUP(scene))
									try {
										repaint();
										Thread.sleep(50);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								else {
									break;
								}
							}
							System.out.println(lxy.x+" "+lxy.y+" "+x+" "+y);
							count++;
						}
						lxy.moveDown(scene);
					}
				}.start();
			}
		}	
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {	
	}
	
	
}
