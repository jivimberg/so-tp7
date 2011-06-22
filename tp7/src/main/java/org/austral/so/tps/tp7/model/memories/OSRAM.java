package org.austral.so.tps.tp7.model.memories;

import java.util.ArrayList;
import java.util.List;

import org.austral.so.tps.tp7.listeners.RAMListener;
import org.austral.so.tps.tp7.model.core.PageFrame;

public class OSRAM {
	
	private List<PageFrame> pageFrames;
	private int lastLocation;

	private List<RAMListener> listeners;


	public OSRAM(int pagesQty) {
		listeners = new ArrayList<RAMListener>();
		pageFrames = new ArrayList<PageFrame>(pagesQty);
		this.lastLocation = 0;
	}

	public PageFrame getFrame(int frameLocation) {
		return pageFrames.get(frameLocation);
	}

	public void addFrame(PageFrame newFrame, int location) {
		pageFrames.add(location, newFrame);
		lastLocation = location;
	}

	public void addListener(RAMListener listener) {
		listeners.add(listener);
	}

	public void notifyPageLoaded(int processNumber, int pageNumber, int frameLocation) {
		for (RAMListener listener : listeners) {
			listener.pageLoaded(processNumber, pageNumber, frameLocation);
		}
	}

	public int getLastLocation() {
		return lastLocation;
	}

}
