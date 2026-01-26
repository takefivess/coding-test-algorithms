import java.util.*;

/**
 * 동적 프로그래밍 (Dynamic Programming) 알고리즘
 */
public class DynamicProgramming {

    // 1. 피보나치 (메모이제이션)
    public static int fibonacci(int n, Map<Integer, Integer> memo) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        
        int result = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    // 2. 피보나치 (타뷰레이션)
    public static int fibonacciTab(int n) {
        if (n <= 1) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }

    // 3. 코인 교환 (최소 개수)
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // 4. 배낭 문제 (0/1 Knapsack)
    public static int knapsack(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        values[i - 1] + dp[i - 1][w - weights[i - 1]],
                        dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        return dp[n][capacity];
    }

    // 5. 부분집합의 합 (Subset Sum)
    public static boolean subsetSum(int[] arr, int sum) {
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        
        for (int num : arr) {
            for (int i = sum; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        
        return dp[sum];
    }

    // 6. 부분집합의 개수
    public static int countSubsetSum(int[] arr, int sum) {
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        
        for (int num : arr) {
            for (int i = sum; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        
        return dp[sum];
    }

    // 7. 최대 곱 부분배열
    public static int maxProductSubarray(int[] arr) {
        int n = arr.length;
        int maxProd = arr[0];
        int minProd = arr[0];
        int result = arr[0];
        
        for (int i = 1; i < n; i++) {
            int temp = maxProd;
            maxProd = Math.max(arr[i], Math.max(arr[i] * maxProd, arr[i] * minProd));
            minProd = Math.min(arr[i], Math.min(arr[i] * temp, arr[i] * minProd));
            result = Math.max(result, maxProd);
        }
        
        return result;
    }

    // 8. 계단 오르기 (경로의 수)
    public static int climbStairs(int n) {
        if (n <= 1) return 1;
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }

    // 9. 문자열 유사성 (Edit Distance / Levenshtein Distance)
    public static int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }
        
        return dp[m][n];
    }

    // 10. 최장 공통 부분수열 (LCS)
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }

    // 11. 최장 증가 부분수열 (LIS)
    public static int longestIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        return Arrays.stream(dp).max().orElse(1);
    }

    // 12. 로드 아일랜드 (House Robber)
    public static int rob(int[] houses) {
        if (houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];
        
        int[] dp = new int[houses.length];
        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);
        
        for (int i = 2; i < houses.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + houses[i]);
        }
        
        return dp[houses.length - 1];
    }

    // ==================== 테스트 코드 ====================
    public static void main(String[] args) {
        System.out.println("===== 동적 프로그래밍 테스트 =====\n");

        // 1. 피보나치
        System.out.println("1. 피보나치(10)");
        System.out.println("메모이제이션: " + fibonacci(10, new HashMap<>()));
        System.out.println("타뷰레이션: " + fibonacciTab(10));
        System.out.println();

        // 2. 코인 교환
        System.out.println("2. 코인 교환");
        int[] coins = {1, 2, 5};
        System.out.println("코인 " + Arrays.toString(coins) + ", 금액 5: " + coinChange(coins, 5));
        System.out.println();

        // 3. 배낭 문제
        System.out.println("3. 0/1 배낭 문제");
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        System.out.println("용량 8: " + knapsack(8, weights, values));
        System.out.println();

        // 4. 부분집합의 합
        System.out.println("4. 부분집합의 합");
        int[] arr = {3, 34, 4, 12, 5, 2};
        System.out.println("배열 " + Arrays.toString(arr) + ", 합 9: " + subsetSum(arr, 9));
        System.out.println();

        // 5. 부분집합 개수
        System.out.println("5. 부분집합 개수");
        System.out.println("배열 " + Arrays.toString(arr) + ", 합 9: " + countSubsetSum(arr, 9));
        System.out.println();

        // 6. 최대 곱 부분배열
        System.out.println("6. 최대 곱 부분배열");
        int[] arr2 = {2, 3, -2, 4};
        System.out.println("배열 " + Arrays.toString(arr2) + ": " + maxProductSubarray(arr2));
        System.out.println();

        // 7. 계단 오르기
        System.out.println("7. 계단 오르기 (n=5)");
        System.out.println("경로의 수: " + climbStairs(5));
        System.out.println();

        // 8. Edit Distance
        System.out.println("8. 문자열 유사성 (Edit Distance)");
        System.out.println("'horse' -> 'ros': " + editDistance("horse", "ros"));
        System.out.println();

        // 9. LCS
        System.out.println("9. 최장 공통 부분수열");
        System.out.println("'abc' 와 'abc': " + longestCommonSubsequence("abc", "abc"));
        System.out.println();

        // 10. LIS
        System.out.println("10. 최장 증가 부분수열");
        int[] arr3 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("배열 " + Arrays.toString(arr3) + ": " + longestIncreasingSubsequence(arr3));
        System.out.println();

        // 11. House Robber
        System.out.println("11. 로드 아일랜드");
        int[] houses = {1, 2, 3, 1};
        System.out.println("금액 " + Arrays.toString(houses) + ": " + rob(houses));
    }
}
