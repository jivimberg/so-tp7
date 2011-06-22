package org.austral.so.tps.tp7.algorithms;

import org.austral.so.tps.tp7.model.core.ProcessPageTable;
import org.austral.so.tps.tp7.model.memories.OSRAM;


public interface PageReplacingAlgorithm {

	int computePageToReplace(OSRAM ram, int pageNumber, ProcessPageTable processPageTable);
	String getName();

}
