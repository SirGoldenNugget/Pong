package com.minhvu.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class Ball
{
	private Point location;
	private Point acceleration;
	
	private int diameter;
	private int speed;

	public Ball()
	{
		diameter = 30;
		location = new Point((Game.getInstance().getWidth() - diameter) / 2, (Game.getInstance().getHeight() - diameter) / 2);
		acceleration = new Point();
		speed = 1;

		if (Math.random() < 0.5)
		{
			acceleration.x = 1;
		}
		
		else
		{
			acceleration.x = -1;
		}
		
		if (Math.random() < 0.5)
		{
			acceleration.y = 1;
		}
		
		else
		{
			acceleration.y = -1;
		}
	}

	public Point getLocation()
	{
		return location;
	}

	public int getRadius()
	{
		return diameter;
	}

	void move()
	{
		boolean directionchange = true;
		
		// Checks For Going Too Far Across The Y Axis.
		if (location.y + acceleration.y > Game.getInstance().getHeight() - diameter || location.y + acceleration.y < 0)
		{
			Game.getInstance().end();
		}
		
		// Collision Detection Of Ball And Paddle.
		else if (Game.getInstance().getTopPaddle().getBounds().intersects(getBounds()) || Game.getInstance().getBottomPaddle().getBounds().intersects(getBounds()))
		{
			Game.getInstance().getScore().increment();
			
			++speed;
			
			if (Game.getInstance().getTopPaddle().getBounds().intersects(getBounds()))
			{	
				acceleration.y = speed;
				location.y = Game.getInstance().getTopPaddle().getLocation().y + Game.getInstance().getTopPaddle().getDimensions().height;
			}
			
			else if (Game.getInstance().getBottomPaddle().getBounds().intersects(getBounds()))
			{
				acceleration.y = -speed;
				location.y = Game.getInstance().getBottomPaddle().getLocation().y - diameter;
			}
		}
		
		// Used For Detection Of Window Borders.
		
		else if (location.x + acceleration.x < 0)
		{
			acceleration.x = speed;
		}
		
		else if (location.x + acceleration.x > Game.getInstance().getWidth() - diameter)
		{
			acceleration.x = -speed;
		}
		
		else if (location.y + acceleration.y < 0)
		{
			acceleration.y = speed;
		}
		
		else if (location.y + acceleration.y > Game.getInstance().getHeight() - diameter)
		{
			acceleration.y = -speed;
		}
		
		else
		{
			directionchange = false;
		}
		
		// Plays The Bounce Sound During A Change Of Direction.
		if (directionchange)
		{
			Sound.BOUNCE.stop();
			Sound.BOUNCE.play();
		}

		location.x += acceleration.x;
		location.y += acceleration.y;
	}

	//  Renders The Image.
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.RED);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillOval(location.x, location.y, diameter, diameter);
		g2d.dispose();
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(location.x, location.y, diameter, diameter);
	}
}
