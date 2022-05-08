import java.awt.Graphics;
public abstract class Sprite {
	int x;
	int y;
	int w;
	int h;
	boolean touchingBrick = false;
	
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
		 touchingBrick = true;
		 return true;
	 }
	 
	 

}

