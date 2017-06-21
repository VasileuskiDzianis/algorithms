package algorithms.bublesort;

public class BubleSort {

	public static void sortString(String[] str) {

		for (int i = 0; i < str.length; i++) {
			for (int j = i + 1; j < str.length; j++) {
				if (str[i].compareTo(str[j]) > 0) {
					String buf = str[i];
					str[i] = str[j];
					str[j] = buf;
				}
			}
		}
	}

	public static void main(String[] args) {
		String[] testArray = { "Dan", "Max", "Alex", "Mike", "Robert", "Ronald", "Fergie", "Dan" };

		System.out.println("Original array: ");
		for (String string : testArray) {
			System.out.print(string + " ");
		}

		sortString(testArray);

		System.out.println("Sorted array: " + testArray);
		for (String string : testArray) {
			System.out.print(string + " ");
		}
	}
}
