package frames;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.Vector;

import org.json.*;





public class ReceptionJFrame extends javax.swing.JFrame {


    public MessageHandler messageHandler;
    public ReceptionAgent myAgent;
    
    public ReceptionJFrame(ReceptionAgent agent) {
        myAgent = agent;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    public void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        loginPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel20 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        patientPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        days = new javax.swing.JComboBox<>();
        months = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jToggleButton2 = new javax.swing.JToggleButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemShowPatient = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Reception Agent");

        loginPanel.setBackground(new java.awt.Color(255, 127, 80));

        jLabel2.setText("Password");

        jPasswordField1.setEchoChar('#');

        jButton3.setText("Login");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        // jToggleButton1.setText("Hide");
        // jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
        //     public void actionPerformed(java.awt.event.ActionEvent evt) {
        //         jToggleButton1ActionPerformed(evt);
        //     }
        // });

        jLabel20.setText("Username");

        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel20)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPanelLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(24, 24, 24)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton1)
                .addGap(32, 32, 32)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(jButton3)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        patientPanel.setBackground(new java.awt.Color(112, 161, 255));

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Name*");

        jLabel7.setText("CIN*");

        jLabel8.setText("mobile*");

        jLabel9.setText("Gender");

        jLabel10.setText("DOB");

        jLabel12.setText("Email");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male","Female" }));

        days.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" }));

months.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "feb", "Mar", "Apr",
    "May", "Jun", "July", "Aug",
    "Sup", "Oct", "Nov", "Dec"  }));

    jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1995", "1996", "1997", "1998",
        "1999", "2000", "2001", "2002",
        "2003", "2004", "2005", "2006",
        "2007", "2008", "2009", "2010",
        "2011", "2012", "2013", "2014",
        "2015", "2016", "2017", "2018",
        "2019" }));

jToggleButton2.setText("Hide");
jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
    jToggleButton2ActionPerformed(evt);
    }
    });

    jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nursery","Consulting","Blood Donation","Visitor" }));

    jLabel21.setText("Service");

    jTextField9.setText(" ");
    jTextField9.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            jTextField9CaretUpdate(evt);
        }
    });
    jTextField9.addAncestorListener(new javax.swing.event.AncestorListener() {
        public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            jTextField9AncestorAdded(evt);
        }
        public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
        }
        public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
        }
    });

    jLabel13.setText("Patient ID(Only for Visitor)");

    javax.swing.GroupLayout patientPanelLayout = new javax.swing.GroupLayout(patientPanel);
    patientPanel.setLayout(patientPanelLayout);
    patientPanelLayout.setHorizontalGroup(
        patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(patientPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(patientPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel13)
                    .addGap(34, 34, 34)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(patientPanelLayout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7))
                    .addGap(71, 71, 71)
                    .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                        .addComponent(jTextField2)
                        .addComponent(jTextField1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10)
                        .addComponent(jLabel12)
                        .addComponent(jLabel21))
                    .addGap(34, 34, 34)
                    .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(patientPanelLayout.createSequentialGroup()
                            .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(months, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 189, Short.MAX_VALUE))
                        .addComponent(jTextField4)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jToggleButton2)
            .addContainerGap())
    );
    patientPanelLayout.setVerticalGroup(
        patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(patientPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel13))
            .addGap(6, 6, 6)
            .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(patientPanelLayout.createSequentialGroup()
                    .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel12)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(patientPanelLayout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(months, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, patientPanelLayout.createSequentialGroup()
                            .addGap(18, 18, Short.MAX_VALUE)
                            .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21))
                            .addGap(53, 53, 53))))
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(patientPanelLayout.createSequentialGroup()
                    .addComponent(jToggleButton2)
                    .addContainerGap())
                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)))
    );

    jLabel11.setText("Not Connected  Yet");

    jMenu1.setText("Options");

    jMenuItem5.setText("Add New Patient");
    jMenu1.add(jMenuItem5);

    jMenuItem6.setText("Add New Visitor");
    jMenu1.add(jMenuItem6);

    jMenuItem7.setText("Disconnect");
    jMenu1.add(jMenuItem7);

    jMenuBar1.add(jMenu1);

    jMenu2.setText("Others");

    jMenu3.setText("Show");

    jMenuItemShowPatient.setText("New Patient");
    jMenuItemShowPatient.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItemShowPatientActionPerformed(evt);
        }
    });
    jMenu3.add(jMenuItemShowPatient);

    jMenuItem1.setText("New Visitor");
    jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            // jMenuItem1ActionPerformed(evt);
        }
    });
    jMenu3.add(jMenuItem1);

    jMenu2.add(jMenu3);

    jMenu4.setText("Hide");

    jMenuItem2.setText("Login");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem2ActionPerformed(evt);
        }
    });
    jMenu4.add(jMenuItem2);

    jMenuItem3.setText("Visitor");
    jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem3ActionPerformed(evt);
        }
    });
    jMenu4.add(jMenuItem3);

    jMenuItem4.setText("Patient");
    jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem4ActionPerformed(evt);
        }
    });
    jMenu4.add(jMenuItem4);

    jMenuItem8.setText("All");
    jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem8ActionPerformed(evt);
        }
    });
    jMenu4.add(jMenuItem8);

    jMenu2.add(jMenu4);

    jMenuBar1.add(jMenu2);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11)
            .addGap(45, 45, 45))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(patientPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(jLabel11))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(patientPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    patientPanel.setVisible(false);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    public void jMenuItemShowPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemShowPatientActionPerformed
        // TODO add your handling code here:
        patientPanel.setVisible(true);
    }//GEN-LAST:event_jMenuItemShowPatientActionPerformed

    public void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        loginPanel.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    public void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        patientPanel.setVisible(false);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    public void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        // visitorPanel.setVisible(false);
    }//GEN-LAST:event_jMenuItem3ActionPerformed


    public void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        patientPanel.setVisible(false);
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    public void jTextField9AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTextField9AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9AncestorAdded

    public void jTextField9CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField9CaretUpdate
        // TODO add your handling code here:
        System.out.println("Searching On : " + jTextField9.getText());
        
        String name = jTextField9.getText();
        
        // jList1.setModel(new javax.swing.AbstractListModel<String>() {
        // String[] strings = { name +"1", name +"2", name+"3"};
        // public int getSize() { return strings.length; }
        // public String getElementAt(int i) { return strings[i]; }
        //      });

        // jScrollPane1.setViewportView(jList1);



    }//GEN-LAST:event_jTextField9CaretUpdate

    public void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        loginPanel.setVisible(false);
        patientPanel.setVisible(false);
        // visitorPanel.setVisible(false);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    public void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    public void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jLabel11.setText("Connecting ...");
        
        

         // creating JSONObject 
        JSONObject jo = new JSONObject(); 
        jo.put("username",jTextField10.getText());
        jo.put("password",String.valueOf(jPasswordField1.getPassword()));


        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setOntology("connexion");
        message.addReceiver(new AID("server", AID.ISLOCALNAME));
        message.setContent(jo.toString());

        myAgent.send(message);
        // if (this.messageHandler != null) {
        //     this.messageHandler.handleMessage( message);
        // }

    }//GEN-LAST:event_jButton3ActionPerformed

    public void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    public void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
    	
    }

    public void jButton1ActionPerformed(java.awt.event.ActionEvent evt){
    	
    }

    /**
     * @param args the command line arguments
     */
   

    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }
    public static interface MessageHandler {

        public void handleMessage(ACLMessage message);
    }
    
    public void connexion(boolean b)
    {
        if(b)
        {
             jLabel11.setText("Connected");
             loginPanel.setVisible(false);
             patientPanel.setVisible(true);
        }else{
            jLabel11.setText("Connexion Refused");
        }
       
    }

     public void toDataBase()
    {
        JSONObject jo = new JSONObject(); 

        jo.put("name",jTextField1.getText());
        jo.put("cin",jTextField2.getText());
        jo.put("mobile",jTextField3.getText());
        jo.put("email",jTextField4.getText());
        jo.put("name2",jTextField9.getText());

        jo.put("gender",(String)jComboBox2.getSelectedItem()); //2
        jo.put("dob","01/01/2021");
        jo.put("service",(String)jComboBox4.getSelectedItem()); //4

        jo.put("address","-");


        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setOntology("new");
        message.addReceiver(new AID("server", AID.ISLOCALNAME));
        message.setContent(jo.toString());

        myAgent.send(message);

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> days;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton3;
    public javax.swing.JComboBox<String> jComboBox2;
    public javax.swing.JComboBox<String> jComboBox4;
    public javax.swing.JComboBox<String> jComboBox5;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
    public javax.swing.JMenu jMenu3;
    public javax.swing.JMenu jMenu4;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JMenuItem jMenuItem2;
    public javax.swing.JMenuItem jMenuItem3;
    public javax.swing.JMenuItem jMenuItem4;
    public javax.swing.JMenuItem jMenuItem5;
    public javax.swing.JMenuItem jMenuItem6;
    public javax.swing.JMenuItem jMenuItem7;
    public javax.swing.JMenuItem jMenuItem8;
    public javax.swing.JMenuItem jMenuItemShowPatient;
    public javax.swing.JPasswordField jPasswordField1;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField10;
    public javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    public javax.swing.JTextField jTextField9;
    public javax.swing.JToggleButton jToggleButton1;
    public javax.swing.JToggleButton jToggleButton2;
    public javax.swing.JPanel loginPanel;
    public javax.swing.JComboBox<String> months;
    public javax.swing.JPanel patientPanel;
    // End of variables declaration//GEN-END:variables
}
