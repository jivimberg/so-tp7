package org.austral.so.tps.tp7.model.core;

import java.util.HashMap;
import java.util.Map;

import org.austral.so.tps.tp7.model.addresses.PhysicalAddress;

public class ProcessPageTable {
	
	private Map<Integer, Page> rows;
	private int maxQuantity;

	public ProcessPageTable(OSProcess process, int maxQuantity) {
		rows = new HashMap<Integer, Page>();
		this.maxQuantity = maxQuantity;
	}

	public PhysicalAddress getPhysicalAddress(int pageNumber){
		Page page = rows.get(pageNumber);
		if(!page.isInRAM()){
			return page.getPhysicalAddress();
		}else{
			return null; 
		}
	}
	
	public void addPage(int key){
		rows.put(key, new Page());
	}

	public Page getPage(int pageNumber){
		return rows.get(pageNumber);				
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}
}
