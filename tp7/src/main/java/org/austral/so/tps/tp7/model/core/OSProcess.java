package org.austral.so.tps.tp7.model.core;

import java.util.ArrayList;
import java.util.List;

import org.austral.so.tps.tp7.listeners.ProcessListener;
import org.austral.so.tps.tp7.model.memories.OSVM;

public class OSProcess {
	
	private int pagesQty;
	private int processNumber;
	private OS os;

	private List<ProcessListener> listeners;

	public OSProcess(int n, int pagesQty) {
		this.pagesQty = pagesQty;
		this.processNumber = n;

		listeners = new ArrayList<ProcessListener>();
	}

	public void run() {
		ProcessPageTable pageTable = os.getProcesses().get(processNumber);
		int pagesRequested = (int) (Math.random() * pageTable.getMaxQuantity());
		for (int i = 0; i < pagesRequested; i++) {
			int pageRequested = (int) (Math.random() * pageTable.getMaxQuantity());
			os.loadPage(this, pageRequested);
			notifyListenersPageRequested(pageRequested);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	public int getPagesQty() {
		return pagesQty;
	}

	public void addListener(ProcessListener listener){
		listeners.add(listener);
	}

	public void notifyListenersPageRequested(int pageNumber){
		 for(int i=0;i<listeners.size();i++){
			 listeners.get(i).pageRequested(processNumber, pageNumber);
		 }
	}

	public void notifyListenersPageReplaced(int processNumber, int pageNumber){
		 for(int i=0;i<listeners.size();i++){
			 listeners.get(i).pageReplaced(processNumber, pageNumber);
		 }
	}

	public int getNumber(){
		return processNumber;
	}

	public void setSO(OS so) {
		this.os=so;
	}
}
