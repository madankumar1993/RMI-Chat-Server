

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface Inter extends Remote
{
public void chat(Integer From,Integer To,String msg) throws RemoteException;
//public String get(Integer a,Integer UserID) throws RemoteException;
public String get(Integer UserID) throws RemoteException;
public int loginCheck(String username,String password) throws RemoteException;
public void addUser(String username,String password) throws RemoteException;
public void exitUser(Integer UserID) throws RemoteException;
public HashMap<Integer, String> currentusers() throws RemoteException;
public void clear(Integer UserID) throws RemoteException;
}

