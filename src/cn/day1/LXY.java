package cn.day1;
//bufferedimage类中有getRGB方法，为image的派生类
//System.out.println(ljcMarktDataMap.getRGB(x, y));//测试值
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LXY extends Role{
	public Image[] images_up;
	public Image[] images_down;
	public Image[] images_left;
	public Image[] images_right;
	public Image nowImage;
	public int speed;
	public int dir;
	public LXY(int x,int y){
		super.x=x;
		super.y=y;
		this.speed=5;
		this.dir=KeyEvent.VK_DOWN;
		super.index=0;
		images_up= new Image[8];
		images_down =new Image[8];
		images_left =new Image[8];
		images_right=new Image[8];
		for(int i =0; i<images_up.length;i++){
			try {
				images_up[i]=ImageIO.read(new File("素材/李逍遥上/"+i+".png"));
				images_down[i]=ImageIO.read(new File("素材/李逍遥下/"+i+".png"));
				images_left[i]=ImageIO.read(new File("素材/李逍遥左/"+i+".png"));
				images_right[i]=ImageIO.read(new File("素材/李逍遥右/"+i+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		nowImage =images_down[index];
		super.height=nowImage.getHeight(null);
		super.width=nowImage.getWidth(null);
	}
	public boolean  moveUP(Scene scene){
		if(this.y>0)
			this.y-=speed;
		index=(index+1)%(images_up.length-1);
		nowImage=images_up[index];
		dir=KeyEvent.VK_UP;
		int x1 = x+width/2;
		int y1=y+height;
		if (scene.isStop(x1, y1)) {
			y+=speed;
		}
		return !scene.isStop(x1, y1);
	}
	public boolean moveDown(Scene scene) {
		if(this.y<scene.height-height){
			this.y+=speed;
			}
		index = (index + 1) % (images_down.length - 1);
		nowImage = images_down[index];
		dir = KeyEvent.VK_DOWN;
		int x1 = x + width/2;
		int y1 = y + height;
		if (scene.isStop(x1, y1)) {
			y -= speed;
		}
		return !scene.isStop(x1, y1);
	}
	public boolean moveLeft(Scene scene){
		if(this.x>0){
			x-=speed;
			}
			index=(index+1)%(images_left.length-1);
			nowImage=images_left[index];
			dir=KeyEvent.VK_LEFT;
			int x1 = x;
			int y1=y+height;
			if (scene.isStop(x1, y1)) {
				x+=speed;
			}
			return !scene.isStop(x1, y1);
	}
	public boolean moveRight(Scene  scene) {
		if(x<scene.width-width){
			x+=speed;
			}
			index=(index+1)%(images_right.length-1);
			nowImage=images_right[index];
			dir=KeyEvent.VK_RIGHT;
			int x1 = x+width;
			int y1=y+height;
			if (scene.isStop(x1, y1)) {
				x-=speed;
			}
			return !scene.isStop(x1, y1);
	}
}
