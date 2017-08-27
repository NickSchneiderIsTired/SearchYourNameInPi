package steakie;

import javax.swing.JFrame;

public class Main{


    public static void main(String[] args) {
    	LoadingScreen ls = new LoadingScreen();
        Fenster f = new Fenster();
        f.setBounds(0, 0, 1920, 1080);
		f.setVisible(true);
		f.setResizable(true);
		f.setLocationRelativeTo(null);
		f.setTitle("Name in Pi");
		ls.exit();
    }

}
