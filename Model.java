//Benton Anderson
//September 7, 2021
//Assignment 2
import java.util.ArrayList;
class Model
{
	ArrayList<Sprite> sprites;
	int x1;
	int y1;
	int width;
	int height;
	Mario mario;
	int coinBlockHit = 0;
	
	Model()
	{
		sprites = new ArrayList<Sprite>();
		Json bruh = Json.load("map.json");
		unmarshal(bruh);
		mario = new Mario();
		sprites.add(mario);
	}
	
	public void setLocation(int x, int y)
	{
		x1 = x;
		y1 = y;
	}

	public void addBricks(int a, int b)
	{
		//add bricks
		int width = a - x1;
		int height = b - y1;
		Brick temp = new Brick(x1, y1, width, height, sprites.size());
		sprites.add(temp);
		width = 0;
		height = 0;
	}
	
	 //Marshals this object into a JSON DOM
     Json marshal()
     {
         Json ob = Json.newObject();
         Json tmpList = Json.newList();
         ob.add("bricks", tmpList);
         for(int i = 0; i < sprites.size(); i++)
             tmpList.add(((Brick) sprites.get(i)).marshal());
         return ob;
     }
	 
	 void unmarshal(Json ob)
	 {
		 sprites = new ArrayList<Sprite>();
		 Json temp = ob.get("bricks");
		 for (int i = 0; i < temp.size(); i++)
		 {
			 sprites.add(new Brick(temp.get(i), sprites.size()));
		 }
		
	 }
	
	 
	
	public void update()
	{
		
		for (int i = 0; i < sprites.size(); i++)
		{
			Sprite s = sprites.get(i);
			s.update();
			//if sprite is brick, check for collisions
			if (s.isBrick())
			{
				//if collision, correct mario
				if (mario.detectCollisions(s))
				{
					mario.correctMario(s, i);
					//if mario hits coin brick, create coin
					if ((mario.hitBottom) && (mario.bottomCollisions <= 5))
					{
						coinBlockHit++;
						int a = (s.x + s.w) / 2;
						Coin c = new Coin(a, s.y, s.w, s.h);
						sprites.add(c);
						mario.count = 50;
						s.update();
						if (c.offScreen)
						{
							sprites.remove(c);
						}
						mario.hitBottom = false;
						//if mario hits the block 5 times, change brick
						if (coinBlockHit == 5)
						{
							sprites.remove(s);
							//sprites.remove(c);
							coinBlockHit = 0;
							Brick b = new Brick(s.x, s.y, s.w, s.h, 1);
							mario.hitBottom = false;
							sprites.add(b);
						}
					}
				}
			}
		}
	}
}