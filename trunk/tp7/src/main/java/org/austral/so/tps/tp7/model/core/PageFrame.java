package org.austral.so.tps.tp7.model.core;

import org.austral.so.tps.tp7.model.addresses.PhysicalAddress;

public class PageFrame {

	private PhysicalAddress physicalAddress;
	private boolean referenced;
	private boolean inRAM;

	public boolean isInRAM() {
		return inRAM;
	}

	public void setInRAM(boolean isInRAM) {
		this.inRAM = isInRAM;
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
