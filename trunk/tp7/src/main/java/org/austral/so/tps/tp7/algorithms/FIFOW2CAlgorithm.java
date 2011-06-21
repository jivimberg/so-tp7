package org.austral.so.tps.tp7.algorithms;

import org.austral.so.tps.tp7.model.core.ProcessPageTable;
import org.austral.so.tps.tp7.model.memories.OSRAM;

public class FIFOW2CAlgorithm implements PageOrderingAlgorithm {

	public int computePageToReplace(OSRAM ram, int pageNumber, ProcessPageTable processPageTable) {
		return ram.getLastWrittenLocation();
	}

	public String getName() {
		return "FIFO";
	}
}
