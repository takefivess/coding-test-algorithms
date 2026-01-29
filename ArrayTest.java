import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

@DisplayName("Array 알고리즘 테스트")
public class ArrayTest {

    @Test
    @DisplayName("배열 교집합 - 공통 요소 찾기")
    void testIntersection() {
        int[] arr1 = {1, 2, 2, 3, 4};
        int[] arr2 = {2, 3, 4, 5, 6};
        List<Integer> result = Array.intersection(arr1, arr2);
        
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("배열 교집합 - 공통 요소 없음")
    void testIntersectionEmpty() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};
        List<Integer> result = Array.intersection(arr1, arr2);
        
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("배열 회전 - 오른쪽으로 2칸 이동")
    void testRotateRight() {
        int[] arr = {1, 2, 3, 4, 5};
        Array.rotateRight(arr, 2);
        
        assertArrayEquals(new int[]{4, 5, 1, 2, 3}, arr);
    }

    @Test
    @DisplayName("배열 회전 - k가 배열 길이보다 클 때")
    void testRotateRightLargeK() {
        int[] arr = {1, 2, 3, 4, 5};
        Array.rotateRight(arr, 7); // 7 % 5 = 2와 같음
        
        assertArrayEquals(new int[]{4, 5, 1, 2, 3}, arr);
    }

    @Test
    @DisplayName("중복 제거 - 원본 순서 유지")
    void testRemoveDuplicates() {
        int[] arr = {1, 2, 2, 3, 3, 3, 4};
        List<Integer> result = Array.removeDuplicates(arr);
        
        assertEquals(4, result.size());
        assertEquals(Arrays.asList(1, 2, 3, 4), result);
    }

    @Test
    @DisplayName("정렬된 두 배열 합치기")
    void testMergeSorted() {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        int[] result = Array.mergeSorted(arr1, arr2);
        
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, result);
    }

    @Test
    @DisplayName("정렬된 두 배열 합치기 - 길이가 다름")
    void testMergeSortedDifferentLength() {
        int[] arr1 = {1, 5, 9};
        int[] arr2 = {2, 3, 4};
        int[] result = Array.mergeSorted(arr1, arr2);
        
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 9}, result);
    }

    @Test
    @DisplayName("부분배열의 최대합 - Kadane's Algorithm")
    void testMaxSubarraySum() {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = Array.maxSubarraySum(arr);
        
        assertEquals(6, result); // [4, -1, 2, 1]
    }

    @Test
    @DisplayName("부분배열의 최대합 - 음수만 있음")
    void testMaxSubarraySumAllNegative() {
        int[] arr = {-5, -2, -8, -1, -4};
        int result = Array.maxSubarraySum(arr);
        
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("두 개의 합 - 타겟 찾기")
    void testTwoSum() {
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        int[] result = Array.twoSum(arr, target);
        
        assertEquals(2, result.length);
        assertTrue((result[0] == 0 && result[1] == 1) || 
                   (result[0] == 1 && result[1] == 0));
    }

    @Test
    @DisplayName("두 개의 합 - 타겟 없음")
    void testTwoSumNotFound() {
        int[] arr = {1, 2, 3};
        int target = 10;
        int[] result = Array.twoSum(arr, target);
        
        assertEquals(0, result.length);
    }

    @Test
    @DisplayName("배열 포함 여부 - 모든 요소가 다른 배열에 포함됨")
    void testContainsAll() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {0, 1, 2, 3, 4};
        
        assertTrue(Array.containsAll(arr2, arr1));
    }

    @Test
    @DisplayName("배열 포함 여부 - 모든 요소가 포함되지 않음")
    void testContainsAllFalse() {
        int[] arr1 = {1, 2, 5};
        int[] arr2 = {0, 1, 2, 3, 4};
        
        assertFalse(Array.containsAll(arr2, arr1));
    }

    @Test
    @DisplayName("K번째 가장 큰 요소")
    void testKthLargest() {
        int[] arr = {3, 2, 1, 5, 6, 4};
        int result = Array.findKthLargest(arr, 2);
        
        assertEquals(5, result);
    }

    @Test
    @DisplayName("빈 배열 처리")
    void testEmptyArray() {
        int[] arr = {};
        List<Integer> result = Array.removeDuplicates(arr);
        
        assertEquals(0, result.size());
    }
}
