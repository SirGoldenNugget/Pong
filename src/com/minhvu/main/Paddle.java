package com.minhvu.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

public class Paddle
{
	private Point location;
	private Dimension dimensions;
	
	private int left;
	private int right;
	
	private boolean moveleft;
	private boolean moveright;
	
	private int speed;

	public Paddle(int elevation, int left, int right)
	{
		dimensions = new Dimension(120, 20);
		location = new Point((Game.getInstance().getWidth() - dimensions.width) / 2, elevation);
		
		this.left = left;
		this.right = right;
		
		moveleft = false;
		moveright = false;
		
		speed = 10;
	}

	// Moves The Paddle Only When There Is Keyboard Input.
	public void move()
	{	
		if (moveleft)
		{
			if (location.x - speed > 0)
			{
				location.x -= speed;
			}
		}
		
		if (moveright)
		{
			if (location.x + speed < Game.getInstance().getWidth() - dimensions.width)
			{
				location.x += speed;
			}
		}
	}

	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.BLACK);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillRect(location.x, location.y, dimensions.width, dimensions.height);
		g2d.dispose();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == left)
		{
			moveleft = false;
		}
		
		if (e.getKeyCode() == right)
		{
			moveright = false;
		}
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == left)
		{
			moveleft = true;
		}
		
		if (e.getKeyCode() == right)
		{
			moveright = true;
		}
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(location.x, location.y, dimensions.width, dimensions.height);
	}
	
	public Point getLocation()
	{
		return location;
	}
	
	public Dimension getDimensions()
	{
		return dimensions;
	}
}
