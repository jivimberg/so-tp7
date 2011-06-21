package org.austral.so.tps.tp7.model.addresses;

public class PhysicalAddress {
	
	private int frameLocation;
	private int offset;

	public PhysicalAddress(int frameLocation, int offset) {
		this.frameLocation = frameLocation;
		this.offset = offset;
	}

	public int getFrameLocation() {
		return frameLocation;
	}

	public int getOffset() {
		return offset;
	}

}
