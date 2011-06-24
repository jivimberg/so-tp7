package org.austral.so.tps.tp7.algorithms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.austral.so.tps.tp7.model.core.PageFrame;

public class FIFOW2CAlgorithm implements PageReplacingAlgorithm{

	Queue<PageFrame> queue;
	
	public FIFOW2CAlgorithm(){
		queue = new LinkedList<PageFrame>(); 
	}
	
	public String getName() {
		return "FIFOW2C";
	}

	@Override
	public PageFrame computePageToReplace() {
		while(true){
			PageFrame auxFrame = queue.poll();
			if(auxFrame.isReferenced()){
				auxFrame.setReferenced(false);
				queue.add(auxFrame);
			}else{
				return auxFrame;
			}
		}
	}

	@Override
	public void pageReferenced(PageFrame pageFrame) {
		Iterator<PageFrame> it = queue.iterator();
		while(it.hasNext()){
			PageFrame aux = it.next();
			if(aux.getPhysicalAddress() == pageFrame.getPhysicalAddress()){
				aux.setReferenced(true);
			}
		}	
	}
}