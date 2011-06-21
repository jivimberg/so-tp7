package org.austral.so.tps.tp7.model.memories;

import java.util.ArrayList;
import java.util.List;

import org.austral.so.tps.tp7.model.addresses.Page;


public class OSVM {
	
	private List<Page> pages;

	public OSVM(int pagesQty, int processNumber){
		pages = new ArrayList<Page>();
		for (int i=0;i < pagesQty; i++){
			pages.add(new Page(i, processNumber));
		}
	}

	public Page getPage(int pagePosition){
		return pages.get(pagePosition);
	}

	public List<Page> getPages() {
		return pages;
	}
}
