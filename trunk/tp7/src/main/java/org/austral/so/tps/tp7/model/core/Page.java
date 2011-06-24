package org.austral.so.tps.tp7.model.core;

import org.austral.so.tps.tp7.model.addresses.PhysicalAddress;


public class Page {
	
	private PhysicalAddress physicalAddress;

	public PhysicalAddress getPhysicalAddress() {
		return physicalAddress;
	}
	
	public void setPhysicalAddress(PhysicalAddress physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
}