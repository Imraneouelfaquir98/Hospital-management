
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;


import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 



public class Nursery extends Agent {

	private class MyFrame 
		extends JFrame { 
	    public JLabel res;
	    
	    
	    private Container c; 

	    	public MyFrame() 
			{ 
				setTitle("Nursery Agent"); 
				setBounds(0, 300, 300, 600); 
				setDefaultCloseOperation(EXIT_ON_CLOSE); 
				setResizable(false); 
		
				c = getContentPane(); 
				c.setLayout(null); 

		        // Frame Title 
		        res = new JLabel("Test"); 
				res.setFont(new Font("Arial", Font.PLAIN, 20)); 
				res.setSize(300, 600); 
				res.setLocation(100, 500); 
				c.add(res); 

				
				setVisible(true); 
			}
	}
	MyFrame f = new MyFrame();

	protected void setup() 
	{

		System.out.println("---------------------------------------");
		System.out.println("-----------------Nursery Room Open---------------");
		System.out.println("---------------------------------------");

		FSMBehaviour agent_beh = new FSMBehaviour();

		agent_beh.registerFirstState(new waiting(), "waiting");
		agent_beh.registerState(new getRequest(), "getrequest");

		agent_beh.registerDefaultTransition("waiting", "getrequest");
		agent_beh.registerTransition("getrequest", "waiting",1);

		addBehaviour(agent_beh);
	}

	private class waiting extends OneShotBehaviour {

		@Override
		public void action() {

			System.out.println("Nursery:waiting ...");
			block();
		}
	}

	private class getRequest extends OneShotBehaviour {

		@Override
		public void action() {

			System.out.println("Nursery: Action Started");
			
			ACLMessage messageRecu = receive();
			System.out.println("Nursery: Recieve >>"+messageRecu.getContent());
			
		}

		public int onEnd() {
			return 1;
		}
	}
}