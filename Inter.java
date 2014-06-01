

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface Inter extends Remote
{
public void chat(Integer a,String b) throws RemoteException;
public String get(Integer a) throws RemoteException;
public String get() throws RemoteException;
public int loginCheck(String username,String password) throws RemoteException;
public void addUser(String username,String password) throws RemoteException;
public void exitUser(Integer UserID) throws RemoteException;
public HashMap<Integer, String> currentusers() throws RemoteException;
}
