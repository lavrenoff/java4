package jv.Seminar4.JSeminar_4.src.main.java.ru.gb.jseminar;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Task1 {

    //Даны два Deque представляющие два неотрицательных целых числа. Цифры хранятся в обратном порядке,
    // и каждый из их узлов содержит одну цифру.
    // Сложите два числа и верните сумму в виде связанного списка.
    public static void main(String[] args) {
        Deque<Integer> d1 = new ArrayDeque<>(Arrays.asList(1,2,3));
        Deque<Integer> d2 = new ArrayDeque<>(Arrays.asList(5,4,7));

        System.out.println(String.valueOf(new Task1().sum(d1, d2)));
        // result [6,6,0,1]

    }


    public Deque<Integer> sum(Deque<Integer> d1, Deque<Integer> d2) {
        int sum = 0;
		Deque<Integer> result = new ArrayDeque<>();
		while (!d1.isEmpty() || !d2.isEmpty()) {
			if (!d1.isEmpty()) {
				sum += d1.pollFirst();
			} 
			if (!d2.isEmpty()) {
				sum += d2.pollFirst();
			}
			if (sum > 9) {
				result.add(sum % 10);
				sum = 1;
			} else {
				result.add(sum);
				sum = 0;
			}
		}
		if (sum != 0)	result.add(sum);
		return result;
    }
}
