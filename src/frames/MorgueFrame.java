package frames;

//import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class MorgueFrame extends JFrame{
	public MorgueFrame() {
		
		super("Morgue Frame");

		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		};

		addWindowListener(l);
		setSize(1000,800);
		setVisible(true);
	}
}
