package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

/** * ${NAME} class.
 *
 * <p>
 * date: 2025-02-23
 * </p>
 *
 * @author : 김정훈
 * @version : V1.0.0
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("타겟 숫자를 입력해주세요 : ");
		int sum = sc.nextInt();
		System.out.println("enter numbers with comma: ");
		String num = sc.next();
		List<Integer> numList = Arrays.stream(num.split(",")).map(Integer::parseInt).collect(Collectors.toList());

		List<List<Integer>> output = new ArrayList<>();
		Stack<Integer> numStack = new Stack<>();
		dfs(numList, output, numStack, sum, 0, 0);
		System.out.println(output);

	}

	private static void dfs(List<Integer> numList, List<List<Integer>> output, Stack<Integer> numStack, int sum,
			int start, int total) {
		if (total > sum) {
			return;
		}

		if (total == sum) {
			output.add(new ArrayList<>(numStack));
			return;
		}

		for (int i = start; i < numList.size(); i++) {
			int num = numList.get(i);
			numStack.push(num);
			dfs(numList, output, numStack, sum, i, total + num);
			numStack.pop();
		}
	}
}