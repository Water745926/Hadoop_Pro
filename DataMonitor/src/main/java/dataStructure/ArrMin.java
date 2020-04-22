package dataStructure;

/**
 * @description: 计算旋转数组最小值
 * @author: lijiayu
 * @create: 2020-04-22 15:16
 */
public class ArrMin {
    public static void main(String[] args) {
        int[] arr = {7, 8, 9, 1, 2, 3, 4, 5, 6};
        System.out.println(arrmin(arr));
    }

    // 求旋转数组最小值
    public static int arrmin(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            //中间值小于右边,最小值在左侧或者中间，
            if (arr[mid] < arr[right]) {
                right = mid;
            }
            //中间值大于右边，说明最小值在右侧，同时中间值不是最小值，所以中间值+1
            else if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                //中间值小于右边
                return arr[mid];
            }
        }
        return arr[left];
    }
}