package cn.day1;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AZWJ extends Role{
	public Image[] images;
	public String[] toLxyMessages;
	public AZWJ(int x, int y){
		super.x=x;
		super.y=y;
		super.index=0;
		this.images= new Image[6];
		for (int i = 0; i < images.length; i++) {
			try {
				this.images[i]=ImageIO.read(new File("ËØ²Ä/°¢ÖìÎ¹¼¦/"+i+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		super.width=images[0].getWidth(null);
		super.height=images[0].getHeight(null);
		this.toLxyMessages =new String[]{"°¢Öì£¬ÕâÖ»¼¦¶àÉÙÇ®Ò»½ï","2222"};
	}
	public void  addIndex(){
		index=(index+1)%(images.length-1);
	}
	public boolean isDiagShow(int x, int y,int lxyDir){
		int x2 = super.x;
		int y2= height+super.y;
		if (lxyDir == KeyEvent.VK_UP && x > x2 - 10 && x < x2 + 20 && y > y2
				&& y < y2 + 40) {
			return true ;
		}else {
			return false;
		}
	}
}
