package org.austral.so.tps.tp7.algorithms;

import org.austral.so.tps.tp7.listeners.OSListener;
import org.austral.so.tps.tp7.model.core.Page;
import org.austral.so.tps.tp7.model.core.ProcessPageTable;
import org.austral.so.tps.tp7.model.memories.OSRAM;


public interface PageReplacingAlgorithm extends OSListener{

	Page computePageToReplace(OSRAM ram, ProcessPageTable processPageTable);
	
	String getName();
}
