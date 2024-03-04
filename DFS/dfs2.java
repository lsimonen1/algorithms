import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class dfs2 {


	private static boolean check(String[] arr, String toCheckValue){
      
        boolean init = false;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] != null && arr[i].equals(toCheckValue)) {
                init = true;
                break;
            }
        }
		return init;
 
       
    }



	
	public static void main(String[] args) {

		// get the amount of nodes in the graph
		Scanner scanner = new Scanner(System.in);

		int itterations =  Integer.parseInt(scanner.nextLine());

		String[] outs = new String[itterations];

		for(int z = 0; z < itterations; z++){

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
		int[] numberspot = new int[nodes];

		String nodeName = nodelist[curr][0];

		/*
		 * 
		 * 
		 * 
		 * Algorithm is after this point
		 * 
		 * 
		 */

		 


		 while (outputSpot < nodes){

			


			//System.out.println(nodeName + "NodeName" + queue.size() + "q size" + curr + "curr" + outputSpot +"outputspot");
			
			

			//search node till not null to find next element
			for(int i = 0; i < nodes; i++){
				if( nodelist[curr][i] != null ){
					//checks if it has been on already
					boolean check = check(output, nodelist[curr][i]);
					if (check == false){
					nodeName = nodelist[curr][i];
					break;
					}
				}
			}


			//go deeper into the tree
			for(int x = 0; x < nodes; x++){
				if (nodelist[x][0] != null && nodelist[x][0].equals(nodeName)){
					curr = x;
				}	
			}

			int goback = 1;

			while(check(output, nodelist[curr][0]) == true){

				boolean anything = false;
				for(int i = 0; i < nodes; i++){
					if( nodelist[curr][i] != null ){
						//checks if it has been on already
						boolean check = check(output, nodelist[curr][i]);
						if (check == false){
							anything = true;
						nodeName = nodelist[curr][i];
						break;
						}
					}
				}
				if(anything == true){

					for(int x = 0; x < nodes; x++){
						if (nodelist[x][0] != null && nodelist[x][0].equals(nodeName)){
							curr = x;
						}	
					}
					break;
				}
				//if doesnt work go up a level or go find a new disconnected tree
				if(outputSpot - goback <= -1){
					for(int i = 0; i < nodes; i++){
						if(check(output, nodelist[i][0]) == false){
							nodeName = nodelist[i][0];
							curr = i;
						}

					}
				}else{
				nodeName = output[outputSpot - goback];
				curr = numberspot[outputSpot - goback];
				goback++;
				}

			}


			
			



			output[outputSpot] = nodeName;
			numberspot[outputSpot] = curr;
			outputSpot++;

			

		}

		outs[z] = "";
		for(int i = 0; i < nodes - 1; i++){

			outs[z] = outs[z] + (output[i] + " ");

		}
		outs[z] = outs[z] + (output[nodes - 1]+ "\n");
	
	}

	for(int i = 0; i < itterations; i++){
		System.out.print(outs[i]);
	}


	}
}