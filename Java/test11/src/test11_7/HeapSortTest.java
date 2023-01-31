package test11_7;

import java.util.Arrays;

public class HeapSortTest {
    public static void main(String[] args) {
        int[] ints = new int[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 1000);
        }
        HeapSort.sortInOrder(ints);
        System.out.println(Arrays.toString(ints));
        HeapSort.sortInReverseOrder(ints);
        System.out.println(Arrays.toString(ints));
    }
}

class HeapSort {
    //正序
    public static void sortInOrder(int[] arr) {
        //从倒数第一个分支点开始向前，将对应子树转换为大顶堆，最后整棵树就会转换为大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            translateToMaxHeap(arr, i, arr.length);
        }
        //此时树的根节点就是数组的最大元素，将其与数组最后一个元素交换，交换完成后将最大元素剔除出树
        //因为根节点发生了变化，所以可能不再是大顶堆了，要重新将树转换成大顶堆。注意：
        //1.因为最大元素已被剔除出树，因此此时的树所对应的数组下标范围应为[0,arr.length-2]
        //2.无需再从倒数第一个分支点开始向前依次调整了，只需调整节点发生了变化的子树。
        //也就是说，只要将根节点调整到正确的位置，整棵树的调整就已经完成
        //重新转换成大顶堆后，此时树的根节点就是数组的第二大元素，将其与数组倒数第二个元素交换，交换完成后将第二大元素剔除出树
        //因为根节点发生了变化，所以可能不再是大顶堆了，要重新将树转换成大顶堆。此时树所对应的数组下标范围应为[0,arr.length-3]
        //如此循环，直到树中只剩下一个节点，该节点就是数组的最小元素
        int temp;
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            translateToMaxHeap(arr, 0, j);
        }
    }

    //将下标index作为根节点，下标maxSize-1作为最后一个节点的子树调整为大顶堆
    public static void translateToMaxHeap(int[] arr, int index, int maxSize) {
        int temp;
        for (int tempIndex = index * 2 + 1; tempIndex < maxSize; tempIndex = tempIndex * 2 + 1) {
            //先找到下标index左右子节点中的最大元素，并将下标赋值给tempIndex
            if (tempIndex + 1 < maxSize && arr[tempIndex + 1] > arr[tempIndex]) {
                tempIndex++;
            }
            //如果arr[tempIndex] > arr[temp]，那么要将这两个元素互换，从而保证大顶堆的特性
            if (arr[tempIndex] > arr[index]) {
                temp = arr[index];
                arr[index] = arr[tempIndex];
                arr[tempIndex] = temp;
                //元素互换后，由于下标tempIndex元素的值发生了变化，那么以它为根节点的子树的结构也发生了变化，可能不再是大顶堆了，需要重新调整
                //因此要将index赋值为tempIndex，循环上述操作，直到无需将下标index与其左右节点互换。此时最初传进来的树就转换成了大顶堆
                index = tempIndex;
            } else {
                //当无需将下标index与其左右节点互换时，为什么能确定其左右子树中的节点都不需要调整了？
                //1.在第一次调整时，因为是从下往上逐层调整的，所以调整上层时，实际下层已经调整好了
                //如果调整上层时没有改变下层的结构，那也就无需调整下层
                //如果调整上层时改变了下层的结构，那就要循环向下调整，直到找到无需调整的子树
                //2.往后的调整中，虽然改变了树的根节点和最后一个节点，但是最后一个节点随后被剔除出树，实际上只改变了根节点
                //得益于第一次调整，接下来的逻辑就和上面差不多
                //如果调整根节点所在子树时没有改变下层的结构，那也就无需调整下层
                //如果调整根节点所在子树时改变了下层的结构，那就要循环向下调整，直到找到无需调整的子树
                break;
            }
        }
    }

    //倒序
    public static void sortInReverseOrder(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            translateToMinHeap(arr, i, arr.length);
        }
        int temp;
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            translateToMinHeap(arr, 0, j);
        }
    }

    //将下标index作为根节点，下标maxSize-1作为最后一个节点的子树调整为小顶堆
    public static void translateToMinHeap(int[] arr, int index, int maxSize) {
        int temp = arr[index];
        for (int tempIndex = index * 2 + 1; tempIndex < maxSize; tempIndex = tempIndex * 2 + 1) {
            if (tempIndex + 1 < maxSize && arr[tempIndex + 1] < arr[tempIndex]) {
                tempIndex++;
            }
            if (arr[tempIndex] < temp) {
                arr[index] = arr[tempIndex];
                arr[tempIndex] = temp;
                index = tempIndex;
            } else {
                break;
            }
        }
    }
}
