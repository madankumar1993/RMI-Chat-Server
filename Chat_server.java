
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
	
	static HashMap<Integer, ArrayList> messages = new HashMap<Integer, ArrayList>();
	static HashMap<Integer, String> users = new HashMap<Integer, String>();
	static int clicount;
	
    Chat_server() throws RemoteException {

        super();

    }
    
    public int loginCheck(String username,String password) throws RemoteException{
    	Boolean validUser = db.checkLogin(username, password);
    	if(validUser){
		users.put(++clicount, username);
    		return clicount;
    	}
    	return -1;
    	
    }

    public void chat(Integer a, String msg) throws RemoteException {

        boolean flag = false;

        if (!messages.isEmpty()) {

            Integer numbers[] = new Integer[messages.size()];

            messages.keySet().toArray(numbers);

            for (int i = 0; i < numbers.length; i++) {
                if (a.intValue() == numbers[i].intValue()) {
                    ArrayList temp;
                    temp = messages.get(a);
                    temp.add(msg);
                    messages.put(a, temp);
                    flag = true;
                }
            }
        }
        if (!flag) {
            ArrayList<String> al = new ArrayList<String>();
            al.add(msg);
            messages.put(a, al);
        }
    }

    public String get(Integer a) throws RemoteException {
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
    //method used for polling

    public String get() throws RemoteException {
        Set s = messages.keySet();
        Iterator use = s.iterator();
        String cusr = null;
        String all = "";
        String final_messages = null;
        while (use.hasNext()) {
            ArrayList temp;
            int temp_no = (Integer) use.next();
            temp = messages.get((Integer) temp_no);
            String all_messages = "";
            if (temp != null) {
                Iterator b = temp.iterator();
               // String final_messages = "<html><I><tt>Message to Client " + temp_no + ": </tt></I>";
                if(temp_no == 0)
                
                	final_messages = "Message to All " +  ": ";                
                
                else{
                 cusr = users.get((Integer)temp_no);
		if(cusr==null)
			continue;
                 final_messages = "Message to " + cusr + ": ";                
                }
                while (b.hasNext()) {
                    all_messages = final_messages + (String) b.next();
                    //all += (all_messages + "<br>");
                    all += (all_messages + "\n");
                    
                }
            }
        }
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
}
