package org.austral.so.tps.tp7.model.core;

import java.util.HashMap;
import java.util.Map;

import org.austral.so.tps.tp7.model.addresses.LogicalAddress;
import org.austral.so.tps.tp7.model.addresses.PhysicalAddress;

public class ProcessPageTable {
	
	private Map<Integer, Page> rows;

	public ProcessPageTable(OSProcess process) {
		rows = new HashMap<Integer, Page>();
	}

	public PhysicalAddress getPhysicalAddress(LogicalAddress address){
		Page page = rows.get(address.getPageNumber());
		if(!page.isInRAM()){
			return new PhysicalAddress(page.getPhysicalAddress().getFrameLocation(),address.getOffset());
		}else{
			return null;
		}
	}

	public Page getPageTableRow(int pageNumber){
		return rows.get(pageNumber);				
	}
}
