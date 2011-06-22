package org.austral.so.tps.tp7.model.core;

import java.util.ArrayList;
import java.util.List;

import org.austral.so.tps.tp7.listeners.ProcessListener;
import org.austral.so.tps.tp7.model.memories.OSVM;

public class OSProcess {
	
	private OSVM vm;
	private int pagesQty;
	private int n;
	private OS os;

	private List<ProcessListener> listeners;

	public OSProcess(int n, int pagesQty) {
		vm = new OSVM(pagesQty, n);
		this.pagesQty = pagesQty;
		this.n = n;

		listeners = new ArrayList<ProcessListener>();
	}

	public void run() {
		int pagesRequested = (int) (Math.random() * vm.getPages().size());
		for (int i = 0; i < pagesRequested; i++) {
			int pageRequested = (int) (Math.random() * vm.getPages().size());
			int chunkRequested = (int) (Math.random() * vm.getPages().get(i).getChunks().size());
			os.loadPage(this, pageRequested, chunkRequested);
			notifyListenersPageRequested(pageRequested);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
	public OSVM getVm() {
		return vm;
	}

	public List<Page> getPages() {
		return vm.getPages();
	}

	public int getPagesQty() {
		return pagesQty;
	}

	public void addListener(ProcessListener listener){
		listeners.add(listener);
	}

	public void notifyListenersPageRequested(int pageNumber){
		 for(int i=0;i<listeners.size();i++){
			 listeners.get(i).pageRequested(n, pageNumber);
		 }
	}

	public void notifyListenersPageReplaced(int processNumber, int pageNumber){
		 for(int i=0;i<listeners.size();i++){
			 listeners.get(i).pageReplaced(processNumber, pageNumber);
		 }
	}

	public int getNumber(){
		return n;
	}

	public void setSO(OS so) {
		this.os=so;
	}
}
