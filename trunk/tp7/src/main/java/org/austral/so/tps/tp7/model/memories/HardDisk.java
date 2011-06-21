package org.austral.so.tps.tp7.model.memories;

import java.util.ArrayList;
import java.util.List;

import org.austral.so.tps.tp7.model.addresses.Page;
import org.austral.so.tps.tp7.model.core.OSProcess;

public class HardDisk {
	
	private List<Page> pages;

	public HardDisk(int wordsQty){
		pages = new ArrayList<Page>(wordsQty);
	}

	public int getFrameLocation(Page page){
		return pages.indexOf(page);
	}

	public void writeProcess(OSProcess process) {
		pages.addAll(process.getPages());
	}

	public Page getFrame(int hdLocation) {
		return pages.get(hdLocation);
	}

}
