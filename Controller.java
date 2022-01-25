//Benton Anderson
//September 7, 2021
//Assignment 2
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean toggleSave = false;
	Controller(Model m)
	{
		model = m;
	}

	void setView(View v)
	{
		view = v;
	}


	public void mousePressed(MouseEvent e)
	{
		model.setLocation(e.getX() + model.mario.x - 200, e.getY());
	}

	public void mouseReleased(MouseEvent e) 
	{    
		if (toggleSave == true)
		{
			model.addBricks(e.getX() + model.mario.x - 200, e.getY());
		}
	}
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e)
	{
		if(e.getY() < 100)
		{
			System.out.println("break here");
		}
	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: 
				keyRight = true;
				break;
				
			case KeyEvent.VK_LEFT: 
				keyLeft = true;
				break;
				
			case KeyEvent.VK_SPACE:
				keyUp = true;
				break;
				
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_SPACE: keyUp = false; break;
		}
		char c = e.getKeyChar();
		if (c == 'e')
		{
			toggleSave = !toggleSave;
		}
		if (toggleSave)
		{
			if (c == 's')
			{
				model.marshal().save("map.json");
				System.out.println("The screen has been saved.");
			}
			if (c == 'l')
			{
				Json bruh = Json.load("map.json");
				model.unmarshal(bruh);
				System.out.println("File loaded.");
			}
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		boolean marioimagecycle = false;
		if (keyRight)
		{
			model.mario.px = model.mario.x;
			model.mario.x += 4;
			marioimagecycle = true;
			model.mario.cycleThroughImages(marioimagecycle);
		}
		if (keyLeft)
		{
			model.mario.px = model.mario.x;
			model.mario.x -= 4;
			marioimagecycle = true;
			model.mario.cycleThroughImages(marioimagecycle);
		}
		if (keyUp)
		{
			if (model.mario.count == 0)
			{
				model.mario.jump();
			}
			else if ((keyUp) && (model.mario.count < 20))
			{
				model.mario.vert_vel -= 20;
			}
			else 
			{
				keyUp = false;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
