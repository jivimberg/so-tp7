package org.austral.so.tps.tp7.model.addresses;

public class LogicalAddress {

	private int pageNumber;
	private int offset;

	public LogicalAddress(int pageNumber, int offset){
		this.pageNumber = pageNumber;
		this.offset = offset;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getOffset() {
		return offset;
	}
}