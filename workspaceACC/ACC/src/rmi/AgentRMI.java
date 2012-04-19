package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import fipa.FipaMessage;

public interface AgentRMI extends Remote{

	public void receivedMessage(FipaMessage fm)throws RemoteException;
	public void receivedMessage2(FipaMessage fm) throws RemoteException;
	public void warning(String message)throws RemoteException;
}
