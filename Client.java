

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Client() {
       initGUI();
       ulist = new HashMap<Integer,String>();
       this.addWindowListener(new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
			//set the security manager for the client
            System.setSecurityManager(new RMISecurityManager());
//get the remote object from the registry
            try {
                System.out.println("Security Manager loaded");
                //String url = "rmi://172.16.18.176/CHAT-SERVER";
                String url = ipField.getText().toString();
                Inter remoteObject = (Inter) Naming.lookup(url);
                System.out.println(" Got message ");
                remoteObject.exitUser(UserID);
                }
             catch (RemoteException exc) {
                System.out.println("Error in lookup: " + exc.toString());
            } catch (java.net.MalformedURLException exc) {
                System.out.println("Malformed URL: " + exc.toString());
            } catch (java.rmi.NotBoundException exc) {
                System.out.println("NotBound: " + exc.toString());
            } 
			
			
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			
						//set the security manager for the client
			            System.setSecurityManager(new RMISecurityManager());
			//get the remote object from the registry
			            try {
			                System.out.println("Security Manager loaded");
			                //String url = "rmi://172.16.18.176/CHAT-SERVER";
			                String url = ipField.getText().toString();
			                Inter remoteObject = (Inter) Naming.lookup(url);
			                System.out.println(" Got message ");
			                remoteObject.exitUser(UserID);
			                }
			             catch (RemoteException exc) {
			                System.out.println("Error in lookup: " + exc.toString());
			            } catch (java.net.MalformedURLException exc) {
			                System.out.println("Malformed URL: " + exc.toString());
			            } catch (java.rmi.NotBoundException exc) {
			                System.out.println("NotBound: " + exc.toString());
			            } 
						
						
					}
					
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
       
    }
	
		// Connect status constants
		   final static int DISCONNECTED = 0;
		   final static int CONNECTED = 1;
		   public Boolean loginStatus = false;
		   HashMap<Integer, String> ulist = null;
		   public int UserID = -1;
		   // Various GUI components and info
		   public  JFrame mainFrame = null;
		   public   JTextArea chatText = null;
		   public   JTextArea chatLine = null;
		   public   JLabel statusBar = null;
		   public   JTextField ipField = null;
		   public   JTextField userName = null;
		   public   JPasswordField passWord = null;
		   public DefaultListModel listmodel=null;
		   public   JList targetlist = null;
		   public   JScrollPane trgtscroll = null;
		   public   JButton signup = null;
		   public   JButton connectButton = null;
		   public   JButton disconnectButton = null;
		   public   JButton sendmessage = null;
		   public   JButton clearButton = null;
		   public JSeparator separator1 =null;
		   	
		   
		   // Connection info
		   public static String hostIP = "CHAT-SERVER";
		   public String username = null;
		   public static String password = null;
		   public   int connectionStatus = DISCONNECTED;
		   public  Poll pollObj = null;
		   
		   @SuppressWarnings("deprecation")
		public void decidePoll(){
			   if(connectionStatus == CONNECTED){
				   pollObj = new Poll();
				   pollObj.start();
			   }
			   else if(connectionStatus == DISCONNECTED && pollObj!=null){
				   pollObj.stop();
				   pollObj=null;
			   }
				   
		   }
		   
		   private   JPanel initOptionsPane() {
		      JPanel pane = null;
		      
		      ActionAdapter disconnectButtonListener = null;
		     

		      // Create an options pane
		      // GridLayout(row,Column)
		      JPanel optionsPane = new JPanel(new GridLayout(6, 1));


		      // Server address input
		      pane = new JPanel(new BorderLayout());
		      pane.add(new JLabel(" Server address: "),BorderLayout.WEST);
		      ipField = new JTextField(40);
		      ipField.setText(hostIP);
		      ipField.setEditable(true);
		      pane.add(ipField,BorderLayout.CENTER);
		      optionsPane.add(pane);

		      pane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		      pane.add(new JSeparator());
		      optionsPane.add(pane);
		      
		      // Username input
		      pane = new JPanel(new BorderLayout());
		      pane.add(new JLabel(" Username: "),BorderLayout.WEST);
		      userName = new JTextField(15); 
		      userName.setEditable(true);
		     // userName.setText(user);
		      pane.add(userName,BorderLayout.CENTER);
		      pane.add(new JLabel("                                                                                     ")
		      ,BorderLayout.EAST);
		      optionsPane.add(pane);

		      
		      //Password input
		      pane = new JPanel(new BorderLayout());
		      
		      JPanel passpane = new JPanel(new BorderLayout());
		      passpane.add(new JLabel(" Password: "),BorderLayout.WEST);
		      passWord = new JPasswordField(15);
		      passWord.setEditable(true);
		      passpane.add(passWord,BorderLayout.CENTER);
		      
		      pane.add(passpane,BorderLayout.WEST);
		      signup = new JButton("Sign up");
		      signup.setMnemonic(KeyEvent.VK_U);
		      signup.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent ev) {
					// TODO Auto-generated method stub
					signupButtonListner(ev);
					
					
				}
			});
		      pane.add(signup,BorderLayout.EAST);
		      optionsPane.add(pane);
		      
		      pane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		      pane.add(new JSeparator());
		      optionsPane.add(pane);
			        
		      
		      // Connect/disconnect buttons
		      JPanel buttonPane = new JPanel(new GridLayout(1, 2));
		      
		               // Disconnect
		      disconnectButtonListener = new ActionAdapter() {
		            public void actionPerformed(ActionEvent e) {
		            	//set the security manager for the client
		                System.setSecurityManager(new RMISecurityManager());
		    //get the remote object from the registry
		                try {
		                    System.out.println("Security Manager loaded");
		                    //String url = "rmi://172.16.18.176/CHAT-SERVER";
		                    String url = ipField.getText().toString();
		                    Inter remoteObject = (Inter) Naming.lookup(url);
		                    System.out.println(" Got message ");
		                    remoteObject.exitUser(UserID);
		                    }
		                 catch (RemoteException exc) {
		                    System.out.println("Error in lookup: " + exc.toString());
		                } catch (java.net.MalformedURLException exc) {
		                    System.out.println("Malformed URL: " + exc.toString());
		                } catch (java.rmi.NotBoundException exc) {
		                    System.out.println("NotBound: " + exc.toString());
		                } 
		            	 finally{ 
		                  UserID = -1;
		                  connectButton.setEnabled(true);
		                  disconnectButton.setEnabled(false);
		                  sendmessage.setEnabled(false);
		                  clearButton.setEnabled(false);
		                  connectionStatus = DISCONNECTED;
		                  signup.setEnabled(true);
		                  decidePoll();
		                  ipField.setEnabled(true);
		                  userName.setEnabled(true);
		                  passWord.setEnabled(true);
		                //  hostOption.setEnabled(true);
		                 // guestOption.setEnabled(true);
		                  chatLine.setText(""); chatLine.setEnabled(false);
		                  statusBar.setText("Offline");
		                  mainFrame.repaint(); //update the frame
		            	 }
		                }
		         };
		      connectButton = new JButton("Sign In");
		      connectButton.setMnemonic(KeyEvent.VK_C);
		      connectButton.setActionCommand("connect");
		      connectButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent ev) {
					// TODO Auto-generated method stub
					connectButtonListener(ev);
				}
			});
		      connectButton.setEnabled(true);
		      disconnectButton = new JButton("Sign Out");
		      disconnectButton.setMnemonic(KeyEvent.VK_D);
		      disconnectButton.setActionCommand("disconnect");
		      disconnectButton.addActionListener(disconnectButtonListener);
		      disconnectButton.setEnabled(false);
		      buttonPane.add(connectButton);
		      buttonPane.add(disconnectButton);
		      optionsPane.add(buttonPane);


		      return optionsPane;
		   }

		  

		protected void signupButtonListner(ActionEvent ev) {
			// TODO Auto-generated method stub
			//set the security manager for the client
             System.setSecurityManager(new RMISecurityManager());
 //get the remote object from the registry
             try {
                 System.out.println("Security Manager loaded");
                 //String url = "rmi://172.16.18.176/CHAT-SERVER";
                 String url = ipField.getText().toString();
                 Inter remoteObject = (Inter) Naming.lookup(url);
                 System.out.println(" Got message ");
                 String User =userName.getText().toString();
                 @SuppressWarnings("deprecation")
				String Pass = passWord.getText().toString();
                 if(!User.isEmpty() && !Pass.isEmpty()){
                 remoteObject.addUser(User, Pass);
                 }
             } catch (RemoteException exc) {
                 System.out.println("Error in lookup: " + exc.toString());
             } catch (java.net.MalformedURLException exc) {
                 System.out.println("Malformed URL: " + exc.toString());
             } catch (java.rmi.NotBoundException exc) {
                 System.out.println("NotBound: " + exc.toString());
             } 
}

		private   void initGUI() {
		      // Set up the status bar
		      statusBar = new JLabel();
		      statusBar.setText("Offline");
		      ActionAdapter sendButtonListener = null;
		      ActionAdapter clearButtonListener = null;
		      
		      sendButtonListener = new ActionAdapter(){
		    	  
		    	  public void actionPerformed(ActionEvent e) {
		    		// TODO add your handling code here:
		    		// set the security manager for the client
		    		        System.setSecurityManager(new RMISecurityManager());
		    		//get the remote object from the registry
		    		        String target = null;
		    		        if(!(chatLine.getText().toString().equals(""))){
		    		        try {
		    		            System.out.println("Security Manager loaded");
		    		//String url = "//localhost/CHAT-SERVER";
		    		            //String url = "rmi://172.16.18.176/CHAT-SERVER";
		    		            String url = ipField.getText().toString();
		    		            Inter remoteObject = (Inter) Naming.lookup(url);
		    		            System.out.println("Got remote object");
		    		            Integer i = new Integer(0);
		    		            target = "All";
			    		        if(targetlist.getSelectedIndex()!= 0){ 
		    		           target = targetlist.getSelectedValue().toString();
		    		             for (Entry<Integer, String> entry : ulist.entrySet()) {
		    		                if (target.equals(entry.getValue())) {
		    		                     i = entry.getKey();
		    		                     break;
		    		                }
		    		            }
			    		        }
		    		            String message = chatLine.getText();
		    		            chatLine.setText("");
		    		            remoteObject.chat(UserID,i,message);
		    		            System.out.println(" Sent message ");
		    		        } catch (RemoteException exc) {
		    		            System.out.println("Error in lookup: " + exc.toString());
		    		        } catch (java.net.MalformedURLException exc) {
		    		            System.out.println("Malformed URL: " + exc.toString());
		    		        } catch (java.rmi.NotBoundException exc) {
		    		            System.out.println("NotBound: " + exc.toString());
		    		        }
		    	  }
		    		        }
		    	  
		      };
		      
		      clearButtonListener = new ActionAdapter(){
		    	  public void actionPerformed(ActionEvent e) {
				    		// set the security manager for the client
				    		        System.setSecurityManager(new RMISecurityManager());
				    		//get the remote object from the registry
				    		        try {
				    		            System.out.println("Security Manager loaded");
				    		//String url = "//localhost/CHAT-SERVER";
				    		            //String url = "rmi://172.16.18.176/CHAT-SERVER";
				    		            String url = ipField.getText().toString();
				    		            Inter remoteObject = (Inter) Naming.lookup(url);
				    		            System.out.println("Got remote object");
				    		            remoteObject.clear(UserID);
				    		            chatText.setText("");
				    		            System.out.println(" Clearing History ");
				    		        } catch (RemoteException exc) {
				    		            System.out.println("Error in lookup: " + exc.toString());
				    		        } catch (java.net.MalformedURLException exc) {
				    		            System.out.println("Malformed URL: " + exc.toString());
				    		        } catch (java.rmi.NotBoundException exc) {
				    		            System.out.println("NotBound: " + exc.toString());
				    		        }
				    	  }
		      };
		      
		      
		      
		      sendmessage = new JButton("Send   ");
		      sendmessage.setEnabled(false);
		      sendmessage.setMnemonic(KeyEvent.VK_S);
		      sendmessage.setActionCommand("send");
		      sendmessage.addActionListener(sendButtonListener);
		      clearButton = new JButton("Clear   ");
		      clearButton.setEnabled(false);
		      clearButton.setMnemonic(KeyEvent.VK_X);
		      clearButton.setActionCommand("clear");
		      clearButton.addActionListener(clearButtonListener);
		      
		      
		      // Set up the options pane
		      JPanel optionsPane = initOptionsPane();
		      
		      JPanel rightpane =  new JPanel(new BorderLayout());
		      //rightpane.add(clearButton,BorderLayout.CENTER);
		      rightpane.add(new JLabel("Send To:   "),BorderLayout.EAST);
		      
		      JPanel downpane =  new JPanel(new BorderLayout());
		      downpane.add(statusBar,BorderLayout.WEST);
		      downpane.add(rightpane,BorderLayout.CENTER);
		      //downpane.add(clearButton,BorderLayout.EAST);
		      
		      // Set up the chat pane
		      JPanel chatPane = new JPanel(new BorderLayout());
		      chatText = new JTextArea(30, 30);
		      chatText.setLineWrap(true);
		      chatText.setEditable(false);
		      chatText.setForeground(Color.blue);
		      chatText.setBackground(Color.LIGHT_GRAY);
		      JScrollPane chatTextPane = new JScrollPane(chatText,
		         JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		         JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		      	
		      	trgtscroll = new JScrollPane();
		      	targetlist = new JList();
		      	targetlist.setModel((listmodel=new DefaultListModel()));
		        trgtscroll.setViewportView(targetlist);

			      listmodel.addElement("Everyone       ");
			      targetlist.setSelectedIndex(0);

		      
		      chatLine = new JTextArea(2,52);
		      chatLine.setEnabled(false);
		      chatPane.add(chatTextPane, BorderLayout.CENTER);
		      chatPane.add(trgtscroll,BorderLayout.EAST);
		      chatPane.setPreferredSize(new Dimension(200, 300));
		      
		      JPanel typeclearpane = new JPanel(new BorderLayout());
		      typeclearpane.add(new JLabel("Type your Message: "),BorderLayout.WEST);
		      typeclearpane.add(clearButton,BorderLayout.EAST);
		      
		      JPanel typesendpane = new JPanel(new BorderLayout());
		      
		      typesendpane.add(chatLine,BorderLayout.WEST);
		      typesendpane.add(sendmessage,BorderLayout.EAST);
		      //chatPane.add(chatLine, BorderLayout.SOUTH);
		      
		      JPanel extraPane = new JPanel(new BorderLayout());
		      extraPane.add(downpane, BorderLayout.SOUTH);
		      extraPane.add(optionsPane, BorderLayout.WEST);
		      
		      JPanel pane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		      pane.add(new JSeparator());
		      
		      JPanel extraPane2 = new JPanel(new BorderLayout());
		      extraPane2.add(chatPane, BorderLayout.CENTER);
		       extraPane2.add(pane,BorderLayout.SOUTH);
		       
		       JPanel extraPane5 = new JPanel(new BorderLayout());
		       extraPane5.add(typeclearpane, BorderLayout.CENTER);
			   extraPane5.add(typesendpane, BorderLayout.SOUTH);
			      
		       
		      JPanel extraPane3 = new JPanel(new BorderLayout());
		      extraPane3.add(extraPane2, BorderLayout.CENTER);
		      extraPane3.add(extraPane5, BorderLayout.SOUTH);
		      
		      JPanel extraPane4 = new JPanel(new BorderLayout());
		      extraPane4.add(extraPane3, BorderLayout.CENTER);
		      extraPane4.add(pane,BorderLayout.SOUTH);
		      
		      
		      JPanel mainPane = new JPanel(new BorderLayout());
		      mainPane.add(extraPane4, BorderLayout.SOUTH);
		      mainPane.add(extraPane, BorderLayout.CENTER);
		      		      

		      // Set up the main frame
		      mainFrame = new JFrame("RMI Chat");
		      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      mainFrame.setContentPane(mainPane);
		      mainFrame.setPreferredSize(new Dimension(700, 525));
//		      mainFrame.setSize(mainFrame.getMaximumSize());
		      mainFrame.setLocation(200, 200);
		      mainFrame.pack();
		      mainFrame.setVisible(true);
		      mainFrame.setAlwaysOnTop(true);
		 
		}
		
		@SuppressWarnings("deprecation")
		private void connectButtonListener(ActionEvent e) {
        	// Request a connection initiation
        	
           //if (e.getActionCommand().equals("connect")) {
        	int validuser = -1;
 		   //set the security manager for the client
 		               System.setSecurityManager(new RMISecurityManager());
 		   //get the remote object from the registry
 		               try {
 		                   System.out.println("Security Manager loaded");
 		                   //String url = "rmi://172.16.18.176/CHAT-SERVER";
 		                   String url = ipField.getText().toString();
 		                   Inter remoteObject = (Inter) Naming.lookup(url);
 		                   System.out.println(" Got message ");
 		                   username = userName.getText().toString();
 		                   validuser = remoteObject.loginCheck(userName.getText().toString(), passWord.getText().toString());
 		               } catch (RemoteException exc) {
 		                   System.out.println("Error in lookup: " + exc.toString());
 		               } catch (java.net.MalformedURLException exc) {
 		                   System.out.println("Malformed URL: " + exc.toString());
 		               } catch (java.rmi.NotBoundException exc) {
 		                   System.out.println("NotBound: " + exc.toString());
 		               } 
 		        if(validuser!= -1){
 		        	UserID = validuser;
	                  connectionStatus = CONNECTED;
	                 decidePoll();
	                  connectButton.setEnabled(false);
              disconnectButton.setEnabled(true);
              signup.setEnabled(false);
              sendmessage.setEnabled(true);
              clearButton.setEnabled(true);
              ipField.setEnabled(false);
              userName.setEnabled(false);
              passWord.setEnabled(false);
             // hostOption.setEnabled(false);
             // guestOption.setEnabled(false);
              chatLine.setEnabled(true);
              statusBar.setText("Online");
              mainFrame.repaint(); //update the frame
           }
		}

		  
		 //polling mechanism
		  public class Poll extends Thread {
			  
			  public void updateuser(){
				  String cusr = null;
				  ArrayList<String> users = new ArrayList<String>(ulist.values());
				  
				 // for(int j=0; j< users.size();j++){ 
				//	cusr = users.get(j); 
			for(String s: users){ 
				  if(!s.contentEquals(username)){
                      boolean exists = false;
                      for(int i = 1; i < listmodel.getSize() ; i++){
                          if(((String) listmodel.getElementAt(i)).contentEquals(s)){
                              exists = true;
                          }
                      }
                      if(!exists){ 
					listmodel.addElement(s);
					System.out.println("\nAdding User: " + s + " \n ");
				 }
			  }

				  for(int j=1; j< listmodel.getSize();j++){ 
						cusr = (String) listmodel.getElementAt(j);  
					  
	                      boolean exists = false;
	                      //for(int i = 0; i < users.size() ; i++){
				
	                        //  if(users.get(i).contentEquals(cusr) && !(users.get(i).contentEquals(username)) ){
	                       for(String ss: users){
					if(ss.contentEquals(cusr) && !(ss.contentEquals(username)) ){
					    exists = true;
	                          }
	                      }
	                      if(!exists){ 
	                    	  listmodel.removeElement(cusr); 
			
					System.out.println("\nRemoving User: " + cusr + " \n ");
					}
	                      }
					  
				  }
			  }
			  
		       public void run() {
		           while (true) {
		   //set the security manager for the client
		               System.setSecurityManager(new RMISecurityManager());
		   //get the remote object from the registry
		               try {
		                   System.out.println("Security Manager loaded");
		                   //String url = "rmi://172.16.18.176/CHAT-SERVER";
		                   String url = ipField.getText().toString();
		                   Inter remoteObject = (Inter) Naming.lookup(url);
		                   System.out.println(" Got message ");
		                   String all = remoteObject.get(UserID);
		                   chatText.setText(all);
		                   ulist = remoteObject.currentusers();
		                   updateuser();
		                   Poll.sleep(1000);
		               } catch (RemoteException exc) {
		                   System.out.println("Error in lookup: " + exc.toString());
		               } catch (java.net.MalformedURLException exc) {
		                   System.out.println("Malformed URL: " + exc.toString());
		               } catch (java.rmi.NotBoundException exc) {
		                   System.out.println("NotBound: " + exc.toString());
		               } catch (InterruptedException ex) {
		               }
		           }
		       }
		   }	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        	} 
        catch(Exception ex){
            System.out.println("Look & Feel exception");
        }
		*/
		 java.awt.EventQueue.invokeLater(new Runnable() {

	            public void run() {
	                new Client().setVisible(true);
	            }
	        });
	}

}


	


	// Action adapter for easy event-listener coding
	class ActionAdapter implements ActionListener {
	   public void actionPerformed(ActionEvent e) {
		   
	   }
	}

