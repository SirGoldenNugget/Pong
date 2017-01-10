package com.minhvu.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Menu
{
	private Rectangle playbutton;
	private Rectangle helpbutton;
	private Rectangle quitbutton;
	private Rectangle backbutton;
	
	public Menu()
	{
		playbutton = new Rectangle((Game.getInstance().getWidth() - 400) / 2, 200, 400, 100);
		helpbutton = new Rectangle((Game.getInstance().getWidth() - 400) / 2, 350, 400, 100);
		quitbutton = new Rectangle((Game.getInstance().getWidth() - 400) / 2, 500, 400, 100);
		backbutton = new Rectangle((Game.getInstance().getWidth() - 400) / 2, 550, 400, 100);
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		Font titlefont = new Font("arial", Font.BOLD, 150);
		g.setFont(titlefont);
		g.setColor(Color.BLACK);
		g.drawString("PONG", (int) ((Game.getInstance().getWidth() - g.getFontMetrics().stringWidth("PONG")) / 2), 150);

		Font buttonfont = new Font("arial", Font.BOLD, 100);
		g.setFont(buttonfont);
		
		if (Game.getInstance().getState().equals(Game.STATE.MENU))
		{
			g.drawString("PLAY", playbutton.x + (playbutton.width - g.getFontMetrics().stringWidth("PLAY")) / 2, playbutton.y + 85);
			g.drawString("HELP", helpbutton.x + (helpbutton.width - g.getFontMetrics().stringWidth("HELP")) / 2, helpbutton.y + 85);
			g.drawString("QUIT", quitbutton.x + (quitbutton.width - g.getFontMetrics().stringWidth("QUIT")) / 2, quitbutton.y + 85);
			
			g2d.draw(playbutton);
			g2d.draw(helpbutton);
			g2d.draw(quitbutton);
		}
		
		else if (Game.getInstance().getState().equals(Game.STATE.HELP))
		{
			Font helpfont = new Font("arial", Font.BOLD, 20);
			
			String helptextad = "Player 1: A & D To Move";
			String helptextlr = "Player 2: Left & Right To Move";

			g.drawString("BACK", backbutton.x + (backbutton.width - g.getFontMetrics().stringWidth("BACK")) / 2, backbutton.y + 85);
			g.setFont(helpfont);
			g.drawString(helptextad, (int) ((Game.getInstance().getWidth() - g.getFontMetrics().stringWidth(helptextad)) / 2), 250);
			g.drawString(helptextlr, (int) ((Game.getInstance().getWidth() - g.getFontMetrics().stringWidth(helptextlr)) / 2), 300);
			
			g2d.draw(backbutton);
		}
	}
	
	public void mousePressed(MouseEvent e)
	{
		Point location = new Point(e.getX(), e.getY());
		
		if (Game.getInstance().getState().equals(Game.STATE.MENU))
		{
			if (playbutton.contains(location))
			{	
				Game.getInstance().setState(Game.STATE.GAME);
			}
			
			else if (helpbutton.contains(location))
			{
				Game.getInstance().setState(Game.STATE.HELP);
			}
			
			else if (quitbutton.contains(location))
			{
				System.exit(1);
			}
		}
		
		else if (Game.getInstance().getState().equals(Game.STATE.HELP))
		{
			if (backbutton.contains(location))
			{
				Game.getInstance().setState(Game.STATE.MENU);
			}
		}
	}
}
