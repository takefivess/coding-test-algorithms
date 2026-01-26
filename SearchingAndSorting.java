import java.util.*;

/**
 * 이진 탐색 및 정렬 관련 알고리즘
 */
public class SearchingAndSorting {

    // 1. 이진 탐색 (Binary Search)
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }

    // 2. 회전된 정렬 배열에서 탐색
    public static int searchRotatedArray(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            }
            
            // 왼쪽 절반이 정렬됨
            if (arr[left] <= arr[mid]) {
                if (arr[left] <= target && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } 
            // 오른쪽 절반이 정렬됨
            else {
                if (arr[mid] < target && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }

    // 3. 버블 정렬
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // 4. 선택 정렬
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    // 5. 삽입 정렬
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 6. 병합 정렬
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) return;
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        
        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // 7. 퀵 정렬
    public static void quickSort(int[] arr) {
        if (arr.length == 0) return;
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int pi = partition(arr, left, right);
            quickSortHelper(arr, left, pi - 1);
            quickSortHelper(arr, pi + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;
        
        return i + 1;
    }

    // 8. 범위 찾기
    public static int[] searchRange(int[] arr, int target) {
        int[] result = {-1, -1};
        
        // 첫 등장 찾기
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                result[0] = mid;
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // 마지막 등장 찾기
        if (result[0] != -1) {
            left = result[0];
            right = arr.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] == target) {
                    result[1] = mid;
                    left = mid + 1;
                } else if (arr[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return result;
    }

    // 9. K번째 가장 작은 원소
    public static int findKthSmallest(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k - 1];
    }

    // ==================== 테스트 코드 ====================
    public static void main(String[] args) {
        System.out.println("===== 탐색 및 정렬 테스트 =====\n");

        int[] testArr = {3, 1, 4, 1, 5, 9, 2, 6};

        // 1. 이진 탐색
        System.out.println("1. 이진 탐색");
        int[] sorted = {1, 3, 5, 7, 9, 11};
        System.out.println("배열: " + Arrays.toString(sorted));
        System.out.println("5 위치: " + binarySearch(sorted, 5));
        System.out.println();

        // 2. 회전 배열 탐색
        System.out.println("2. 회전된 배열에서 탐색");
        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("배열: " + Arrays.toString(rotated));
        System.out.println("0 위치: " + searchRotatedArray(rotated, 0));
        System.out.println();

        // 3. 버블 정렬
        System.out.println("3. 버블 정렬");
        int[] arr1 = testArr.clone();
        System.out.println("원본: " + Arrays.toString(arr1));
        bubbleSort(arr1);
        System.out.println("정렬: " + Arrays.toString(arr1));
        System.out.println();

        // 4. 선택 정렬
        System.out.println("4. 선택 정렬");
        int[] arr2 = testArr.clone();
        selectionSort(arr2);
        System.out.println("정렬: " + Arrays.toString(arr2));
        System.out.println();

        // 5. 삽입 정렬
        System.out.println("5. 삽입 정렬");
        int[] arr3 = testArr.clone();
        insertionSort(arr3);
        System.out.println("정렬: " + Arrays.toString(arr3));
        System.out.println();

        // 6. 병합 정렬
        System.out.println("6. 병합 정렬");
        int[] arr4 = testArr.clone();
        mergeSort(arr4);
        System.out.println("정렬: " + Arrays.toString(arr4));
        System.out.println();

        // 7. 퀵 정렬
        System.out.println("7. 퀵 정렬");
        int[] arr5 = testArr.clone();
        quickSort(arr5);
        System.out.println("정렬: " + Arrays.toString(arr5));
        System.out.println();

        // 8. 범위 찾기
        System.out.println("8. 범위 찾기");
        int[] arr6 = {5, 7, 7, 8, 8, 10};
        System.out.println("배열: " + Arrays.toString(arr6));
        System.out.println("8의 범위: " + Arrays.toString(searchRange(arr6, 8)));
        System.out.println();

        // 9. K번째 가장 작은 원소
        System.out.println("9. K번째 가장 작은 원소");
        int[] arr7 = {3, 2, 1, 5, 6, 4};
        System.out.println("배열: " + Arrays.toString(arr7));
        System.out.println("3번째 작은 원소: " + findKthSmallest(arr7, 3));
    }
}
