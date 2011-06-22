package org.austral.so.tps.tp7.model.memories;

import java.util.ArrayList;
import java.util.List;

import org.austral.so.tps.tp7.model.addresses.PhysicalAddress;
import org.austral.so.tps.tp7.model.core.PageFrame;

public class HardDisk {
	
	private List<PageFrame> frames;

	public HardDisk(int wordsQty){
		frames = new ArrayList<PageFrame>(wordsQty);
	}

	public PageFrame getFrame(PhysicalAddress physicalAddress) {
		for (PageFrame frame : frames){
			if(frame.getPhysicalAddress().equals(physicalAddress))
				return frame;
		}
		return null;
	}
}
