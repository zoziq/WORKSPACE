package tiryaki.planner;

import agent.MatcherOntology;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import fipa.FipaMessage;

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
				System.out.println("Matcher => run() metodu...");
				runMatcher();
			} catch (Exception e) {
				System.out.println("Matcher calisirken hata olustu!");
			}
		}
	}

	public void runMatcher() {
		System.out.println("Matcher => runMatcher() metodu...");
		if (ObjectiveQ == null)
			System.out.println("incomingQ null...");
		if (ObjectiveQ.isEmpty()) {
			System.out.println("incomingQ bos...");
		}
		Objective incomingObjective = (Objective) ObjectiveQ.poll();
		Match(incomingObjective);
	}

	private void Match(Objective incomingObjective) {
		System.out.println("Matcher => Match() metodu...");
		ArrayList matchedBehaviours = new ArrayList();
		try {
			System.out.println("Content'te bir olay var!!!");
			FipaMessage fm = incomingObjective.getMessage();
			int msgNum = incomingObjective.getMessageNum();
			MatcherOntology mo = new MatcherOntology();
			matchedBehaviours = mo.getPlan(fm);
			int numTasks = matchedBehaviours.size();

			System.out.println("Matcher'ýn eþleþtirdiði plan sayýsý: "
					+ numTasks);
			if (numTasks > 1)
				System.out.println("Birden fazla Behaviour ile eslestirildi.");
			else if (numTasks == 0)
				System.out
						.println("Objective, hicbir behaviour ile eslestirilemedi.");
			else {
				String matchedBehaviour = (String) matchedBehaviours.get(0);
				System.out.println(matchedBehaviour);
				Class c = Class.forName("test." + matchedBehaviour);
				System.out.println("Matcher Behaviour sinifi...");
				Behaviour Plan = (Behaviour) c.newInstance();

				BehaviourQCell behQCell = new BehaviourQCell(Plan, msgNum);

				if (this.setProvisions(incomingObjective, Plan)) { // mesajdaki
					// parametreler,
					// provisionlara
					// getirilecektir
					BehaviourQ.add(behQCell);
					scheduler_notify.V();
					System.out.println("Objective " + matchedBehaviour
							+ " ile eslestirildi.");
				}

			}
		} catch (Exception e) {
			System.out.println("Failed: " + e);
		}
	}

	private boolean setProvisions(Objective tmpMsg, Behaviour task4Plan) {
		System.out.println("Setting Provisions...");
		ArrayList ar = tmpMsg.getMessage().getAcl().getContent().getActions()
				.get(0).getArguments();
		System.out.println(ar.size());

		if (!(task4Plan.getProvisionList().isEmpty())) {

			// Planýn provisioný yoksa set etmete çalýþmak hata verecekti.
			// Onun için böyle bir kontrol yapýyoruz.

			for (int j = 0; j < ar.size(); j++) {

				task4Plan.getProvisionList().get(j).setValue(ar.get(j));
				System.out.println("Provison'lar "
						+ task4Plan.getProvisionList().get(j).getValue());
				System.out.println("Provision deger: "
						+ task4Plan.getProvisionList().get(j).getValue());

			}

			for (int i = 0; i < task4Plan.getProvisionList().size(); i++) {
				{
					if (!task4Plan.getProvisionList().get(i).isSet()) {
						System.out.println(task4Plan.getBehaviourName()
								+ " planin "
								+ task4Plan.getProvisionList().get(i)
										.getprovName()
								+ " Provision'u set edilememis.");
						return false;
					}
				}
			}
		}
		return true;
	}
}