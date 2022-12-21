package jv.Seminar4.JSeminar_4.src.main.java.ru.gb.jseminar;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Task0 {

    // Дан Deque состоящий из последовательности цифр.
    // Необходимо проверить, что последовательность цифр является палиндромом
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>(Arrays.asList(1,2,3,4,5,6));
        System.out.println(String.valueOf(new Task0().checkOn(deque)));
        // result false
    }


    public boolean checkOn(Deque<Integer> deque){
        if (deque.size() == 0)		return false;
		boolean result = true;
		while (deque.size() > 1) {
			if (!deque.pollFirst().equals(deque.pollLast())) {
				result = false;
				break;
			}
		}
		return result;
    }
}
