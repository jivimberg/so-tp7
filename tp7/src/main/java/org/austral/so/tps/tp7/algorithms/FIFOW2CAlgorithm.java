package org.austral.so.tps.tp7.algorithms;

import java.util.LinkedList;
import java.util.Queue;

import org.austral.so.tps.tp7.model.core.Page;
import org.austral.so.tps.tp7.model.core.PageFrame;
import org.austral.so.tps.tp7.model.core.ProcessPageTable;
import org.austral.so.tps.tp7.model.memories.OSRAM;

public class FIFOW2CAlgorithm implements PageReplacingAlgorithm{

	Queue<PageFrame> queue;
	
	public FIFOW2CAlgorithm(){
		queue = new LinkedList<PageFrame>(); 
	}
	
	public String getName() {
		return "FIFOW2C";
	}

	@Override
	public int computePageToReplace(OSRAM ram, ProcessPageTable processPageTable) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void pageReferenced(Page page) {
		// TODO Auto-generated method stub
		
	}
}