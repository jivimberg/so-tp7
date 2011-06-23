package org.austral.so.tps.tp7.model.core;

import org.austral.so.tps.tp7.model.addresses.PhysicalAddress;


public class Page {
	
	private PhysicalAddress physicalAddress;
	private boolean referenced;
	private boolean isInRAM;
	

	public boolean isInRAM() {
		return isInRAM;
	}

	public void setInRAM(boolean isInRAM) {
		this.isInRAM = isInRAM;
	}

	public PhysicalAddress getPhysicalAddress() {
		return physicalAddress;
	}
	
	public void setPhysicalAddress(PhysicalAddress physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	
	public boolean isReferenced() {
		return referenced;
	}
	
	public void setReferenced(boolean referenced) {
		this.referenced = referenced;
	}
}