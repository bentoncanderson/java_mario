import java.awt.Graphics;
import java.awt.image.BufferedImage;


class Mario extends Sprite
{
	int px;
	int py;
	double vert_vel;
	int count = 0;
	int marioimagecycle = 0;
	int bottomCollisions = 0;
	static BufferedImage[] images;
	boolean hitBottom = false;
	
	public Mario()
	{
		x = 200;
		y = 500;
		w = 60;
		h = 95;
		vert_vel = 0;
		images = new BufferedImage[5];
		for (int i = 0; i < 5; i++)
		{
			if (images[i] == null)
			{
				images[i] = View.loadImage("mario" + (i+1) + ".png");
			}
		}
	}
	
	@Override
	public boolean isMario()
	{
		return true;
	}
	
	public void jump()
	{
		y -= 1;
		vert_vel += -40;
		y += vert_vel;
	}
	
	
	void correctMario(Sprite s, int num)
	 {
		 //left collision
		 if (((x + w) >= s.x) && ((px + w) <= s.x))
		 {
			 x = s.x - w;
		 }
		 //right collision
		 else if ((x <= (s.x + s.w)) && (px >= (s.x + s.w)))
		 {
			 x = s.x + s.w;
		 }
		 //bottom collision
		 else if (py >= (s.y + s.h))
		 {
			 y = s.y + s.h;
			 if ((s.isCoinBrick(num)) && (count < 50))
			 {
				 hitBottom = true;
				 bottomCollisions++;
			 }
		 }
		 //top collision
		 else if ((py + h) <= s.y)
		 {
			 vert_vel = 0;
			 y = s.y - h;
			 count = 0;
		 }
		 
		 touchingBrick = false;
	 }
	public void cycleThroughImages(boolean i)
	{
		if (i == true)
		{
			if (marioimagecycle < 4)
			{
				marioimagecycle += 1;
			}
			else
				marioimagecycle = (marioimagecycle + 1) % 5;
		}
	}
	
	public boolean update()
	{
		if (count != 0)
		{
			vert_vel += 6.2;
		}
		//vert_vel += 6.2;
		py = y;
		y += vert_vel;
		if (vert_vel == 0)
		{
			count = 0;
			if (touchingBrick == false)
			{
				vert_vel += 6.2;
				py = y;
				y += vert_vel;
			}
		}
		else 
		{
			count++;
		}
		
		vert_vel = 0;
		
		if (y > 500)
		{
			vert_vel = 0;
			count = 0;
			y = 500;
		}
		

		return true;
	}
	
	public void draw(Graphics g, int num)
	{
		g.drawImage(images[marioimagecycle], 200, this.y, null);
	}
}

