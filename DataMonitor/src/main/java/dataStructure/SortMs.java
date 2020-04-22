package dataStructure;

/**
 * @description: 快排序
 * @author: lijiayu
 * @create: 2020-04-20 14:18
 */
public class SortMs {
    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 2, 3, 8, 4, 6, 9, 10, 7};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr
        ) {
            System.out.println(i);
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        //定义基准值、下标i和j
        int base = arr[left];
        int i = left;
        int j = right;

        while (i != j) {
            while (arr[j] >= base && i < j) {
                j--;
            }
            while (arr[i] <= base && i < j) {
                i++;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        //i，j相遇
        arr[left] = arr[i];
        arr[i] = base;
        //
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    public static void MergeSort(){

    }
}