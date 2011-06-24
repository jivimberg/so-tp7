package org.austral.so.tps.tp7.model.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.austral.so.tps.tp7.algorithms.PageReplacingAlgorithm;
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

	public OS(PageReplacingAlgorithm algorithm) {
		this.processesAndTables = new HashMap<OSProcess, ProcessPageTable>();
		ram = new OSRAM(RAM_SIZE);
		hd = new HardDisk(HD_SIZE);
		this.algorithm = algorithm;
	}

	public void addProcess(OSProcess process) {
		process.setSO(this);
		processesAndTables.put(process, new ProcessPageTable(process, PAGE_AND_FRAME_SIZE));

		for(int i= 0; i< PAGE_AND_FRAME_SIZE; i++){
			processesAndTables.get(process).addPage(i);
		}
	}

	public void loadPage(OSProcess process, int pageNumber) {
		ProcessPageTable pageTable = processesAndTables.get(process);
		PhysicalAddress physicalAddress = pageTable.getPhysicalAddress(pageNumber);

		if(ram.isPageFrameInRAM(physicalAddress)){
			algorithm.pageReferenced(ram.getFrame(physicalAddress));
		}else{
			PageFrame newFrame = hd.getFrame(physicalAddress);
			if (ram.isFull()) {
				PageFrame pageToReplace = algorithm.computePageToReplace();
				
				ram.replaceFrame(newFrame, pageToReplace);
				pageToReplace.setInRAM(false);
			}else{
				ram.addFrame(newFrame);			
			}		
		}
	}

	public void addOSRAMListener(RAMListener listener){
		ram.addListener(listener);		
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
	
	public ProcessPageTable getProcesses(OSProcess process) {
		return processesAndTables.get(process);
	}

	public void setProcesses(Map<OSProcess, ProcessPageTable> processes) {
		this.processesAndTables = processes;
	}
}