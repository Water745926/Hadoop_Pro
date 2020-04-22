package dataStructure;


/**
 * @description:
 * @author: lijiayu
 * @create: 2020-04-13 13:49
 */
public class Test_Main {

    public static void main(String[] args) {
        MyList list= new MyList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(39);
        System.out.println(list);
        MyList nlist = list.convers();
        System.out.println(nlist);

        //二叉树后序遍历
        MyTree mt = new MyTree();
        mt.add(2);
        mt.add(1);
        mt.add(3);
        mt.print();

        //快速
        int[] arr = {1,6,4,5,2,8,5,9,1,10};
        quickSort(arr,0,arr.length-1);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }

    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        //定义变量保存基准数下标、指针i和j
        int base = arr[left];
        int i = left;
        int j = right;

        // 开始排序：
        while (i != j) {
            //找到小的停止
            while (arr[j] >= base && i < j) {
                j--;
            }
            //找到大的停止
            while (arr[i] <= base && i < j) {
                i++;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        //相遇
        arr[left] = arr[i];
        arr[i] = base;
        //再次交换子区间
        quickSort(arr, left, i - 1);
        quickSort(arr, i+1, right);
    }
}