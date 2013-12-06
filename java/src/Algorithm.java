import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {
	
	private List<Integer> sequence;
	private int current;
	private int previous;
	
	public Algorithm(int previousCylinder, int currentCylinder, List<Integer> inputSequence){
		this.sequence = new ArrayList<Integer>();
		this.current = currentCylinder;
		this.previous = previousCylinder;
		
		this.sequence.add(currentCylinder);
		this.sequence.addAll(inputSequence);
	}
	
	public List<Integer> fcfs() {
		return this.sequence;
	}

	public List<Integer> sstf() {
		int n = sequence.size();
		int sstf[] = new int[n];
		for (int i = 0; i < n; i++) {
			sstf[i] = sequence.get(i);
		}

		int ii = -1;
		for (int i = 0; i < n; i++) {
			int minimum = Integer.MAX_VALUE;
			ii = i;
			for (int j = i; j < n; j++) {
				int distance = Math.abs(current - sstf[j]);
				if (distance < minimum) {
					ii = j;
					minimum = distance;
				}
			}
			int tmp = sstf[i];
			sstf[i] = sstf[ii];
			sstf[ii] = tmp;
			current = sstf[i];
		}
		
	    List<Integer> sstfList = new ArrayList<Integer>();
	    for (int index = 0; index < sstf.length; index++)
	    {
	        sstfList.add(sstf[index]);
	    }

		return sstfList;
	}
	
	public List<Integer> scan() {
		
		int direction = previous - current;
		
		if(direction<0){ // increasing
			Collections.sort(sequence);
			sequence.add(4999);
		}else if(direction>0){ // decreasing
			Collections.sort(sequence);
			Collections.reverse(sequence);
			sequence.add(0);
		}else{
			// do nothing
		}
		
		int currentCylinderIndex = sequence.indexOf(current);
		
		List<Integer> scanSplicedArr1 = sequence.subList(0, currentCylinderIndex+1);
		List<Integer> scanSplicedArr2 = sequence.subList(currentCylinderIndex+1, sequence.size());
		
		Collections.reverse(scanSplicedArr1);
		
		scanSplicedArr1.addAll(scanSplicedArr2);
		
		
		return scanSplicedArr1;
	}
	
	public List<Integer> cscan(int previousCylinder, int currentCylinder,
			List<Integer> inputSequence) {
		
		int direction = previousCylinder - currentCylinder;
		
		if(direction<0){ // increasing
			Collections.sort(sequence);
		}else if(direction>0){ // decreasing
			Collections.sort(sequence);
			Collections.reverse(sequence);
		}else{
			// do nothing
		}
		
		int currentCylinderIndex = sequence.indexOf(currentCylinder);
		
		List<Integer> scanSplicedArr1 = sequence.subList(0, currentCylinderIndex+1);
		List<Integer> scanSplicedArr2 = sequence.subList(currentCylinderIndex+1, sequence.size());
		
		Collections.reverse(scanSplicedArr1);
		
		scanSplicedArr1.addAll(scanSplicedArr2);		
		
		return scanSplicedArr1;
	}
	
	
}
