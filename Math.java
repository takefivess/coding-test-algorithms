import java.util.*;

/**
 * 수학 관련 알고리즘 및 메서드
 */
public class Math {

    // 1. 최대공약수 (GCD - Euclidean Algorithm)
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 2. 최소공배수 (LCM)
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    // 3. 소수 확인
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // 4. 에라토스테네스의 체 (소수 찾기)
    public static List<Integer> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        
        for (int i = 2; i * i <= n; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = false;
                }
            }
        }
        
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) primes.add(i);
        }
        return primes;
    }

    // 5. 팩토리얼
    public static long factorial(int n) {
        if (n < 0) return 0;
        if (n <= 1) return 1;
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // 6. 조합 (nCr)
    public static long combination(int n, int r) {
        if (r > n) return 0;
        if (r == 0 || r == n) return 1;
        
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    // 7. 순열 (nPr)
    public static long permutation(int n, int r) {
        if (r > n) return 0;
        return factorial(n) / factorial(n - r);
    }

    // 8. 거듭제곱 (Power)
    public static long power(int base, int exp) {
        long result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result *= base;
            }
            base *= base;
            exp /= 2;
        }
        return result;
    }

    // 9. 모듈러 거듭제곱
    public static long modularPower(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }
        return result;
    }

    // 10. 정수 판별 제곱근
    public static int sqrtInt(int n) {
        if (n == 0) return 0;
        
        long left = 1, right = n;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (mid * mid == n) {
                return (int) mid;
            } else if (mid * mid < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) right;
    }

    // 11. 자릿수 합
    public static int sumOfDigits(int n) {
        int sum = 0;
        n = java.lang.Math.abs(n);
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    // 12. 팰린드롬 숫자
    public static boolean isPalindromeNumber(int n) {
        int original = n;
        int reversed = 0;
        n = java.lang.Math.abs(n);
        
        while (n > 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        
        return original == reversed;
    }

    // 13. 최대공약수 (재귀)
    public static int gcdRecursive(int a, int b) {
        return b == 0 ? a : gcdRecursive(b, a % b);
    }

    // 14. 완전제곱수 확인
    public static boolean isPerfectSquare(int n) {
        if (n < 0) return false;
        int sqrt = sqrtInt(n);
        return sqrt * sqrt == n;
    }

    // ==================== 테스트 코드 ====================
    public static void main(String[] args) {
        System.out.println("===== 수학 알고리즘 테스트 =====\n");

        // 1. GCD
        System.out.println("1. 최대공약수");
        System.out.println("GCD(48, 18): " + gcd(48, 18));
        System.out.println();

        // 2. LCM
        System.out.println("2. 최소공배수");
        System.out.println("LCM(12, 18): " + lcm(12, 18));
        System.out.println();

        // 3. 소수 확인
        System.out.println("3. 소수 확인");
        System.out.println("17은 소수: " + isPrime(17));
        System.out.println("15는 소수: " + isPrime(15));
        System.out.println();

        // 4. 에라토스테네스의 체
        System.out.println("4. 에라토스테네스의 체 (30 이하 소수)");
        System.out.println(sieveOfEratosthenes(30));
        System.out.println();

        // 5. 팩토리얼
        System.out.println("5. 팩토리얼");
        System.out.println("5! = " + factorial(5));
        System.out.println();

        // 6. 조합
        System.out.println("6. 조합 C(5,2)");
        System.out.println("C(5,2) = " + combination(5, 2));
        System.out.println();

        // 7. 순열
        System.out.println("7. 순열 P(5,2)");
        System.out.println("P(5,2) = " + permutation(5, 2));
        System.out.println();

        // 8. 거듭제곱
        System.out.println("8. 거듭제곱 2^10");
        System.out.println("2^10 = " + power(2, 10));
        System.out.println();

        // 9. 모듈러 거듭제곱
        System.out.println("9. 모듈러 거듭제곱 (2^10) % 1000");
        System.out.println("(2^10) % 1000 = " + modularPower(2, 10, 1000));
        System.out.println();

        // 10. 정수 제곱근
        System.out.println("10. 정수 제곱근");
        System.out.println("√36 = " + sqrtInt(36));
        System.out.println("√37 = " + sqrtInt(37));
        System.out.println();

        // 11. 자릿수 합
        System.out.println("11. 자릿수 합");
        System.out.println("12345의 자릿수 합: " + sumOfDigits(12345));
        System.out.println();

        // 12. 팰린드롬 숫자
        System.out.println("12. 팰린드롬 숫자");
        System.out.println("121은 팰린드롬: " + isPalindromeNumber(121));
        System.out.println("123은 팰린드롬: " + isPalindromeNumber(123));
        System.out.println();

        // 13. GCD (재귀)
        System.out.println("13. GCD 재귀");
        System.out.println("GCD(48, 18) = " + gcdRecursive(48, 18));
        System.out.println();

        // 14. 완전제곱수
        System.out.println("14. 완전제곱수");
        System.out.println("16은 완전제곱수: " + isPerfectSquare(16));
        System.out.println("17은 완전제곱수: " + isPerfectSquare(17));
    }
}
