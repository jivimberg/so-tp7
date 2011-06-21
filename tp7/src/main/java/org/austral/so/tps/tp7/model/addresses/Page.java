package org.austral.so.tps.tp7.model.addresses;

import java.util.ArrayList;
import java.util.List;

import org.austral.so.tps.tp7.model.core.Chunk;
import org.austral.so.tps.tp7.model.core.OS;

public class Page {
	
	private List<Chunk> chunks;
	private int n;
	private int fatherN;

	public Page(int n, int fatherN){
		chunks = new ArrayList<Chunk>();
		for (int i=0;i < OS.PAGE_AND_FRAME_SIZE; i++){
			chunks.add(new Chunk());
		}
		this.n = n;
		this.fatherN = fatherN;
	}

	public List<Chunk> getChunks() {
		return chunks;
	}

	public int getNumber() {
		return n;
	}

	public int getFatherN() {
		return fatherN;
	}
}