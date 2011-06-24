package org.austral.so.tps.tp7.model.core;


public class OSProcess extends Thread{
	
	private int processNumber;
	private OS os;

	public OSProcess(int n) {
		this.processNumber = n;
	}

	public void run() {
		ProcessPageTable pageTable = os.getProcesses(this);
		int pagesRequested = (int) (Math.random() * pageTable.getMaxQuantity());
		for (int i = 0; i < pagesRequested; i++) {
			int pageRequested = (int) (Math.random() * pageTable.getMaxQuantity());
			os.loadPage(this, pageRequested);
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getNumber(){
		return processNumber;
	}

	public void setSO(OS so) {
		this.os=so;
	}
}
