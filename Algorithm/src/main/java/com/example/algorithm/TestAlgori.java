package com.example.algorithm;

/**
 * class description
 *
 * @author garry
 * @version 1.0.0
 * @date 2018-03-09 15:20
 */
public class TestAlgori {

    public static void main(String[] args) {
//        int[] arr = new int[100];
//        for (int i = 0; i < 100; i++) {
//            arr[i] = i;
//        }
//        System.out.println(binarySearch(arr, 93));
//        System.out.println(binarySearch(arr, 93, 0, arr.length - 1));
//        selectSort();
//        System.out.println();
        bubblingSort();
//        System.out.println();

        int[] arr = new int[]{3, 7, 2, 1, 5, 9, 8, 6, 4};
        quickSort3(arr, 0, arr.length - 1);
//        insertSort(arr, arr.length);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    /**
     * 二分查找，循环实现
     * 在数组aar中查找x
     *
     * @param aar
     * @param x
     * @return -1 没有查找到
     */
    private static int binarySearch(int[] aar, int x) {
        int low = 0;
        int hight = aar.length - 1;
        while (low <= hight) {
            System.out.println(low + " --- " + hight);
            int middle = (low + hight) / 2;
            if (x == aar[middle]) {
                return x;
            } else if (x < aar[middle]) {
                hight = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找，递归实现
     *
     * @param aar
     * @param x
     * @param startIndex
     * @param endIndex
     * @return
     */
    private static int binarySearch(int[] aar, int x, int startIndex, int endIndex) {
        int middleIndex = (startIndex + endIndex) / 2;
        System.out.println(startIndex + " --- " + endIndex);
        if (x < aar[startIndex] || x > aar[endIndex]) {
            return -1;
        }
        if (x < aar[middleIndex]) {
            return binarySearch(aar, x, startIndex, middleIndex - 1);
        } else if (x > aar[middleIndex]) {
            return binarySearch(aar, x, middleIndex + 1, endIndex);
        } else {
            return aar[middleIndex];
        }
    }

    /**
     * 选择排序
     * 从小到大排序数组
     */
    private static void selectSort() {
        int[] arr = new int[]{1, 3, 53, 5, 345, 6, 34, 762, 57, 234, 747, 24, 576};
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] >= arr[j]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    /**
     * 冒泡排序
     * 从小到大排序数组
     */
    private static void bubblingSort() {
        int[] arr = new int[]{1, 3, 53, 5, 345, 6, 34, 762, 57, 234, 747, 24, 576};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                int temp;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    /**
     * 快速排序，普通版，交换次数多
     *
     * @param arr
     * @param low
     * @param high
     */
    public void quickSort(int arr[], int low, int high) {
        int l = low;
        int h = high;
        int povit = arr[low];

        while (l < h) {
            while (l < h && arr[h] >= povit)
                h--;
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                l++;
            }

            while (l < h && arr[l] <= povit)
                l++;

            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                h--;
            }
        }
        if (l > low) quickSort(arr, low, l - 1);
        if (h < high) quickSort(arr, l + 1, high);
    }

    /**
     * 快速排序，效率高，交换次数少
     *
     * @param arr
     * @param low
     * @param high
     */
    private static void quickSort2(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int i = low;
        int j = high;
        int k = arr[low];
        while (i < j) {
            while (i < j && arr[j] >= k) {
                j--;
            }
            //交换数据，由于arr[i]已经给了k，所以直接赋值arr[j]，减少交换次数
            arr[i] = arr[j];
            while (i < j && arr[i] <= k) {
                i++;
            }
            arr[j] = arr[i];
        }
        //当在当组内找完一遍以后就把中间数k回归, i==j
        arr[i] = k;
        //最后用同样的方式对分出来的左边的小组进行同上的做法
        quickSort2(arr, low, i - 1);
        quickSort2(arr, j + 1, high);
    }

    /**
     * 一次快速排序, 返回基准点索引
     *
     * @param array 数组
     * @param lo    数组的前下标
     * @param hi    数组的后下标
     * @return key的下标index，也就是分片的间隔点
     */
    public static int partition(int[] array, int lo, int hi) {
        int k = array[lo];
        while (lo < hi) {
            while (lo < hi && array[hi] >= k) {
                hi--;
            }
            array[lo] = array[hi];
            while (lo < hi && array[lo] <= k) {
                lo++;
            }
            array[hi] = array[lo];
        }
        array[lo] = k;
        return lo;
    }

    private static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    /**
     * 三数取中优化, 返回基准点索引
     *
     * @param array 数组
     * @param low   数组的前下标
     * @param high  数组的后下标
     * @return key的下标index，也就是分片的间隔点
     */
    public static int partition2(int[] array, int low, int high) {
        int middle = (low + high) / 2;
        if (array[middle] > array[high]) {
            swap(array[middle], array[high]);
        }
        if (array[low] > array[high]) {
            swap(array[low], array[high]);
        }
        //使得low存放中间的值
        if (array[middle] > array[low]) {
            swap(array[middle], array[low]);
        }
        int k = array[low];
        while (low < high) {
            while (low < high && array[high] >= k) {
                high--;
            }
            array[low] = array[high];
            while (low < high && array[low] <= k) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = k;
        return low;
    }

    /**
     * 快速排序
     *
     * @param array
     * @param lo
     * @param hi
     */
    public static void quickSort3(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int k = partition2(array, lo, hi);
        quickSort3(array, lo, k - 1);
        quickSort3(array, k + 1, hi);
    }

    /**
     * 插入排序
     * 适合小数组排序，length < 7
     *
     * @param arr
     * @param n
     */
    public static void insertSort(int[] arr, int n) {
        int i, j, temp;
        for (i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                temp = arr[i];
                for (j = i - 1; j >= 0 && arr[j] > temp; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[j + 1] = temp;
            }
        }
    }
}
