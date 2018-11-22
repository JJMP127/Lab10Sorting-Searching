public class FileRecord implements Comparable<FileRecord> {
	public enum Sex {MALE, FEMALE} //Enumerated type to categorize between Male and Female

	private int recordID;
	private String patientName;
	private Sex sex;
	private char type;


	//Constructors
	public FileRecord(int recordID, String patientName, Sex sex, char patientType) {
		this.recordID = recordID;
		this.patientName = patientName;
		this.sex = sex;
		type = patientType;
	}

	public FileRecord(FileRecord otherFile) {
		recordID = otherFile.getID();
		patientName = otherFile.getPName();
		sex = otherFile.getSex();
		type = otherFile.getType();
	}

	@Override
	public int compareTo(FileRecord oFile) {
		return this.recordID - oFile.getID();
	}

	//Getters
	public Sex getSex() {
		return sex;
	}

	public char getType() {
		return type;
	}

	public String getPName() {
		return patientName;
	}

	public int getID() {
		return recordID;
	}

	//Equals method for comparing if another object is the same as this instance
	public boolean equals(Object obj) {
		if(!(obj instanceof FileRecord))
			return false;
		FileRecord other = (FileRecord) obj;
		return this.recordID == other.getID() &&
				this.patientName.equals(other.getPName()) &&
				this.sex == other.getSex() && this.type == other.getType();
	}

	/**
	 * A method to return a copy of the given array
	 * @param shelve - Original Array
	 * @return - Copy of shelve
	 */
	public static FileRecord[] copyOf(FileRecord[] shelve) {
		FileRecord[] copy = new FileRecord[shelve.length];
		for(int i = 0; i < shelve.length; i++) {
			copy[i] = shelve[i];
		}
		return copy;
	}

	/**
	 * Linear Algorithm to find the index of a given target.
	 * @param shelve - Target List
	 * @param target - Target Element
	 * @return - Index of Target Element (-1 if not found)
	 */
	public static int linearSearch(FileRecord[] shelve, FileRecord target) {
		return linHelper(shelve, 0, new FileRecord(target));
	}

	/**
	 * Linear search helper
	 * @param shelve - Array to find target in
	 * @param index - index of current object we are comparing the target with 
	 * @param target - The object we want to find in the array
	 * @return index of what was found (-1 if not found)
	 */
	private static int linHelper(FileRecord[] shelve, int index, FileRecord target) {
		if(index >= shelve.length) {
			return -1;
		}
		if(target.equals(shelve[index])) {
			return index;
		}
		return linHelper(shelve, index+1, target);
	}

	/**
	 * Binary Algorithm to find the index of a given target.
	 * @param arr - Target List
	 * @param target - Target Element
	 * @return - Index of Target Element (-1 if not found)
	 */
	public static int binarySearch(FileRecord[] shelve, FileRecord target) {
		//Add Code
		//You should implement a helper method and use an anonymous inner class
		return binarySearchHelper(shelve, target, 0, shelve.length - 1); //Temp return
	}

	public static int binarySearchHelper(FileRecord[] shelve, FileRecord target, int start, int end){

		
		if(start > end) return  -1;
		
		int middle = (end + start)/2;
		
		if(target.equals(shelve[middle])) return middle;
		
		if(target.compareTo(shelve[middle]) <= 0){
			
			return binarySearchHelper(shelve, target, start, middle - 1);
			
		}
		else{
			
			return binarySearchHelper(shelve,target, middle+1, end);
			
		}
	}
	
	/**
	 * Iterative bubble sort for an array
	 * @param shelve array to be sorted.
	 */
	public static void bubbleSort(FileRecord[] shelve) {
		boolean isSorted = false;
		while(!isSorted) {
			isSorted = true;
			for(int index = 1; index < shelve.length; index++) {
				if(shelve[index-1].compareTo(shelve[index]) >= 0) {
					swap(shelve, index-1, index);
					isSorted = false;
				}
			}
		}		
	}

	/**
	 * Helper method to swap the position of two objects in a given array
	 * @param shelve - array to work in
	 * @param a - index of the first object to swap
	 * @param b - index of the second object to swap
	 */
	private static void swap(FileRecord[] shelve, int a, int b) {
		FileRecord temp = shelve[a];
		shelve[a] = shelve[b];
		shelve[b] = temp;
	}

	/**
	 * Recursive Insertion Sort for an array
	 * @param shelve - Array to be sorted
	 */
	public static void insertionSort(FileRecord[] shelve) {
		insHelper(shelve, 1);
	}

	/**
	 * A helper method for the insertion sort's recursive algorithm
	 * @param shelve - Array to be sorted
	 * @param tailIndex - Index of the right part of the array that is yet to be sorted.
	 */
	private static void insHelper(FileRecord[] shelve, int tailIndex) {
		if(tailIndex >= shelve.length) {return;}
		for(int i = tailIndex-1; i >= 0; i--) {
			if(shelve[i+1].compareTo(shelve[i]) >= 0) {
				break;
			}
			swap(shelve, i, i+1);
		}
		insHelper(shelve, tailIndex+1);
	}

	/**
	 * Recursive Selection Sort for an array
	 * @param shelve - Array to be sorted
	 */
	public static void selectionSort(FileRecord[] shelve) {
	
		selHelper(shelve, 0);
		
		
		//FIRST TRY


		//		boolean isSorted = false;
		//		
		//		while(!isSorted){
		//			
		//			int min = 0;
		//			
		//			for(int i = 1; i < shelve.length; i++){
		//				
		//				if(shelve[min].compareTo(shelve[i]) < 0) swap(shelve, i, min);
		//			}

	}

	public static void selHelper(FileRecord[] shelve, int tailIndex){

		if(tailIndex >= shelve.length){

			return;
			
		}
		
		int minIndex = tailIndex;

		for (int i = tailIndex; i < shelve.length;i++){

			if(shelve[minIndex].compareTo(shelve[i]) > 0){

				minIndex = i;

			}
		}

		swap(shelve, minIndex, tailIndex);

		selHelper(shelve, tailIndex + 1);
	}

}