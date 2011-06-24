package org.austral.so.tps.tp7.algorithms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.austral.so.tps.tp7.model.core.PageFrame;


public class LRUAlgorithm implements PageReplacingAlgorithm {

	private Map<PageFrame, int[]> lruMatrix;
	
	public LRUAlgorithm(int framesQty) {
		lruMatrix = new HashMap<PageFrame, int[]>();
	}
	
	public String getName() {
		return "LRU";
	}

	@Override
	public PageFrame computePageToReplace() {
		int maxAmountOfZeros = 0;
		PageFrame frameToReplace = new PageFrame();
		Set<PageFrame> keys = lruMatrix.keySet();
		
		Iterator<PageFrame> it = keys.iterator();
		while(it.hasNext()){
			PageFrame aux = it.next();
			int[] intAux = lruMatrix.get(aux);
			int amountOfZeros = 0;
			
			for(int i=0; i< intAux.length; i++){
				if(intAux[i] == 0){
					amountOfZeros++;
				}
			}
			
			if(amountOfZeros > maxAmountOfZeros){
				frameToReplace = aux;
			}
		}
		return frameToReplace;
	}

	@Override
	public void pageReferenced(PageFrame pageFrame) {
		// Set all to 1
		int[] intAux = lruMatrix.get(pageFrame);
		for(int i=0; i< intAux.length; i++){
			intAux[i] = 1;
		}
		lruMatrix.put(pageFrame, intAux);
		
		// Set de todos los 0
		Set<PageFrame> keys = lruMatrix.keySet();
		Iterator<PageFrame> it = keys.iterator();
		int position = 0;
		
		while (it.hasNext()) {
			PageFrame aux = it.next();
			if (aux.getPhysicalAddress().equals(pageFrame.getPhysicalAddress()))
				continue;
			position++;
		}
		
		Iterator<PageFrame> it2 = keys.iterator();
		while (it2.hasNext()) {
			PageFrame aux = it.next();
			int[] intAux2 = lruMatrix.get(aux);
			intAux2[position] = 0;
		}	
	}
}
