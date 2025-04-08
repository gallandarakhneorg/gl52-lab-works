package fr.utbm.de52.stack;

public class IteratorMainExample {

	public static void main(String[] args) {
		var s = new ArrayStack<Integer>();
		s.push(2);
		s.push(6);
		s.push(125);
		s.push(45);
		
		var iterator = s.iterator();
		while (iterator.hasNext()) {
			var value = iterator.next();
			System.out.println(value);
		}
	}

}
