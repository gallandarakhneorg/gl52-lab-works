package fr.utbm.gl52.stack;

import java.util.Iterator;

public class IteratorMainExample {

	public static void main(String[] args) {
		Stack<Integer> s = new ArrayStack<Integer>();
		s.push(2);
		s.push(6);
		s.push(125);
		s.push(45);
		
		Iterator<Integer> iterator = s.iterator();
		while (iterator.hasNext()) {
			Integer value = iterator.next();
			System.out.println(value);
		}
	}

}
