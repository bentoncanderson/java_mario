import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
public abstract class Sprite {
	int x;
	int y;
	int w;
	int h;
	
	public abstract boolean update();
	public abstract void draw(Graphics g, int num);
	
	public boolean isMario()
	{
		return false;
	}
	public boolean isBrick()
	{
		return false;
	}
	
	public boolean isCoinBrick(int num)
	{
		return false;
	}
	public boolean isCoin()
	{
		return false;
	}
	 public boolean detectCollisions(Sprite s)
	 {
		 //moving right
		 if ((x + w) < s.x)
		 {
			 return false;
		 }
		 //moving left
		 else if (x > (s.x + s.w))
		 {
			 return false;
		 }
		 //moving up
		 else if (y > (s.y + s.h))
		 {
			 return false;
		 }
		 //moving down
		 else if ((y + h) < s.y)
		 {
			 return false;
		 }
		 return true;
	 }
	 
	 

}

