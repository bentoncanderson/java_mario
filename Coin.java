import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.Math;
public class Coin extends Sprite
{
	double vert_vel;
	double hori_vel;
	boolean offScreen;
	BufferedImage image = null;
	
	public Coin(int a, int b, int c, int d)
	{
		x = a;
		y = b;
		w = c;
		h = d;
		vert_vel = -20;
		hori_vel = Math.random() * 100;
		offScreen = false;
		if (image == null)
		{
			image = View.loadImage("coin.gif");
		}
	}
	
	@Override
	public boolean isCoin()
	{
		return true;
	}
	public boolean update()
	{
		vert_vel += 6.2;
		y += vert_vel;
		x += hori_vel;
		if (y > 600)
		{
			offScreen = true;
		}
		return offScreen;
	}
	public void draw(Graphics g, int num)
	{
		g.drawImage(image, x, y, w, h, null);
	}
}
