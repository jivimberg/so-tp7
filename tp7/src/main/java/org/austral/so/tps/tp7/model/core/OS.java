package org.austral.so.tps.tp7.model.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.austral.so.tps.tp7.algorithms.PageReplacingAlgorithm;
import org.austral.so.tps.tp7.listeners.ProcessListener;
import org.austral.so.tps.tp7.listeners.RAMListener;
import org.austral.so.tps.tp7.model.addresses.PhysicalAddress;
import org.austral.so.tps.tp7.model.memories.HardDisk;
import org.austral.so.tps.tp7.model.memories.OSRAM;

public class OS {
	
	private Map<OSProcess, ProcessPageTable> processes;

	public static final int PAGE_AND_FRAME_SIZE = 5;
	public static final int RAM_SIZE = 20;
	public static final int HD_SIZE = RAM_SIZE * 5;
	public static final int SLEEP_TIME = 100;

	private OSRAM ram;
	private HardDisk hd;

	private PageReplacingAlgorithm algorithm;

	public OS(PageReplacingAlgorithm algorithm) {
		this.processes = new HashMap<OSProcess, ProcessPageTable>();
		ram = new OSRAM(RAM_SIZE);
		hd = new HardDisk(HD_SIZE);
		this.algorithm = algorithm;
	}

	public void addProcess(OSProcess process) {
		process.setSO(this);
		processes.put(process, new ProcessPageTable(process, PAGE_AND_FRAME_SIZE));
		for(int i= 0; i< PAGE_AND_FRAME_SIZE; i++){
			processes.get(process).addPage(i);
		}
	}

	public void loadPage(OSProcess process, int pageNumber) {
		ProcessPageTable pageTable = processes.get(process);
		PhysicalAddress physicalAddress = pageTable.getPhysicalAddress(pageNumber);
		
		PageFrame newFrame = hd.getFrame(physicalAddress);
		int ramLocation;
		if (ram.isFull()) {
			ramLocation = algorithm.computePageToReplace(ram, pageNumber, pageTable);
			ram.addFrame(newFrame, ramLocation);
		}else{
			ram.addFrame(newFrame);			
		}
	}

	public void addOSProcessListener(OSProcess process, ProcessListener listener){
		process.addListener(listener);		
	}

	public void addOSRAMListener(RAMListener listener){
		ram.addListener(listener);		
	}

	public void run() {
		Iterator<OSProcess> it;
		while (true) {
			it = processes.keySet().iterator();
			while (it.hasNext()){
				try {
					it.next().run();
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {}
			}
		}
	}

	public Map<OSProcess, ProcessPageTable> getProcesses() {
		return processes;
	}

	public void setProcesses(Map<OSProcess, ProcessPageTable> processes) {
		this.processes = processes;
	}
	
	
}