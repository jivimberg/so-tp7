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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PhysicalAddress){
			return (frameLocation == ((PhysicalAddress)obj).getFrameLocation() &&
			(offset == ((PhysicalAddress)obj).getOffset()));
		}
		return super.equals(obj);
	}
}
