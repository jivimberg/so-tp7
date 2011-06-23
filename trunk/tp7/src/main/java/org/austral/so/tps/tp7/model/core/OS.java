package org.austral.so.tps.tp7.model.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.austral.so.tps.tp7.algorithms.PageReplacingAlgorithm;
import org.austral.so.tps.tp7.listeners.OSListener;
import org.austral.so.tps.tp7.listeners.ProcessListener;
import org.austral.so.tps.tp7.listeners.RAMListener;
import org.austral.so.tps.tp7.model.addresses.PhysicalAddress;
import org.austral.so.tps.tp7.model.memories.HardDisk;
import org.austral.so.tps.tp7.model.memories.OSRAM;

public class OS {
	
	private Map<OSProcess, ProcessPageTable> processesAndTables;

	public static final int PAGE_AND_FRAME_SIZE = 5;
	public static final int RAM_SIZE = 20;
	public static final int HD_SIZE = RAM_SIZE * 5;
	public static final int SLEEP_TIME = 100;

	private OSRAM ram;
	private HardDisk hd;

	private PageReplacingAlgorithm algorithm;
	
	private List<OSListener> listeners;

	public OS(PageReplacingAlgorithm algorithm) {
		this.processesAndTables = new HashMap<OSProcess, ProcessPageTable>();
		ram = new OSRAM(RAM_SIZE);
		hd = new HardDisk(HD_SIZE);
		this.algorithm = algorithm;
	}

	public void addProcess(OSProcess process) {
		process.setSO(this);
		processesAndTables.put(process, new ProcessPageTable(process, PAGE_AND_FRAME_SIZE));
		//N pages are added to the process table
		for(int i= 0; i< PAGE_AND_FRAME_SIZE; i++){
			processesAndTables.get(process).addPage(i);
		}
	}

	public void loadPage(OSProcess process, int pageNumber) {
		ProcessPageTable pageTable = processesAndTables.get(process); //getPageTable
		PhysicalAddress physicalAddress = pageTable.getPhysicalAddress(pageNumber);
		
		Page page = pageTable.getPage(pageNumber);
		page.setReferenced(true);
		notifyPageReferenced(page);
		
		if(physicalAddress != null){
			//Page is not already on RAM
			PageFrame newFrame = hd.getFrame(physicalAddress);
			Page pageToReplace;
			if (ram.isFull()) {
				pageToReplace = algorithm.computePageToReplace(ram, pageTable);
				int location = ram.getFrameLocation(pageToReplace); //Something is missing
				ram.replaceFrame(newFrame, location);
				pageToReplace.setInRAM(false);
			}else{
				ram.addFrame(newFrame);			
			}		
			page.setInRAM(true);
		}
	}
	
	public void notifyPageReferenced(Page page){
		for (OSListener listener : listeners) {
			listener.pageReferenced(page);
		}
	}

	public void addOSProcessListener(OSProcess process, ProcessListener listener){
		process.addListener(listener);		
	}

	public void addOSRAMListener(RAMListener listener){
		ram.addListener(listener);		
	}
	
	public void addOSListener(OSListener listener){
		listeners.add(listener);		
	}

	public void run() {
		Iterator<OSProcess> it;
		while (true) {
			it = processesAndTables.keySet().iterator();
			while (it.hasNext()){
				try {
					it.next().run();
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {}
			}
		}
	}

	public Map<OSProcess, ProcessPageTable> getProcesses() {
		return processesAndTables;
	}

	public void setProcesses(Map<OSProcess, ProcessPageTable> processes) {
		this.processesAndTables = processes;
	}
	
	
}