import java.util.ArrayList;

public class Demos {

	public static void bubbleSort(ArrayList<Comparable> list) {

		for (int i = 0; i < list.size(); i++) {
			for (int k = i; k < list.size(); k++) {
				if (list.get(i).compareTo(list.get(k)) > 0) {
					swap(i, k, list);
				}
			}
		}

	}

	public static void swap(int i, int k, ArrayList<Comparable> list) {

		Comparable temp = list.get(k);
		list.set(k, list.get(i));
		list.set(i, temp);

	}

	public static void main(String[] args) {

		ArrayList<Comparable> list = new ArrayList<Comparable>();

		list.add(3);
		list.add(9);
		list.add(5);
		list.add(2);
		list.add(1);
		list.add(4);
		list.add(4);
		list.add(10);
		list.add(-1);
		list.add(0);
		list.add(3);
		list.add(0);
		list.add(7);
		list.add(8);
		list.add(1);

		System.out.println(list.toString());
		bubbleSort(list);
		System.out.println(list.toString());

	}

}
