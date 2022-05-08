//Benton Anderson
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Brick extends Sprite
{
	BufferedImage image = null;

	Brick(int a, int b, int c, int d, int e)
	{
		x = a;
		y = b;
		w = c;
		h = d;
		if (isCoinBrick(e))
		{
			loadImage("brick_coin.png");
		}
		else
		{
			loadImage("Brick_Block.png");
		}
	}
	
	
	Brick(Json ob, int size)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		if (isCoinBrick(size))
		{
			loadImage("brick_coin.png");
		}
		else
		{
			loadImage("Brick_Block.png");
		}
	}
	
	
	
	void loadImage(String filename)
	{
		if (this.image == null)
		{
			image = View.loadImage(filename);
		}
	}
	
	Json marshal()
	  {
	      Json ob = Json.newObject();
	      ob.add("x", x);
	      ob.add("y", y);
	      ob.add("w", w);
	      ob.add("h", h);
	      return ob;
	  }
	
	@Override
	public boolean isBrick()
	{
		return true;
	}
	
	@Override
	public boolean isCoinBrick(int num)
	{
		if ((num % 5) == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean update()
	{
		return true;
	}
	
	public void draw(Graphics g, int pos)
	{
		g.drawImage(image, x - pos + 200, y, w, h, null);
	}
}