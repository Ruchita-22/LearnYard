package stack;

import java.util.*;

public class AdvanceStackProb {
    // 726. Number of Atoms
    // https://www.youtube.com/watch?v=XnVWIT47H0Y&list=PLpIkg8OmuX-IA6_cJxfTYCmnv1jkqox47&index=21
    public String countOfAtoms(String formula) {
        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new HashMap<>());
        int n = formula.length();
        int i = 0;

        while (i < n) {
            char c = formula.charAt(i);

            // Case 1: Opening parenthesis
            if (c == '(') {
                stack.push(new HashMap<>());
                i++;
            }

            // Case 2: Closing parenthesis
            else if (c == ')') {
                Map<String, Integer> currMap = stack.pop();
                i++;

                // Parse multiplier (e.g. ")2" → multiply inner map by 2)
                int start = i;
                while (i < n && Character.isDigit(formula.charAt(i)))
                    i++;
                int mul = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;

                // Multiply all counts inside parentheses
                if (mul > 1) {
                    currMap.replaceAll((k, v) -> v * mul);
                }

                // Merge into map below (stack top)
                Map<String, Integer> peekMap = stack.peek();
                for (Map.Entry<String, Integer> entry : currMap.entrySet()) {
                    peekMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
                }
            }

            // Case 3: Element name + count
            else {
                int start = i;
                i++; // move past uppercase letter

                // get lowercase letters if any
                while (i < n && Character.isLowerCase(formula.charAt(i)))
                    i++;
                String element = formula.substring(start, i);

                // parse optional number
                start = i;
                while (i < n && Character.isDigit(formula.charAt(i)))
                    i++;
                int count = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;

                // add to current top map
                stack.peek().merge(element, count, Integer::sum);
            }
        }

        // Sort final map alphabetically
        Map<String, Integer> sortedMap = new TreeMap<>(stack.peek());

        // Build result string
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            sb.append(entry.getKey());
            if (entry.getValue() > 1)
                sb.append(entry.getValue());
        }

        return sb.toString();
    }
    // 2751. Robot Collisions
    // https://www.youtube.com/watch?v=fkcA9zvP7_w&list=PLpIkg8OmuX-IA6_cJxfTYCmnv1jkqox47&index=21
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        List<int[]> robots = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            robots.add(new int[] { positions[i], healths[i], directions.charAt(i), i });
        }
        // print1(robots);
        Collections.sort(robots, (a, b) -> a[0] - b[0]);
        // print1(robots);
        Stack<int[]> stack = new Stack<>();
        for (int[] robot : robots) {
            if (stack.isEmpty() || stack.peek()[2] == 'L' || robot[2] == 'R') { // no collsion
                stack.push(robot);
                continue;
            }
            if (robot[2] == 'L') {
                boolean robotAlive = true;
                while (!stack.isEmpty() && stack.peek()[2] == 'R' && robotAlive) {
                    if (robot[1] > stack.peek()[1]) {
                        stack.pop();
                        robot[1] = robot[1] - 1;
                        robotAlive = true;
                    } else if (robot[1] < stack.peek()[1]) {
                        stack.peek()[1] = stack.peek()[1] - 1;
                        robotAlive = false;
                    } else { // if equal health
                        stack.pop();
                        robotAlive = false;
                    }
                }
                if (robotAlive) {
                    stack.push(robot);
                }
            }

        }
        List<int[]> list = new ArrayList<>(stack);
        // print1(list);
        list.sort(Comparator.comparingInt(a -> a[3]));
        // print1(list);
        List<Integer> res = new ArrayList();
        for (int[] robot : list) {
            res.add(robot[1]);
        }
        return res;
    }
}
