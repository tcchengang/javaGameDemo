package cn.day1;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Chicken extends Role{
	public Image[] images;
	public String[] toLxyMessages;
	public Chicken(int x, int y){
		super.x=x;
		super.y=y;
		super.index=0;
		this.images= new Image[6];
		for (int i = 0; i < images.length; i++) {
			try {
				this.images[i]=ImageIO.read(new File("ËØ²Ä/Ä¸¼¦/"+i+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		super.width=images[0].getWidth(null);
		super.height=images[0].getHeight(null);
		this.toLxyMessages =new String[]{"111","2222"};
	}
	public void  addIndex(){
		index=(index+1)%(images.length-1);
	}
}
