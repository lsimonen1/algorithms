import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class dfs {

	public static void main(String[] args) {

		// get the amount of nodes in the graph
		Scanner scanner = new Scanner(System.in);
		int nodes = Integer.parseInt(scanner.nextLine());

		String[][] nodelist = new String[nodes][nodes];

		// for each node, put the data into an array
		for(int i = 0; i < nodes; i++){
			String[] nodeConnections = scanner.nextLine().trim().split(" ");

			for(int x = 0; x < nodeConnections.length; x++){
				nodelist[i][x] = nodeConnections[x];
			}
			
		}

		// make a queue
		ArrayList<Integer> queue = new ArrayList<Integer>();
		int curr = 0;
		int spot = 0;
		int outputSpot = 0;
		// make output
		String[] output = new String[nodes];

		String nodeName = nodelist[curr][0];

		/*
		 * 
		 * 
		 * 
		 * Algorithm is after this point
		 * 
		 * 
		 */


		
		// if first node is there, delete it from rest of array and go to next in array, if none in array go back on queue

		while (outputSpot < nodes){
			//if it is in the array it does not re-add to queue
			boolean inalr = false;
			for(int j = 0; j < nodes; j++){
				if(output[j] != null && output[j].equals(curr)){
					inalr = true;
				}
			}
			if (inalr == false){
				queue.add(curr);
			}
			System.out.println(nodeName + "NodeName" + queue.size() + "q size" + curr + "curr" + outputSpot +"outputspot" + output + "output");
			output[outputSpot] = nodeName;
			outputSpot++;
			
	
			// delete all of the same named nodes from all arrays
			for(int x = 0; x < nodes; x++){
				for(int i = 0; i < nodes; i++){
					if(nodelist[x][i] != null && nodelist[x][i].equals(nodeName)){
						nodelist[x][i] = null;
					}
				}
			}

			//search array till not null to find next element
			for(int i = 0; i < nodes; i++){
				if( nodelist[curr][i] != null){
					nodeName = nodelist[curr][i];
					System.out.println("point1");
					break;
				}
				// if there is nothing in queue or current array, go down an array
				else if(i == nodes - 1 && queue.size() <= 0){
					curr++;
					System.out.println("point55");

					//looks for first array with element in it
					findNewAr:{
					for(int x = 0; x < nodes; x++){
						for(int j = 0; j < nodes; j++){
							if(nodelist[x][j] != null ){
								curr = x;
								break findNewAr;
							}
						}
					}
				}


					System.out.println("point2");
				// if there is nothing in array but something in queue, go to last spot
				}  else if(i == nodes - 1 && queue.size() >= 0){
					System.out.println(queue.size() + "bobhjb");

					boolean validArr = false;
					//not working at end because there are no nodes left
					while (validArr == false){
						curr = queue.get(queue.size()-1);
						queue.remove(queue.size()-1);
						if (queue.size() == 0){
							validArr = true;
							break;
						}
						System.out.println(queue.size() + "hello");
						for(int j = 0; j < nodes; j++){
							if(nodelist[curr][j] != null){
								validArr = true;
								nodeName = nodelist[curr][j];
								System.out.println("itran");
								break;

							}
						}
						System.out.println(queue.size() + "bob");

						}
					System.out.println("point3");
				}
			}



			//go deeper into the tree
			for(int x = 0; x < nodes; x++){
				if (nodelist[x][0] != null && nodelist[x][0].equals(nodeName)){
					curr = x;
				}	
			}
			System.out.println("point4" + curr);

			for(int i = 0; i < nodes; i++){

				System.out.println("l" + Arrays.toString( nodelist[i]));
			}
			System.out.println(Arrays.toString(output));
		}

		System.out.println(Arrays.toString(output));





		for(int i = 0; i < nodes; i++){

			System.out.println(Arrays.toString(output));
		}

		scanner.close();

	
	}

}