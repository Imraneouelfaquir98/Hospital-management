package frames;

//import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class QuarantineFrame extends JFrame{
	public QuarantineFrame() {
		
		super("Quarantine Frame");

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
