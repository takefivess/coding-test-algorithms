import java.util.*;

/**
 * 스택, 큐 및 관련 알고리즘
 */
public class StackAndQueue {

    // 1. 균형잡힌 괄호 확인
    public static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        
        return stack.isEmpty();
    }

    // 2. 후위 표기법 (역폴란드 표기법) 계산
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                
                int result = 0;
                switch (token) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        result = a / b;
                        break;
                }
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }

    // 3. 스택을 이용한 DFS (그래프 탐색)
    public static void dfsStack(int start, int n, List<List<Integer>> graph) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];
        stack.push(start);
        
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                System.out.print(node + " ");
                
                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    // 4. 큐를 이용한 BFS (그래프 탐색)
    public static void bfsQueue(int start, int n, List<List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    // 5. 슬라이딩 윈도우 최댓값
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        
        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        
        for (int i = 0; i < nums.length; i++) {
            // 윈도우 밖의 원소 제거
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // 현재 원소보다 작은 원소들 제거
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            
            deque.addLast(i);
            
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }

    // 6. 큐를 이용한 스택 구현
    static class StackFromQueues {
        Queue<Integer> main = new LinkedList<>();
        Queue<Integer> helper = new LinkedList<>();
        
        public void push(int x) {
            main.add(x);
        }
        
        public int pop() {
            while (main.size() > 1) {
                helper.add(main.poll());
            }
            int result = main.poll();
            Queue<Integer> temp = main;
            main = helper;
            helper = temp;
            return result;
        }
        
        public int peek() {
            while (main.size() > 1) {
                helper.add(main.poll());
            }
            int result = main.peek();
            helper.add(main.poll());
            Queue<Integer> temp = main;
            main = helper;
            helper = temp;
            return result;
        }
        
        public boolean isEmpty() {
            return main.isEmpty();
        }
    }

    // 7. 스택을 이용한 큐 구현
    static class QueueFromStacks {
        Stack<Integer> in = new Stack<>();
        Stack<Integer> out = new Stack<>();
        
        public void push(int x) {
            in.push(x);
        }
        
        public int pop() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
            return out.pop();
        }
        
        public int peek() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
            return out.peek();
        }
        
        public boolean isEmpty() {
            return in.isEmpty() && out.isEmpty();
        }
    }

    // 8. 다음 더 큰 원소 찾기
    public static int[] nextGreaterElement(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            
            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            }
            
            stack.push(nums[i]);
        }
        
        return result;
    }

    // ==================== 테스트 코드 ====================
    public static void main(String[] args) {
        System.out.println("===== 스택 및 큐 테스트 =====\n");

        // 1. 균형잡힌 괄호
        System.out.println("1. 균형잡힌 괄호");
        System.out.println("'()': " + isValidParentheses("()"));
        System.out.println("'([{}])': " + isValidParentheses("([{}])"));
        System.out.println("'(]': " + isValidParentheses("(]"));
        System.out.println();

        // 2. 역폴란드 표기법
        System.out.println("2. 역폴란드 표기법");
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(Arrays.toString(tokens) + " = " + evalRPN(tokens));
        System.out.println();

        // 3. 스택 DFS
        System.out.println("3. 스택을 이용한 DFS");
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 4; i++) graph.add(new ArrayList<>());
        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(2);
        graph.get(2).add(0);
        graph.get(2).add(3);
        System.out.print("DFS (0부터): ");
        dfsStack(0, 4, graph);
        System.out.println("\n");

        // 4. 큐 BFS
        System.out.println("4. 큐를 이용한 BFS");
        System.out.print("BFS (0부터): ");
        bfsQueue(0, 4, graph);
        System.out.println("\n");

        // 5. 슬라이딩 윈도우
        System.out.println("5. 슬라이딩 윈도우 최댓값");
        int[] nums = {1, 3, 1, 2, 0, 5};
        System.out.println("배열: " + Arrays.toString(nums) + ", 윈도우: 3");
        System.out.println("결과: " + Arrays.toString(maxSlidingWindow(nums, 3)));
        System.out.println();

        // 6. 큐로 스택 구현
        System.out.println("6. 큐를 이용한 스택");
        StackFromQueues stack1 = new StackFromQueues();
        stack1.push(1);
        stack1.push(2);
        System.out.println("Pop: " + stack1.pop());
        System.out.println();

        // 7. 스택으로 큐 구현
        System.out.println("7. 스택을 이용한 큐");
        QueueFromStacks queue1 = new QueueFromStacks();
        queue1.push(1);
        queue1.push(2);
        System.out.println("Pop: " + queue1.pop());
        System.out.println();

        // 8. 다음 더 큰 원소
        System.out.println("8. 다음 더 큰 원소");
        int[] nums2 = {1, 2, 1};
        System.out.println("배열: " + Arrays.toString(nums2));
        System.out.println("결과: " + Arrays.toString(nextGreaterElement(nums2)));
    }
}
