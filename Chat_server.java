
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Chat_server extends UnicastRemoteObject implements Inter {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Database db = new Database("Data.xml");
	
	@SuppressWarnings("rawtypes")
	ArrayList<ArrayList<String>> messages = null;
	String everyone;
	static HashMap<Integer, String> users = new HashMap<Integer, String>();
	static int clicount;
	
    Chat_server() throws RemoteException {

        super();
    }
    
    public int loginCheck(String username,String password) throws RemoteException{
    	Boolean validUser = db.checkLogin(username, password);
    	if(validUser){
		users.put(++clicount, username);
		messages.add(clicount, new ArrayList<String>());
		messages.get(clicount).add(everyone);
		return clicount;
    	}
    	return -1;
    	
    }

    @SuppressWarnings("unchecked")
	public void chat(Integer From,Integer To, String msg) throws RemoteException {
    	String sender  = users.get(From);
    	String reciever = users.get(To);
    	String addextra = null;
    	if(To.intValue()==0){
    		everyone += sender+" to "+ "All" +" : "+msg + "\n";
    	}
        for (int u = 1; u <= clicount; u++){
            boolean flag = false;
        	if(u==From)
        		sender = "Me";
        	else
        		sender = users.get(From);
        	if(u==To)
        		reciever = "Me";
        	else if(To==0)
        		reciever = "All";
        	else
        		reciever = users.get(To); 
        	
        if (!(messages.get(u).isEmpty())) {
        	addextra = sender+" to "+ reciever+" : "+msg +"\n";
        	messages.get(u).add(addextra);
        	flag = true;
        	}
            
        if (!flag) {
            String al = "";
            al = sender+" to "+ reciever+" : "+msg + "\n";
            messages.get(u).add(al);
        }
       }
    }
/*
    public String get(Integer a,Integer UserID) throws RemoteException {
        Integer numbers[] = new Integer[messages.size()];
        messages.keySet().toArray(numbers);
        boolean flag = false;
        for (int i = 0; i < numbers.length; i++) {
            if (a.intValue() == numbers[i].intValue()) {
                ArrayList temp;
                temp = messages.get(a);
                Iterator b = temp.iterator();
               // String all_messages = "<html>", all = "";
                String all_messages = "";
                String all = "";
                flag = true;
                while (b.hasNext()) {
                    all_messages = (String) b.next();
                    //all += (all_messages + "<br>");
                    all += (all_messages + "\n");
                }
                return "<html>" + all;
            }
	 
        }
        if (!flag) {
            return "There are no messages from Client " + a;
        }
        return "test";
    }
  */
    //method used for polling

    public String get(Integer UserID) throws RemoteException {
        String all = "";
        Iterator iter = messages.get(UserID).iterator();
        while(iter.hasNext())
        	all += (String) iter.next();
        return all;
    }

    public static void main(String args[]) {
        try {
            System.setSecurityManager(new RMISecurityManager());
            //set the security manager
            //create a local instance of the object
            Chat_server server = new Chat_server();
            //put the local instance in the registry
            Naming.rebind("CHAT-SERVER", server);
            server.messages = new ArrayList<ArrayList<String>>();
            server.messages.add(0, new ArrayList<String>());
            server.everyone = new String(" ");
            System.out.println("Server waiting.....");
        } catch (java.net.MalformedURLException me) {
            System.out.println("Malformed URL: " + me.toString());
        } catch (RemoteException re) {
            System.out.println("Remote exception: " + re.toString());
        }
    }

	@Override
	public void addUser(String username, String password)
			throws RemoteException {
		// TODO Auto-generated method stub
		db.addUser(username, password);
    	}

	@Override
	public void exitUser(Integer UserID) throws RemoteException {
		// TODO Auto-generated method stub
			users.remove(UserID);
	}

	@Override
	public HashMap<Integer, String> currentusers() throws RemoteException {
		// TODO Auto-generated method stub
		HashMap<Integer, String> ulist = new HashMap<Integer,String>();
		ulist = (HashMap<Integer, String>) users.clone();
		return ulist;
	}

	public void clear(Integer UserID) throws RemoteException {
		// TODO Auto-generated method stub
		messages.get(UserID).clear();
	}
}

