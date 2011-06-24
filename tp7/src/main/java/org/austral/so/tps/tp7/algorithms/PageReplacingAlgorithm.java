package org.austral.so.tps.tp7.algorithms;

import org.austral.so.tps.tp7.model.core.PageFrame;
import org.austral.so.tps.tp7.model.core.ProcessPageTable;
import org.austral.so.tps.tp7.model.memories.OSRAM;


public interface PageReplacingAlgorithm{

	PageFrame computePageToReplace();
	
	String getName();
	
	void pageReferenced(PageFrame pageFrame);
}
