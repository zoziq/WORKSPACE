package tiryaki.planner;

import agent.MatcherOntology;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

import fipa.ACLMessage;
import fipa.FipaMessage;
import fipa.ObjectiveMessage;

import task.Behaviour;

public class Matcher extends Thread {
	private LinkedBlockingQueue ObjectiveQ;
	LinkedBlockingQueue RunningBehaviourQ;
	private LinkedBlockingQueue BehaviourQ;
	private Semaphore matcher_wait;

	private Semaphore scheduler_notify;

	public Matcher(LinkedBlockingQueue objectiveQ,
			LinkedBlockingQueue behaviourQ,
			LinkedBlockingQueue RunningBehaviourQ, Semaphore matcherSem,
			Semaphore schedulerSem) {
		this.ObjectiveQ = objectiveQ;
		this.BehaviourQ = behaviourQ;
		this.RunningBehaviourQ = RunningBehaviourQ;
		this.matcher_wait = matcherSem;
		this.scheduler_notify = schedulerSem;
	}

	public void run() {
		while (true) {
			matcher_wait.P();
			try {
				runMatcher();
			} catch (Exception e) {
				System.out.println("Matcher calisirken hata olustu!");
			}
		}
	}

	public void runMatcher() {
		if (ObjectiveQ == null)
			System.out.println("incomingQ null");
		if (ObjectiveQ.isEmpty()) {
			System.out.println("incomingQ bos");
		}
		Objective incomingObjective = (Objective) ObjectiveQ.poll();
		Match(incomingObjective);
	}

	private void Match(Objective incomingObjective) {
		ArrayList matchedBehaviours = new ArrayList();
		try {
			System.out.println("contentte bi olay var!!!");
			FipaMessage fm = incomingObjective.getMessage();
			int msgNum = incomingObjective.getMessageNum();
			MatcherOntology mo = new MatcherOntology();
			matchedBehaviours = mo.getPlan(fm);
			int numTasks = matchedBehaviours.size();

			System.out.println("matcher    " + numTasks);
			if (numTasks > 1)
				System.out.println("birden fazla behavior ile eslestirildi");
			else if (numTasks == 0)
				System.out.println("objective hic bir behavior ile eslestirilemedi");
			else {
				String matchedBehaviour = (String) matchedBehaviours.get(0);
				System.out.println(matchedBehaviour);
				Class c = Class
						.forName("tiryaki.behaviour." + matchedBehaviour);
				System.out.println("matcher beh sinifi");
				Behaviour Plan = (Behaviour) c.newInstance();

				BehaviourQCell behQCell = new BehaviourQCell(Plan, msgNum);

				if (this.setProvisions(incomingObjective, Plan)) { // mesajdaki
					// parametreler,
					// provisionlara
					// getirilecektir
					BehaviourQ.add(behQCell);
					scheduler_notify.V();
					System.out.println("objective " + matchedBehaviour
							+ " ile eslestirildi ");
				}

			}
		} catch (Exception e) {
			System.out.println("Failed: " + e);
		}
	}

	private boolean setProvisions(Objective tmpMsg, Behaviour task4Plan) {
		System.out.println("set provision");
		ArrayList ar = tmpMsg.getMessage().getAcl().getContent().getActions()
				.get(0).getArguments();
		System.out.println(ar.size());
		for (int j = 0; j < ar.size(); j++) {

			task4Plan.getProvisionList().get(j).setValue(ar.get(j));
			System.out.println("provisonlar "
					+ task4Plan.getProvisionList().get(j).getValue());
			System.out.println("provision deger"
					+ task4Plan.getProvisionList().get(j).getValue());

		}

		for (int i = 0; i < task4Plan.getProvisionList().size(); i++) {
			{
				if (!task4Plan.getProvisionList().get(i).isSet()) {
					System.out.println(task4Plan.getBehaviourName()
							+ " planin "
							+ task4Plan.getProvisionList().get(i).getprovName()
							+ " provision'u set edilememis.");
					return false;
				}
			}
		}
		return true;
	}
}