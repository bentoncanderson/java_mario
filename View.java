//Benton Anderson
//September 7, 2021
//Assignment 2
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	Model model;
	Controller controller;

	View(Controller c, Model m)
	{
		c.setView(this);
		model = m;
		controller = c;
		
		

	}
	
	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
		} catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}
	

	public void paintComponent(Graphics g)
	{
		//draw background
		BufferedImage sky = loadImage("mario_sky.png");
		g.drawImage(sky,  0,  0, 2000, 1500, null);
		//draw ground
		g.setColor(Color.green);
		g.fillRect(0 , 600, 2000, 2000);
		//draw sprites
		g.setColor(Color.orange);
		for (int i = 0; i < model.sprites.size(); i++)
		{
			Sprite s = model.sprites.get(i);
			int pos = model.mario.x;
			s.draw(g, pos);
		}
	}
}
