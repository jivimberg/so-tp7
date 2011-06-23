package org.austral.so.tps.tp7.listeners;

import org.austral.so.tps.tp7.model.core.PageFrame;

public interface RAMListener {

	void pageFrameLoaded(int processNumber,int pageNumber ,int frameLocation);
	
	void pageFrameRequested(PageFrame pageFrame);
}
