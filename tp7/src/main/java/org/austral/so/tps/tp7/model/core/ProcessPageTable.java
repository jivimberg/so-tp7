package org.austral.so.tps.tp7.model.core;

import java.util.HashMap;
import java.util.Map;

import org.austral.so.tps.tp7.model.addresses.LogicalAddress;
import org.austral.so.tps.tp7.model.addresses.PhysicalAddress;

public class ProcessPageTable {
	
	private Map<Integer, PageTableRow> rows;

	public ProcessPageTable(OSProcess process) {
		rows = new HashMap<Integer, PageTableRow>(process.getPagesQty());
		for (int i=0; i<process.getPagesQty() ; i++){
			rows.put(i,new PageTableRow(-1,false, false));
		}
	}

	public PhysicalAddress getPhysicalAddress(LogicalAddress address){
		PageTableRow page = rows.get(address.getPageNumber());
		if(page.isValid()){
			return new PhysicalAddress(page.getFrameLocation(),address.getOffset());
		}else{
			return null;
		}
	}

	public PageTableRow getPageTableRow(int pageNumber){
		return rows.get(pageNumber);				
	}

	public void linkPage(int pageNumber, int ramLocation){
	    rows.get(pageNumber).setFrameLocation(ramLocation);
		rows.get(pageNumber).setValid(true);
		rows.get(pageNumber).setReference(true);		
	}

	public Map<Integer, PageTableRow> getPageTableRows() {
		return rows;
	}

	public class PageTableRow{

		private int frameLocation;
		private boolean valid;
		private boolean reference;

		PageTableRow(int frameLocation, boolean valid, boolean reference) {
			this.frameLocation = frameLocation;
			this.valid = valid;
			this.reference = reference;
		}

		public int getFrameLocation() {
			return frameLocation;
		}

		public void setFrameLocation(int frameLocation) {
			this.frameLocation = frameLocation;
		}

		public boolean isValid() {
			return valid;
		}

		public void setValid(boolean valid) {
			this.valid = valid;
		}

		public boolean isReference() {
			return reference;
		}

		public void setReference(boolean reference) {
			this.reference = reference;
		}
	}

}
