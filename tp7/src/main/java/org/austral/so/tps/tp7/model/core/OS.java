package org.austral.so.tps.tp7.model.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.austral.so.tps.tp7.algorithms.PageOrderingAlgorithm;
import org.austral.so.tps.tp7.listeners.ProcessListener;
import org.austral.so.tps.tp7.listeners.RAMListener;
import org.austral.so.tps.tp7.model.addresses.LogicalAddress;
import org.austral.so.tps.tp7.model.addresses.Page;
import org.austral.so.tps.tp7.model.addresses.PhysicalAddress;
import org.austral.so.tps.tp7.model.memories.HardDisk;
import org.austral.so.tps.tp7.model.memories.OSRAM;
import org.austral.so.tps.tp7.model.memories.OSVM;

public class OS {
	private Map<OSProcess, ProcessPageTable> processes;

	public static final int PAGE_AND_FRAME_SIZE = 5;
	public static final int RAM_SIZE = 20;
	public static final int HD_SIZE = RAM_SIZE * 5;
	public static final int SLEEP_TIME = 100;

	private OSRAM ram;
	private OSVM vm;
	private HardDisk hd;

	private PageOrderingAlgorithm algorithm;

	public OS(PageOrderingAlgorithm algorithm) {
		this.processes = new HashMap<OSProcess, ProcessPageTable>();
		ram = new OSRAM(RAM_SIZE);
		hd = new HardDisk(HD_SIZE);
		this.algorithm = algorithm;
	}

	public void addProcess(OSProcess process) {
		processes.put(process, new ProcessPageTable(process));
		process.setSO(this);
		hd.writeProcess(process);
	}

	public Page loadPage(OSProcess process, int pageNumber, int chunkNumber) {
		LogicalAddress vAddress = new LogicalAddress(pageNumber, chunkNumber);
		ProcessPageTable pageTable = processes.get(process);
		PhysicalAddress pAddress = pageTable.getPhysicalAddress(vAddress);
		if (pAddress != null) {
			return ram.getFrame(pAddress.getFrameLocation());
		} else {
			int hdLocation = hd.getFrameLocation(process.getVm().getPage(pageNumber));
			Page newPage = hd.getFrame(hdLocation);
			int ramLocation;
			if (ram.isFull()) {
				ramLocation = algorithm.computePageToReplace(ram, pageNumber, pageTable);
				process.notifyListenersPageReplaced(ram.getFrame(ramLocation).getFatherN(), ram.getFrame(ramLocation).getNumber());
				ram.addFrame(newPage, ramLocation);
			}else{
				ramLocation = ram.addFrame(newPage);			
			}
			pageTable.linkPage(pageNumber, ramLocation);
			ram.notifyPageLoaded(process.getNumber(), pageNumber, ramLocation);
			return ram.getFrame(ramLocation);
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
}