package org.austral.so.tps.tp7.model.memories;

import java.util.ArrayList;
import java.util.List;

import org.austral.so.tps.tp7.listeners.RAMListener;
import org.austral.so.tps.tp7.model.addresses.Page;

public class OSRAM {
	
	private Page[] frames;
	private int i;
	private int lastLocation;

	private List<RAMListener> listeners;


	public OSRAM(int pagesQty) {
		frames = new Page[pagesQty];
		i = 0;
		lastLocation = 0;
		listeners = new ArrayList<RAMListener>();
	}

	public Page getFrame(int frameLocation) {
		return frames[frameLocation];
	}

	public boolean isFull() {
		return i == frames.length;
	}

	public void addFrame(Page newPage, int location) {
		frames[location] = newPage;
		lastLocation = location;
	}

	public int addFrame(Page newPage) {
		frames[i] = newPage;
		lastLocation = i;
		return i++;
	}

	public void addListener(RAMListener listener) {
		listeners.add(listener);
	}

	public void notifyPageLoaded(int processNumber, int pageNumber, int frameLocation) {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).pageLoaded(processNumber, pageNumber, frameLocation);
		}
	}

	public int getLastWrittenLocation() {
		return lastLocation;
	}

}
