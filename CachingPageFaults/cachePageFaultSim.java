import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.PriorityQueue;

public class cachePageFaultSim {

	public static void main(String[] args) {

		// get the amount of instances
		Scanner scanner = new Scanner(System.in);
		int instances = Integer.parseInt(scanner.nextLine());
		int[] results = new int[instances];

		for (int i = 0; i < instances; i++) {
			int cacheSize = Integer.parseInt(scanner.nextLine());
			int jobs = Integer.parseInt(scanner.nextLine());

			//ArrayList<PriorityQueue<Integer>> queueArray = new ArrayList<>();
			HashMap<Integer, PriorityQueue<Integer>> hashMapOfPriorityQueues = new HashMap<>();
			ArrayList<Integer> pagesList = new ArrayList<Integer>();
			int[] cache = new int[cacheSize];
			Arrays.fill(cache, -1);

			String[] nodeConnections = scanner.nextLine().trim().split(" ");

			//We do this for every single page
			for (int x = 0; x < jobs; x++) {
				//we add to the list of pages (but this time they are ints not strings)
				pagesList.add(Integer.parseInt(nodeConnections[x]));
				//if there is no hashmap for a page we put a hashmap in for the number
				if(hashMapOfPriorityQueues.get(Integer.parseInt(nodeConnections[x])) == null){
					hashMapOfPriorityQueues.put(Integer.parseInt(nodeConnections[x]), new PriorityQueue<Integer>());
				}
				//now for any given page, we take the priority queue out for the page and put in the indicie we are at for that priority queue
				PriorityQueue<Integer> hashInsert = hashMapOfPriorityQueues.get(Integer.parseInt(nodeConnections[x]));
				hashInsert.add(x);
				hashMapOfPriorityQueues.put(Integer.parseInt(nodeConnections[x]), hashInsert);
			}

			//checking if this data works
			//System.out.println(Arrays.asList(hashMapOfPriorityQueues));
			//System.out.println(Arrays.asList(pagesList));

			//base case until cache is filled
			int x = 0;
			while (cache[cacheSize - 1] < 0){
				//is it in cache already??
				boolean inCache = false;
				for (int z = 0; z < cacheSize; z++) {
					if (pagesList.get(0).equals(cache[z])){
						inCache = true;
					}
				}
				if (inCache == false){
				cache[x] = pagesList.remove(0);
				//removes it from the pages list and gets the priority queue
				PriorityQueue<Integer> hashEdit = hashMapOfPriorityQueues.get(cache[x]);
				//we now poll from priority queue, this should be a shallow copy so it should work
				hashEdit.poll();
				results[i]++;
				x++;
			}else{
				cache[x] = pagesList.remove(0);
				//removes it from the pages list and gets the priority queue
				PriorityQueue<Integer> hashEdit = hashMapOfPriorityQueues.get(cache[x]);
				//we now poll from priority queue, this should be a shallow copy so it should work
				hashEdit.poll();
			}
			

			}
			
			//continues until we are empty
			while (pagesList.size() >= 1){

				boolean inCache = false;
				for (int z = 0; z < cacheSize; z++) {
		
					if (pagesList.get(0).equals(cache[z])){
						inCache = true;
					}
				}

				if (inCache == true){
					//removes it from the pages list and gets the priority queue
					PriorityQueue<Integer> hashEdit = hashMapOfPriorityQueues.get(pagesList.remove(0));
					//we now poll from priority queue, this should be a shallow copy so it should work
					hashEdit.poll();
					

				}else{
					//mark the farthest in future pq
					int ffInt = 0;
					int largestSoFar = 0;

					//go through each element in the cache
					for (int z = 0; z < cacheSize; z++) {
						//get pq
						PriorityQueue<Integer> hashEdit = hashMapOfPriorityQueues.get(cache[z]);
						//check if first element is null, or if it is not null is it the largest yet?
						
							if (hashEdit.size() < 1){
								ffInt = z;
								break;
							}
							if (hashEdit.peek() > largestSoFar){
								ffInt = z;
								largestSoFar = hashEdit.peek();
							}
						
					}
					

					//pop element in priority queue
					PriorityQueue<Integer> hashEdit = hashMapOfPriorityQueues.get(pagesList.get(0));
					hashEdit.poll();

					//take from the pages list and put in priority queue
					cache[ffInt] = pagesList.remove(0);
					//add 1 to misses
					results[i]++;
					

				}

			}





			


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