package daw;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

    	JFrame frame = new JFrame("Calculadora");
    	frame.setBounds(250, 250, 0, 0);
    	frame.add(new Panel());
   	 
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.pack();
   	 
	}
    
}
