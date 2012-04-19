package tiryaki.planner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.LinkedBlockingQueue;

import task.Behaviour;

import links.ExternalLink;

import fipa.FipaMessage; //import java.util.Vector;
import agent.AID;
import agent.AbstractAgent;
import agent.AgentIdentifier;
import amsPlan.Register;

public class Agent extends AbstractAgent {

	LinkedBlockingQueue IncomingMessageQ;
	LinkedBlockingQueue ReadyQ;
	LinkedBlockingQueue ObjectiveQ;
	LinkedBlockingQueue BehaviourQ;
	LinkedBlockingQueue OutMessageQ;
	LinkedBlockingQueue PendingQ;
	LinkedBlockingQueue RunningBehaviourQ;
	HashMap SonucQ;
	HashMap InterruptQ;
	HashMap IncomeMessageQ;
	Dispatcher Agent_Dispatcher;
	Executor executor;
	Platform platform;
	Scheduler scheduler;
	Waiter waiter;
	Matcher matcher;
	String actionsDir;
	String classesDir;
	Semaphore DispatcherSem = new Semaphore();
	Semaphore MatcherSem = new Semaphore();
	Semaphore ExecutorSem = new Semaphore();
	Semaphore SchedSem = new Semaphore();
	Semaphore inMesQSem = new Semaphore();
	Semaphore ExecToSched = new Semaphore();
	Semaphore waiterSem = new Semaphore();

	public static String state;

	public Agent(AgentIdentifier aid, String accAddress) {

		super(aid, accAddress);

		state = "running";
		IncomingMessageQ = new LinkedBlockingQueue();
		ReadyQ = new LinkedBlockingQueue();
		ObjectiveQ = new LinkedBlockingQueue();
		BehaviourQ = new LinkedBlockingQueue();
		PendingQ = new LinkedBlockingQueue();
		RunningBehaviourQ = new LinkedBlockingQueue();
		SonucQ = new HashMap();
		OutMessageQ = new LinkedBlockingQueue();
		IncomeMessageQ = new HashMap();
		InterruptQ = new HashMap();

		// platform=new Platform(IncomingMessageQ,inMesQSem,DispatcherSem);
		Agent_Dispatcher = new Dispatcher(IncomingMessageQ, ObjectiveQ, ReadyQ,
				PendingQ, OutMessageQ, IncomeMessageQ, this, DispatcherSem,
				MatcherSem, ExecutorSem);
		matcher = new Matcher(ObjectiveQ, BehaviourQ, RunningBehaviourQ,
				MatcherSem, SchedSem);
		scheduler = new Scheduler(BehaviourQ, RunningBehaviourQ, ReadyQ,
				PendingQ, SonucQ, InterruptQ, this, SchedSem, ExecutorSem,
				ExecToSched);
		executor = new Executor(ReadyQ, OutMessageQ, BehaviourQ,
				RunningBehaviourQ, PendingQ, SonucQ, this, ExecutorSem,
				SchedSem, ExecToSched, DispatcherSem);
		waiter = new Waiter(PendingQ, SonucQ, InterruptQ, BehaviourQ, this,
				SchedSem, waiterSem);
		// platform.start();
		Agent_Dispatcher.start();
		matcher.start();
		scheduler.start();
		executor.start();
		waiter.start();
		// witer.rwait()=agent.platform.getACLMesage();
		//
	}
	
	
	
//	public void planAta(Behaviour beh) {
//		
//		BehaviourQ.add(beh);
//		DispatcherSem.V();
//		System.out.println("planAta çalýþtý...");
//
//	}
	
	public Agent(AgentIdentifier aid, String accAddress, String behaviourName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		super(aid, accAddress);

		state = "running";
		IncomingMessageQ = new LinkedBlockingQueue();
		ReadyQ = new LinkedBlockingQueue();
		ObjectiveQ = new LinkedBlockingQueue();
		BehaviourQ = new LinkedBlockingQueue();
		PendingQ = new LinkedBlockingQueue();
		RunningBehaviourQ = new LinkedBlockingQueue();
		SonucQ = new HashMap();
		OutMessageQ = new LinkedBlockingQueue();
		IncomeMessageQ = new HashMap();
		InterruptQ = new HashMap();

		String matchedBehaviour = behaviourName;
		System.out.println("Plan Adý: " + matchedBehaviour);
		Class c = Class.forName("test." + matchedBehaviour);
		System.out.println("Plan nesnesi oluþturuldu: " + c.getName());
		Behaviour plan = (Behaviour) c.newInstance();

		BehaviourQCell behQCell = new BehaviourQCell(plan, plan.getPlanNum());
		
		// platform=new Platform(IncomingMessageQ,inMesQSem,DispatcherSem);
		Agent_Dispatcher = new Dispatcher(IncomingMessageQ, ObjectiveQ, ReadyQ,
				PendingQ, OutMessageQ, IncomeMessageQ, this, DispatcherSem,
				MatcherSem, ExecutorSem);
//		matcher = new Matcher(ObjectiveQ, BehaviourQ, RunningBehaviourQ,
//				MatcherSem, SchedSem);
		
		BehaviourQ.add(behQCell);
		SchedSem.V();
		
		scheduler = new Scheduler(BehaviourQ, RunningBehaviourQ, ReadyQ,
				PendingQ, SonucQ, InterruptQ, this, SchedSem, ExecutorSem,
				ExecToSched);
		executor = new Executor(ReadyQ, OutMessageQ, BehaviourQ,
				RunningBehaviourQ, PendingQ, SonucQ, this, ExecutorSem,
				SchedSem, ExecToSched, DispatcherSem);
		waiter = new Waiter(PendingQ, SonucQ, InterruptQ, BehaviourQ, this,
				SchedSem, waiterSem);
		// platform.start();
		Agent_Dispatcher.start();
//		matcher.start();
		scheduler.start();
		executor.start();
		waiter.start();
		// witer.rwait()=agent.platform.getACLMesage();
		//
		
	}

	public void message(FipaMessage msg) {
		
		try {
			System.out.println("mesaj geldi");
			IncomingMessageQ.add(msg);
			DispatcherSem.V();
		} catch (Exception e) {
			System.out.println("Error occured while dispather is running");
		}
	}

	@Override
	protected void deregisterFromAMS() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void registerToAMS() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void sendMessage(FipaMessage msg) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void startServer() {
		// TODO Auto-generated method stub

	}
}