import java.util.Arrays;
import java.util.Scanner;

public class MSInversionCount {

	


	private static long mergeCount(int[] ar, int left, int middle, int right){
		//Initialize S to an empty list and c := 0.
		// s is the normal array since we are going left to right on the whole thing
		int[] rightAr = Arrays.copyOfRange(ar,middle +1,right +1);

		int[] leftAr = Arrays.copyOfRange(ar,left,middle+1);

		int l = 0;
		int r = 0;
		int spot = left;
		long inversions = 0;

		while(l < leftAr.length && r < rightAr.length){
			if(leftAr[l] > rightAr[r]){
				ar[spot] = rightAr[r];
				spot++;
				r++;
				inversions += (middle + 1) - (left + l);
				

			}else if(leftAr[l] <= rightAr[r]){
				ar[spot] = leftAr[l];
				l++;
				spot++;

			}
		}
			//when there are no elements left in one array we just add the rest of the other
			while(l < leftAr.length){
				ar[spot] = leftAr[l];
				l++;
				spot++;
			}

			while(r < rightAr.length){
				ar[spot] = rightAr[r];
				r++;
				spot++;
			}
			//System.out.println("inversions for " + left + " to " + right + " is " + inversions + " " + Arrays.toString(rightAr) + Arrays.toString(leftAr));
		


		return inversions;
	}
	
	private static long countSort(int[] ar, int left, int right){

		long inversions = 0;
		

             // if |A| = 1 then return (A, 0)
        if (left < right) {
			//gets the middle
            int middle = (left + right) / 2;
			//System.out.println(ar[left] + "r "+ ar[right]);
			//(A1, c1) := CountSort(Front-half of A)
            inversions = inversions + countSort(ar, left, middle);
 			//(A2, c2) := CountSort(Back-half of A)
            inversions = inversions + countSort(ar, middle + 1, right);
 			//(A, c) :=MergeCount(A1,A2)
            inversions = inversions + mergeCount(ar, left, middle, right);
        }
		//return inversion count
        return inversions;
	 }
	public static void main(String[] args) {

		// get the amount of instances
		Scanner scanner = new Scanner(System.in);
		int instances = Integer.parseInt(scanner.nextLine());
		long[] results = new long[instances];

		for (int i = 0; i < instances; i++) {
			int size = Integer.parseInt(scanner.nextLine());
			int[] fullArray = new int[size];
			String[] nodeConnections = scanner.nextLine().trim().split(" ");

			for (int x = 0; x < size; x++) {
				//we add to the array (but this time they are ints not strings)
				fullArray[x] = Integer.parseInt(nodeConnections[x]);
			}
			/*
			 * Setup above ^^^^^^^^^^^^^^^^^^^^^^^^
			 */

			//System.out.println(Arrays.toString(fullArray));
			


			results[i] = countSort(fullArray, 0, fullArray.length - 1);
		}
		/*
		 * AFTER ALL IS DONE WE PRINT RESULTS AND ALSO CLOSE SCANNER----------------------------------------------
		 */
		for (int i = 0; i < instances; i++) {
			System.out.println(results[i]);
		}

		scanner.close();
	}

}