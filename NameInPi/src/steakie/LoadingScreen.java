package steakie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class LoadingScreen extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public int width = 500;
	public int height = 150;
	
	boolean running = false;
	private JFrame frame;
	private Thread thread;
	private Graphics g;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public LoadingScreen() {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		frame = this;
		setUndecorated(true);
		setResizable(false);
		setVisible(true);
		setTitle("Pong");
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		start();
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "LoadingScreen");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Font f = new Font("Arial", Font.BOLD, 100);
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(new Color(0xffffff));
		g.setFont(f);
		g.drawString("Loading...", 10, 105);
		
		g.dispose();
		bs.show();
	}
	
	public void update() {
		
	}
	
	public void exit() {
		frame.dispose();
		stop();
		frame = null;
		
	}
	
	public void run() {
		long last = System.nanoTime();
		double ns = 1000000000.0 / 100.0;
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - last) / ns;
			last = now;
			while (delta >= 1) {
				update();
				delta--;
			}
			render();
		}
		stop();
	}
	
}
