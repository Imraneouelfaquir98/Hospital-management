
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.*;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class Consulting extends Agent {
	Queue<String> patients = new LinkedList<>(); // ID@***#***
	String currentPatient ; // ex:   ID@20#Service@consulting
	private class MyFrame 
		extends JFrame implements ActionListener { 
	    public JLabel res;
	    private JButton vsub,nsub,tsub; 
	    private Container c; 

	    	public MyFrame() 
			{ 
				setTitle("Consulting Agent"); 
				setBounds(0, 0, 300, 300); 
				setDefaultCloseOperation(EXIT_ON_CLOSE); 
				setResizable(false); 
		
				c = getContentPane(); 
				c.setLayout(null); 

		        // Frame Title 
		        res = new JLabel("NO ONE,CLICK NEXT"); 
				res.setFont(new Font("Arial", Font.PLAIN, 10)); 
				res.setSize(300, 20); 
				res.setLocation(0, 0); 
				c.add(res); 

				
				vsub = new JButton("GOTO Vaccination"); 
				vsub.setFont(new Font("Arial", Font.PLAIN, 15)); 
				vsub.setSize(300, 20); 
				vsub.setLocation(0, 50); 
				vsub.addActionListener(this); 
				c.add(vsub); 

				nsub = new JButton("GOTO Nursery"); 
				nsub.setFont(new Font("Arial", Font.PLAIN, 15)); 
				nsub.setSize(300, 20); 
				nsub.setLocation(0, 100); 
				nsub.addActionListener(this); 
				c.add(nsub); 

				tsub = new JButton("Next"); 
				tsub.setFont(new Font("Arial", Font.PLAIN, 15)); 
				tsub.setSize(300, 20); 
				tsub.setLocation(0, 150); 
				tsub.addActionListener(this); 
				c.add(tsub); 


				setVisible(true); 


			}

		public void actionPerformed(ActionEvent e) 
		{ 
			if (e.getSource() == vsub ) { 

				System.out.println("Consumting>> Sending to vaccintion : "+currentPatient);
				
				ACLMessage message = new ACLMessage(ACLMessage.INFORM);
				message.addReceiver(new AID("vacination", AID.ISLOCALNAME));
				message.setContent(currentPatient); // TODO change the id
				send(message);

				}
			if (e.getSource() == tsub) { 

				currentPatient = patients.peek();
				res.setText(currentPatient);

				}
	
		}
	}
	MyFrame f = new MyFrame();

	protected void setup() 
	{

		System.out.println("---------------------------------------");
		System.out.println("-----------------Consulting Room Open---------------");
		System.out.println("---------------------------------------");



		FSMBehaviour agent_beh = new FSMBehaviour();

		agent_beh.registerFirstState(new waiting(), "waiting");
		agent_beh.registerState(new consultingprocess(), "consultingprocess");



		agent_beh.registerDefaultTransition("waiting", "consultingprocess");

		agent_beh.registerTransition("consultingprocess", "waiting",1);





		addBehaviour(agent_beh);
	}

	private class waiting extends OneShotBehaviour {

		@Override
		public void action() {

			System.out.println("Consulting>> waiting ...");
			block();
		}
	}

	private class consultingprocess extends OneShotBehaviour {

		@Override
		public void action() {

			System.out.println("Consulting>> Action Started");
		
			ACLMessage messageRecu = receive();
			System.out.println("Consulting>> Recieve >>"+messageRecu.getContent());
			patients.add(messageRecu.getContent());
			
		}

		public int onEnd() {
			return 1;
		}
	}

}