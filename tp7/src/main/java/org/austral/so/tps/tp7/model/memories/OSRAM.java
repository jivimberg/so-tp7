package org.austral.so.tps.tp7.model.memories;

import java.util.ArrayList;
import java.util.List;

import org.austral.so.tps.tp7.listeners.RAMListener;
import org.austral.so.tps.tp7.model.addresses.PhysicalAddress;
import org.austral.so.tps.tp7.model.core.PageFrame;

public class OSRAM {
	
	private List<PageFrame> pageFrames;
	private int framesQuantity;
	
	private List<RAMListener> listeners;


	public OSRAM(int pagesQty) {
		this.listeners = new ArrayList<RAMListener>();
		this.pageFrames = new ArrayList<PageFrame>(pagesQty);
		
		this.framesQuantity = pagesQty;
	}

	public PageFrame getFrame(PhysicalAddress address) {
		for (PageFrame pageFrame : pageFrames){
			if(pageFrame.getPhysicalAddress().equals(address))
				return pageFrame;
		}
		return null;
	}

	public void replaceFrame(PageFrame newFrame, PageFrame frameToReplace) {		
		pageFrames.add(pageFrames.indexOf(frameToReplace), newFrame);
		newFrame.setInRAM(true);
	}
	
	public void addFrame(PageFrame newFrame) {
		pageFrames.add(newFrame);
		newFrame.setInRAM(true);
	}
	
	public int getFrameLocation(PageFrame newFrame) {
		for(int i= 0; i < framesQuantity; i++){
			if(pageFrames.get(i).equals(newFrame)){
				return i;
			}
		}
		return -1;
	}

	public void addListener(RAMListener listener) {
		listeners.add(listener);
	}
	
	public boolean isFull() {
		return framesQuantity == pageFrames.size();
	}
	
	public boolean isPageFrameInRAM (PhysicalAddress address){
		for(PageFrame frame: pageFrames){
			if(frame.getPhysicalAddress().equals(address))
				return true;
		}
		return false;
	}
}