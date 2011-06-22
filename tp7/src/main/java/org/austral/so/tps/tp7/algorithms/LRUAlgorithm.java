package org.austral.so.tps.tp7.algorithms;

import java.util.Map;

import org.austral.so.tps.tp7.model.core.ProcessPageTable;
import org.austral.so.tps.tp7.model.core.ProcessPageTable.PageTableRow;
import org.austral.so.tps.tp7.model.memories.OSRAM;

public class LRUAlgorithm implements PageReplacingAlgorithm {

	public int computePageToReplace(OSRAM ram, int pageNumber, ProcessPageTable processPageTable) {
		Map<Integer, PageTableRow> rows = processPageTable.getPageTableRows();
		PageTableRow row;
		
		int passed = 0;
		int i = 0;
		
		while (passed <= 2) {
			if (i == rows.size()) {
				i = 0;
				passed++;
			}
			row = rows.get(i);
			if (row.isReference()) {
				row.setReference(false);
			} else if (row.isValid()) {
				if (i != pageNumber) {
					row.setValid(false);
					return rows.get(i).getFrameLocation();
				}
			}
			i++;
		}
		return ram.getLastWrittenLocation();
	}

	public String getName() {
		return "LRU";
	}
}
