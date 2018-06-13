package cn.day1;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Scene {
	public Image sceneImage;
	public BufferedImage dataMap;
	public int sceneID;
	public int x;
	public int y;
	public int height;
	public int width;
	public int mapRgb;
	public Scene(Image image,BufferedImage map,int id, int x,int y,int rgb){
		sceneImage=image;
		dataMap=map;
		this.x=x;
		this.y=y;
		sceneID=id;
		height=image.getHeight(null);
		width=image.getWidth(null);
		mapRgb=rgb;
	}
	public int getRGB(int x,int y){
		return dataMap.getRGB(x, y);
	}
	public boolean isStop(int x,int y){
		return dataMap.getRGB(x, y)==mapRgb;
	}
}
