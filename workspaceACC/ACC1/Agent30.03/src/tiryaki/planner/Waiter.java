package tiryaki.planner;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Waiter extends Thread{

private LinkedBlockingQueue pendingQ;
private HashMap sonucQ;
private HashMap interruptQ;
private LinkedBlockingQueue behaviourQ;
private Agent local;
private Semaphore scheduler_notify;
private Semaphore waiter_wait;
long currentTime;
 public Waiter(LinkedBlockingQueue PendingQ,HashMap SonucQ,HashMap InterruptQ,LinkedBlockingQueue BehaviourQ,Agent Local,Semaphore schedulerSem, Semaphore waiterSem) {
    this.pendingQ=PendingQ;
    this.sonucQ=SonucQ;
    this.interruptQ=InterruptQ;
    this.local=Local;
    this.behaviourQ=BehaviourQ;
    this.scheduler_notify=schedulerSem;
    this.waiter_wait = waiterSem;
  }

public void run(){
   try{
   while(true){
    waiter_wait.P();
    runWaiter();
   }
   }catch(Exception e){
      System.out.println(e);
   }
  }

private void runWaiter(){
  System.out.println("waiter calisiyor");
  currentTime=System.currentTimeMillis();
  /*
  for (Enumeration e=interruptQ.keys() ; e.hasMoreElements() ;) {
    Key interruptkey=(Key)e.nextElement();
    long putingTime=interruptkey.getPuttingTime();
    int period=interruptkey.getPeriod();
    if (putingTime+period*1000>=currentTime){
      behaviourQ.add(interruptQ.get(interruptkey));
      scheduler_notify.V();
    }
  }
  for (Enumeration e=pendingQ.keys() ; e.hasMoreElements() ;) {
    Key pendingKey=(Key)e.nextElement();
    long putingTime=pendingKey.getPuttingTime();
    if (putingTime+10000>=currentTime){
       pendingQ.remove(pendingKey);
    }
  }
  for (Enumeration e=sonucQ.keys() ; e.hasMoreElements() ;) {
    Key sonucKey=(Key)e.nextElement();
    long putingTime=sonucKey.getPuttingTime();
    if (putingTime+10000>=currentTime){
       sonucQ.remove(sonucKey);
    }
}*/
}


}