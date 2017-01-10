package com.minhvu.pong;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable
{
	// Entry Point.
	public static void main(String[] args)
	{
		new Game();
	}
	
	// Used For Accessing JPanel Method.
	private static Game instance;

	private boolean running = false;
	private Thread thread;
	
	// State Of The Game.
	public static enum STATE
	{
		MENU,
		GAME,
		HELP
	};
	
	private STATE state;
	
	// Menu For The Game.
	private Menu menu;
	
	// Keeps The Game's Score.
	private Score score;
	
	// Objects Used In The Game.
	private Ball ball;
	private Paddle toppaddle;
	private Paddle bottompaddle;

	/** Constructor */
	public Game()
	{
		instance = this;
		state = STATE.MENU;
		
		// Anonymous Use Of Keyboard Input.
		KeyListener keylistener = new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{

			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (state.equals(STATE.GAME))
				{
					toppaddle.keyPressed(e);
					bottompaddle.keyPressed(e);
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if (state.equals(STATE.GAME))
				{
					toppaddle.keyReleased(e);
					bottompaddle.keyReleased(e);
				}
			}
		};
		
		// Anonymouse Use Of Mouse Input.
		MouseListener mouselistener = new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				if (state.equals(STATE.MENU) || state.equals(STATE.HELP))
				{
					menu.mousePressed(e);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				
			}
		};

		addKeyListener(keylistener);
		addMouseListener(mouselistener);
		setFocusable(true);

		//Sound.BACKGROUND.loop();
		
		// Create The Frame.
		JFrame frame = new JFrame("Ping Pong");
		frame.add(this);
		frame.setSize(1260, 720);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize Everyhing.
		menu = new Menu();
		score = new Score();
		ball = new Ball();
		toppaddle = new Paddle(30, KeyEvent.VK_A, KeyEvent.VK_D);
		bottompaddle = new Paddle(getHeight() - 60, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
		
		// Begins The Thread.
		start();
	}
	
	// Starts The Thread.
	private synchronized void start()
	{
		if (running)
		{
			return;
		}
		
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	// Stops The Thread.
	private synchronized void stop()
	{
		if (!running)
		{
			return;
		}
		
		running = false;
		
		try
		{
			thread.join();
		}
		
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.exit(1);
	}
	
	// The Heart Of The Game: The Game Loop.
	@Override
	public void run()
	{
		long lasttime = System.nanoTime();
		final double ticks = 60.0;
		double nanoseconds = 1000000000 / ticks;
		double delta = 0;
		
		while (running)
		{
			long time = System.nanoTime();
			delta += (time - lasttime) / nanoseconds;
			lasttime = time;
			
			if (delta >= 1)
			{
				update();
				delta--;
			}
		}
		
		stop();
	}
	
	// Updates The Objects.
	private void update()
	{
		if (state.equals(STATE.GAME))
		{
			// Movement Of All Objects.
			ball.move();
			toppaddle.move();
			bottompaddle.move();
		}
		
		repaint();
	}

	// Used For Painting/Rendering Images.
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		if (state.equals(STATE.GAME))
		{
			ball.paint(g);
			toppaddle.paint(g);
			bottompaddle.paint(g);
			score.paint(g);
		}
		
		else if (state.equals(STATE.MENU) || state.equals(STATE.HELP))
		{
			menu.paint(g);
		}
	}

	// Used When The Game Is Over.
	public void end()
	{
		//Sound.BACKGROUND.stop();
		//Sound.GAMEOVER.play();
		JOptionPane.showMessageDialog(this, "Final Score: " + score.getScore(), "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}

	// Getters For The Class Objects.
	
	public static Game getInstance()
	{
		return instance;
	}
	
	public STATE getState()
	{
		return state;
	}

	public void setState(STATE state)
	{
		this.state = state;
	}
	
	public Score getScore()
	{
		return score;
	}

	public Ball getBall()
	{
		return ball;
	}

	public Paddle getTopPaddle()
	{
		return toppaddle;
	}
	
	public Paddle getBottomPaddle()
	{
		return bottompaddle;
	}
}