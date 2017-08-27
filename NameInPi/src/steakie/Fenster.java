package steakie;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import steakie.res.Keyboard;

public class Fenster extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Maths m;
	private Pi pi = new Pi();
	//private int width = 1920;
	private int height = 1080;
	private boolean running = false;
	private JFrame frame;

	private JTextField inputField;
	private JTextField outputField, outputField2;
	private JButton button, exit;
	//private JButton button2;
	private Thread thread;
	private Keyboard keys;
	
	private Font font1;
	private Font font2;
	private Font font3;

	public Fenster() {
		setUndecorated(true);
		Color neu = new Color(0x444444);
		frame = this;
		m = new Maths();
		font1 = new Font("Arial", Font.BOLD, 160);
		font2 = new Font("Arial", 3, 80);
		font3 = new Font("Arail", Font.BOLD, 50);
		keys = new Keyboard();
		addKeyListener(keys);
		this.getContentPane().setLayout(null);
		getContentPane().setBackground(neu);
		einlesen();
		
		this.initWindow();

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		start();
		

	}
	
	public void update() {
		keys.update();
		if(keys.enter) {
			suchen();
		}
		//System.out.println(keys.enter);
	}

	protected void initWindow() {

		inputField = new JTextField();
		outputField = new JTextField("Name als Zahl:");
		outputField2 = new JTextField("Stelle:");
		button = new JButton("In Pi suchen");
		exit = new JButton("Exit");
		//button2 = new JButton("Einlesen");
		
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				suchen();
			}

		});
		
		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				exit();
			}

		});
		/*button2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//einlesen();
			}

		});*/

		inputField.setBounds(300, 15, 1320, 200);
		outputField.setBounds(300, height - 300, 1320, 150);
		outputField2.setBounds(300, height - 550, 1320, 150);
		button.setBounds(740, 330, 400, 100);
		exit.setBounds(1800, 20, 100, 40);
		//button2.setBounds(1100, 420, 400, 100);
		
		inputField.setFont(font1);
		outputField.setFont(font2);
		outputField2.setFont(font2);
		outputField.setEditable(false);
		outputField2.setEditable(false);
		button.setFont(font3);
		exit.setFont(new Font("Arial", 0, 20));
		//button2.setFont(font3);
		
		this.getContentPane().add(inputField);
		this.getContentPane().add(outputField);
		this.getContentPane().add(outputField2);
		this.getContentPane().add(button);
		this.getContentPane().add(exit);
		//this.getContentPane().add(button2);
		
		this.pack();

	}

	public void exit() {
		dispose();
		stop();
		System.exit(0);
	}
	
	public void einlesen() {
		pi.einlesen();
	}
	
	public void print(String s) {
		System.out.println(s);
	}
	
	public void suchen() {
		String name = "";
		try {
			name = inputField.getText();
		} catch (NumberFormatException e) {
			name = "";
		}
		char[] letters = name.toCharArray(); //Bugfree
		
		int numberOfLetters = letters.length;
		print("" + numberOfLetters);
		int[] koeffizienten = new int[numberOfLetters]; 

		for (int i = 0; i < numberOfLetters; i++) {
			koeffizienten[i] = m.getNumber(letters[i]);
		}
		
		int anzahlZiffern = koeffizienten.length;
		long wortWert = 0;
		wortWert = m.nameToNumber(koeffizienten);	
		//System.out.println(wortWert);
		String wortwertString = String.valueOf(wortWert);
		//System.out.println(wortwertString);
		char[] wortwertArray = wortwertString.toCharArray();
		
		for(int i = 0; i < wortwertArray.length; i++) {
			System.out.println(wortwertArray[i]);
		}
		
		
		pi.setArray(wortwertArray);
		
		long stelle = 0;
		//try {
			stelle = pi.stelleSuchen(anzahlZiffern);
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		if (stelle != 0) {
			outputField.setText("Name als Zahl: " + wortWert);
			outputField2.setText("Stelle: " + (stelle - 2));
		} else {
			outputField.setText("Name als Zahl: " + wortWert);
			outputField2.setText("Zahl nicht gefunden");
		}
	}
	
	public void inPiSuchen() {

		String name = "";

		try {
			name = inputField.getText();
		} catch (NumberFormatException e) {
			name = "";
		}

		char[] letters = name.toCharArray();
		int numberOfLetters = letters.length;

		int[] ziffernDerZahl = new int[numberOfLetters]; 

		for (int i = 0; i < numberOfLetters; i++) {
			ziffernDerZahl[i] = m.getNumber(letters[i]);
		}

		int anzahlZiffern = ziffernDerZahl.length;

		long wortWert = 0; 

		wortWert = m.nameToNumber(ziffernDerZahl);
		
		outputField.setText("Name als Zahl: " + wortWert + " | " + "suchen...");
		
		String wortwertString = String.valueOf(wortWert);

		char[] wortwertArray = wortwertString.toCharArray();

		pi.setArray(wortwertArray);

		long stelle = 0;
		
		try {
			stelle = pi.piStellenStream(anzahlZiffern);
		} catch (IOException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}


		if (stelle != 0) {
			outputField.setText("Name als Zahl: " + wortWert + " | " + "Stelle: " + (stelle - 2));
		} else {
			outputField.setText("Name als Zahl: " + wortWert + " | " + "Zahl nicht gefunden");
		}

	}
	
	public void inESuchen() {

		String name = "";

		try {
			name = inputField.getText();
		} catch (NumberFormatException e) {
			name = "";
		}

		char[] letters = name.toCharArray();
		int numberOfLetters = letters.length;

		int[] ziffernDerZahl = new int[numberOfLetters]; 

		for (int i = 0; i < numberOfLetters; i++) {
			ziffernDerZahl[i] = m.getNumber(letters[i]);
		}

		int anzahlZiffern = ziffernDerZahl.length;

		long wortWert = 0; 

		wortWert = m.nameToNumber(ziffernDerZahl);
		
		outputField.setText("Name als Zahl: " + wortWert + " | " + "suchen...");
		
		String wortwertString = String.valueOf(wortWert);

		char[] wortwertArray = wortwertString.toCharArray();

		pi.setArray(wortwertArray);

		long stelle = 0;
		try {
			stelle = pi.eStellenStream(anzahlZiffern);
		} catch (IOException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}


		if (stelle != 0) {
			outputField.setText("Name als Zahl: " + wortWert + " | " + "Stelle: " + (stelle - 2));
		} else {
			outputField.setText("Name als Zahl: " + wortWert + " | " + "Zahl nicht gefunden");
		}

	}
	
	private synchronized void start() {
		running = true;
		thread = new Thread(this, "MAIN");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		}
		stop();
	}

}
