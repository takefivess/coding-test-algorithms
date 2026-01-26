import java.util.*;

/**
 * 배열 및 리스트 관련 자주 사용되는 메서드와 알고리즘
 */
public class Array {

    // 1. 배열의 최댓값, 최솟값, 합
    public static void arrayStats(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);
        int min = Arrays.stream(arr).min().orElse(Integer.MAX_VALUE);
        int sum = Arrays.stream(arr).sum();
        double avg = Arrays.stream(arr).average().orElse(0.0);
        
        System.out.println("Max: " + max + ", Min: " + min + ", Sum: " + sum + ", Avg: " + avg);
    }

    // 2. 두 배열의 교집합
    public static List<Integer> intersection(int[] arr1, int[] arr2) {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(
            Arrays.stream(arr1).boxed().toArray(Integer[]::new)));
        Set<Integer> result = new HashSet<>();
        
        for (int num : arr2) {
            if (set1.contains(num)) {
                result.add(num);
            }
        }
        return new ArrayList<>(result);
    }

    // 3. 배열 회전 (오른쪽으로 k칸 이동)
    public static void rotateRight(int[] arr, int k) {
        if (arr.length == 0) return;
        k = k % arr.length;
        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // 4. 중복 제거
    public static List<Integer> removeDuplicates(int[] arr) {
        return new ArrayList<>(new LinkedHashSet<>(
            Arrays.asList(Arrays.stream(arr).boxed().toArray(Integer[]::new))));
    }

    // 5. 두 배열 합치기 (정렬된 배열)
    public static int[] mergeSorted(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        
        while (i < arr1.length && j < arr2.length) {
            result[k++] = arr1[i] <= arr2[j] ? arr1[i++] : arr2[j++];
        }
        
        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }
        
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }
        
        return result;
    }

    // 6. 부분배열의 최대합 (Kadane's Algorithm)
    public static int maxSubarraySum(int[] arr) {
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }

    // 7. 특정 원소를 목표로 이동
    public static void moveElement(int[] arr, int target) {
        int write = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != target) {
                arr[write++] = arr[i];
            }
        }
        while (write < arr.length) {
            arr[write++] = target;
        }
    }

    // 8. 빈도수 계산
    public static Map<Integer, Integer> frequencyMap(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        return freq;
    }

    // ==================== 테스트 코드 ====================
    public static void main(String[] args) {
        System.out.println("===== 배열 메서드 테스트 =====\n");

        // 1. 배열 통계
        int[] arr1 = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println("1. 배열 통계");
        arrayStats(arr1);
        System.out.println();

        // 2. 교집합
        int[] a = {1, 2, 3, 4};
        int[] b = {3, 4, 5, 6};
        System.out.println("2. 교집합: " + intersection(a, b));
        System.out.println();

        // 3. 배열 회전
        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("3. 배열 회전 (오른쪽 2칸)");
        System.out.println("원본: " + Arrays.toString(arr3));
        rotateRight(arr3, 2);
        System.out.println("결과: " + Arrays.toString(arr3));
        System.out.println();

        // 4. 중복 제거
        int[] arr4 = {1, 1, 2, 2, 3, 3, 4};
        System.out.println("4. 중복 제거: " + removeDuplicates(arr4));
        System.out.println();

        // 5. 정렬된 배열 합치기
        int[] sorted1 = {1, 3, 5};
        int[] sorted2 = {2, 4, 6};
        System.out.println("5. 정렬된 배열 합치기: " + Arrays.toString(mergeSorted(sorted1, sorted2)));
        System.out.println();

        // 6. 최대 부분배열 합
        int[] arr6 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("6. 최대 부분배열 합: " + maxSubarraySum(arr6));
        System.out.println();

        // 7. 원소 이동
        int[] arr7 = {1, 2, 0, 3, 0, 4};
        System.out.println("7. 0을 끝으로 이동");
        System.out.println("원본: " + Arrays.toString(arr7));
        moveElement(arr7, 0);
        System.out.println("결과: " + Arrays.toString(arr7));
        System.out.println();

        // 8. 빈도수 계산
        int[] arr8 = {1, 1, 2, 2, 2, 3, 3, 3, 3};
        System.out.println("8. 빈도수: " + frequencyMap(arr8));
    }
}
