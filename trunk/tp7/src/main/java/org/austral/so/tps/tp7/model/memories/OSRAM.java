package org.austral.so.tps.tp7.model.memories;

import java.util.ArrayList;
import java.util.List;

import org.austral.so.tps.tp7.listeners.RAMListener;
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

	public PageFrame getFrame(int frameLocation) {
		return pageFrames.get(frameLocation);
	}

	public void replaceFrame(PageFrame newFrame, int location) {
		pageFrames.add(location, newFrame);
		//TODO notify
	}
	
	public void addFrame(PageFrame newFrame) {
		pageFrames.add(newFrame);
		//TODO notify
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

	private void notifyPageFrameLoaded(int processNumber, int pageNumber, int frameLocation) {
		for (RAMListener listener : listeners) {
			listener.pageFrameLoaded(processNumber, pageNumber, frameLocation);
		}
	}
	
	private void notifyPageFrameRequested(PageFrame pageFrame) {
		for (RAMListener listener : listeners) {
			listener.pageFrameRequested(pageFrame);
		}
	}
	
	public boolean isFull() {
		return framesQuantity == pageFrames.size();

	}
}