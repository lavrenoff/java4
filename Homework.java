package jv.Seminar4.JSeminar_4.src.main.java.ru.gb.jseminar;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Homework {

    //Даны два Deque представляющие два целых числа. Цифры хранятся в обратном порядке,
    // и каждый из их узлов содержит одну цифру.
    public static void main(String[] args) {
        Homework hw = new Homework();

        System.out.println(hw.multiple(new ArrayDeque<>(Arrays.asList(5,2)), new ArrayDeque<>(Arrays.asList(4))));
        // result [0,0,1]
        System.out.println(hw.sum(new ArrayDeque<>(Arrays.asList(5,-2)), new ArrayDeque<>(Arrays.asList(5))));
        // result [0,-2]
    }

    // Умножьте два числа и верните произведение в виде связанного списка.
    public Deque<Integer> multiple(Deque<Integer> d1, Deque<Integer> d2){
        int sum = 0;
		int mul;
		boolean flagFirstCycle = true;
		boolean flagFirstStep = true;
		Deque<Integer> tmp = new ArrayDeque<>();
		Deque<Integer> result = new ArrayDeque<>();

		while (!d1.isEmpty()) {
			int d1Number = d1.pollFirst();
			if (flagFirstCycle) {
				flagFirstCycle = false;
				for (int d2Number : d2) {
					mul = d1Number * d2Number;
					if (flagFirstStep) {
						flagFirstStep = false;
						result.addLast(mul % 10);
						sum += mul;
					} else {
						sum += mul;
						tmp.addLast(sum % 10);
					}
					sum /= 10;
				}
				if (sum != 0)	tmp.addLast(sum);
				sum = 0;
				flagFirstStep = true;
			} else {
				for (int d2Number : d2) {
					mul = d1Number * d2Number;

					if (!tmp.isEmpty())	sum += mul + tmp.pollFirst();
					else				sum += mul;

					if (flagFirstStep) {
						flagFirstStep = false;
						result.addLast(sum % 10);
					} else {
						tmp.addLast(sum % 10);
					}
					sum /= 10;
				}
				if (sum != 0)	tmp.addLast(sum);
				sum = 0;
				flagFirstStep = true;
			}
		}
		while (!tmp.isEmpty())	result.addLast(tmp.pollFirst());
		return result;
    }

    // Сложите два числа и верните сумму в виде связанного списка. Одно или два числа должны быть отрицательными
    public Deque<Integer> sum(Deque<Integer> d1, Deque<Integer> d2){

        Deque<Integer> result = new ArrayDeque<>();

		if (d1.isEmpty() && d2.isEmpty())	return result;
		if (d1.isEmpty())					return d2;
		if (d2.isEmpty())					return d1;

		if (d1.getLast() >= 0 && d2.getLast() < 0) {
			int tmp = d2.pollLast();
			d2.addLast(-tmp);
			result = subtraction(d1, d2);
			return result;
		} else if (d1.getLast() < 0 && d2.getLast() >= 0) {
			int tmp = d1.pollLast();
			d1.addLast(-tmp);
			result = subtraction(d2, d1);
			return result;
		} else if (d1.getLast() < 0 && d2.getLast() < 0) {
			int tmp = d1.pollLast();
			d1.addLast(-tmp);
			tmp = d2.pollLast();
			d2.addLast(-tmp);

			result = positiveSum(d1, d2);

			int last = result.pollLast();
			result.addLast(-last);
			return result;
		} else {
			return positiveSum(d1, d2);
		}
    }

    public Deque<Integer> subtraction (Deque<Integer> d1, Deque<Integer> d2) {
		Deque<Integer> result = new ArrayDeque<>();

		if (d1.isEmpty() && d2.isEmpty()) return result;
		if (d1.isEmpty()) return d2;
		if (d2.isEmpty()) return d1;

		if (d1.size() == d2.size()) {
			while (d1.getLast().equals(d2.getLast())) {
				d1.removeLast();
				d2.removeLast();
				if (d1.isEmpty() || d2.isEmpty()) {
					result.addLast(0);
					return result;
				}
			}
			if (d1.getLast() > d2.getLast()) {
				int digit = 0;
				while (!d1.isEmpty()) {
					int dif = d1.pollFirst() - d2.pollFirst() - digit;
					if (dif < 0) {
						result.addLast(10 + dif );
						digit = 1;
					} else {
						result.addLast(dif);
						digit = 0;
					}
				}
				while ((result.getLast() == 0)) {
					result.removeLast();
					if (result.isEmpty()) {
						result.addLast(0);
						return result;
					}
				}
				return result;
			} else {
				int digit = 0;
				while (!d1.isEmpty()) {
					int dif = d2.pollFirst() - d1.pollFirst() - digit;
					if (dif < 0) {
						result.addLast(10 + dif );
						digit = 1;
					} else {
						result.addLast(dif);
						digit = 0;
					}
				}
				while (result.getLast() == 0) {
					result.removeLast();
					if (result.isEmpty()) {
						result.addLast(0);
						return result;
					}
				}
				int last = result.pollLast();
				result.addLast(-last);
				return result;
			}
		} else if (d1.size() > d2.size()) {
			int digit = 0;
			while (!d1.isEmpty()) {
				int dif;
				if (!d2.isEmpty()) {
					dif = d1.pollFirst() - d2.pollFirst() - digit;
				} else {
					dif = d1.pollFirst() - digit;
				}
				if (dif < 0) {
					result.addLast(10 + dif);
					digit = 1;
				} else {
					result.addLast(dif);
					digit = 0;
				}
			}
			while (result.getLast() == 0) {
				result.removeLast();
				if (result.isEmpty()) {
					result.addLast(0);
					return result;
				}
			}
			return result;
		} else {
			int digit = 0;
			while (!d2.isEmpty()) {
				int dif;
				if (!d1.isEmpty()) {
					dif = d2.pollFirst() - d1.pollFirst() - digit;
				} else {
					dif = d2.pollFirst() - digit;
				}
				if (dif < 0) {
					result.addLast(10 + dif);
					digit = 1;
				} else {
					result.addLast(dif);
					digit = 0;
				}
			}
			while (result.getLast() == 0) {
				result.removeLast();
				if (result.isEmpty()) {
					result.addLast(0);
					return result;
				}
			}
			int last = result.pollLast();
			result.addLast(-last);
			return result;
		}
	}

    public Deque<Integer> positiveSum (Deque<Integer> d1, Deque<Integer> d2) {
		Deque<Integer> result = new ArrayDeque<>();

		if (d1.isEmpty() && d2.isEmpty())	return result;
		if (d1.isEmpty())					return d2;
		if (d2.isEmpty())					return d1;

		int sum = 0;
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
