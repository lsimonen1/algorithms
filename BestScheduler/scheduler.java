import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.IntStream;

public class scheduler {

	static int endTime;

	private static int[] check(int[] end, int[] front, int spot) {

		// System.out.println("This is [" + spot + "]
		// ---------------------------------");
		// System.out.println("if " + end.length +" - 1 <= " + spot);
		// System.out.println("if " + (front.length - 1) + " >= " + (spot + 1) + " && "
		// + endTime + " > next start");

		// if the next start time is later, then we return the array, while deleting
		// current spot
		// System.out.println(front.length + "<=" + spot + "+1");
		// if it is the last, it makes it -1
		if (end.length - 1 <= spot) {
			end[spot] = -1;
			// System.out.println("first if------");
			return end;
			// if it is in the array, and the spot ahead of its start time is less
		} else if (front.length - 1 >= spot && !(endTime <= front[spot + 1])
				|| end[spot] == end[spot + 1] && front[spot] == front[spot + 1]) {
			// System.out.println("second if-----");
			end = check(end, front, spot + 1);

		}
		// System.out.println("ending check method of " + spot);
		end[spot] = -1;

		// System.out.println("end array:" + Arrays.toString(end));

		return end;
	}

	public static void main(String[] args) {

		// get the amount of instances
		Scanner scanner = new Scanner(System.in);
		int instances = Integer.parseInt(scanner.nextLine());
		int[] results = new int[instances];

		for (int i = 0; i < instances; i++) {
			int jobs = Integer.parseInt(scanner.nextLine());
			final int[] finishTimes = new int[jobs];
			final int[] startTimes = new int[jobs];
			int[] finishTimesSorted = new int[jobs];
			int[] startTimesSorted = new int[jobs];

			for (int x = 0; x < jobs; x++) {
				String[] nodeConnections = scanner.nextLine().trim().split(" ");

				finishTimes[x] = Integer.parseInt(nodeConnections[nodeConnections.length - 1]);
				startTimes[x] = Integer.parseInt(nodeConnections[0]);

				// System.out.println("FinishTimes" + Arrays.toString(finishTimes));
				// System.out.println("StartTimes" + Arrays.toString(startTimes));
			}
			/*
			 * Sort the times from first end to last end
			 */

			Integer[] indices = new Integer[finishTimes.length];
			Integer[] indices1 = new Integer[finishTimes.length];

			for (int x = 0; x < indices.length; x++) {
				indices[x] = x;
				indices1[x] = x;

			}

			// Sort the indices based on finishTimes

			Arrays.sort(indices1, (a, b) -> Integer.compare(startTimes[a], startTimes[b]));

			// Rearrange both finishTimes and startTimes based on the sorted indices
			finishTimesSorted = Arrays.stream(indices1).mapToInt(x -> finishTimes[x]).toArray();
			startTimesSorted = Arrays.stream(indices1).mapToInt(x -> startTimes[x]).toArray();

			for (int x = 0; x < finishTimes.length; x++) {
				finishTimes[x] = finishTimesSorted[x];
				startTimes[x] = startTimesSorted[x];

			}

			Arrays.sort(indices, (a, b) -> Integer.compare(finishTimes[a], finishTimes[b]));

			// Rearrange both finishTimes and startTimes based on the sorted indices
			finishTimesSorted = Arrays.stream(indices).mapToInt(x -> finishTimes[x]).toArray();
			startTimesSorted = Arrays.stream(indices).mapToInt(x -> startTimes[x]).toArray();

			// System.out.println("finish" + Arrays.toString(finishTimes));

			// System.out.println("start" + Arrays.toString(startTimes));

			// System.out.println("finish" + Arrays.toString(finishTimesSorted));

			// System.out.println("start" + Arrays.toString(startTimesSorted));
			/*
			 * Start of each instance after everthing is loaded
			 * 
			 */

			// start at first part of array, go thru each element, if it is -1, go to next
			for (int z = 0; z < finishTimes.length; z++) {
				if (finishTimesSorted[z] != -1) {
					endTime = finishTimesSorted[z];
					finishTimesSorted = check(finishTimesSorted, startTimesSorted, z);
					// System.out.println("added");
					results[i]++;
				}
			}
			// System.out.println("end array:" + Arrays.toString(finishTimesSorted));

			// System.out.println(Arrays.toString(results));
		}
		for (int i = 0; i < instances; i++) {
			System.out.println(results[i]);
		}

		scanner.close();
	}

}