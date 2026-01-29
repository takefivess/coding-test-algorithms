import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Math 알고리즘 테스트")
public class MathTest {

    @Test
    @DisplayName("최대공약수 - 정상 케이스")
    void testGCD() {
        assertEquals(12, Math.gcd(48, 36));
        assertEquals(5, Math.gcd(15, 25));
        assertEquals(1, Math.gcd(7, 13));
    }

    @Test
    @DisplayName("최대공약수 - 같은 수")
    void testGCDSameNumber() {
        assertEquals(10, Math.gcd(10, 10));
    }

    @Test
    @DisplayName("최대공약수 - 한쪽이 0")
    void testGCDWithZero() {
        assertEquals(5, Math.gcd(5, 0));
        assertEquals(5, Math.gcd(0, 5));
    }

    @Test
    @DisplayName("최소공배수")
    void testLCM() {
        assertEquals(60, Math.lcm(12, 20));
        assertEquals(15, Math.lcm(3, 5));
        assertEquals(24, Math.lcm(8, 12));
    }

    @Test
    @DisplayName("최소공배수 - 같은 수")
    void testLCMSameNumber() {
        assertEquals(10, Math.lcm(10, 10));
    }

    @Test
    @DisplayName("소수 판별 - 소수")
    void testIsPrime() {
        assertTrue(Math.isPrime(2));
        assertTrue(Math.isPrime(13));
        assertTrue(Math.isPrime(97));
    }

    @Test
    @DisplayName("소수 판별 - 소수 아님")
    void testIsNotPrime() {
        assertFalse(Math.isPrime(4));
        assertFalse(Math.isPrime(15));
        assertFalse(Math.isPrime(100));
    }

    @Test
    @DisplayName("소수 판별 - 경계값")
    void testIsPrimeBoundary() {
        assertFalse(Math.isPrime(0));
        assertFalse(Math.isPrime(1));
    }

    @Test
    @DisplayName("에라토스테네스의 체 - 10 이하 소수")
    void testSieveOfEratosthenes() {
        boolean[] result = Math.sieveOfEratosthenes(10);
        
        assertTrue(result[2]);
        assertTrue(result[3]);
        assertTrue(result[5]);
        assertTrue(result[7]);
        assertFalse(result[0]);
        assertFalse(result[1]);
        assertFalse(result[4]);
    }

    @Test
    @DisplayName("팩토리얼")
    void testFactorial() {
        assertEquals(1, Math.factorial(0));
        assertEquals(1, Math.factorial(1));
        assertEquals(120, Math.factorial(5));
        assertEquals(3628800, Math.factorial(10));
    }

    @Test
    @DisplayName("조합 C(n, r)")
    void testCombination() {
        assertEquals(1, Math.combination(5, 0));
        assertEquals(5, Math.combination(5, 1));
        assertEquals(10, Math.combination(5, 2));
        assertEquals(10, Math.combination(5, 3));
    }

    @Test
    @DisplayName("순열 P(n, r)")
    void testPermutation() {
        assertEquals(1, Math.permutation(5, 0));
        assertEquals(5, Math.permutation(5, 1));
        assertEquals(20, Math.permutation(5, 2));
        assertEquals(60, Math.permutation(5, 3));
    }

    @Test
    @DisplayName("거듭제곱")
    void testPower() {
        assertEquals(8, Math.power(2, 3));
        assertEquals(1, Math.power(5, 0));
        assertEquals(16, Math.power(2, 4));
    }

    @Test
    @DisplayName("거듭제곱 - 음수 지수")
    void testPowerNegative() {
        assertEquals(0.25, Math.power(2, -2), 0.0001);
        assertEquals(0.5, Math.power(2, -1), 0.0001);
    }

    @Test
    @DisplayName("최댓값")
    void testMax() {
        assertEquals(5, Math.max(3, 5));
        assertEquals(10, Math.max(10, 10));
        assertEquals(7, Math.max(7, -3));
    }

    @Test
    @DisplayName("최솟값")
    void testMin() {
        assertEquals(3, Math.min(3, 5));
        assertEquals(10, Math.min(10, 10));
        assertEquals(-3, Math.min(7, -3));
    }

    @Test
    @DisplayName("절댓값")
    void testAbs() {
        assertEquals(5, Math.abs(-5));
        assertEquals(0, Math.abs(0));
        assertEquals(10, Math.abs(10));
    }

    @Test
    @DisplayName("완전제곱수 판별")
    void testIsPerfectSquare() {
        assertTrue(Math.isPerfectSquare(1));
        assertTrue(Math.isPerfectSquare(4));
        assertTrue(Math.isPerfectSquare(9));
        assertTrue(Math.isPerfectSquare(16));
    }

    @Test
    @DisplayName("완전제곱수 판별 - 아님")
    void testIsNotPerfectSquare() {
        assertFalse(Math.isPerfectSquare(3));
        assertFalse(Math.isPerfectSquare(5));
        assertFalse(Math.isPerfectSquare(10));
    }

    @Test
    @DisplayName("피보나치 수열")
    void testFibonacci() {
        assertEquals(0, Math.fibonacci(0));
        assertEquals(1, Math.fibonacci(1));
        assertEquals(1, Math.fibonacci(2));
        assertEquals(5, Math.fibonacci(5));
        assertEquals(21, Math.fibonacci(8));
    }

    @Test
    @DisplayName("자릿수 합계")
    void testDigitSum() {
        assertEquals(6, Math.digitSum(123));
        assertEquals(15, Math.digitSum(555));
        assertEquals(1, Math.digitSum(1000));
    }
}
