package org.austral.so.tps.tp7.listeners;

public interface ProcessListener {
	
	public void pageRequested(int processNumber, int pageNumber);
	
	public void pageReplaced(int processNumber, int pageNumber);

}
