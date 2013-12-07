import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * IT1205 - Disk Optimization Algorithms Assignment (Module Group IT1206)
 * 
 * @author Wong Kang Fei (124465R)
 * @author Loi Fuh Chang (120501J)
 * @version 1.0
 * 
 */
public class DiskOptimizationAlgorithm {

	private List<Integer> sequence;
	private int current;
	private int previous;

	/**
	 * 
	 * Initialize a DiskOptimizationAlgorithm instance.
	 * 
	 * @param previous
	 *            - The previous cylinder served.
	 * @param current
	 *            - Current position of the cylinder.
	 * @param sequence
	 *            - Given queued cylinder sequence, excluding current cylinder.
	 */
	public DiskOptimizationAlgorithm(int previous, int current,
			List<Integer> sequence) {
		this.sequence = new ArrayList<Integer>();
		this.current = current;
		this.previous = previous;

		// Concatenates current cylinder and given sequence into new list, for
		// ease of calculations later.
		this.sequence.add(current);
		this.sequence.addAll(sequence);
	}

	/**
	 * 
	 * Sort the instance with First Come First Serve algorithm.
	 * 
	 * @return A list of sorted sequence in terms of FCFS algorithm.
	 */
	public List<Integer> fcfs() {
		return this.sequence;
	}

	/**
	 * 
	 * Sort the instance with Shortest Seek Time First algorithm
	 * 
	 * @return A list of sorted sequence in terms of SSTF algorithm.
	 */
	public List<Integer> sstf() {
		// Modified version from the given practical PDF
		int n = sequence.size();
		int c = current;
		int sstf[] = new int[n];
		for (int i = 0; i < n; i++) {
			sstf[i] = sequence.get(i);
		}

		int ii = -1;
		for (int i = 0; i < n; i++) {
			int minimum = Integer.MAX_VALUE;
			ii = i;
			for (int j = i; j < n; j++) {
				int distance = Math.abs(c - sstf[j]);
				if (distance < minimum) {
					ii = j;
					minimum = distance;
				}
			}
			int tmp = sstf[i];
			sstf[i] = sstf[ii];
			sstf[ii] = tmp;
			c = sstf[i];
		}

		List<Integer> sstfList = new ArrayList<Integer>();
		for (int index = 0; index < sstf.length; index++) {
			sstfList.add(sstf[index]);
		}

		return sstfList;
	}

	/**
	 * Sort the instance with SCAN (Elavator) algorithm
	 * 
	 * @return A list of sorted sequence in terms of SCAN algorithm.
	 */
	public List<Integer> scan() {
		
		/*
		 * Logic applied:
		 * 
		 * previous = 700
		 * current = 857
		 * given 751, 4505, 757, 3506, 4578, 50, 351, 22, 1058
		 * 
		 * Starting sequence: 
		 * [857, 751, 4505, 757, 3506, 4578, 50, 351, 22, 1058]
		 * 
		 * Sort by ascending: 
		 * [22, 50, 351, 751, 757, 857, 1058, 3506, 4505, 4578, 4999]
		 * 
		 * Find the index of current cylinder after sorting :
		 * [22, 50, 351, 751, 757, 857, 1058, 3506, 4505, 4578, 4999]
		 * 						    ^
		 * index =				   (5)
		 * 
		 * Split the sequence into two by taking index of the current cylinder as reference
		 * [22, 50, 351, 751, 757]
		 * [857, 1058, 3506, 4505, 4578, 4999]
		 * 
		 * Reverse the order of the first sequence
		 * [757, 751, 351, 50, 22]
		 * 
		 * Append the first sequence on to the second sequence
		 * [857, 1058, 3506, 4505, 4578, 4999, 757, 751, 351, 50, 22]
		 * 
		 */

		// Initialize a new List to prevent overwriting original sequence
		List<Integer> aSeq = new ArrayList<Integer>(sequence);

		// Determine direction
		int direction = previous - current;

		// If direction is less than 0, it is increasing
		// If direction is greater than 0, it is decreasing
		// If direction is 0, the arm does not move, throw exception
		if (direction < 0) {
			// Check if the input sequence contain boundary cylinder
			// If not, include the max cylinder to the sequence
			if (!aSeq.contains(4999)) {
				aSeq.add(4999);
			}

			// Sort the current sequence in ascending order
			Collections.sort(aSeq);
		} else if (direction > 0) {
			if(!aSeq.contains(0)){
				aSeq.add(0);
			}
			
			// Sort the current sequence in descending order
			Collections.sort(aSeq);
			Collections.reverse(aSeq);
		} else {
			throw new UnsupportedOperationException(
					"Previous cylinder and current cylinder is the same. The arm does not move at all!");
		}
		
		// Find the index position of the current cylinder after sorting
		int currentCylinderIndex = aSeq.indexOf(current);
		
		// Split the sequence into two part, taking the index of the current cylinder as reference
		List<Integer> scanSplicedArr1 = aSeq.subList(0, currentCylinderIndex);
		List<Integer> scanSplicedArr2 = aSeq.subList(currentCylinderIndex,
				aSeq.size());

		// Reverse first sequence
		Collections.reverse(scanSplicedArr1);
		
		// Append the first sequence onto the second sequence 
		scanSplicedArr2.addAll(scanSplicedArr1);

		return scanSplicedArr2;
	}

	public List<Integer> cscan() {

		List<Integer> aSeq = new ArrayList<Integer>(sequence);

		int direction = previous - current;
		
		// Adding cylinder boundaries if the given sequence do not have them
		if (!aSeq.contains(4999)) {
			aSeq.add(4999);
		}
		if (!aSeq.contains(0)) {
			aSeq.add(0);
		}

		if (direction < 0) { // increasing
			Collections.sort(aSeq);
		} else if (direction > 0) { // decreasing
			Collections.sort(aSeq);
			Collections.reverse(aSeq);
		} else {
			// do nothing
		}

		int currentCylinderIndex = aSeq.indexOf(current);

		List<Integer> scanSplicedArr1 = aSeq.subList(0, currentCylinderIndex);
		List<Integer> scanSplicedArr2 = aSeq.subList(currentCylinderIndex,
				aSeq.size());

		scanSplicedArr2.addAll(scanSplicedArr1);

		return scanSplicedArr2;
	}

	public List<Integer> look() {

		List<Integer> aSeq = new ArrayList<Integer>(sequence);

		int direction = previous - current;

		if (direction < 0) {// increasing
			Collections.sort(aSeq);
		} else if (direction > 0) {// decreasing
			Collections.sort(aSeq);
			Collections.reverse(aSeq);
		}

		int currentCylinderIndex = aSeq.indexOf(current);

		List<Integer> lookSplicedArr1 = aSeq.subList(0, currentCylinderIndex);
		List<Integer> lookSplicedArr2 = aSeq.subList(currentCylinderIndex,
				aSeq.size());
		Collections.reverse(lookSplicedArr1);
		lookSplicedArr2.addAll(lookSplicedArr1);

		return lookSplicedArr2;
	}

	public List<Integer> clook() {

		List<Integer> aSeq = new ArrayList<Integer>(sequence);

		int direction = previous - current;

		if (direction < 0) {// increasing
			Collections.sort(aSeq);
		} else if (direction > 0) {// decreasing
			Collections.sort(aSeq);
			Collections.reverse(aSeq);
		}

		int currentCylinderIndex = aSeq.indexOf(current);

		List<Integer> lookSplicedArr1 = aSeq.subList(0, currentCylinderIndex);
		List<Integer> lookSplicedArr2 = aSeq.subList(currentCylinderIndex,
				aSeq.size());

		lookSplicedArr2.addAll(lookSplicedArr1);

		return lookSplicedArr2;
	}
	
	/*
	 * FOLLOWING TWO METHOD ARE FOR PLOTTING PURPOSE ONLY
	 * both include a null value to plot the discontinuous line between boundary seek values
	 * use clook() or cscan() to display sequence without null values.
	 * 
	 */
	
	public List<Integer> cscanPlot() {

		List<Integer> aSeq = new ArrayList<Integer>(sequence);

		int direction = previous - current;
		
		// Adding cylinder boundaries if the given sequence do not have them
		if (!aSeq.contains(4999)) {
			aSeq.add(4999);
		}
		if (!aSeq.contains(0)) {
			aSeq.add(0);
		}

		if (direction < 0) { // increasing
			Collections.sort(aSeq);
		} else if (direction > 0) { // decreasing
			Collections.sort(aSeq);
			Collections.reverse(aSeq);
		} else {
			// do nothing
		}
		
		// This is for plotting purpose, to simulate discontinuous line between the boundary seek
		// Should remove the null when displaying the sequence
		aSeq.add(null);

		int currentCylinderIndex = aSeq.indexOf(current);

		List<Integer> scanSplicedArr1 = aSeq.subList(0, currentCylinderIndex);
		List<Integer> scanSplicedArr2 = aSeq.subList(currentCylinderIndex,
				aSeq.size());

		scanSplicedArr2.addAll(scanSplicedArr1);

		return scanSplicedArr2;
	}
	
	public List<Integer> clookPlot() {

		List<Integer> aSeq = new ArrayList<Integer>(sequence);

		int direction = previous - current;

		if (direction < 0) {// increasing
			Collections.sort(aSeq);
		} else if (direction > 0) {// decreasing
			Collections.sort(aSeq);
			Collections.reverse(aSeq);
		}
		
		// This is for plotting purpose, to simulate discontinuous line between the boundary seek
		// Should remove the null when displaying the sequence
		aSeq.add(null);

		int currentCylinderIndex = aSeq.indexOf(current);

		List<Integer> lookSplicedArr1 = aSeq.subList(0, currentCylinderIndex);
		List<Integer> lookSplicedArr2 = aSeq.subList(currentCylinderIndex,
				aSeq.size());

		lookSplicedArr2.addAll(lookSplicedArr1);

		return lookSplicedArr2;
	}

}
