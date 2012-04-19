package rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import fipa.FipaMessage;

import agent.AID;


public interface ACCFacade extends Remote
{
	public void send(FipaMessage fm) throws RemoteException;
	public FipaMessage received(AID aid) throws RemoteException;
}
