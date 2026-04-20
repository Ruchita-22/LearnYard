package sorting;

public class MergeSort {

    private int[] merge2SortedArray(int arr1[], int arr2[]) {

        int i=0, j=0, k = 0;
        int res[] = new int[arr1.length + arr2.length];

        while(i < arr1.length && j < arr2.length) {
            if(arr1[i] <= arr2[j]) {
                res[k++] = arr1[i++];
            } else {
                res[k++] = arr2[j++];
            }
        }
        while(i < arr1.length) {
            res[k++] = arr1[i++];
        }
        while(j < arr2.length) {
            res[k++] = arr2[j++];
        }
        return res;
    }

    private int[] merge2SortedPartOfArray(int arr[], int s, int m, int e) {

        int i=s, j=m+1, k = 0;
        int res[] = new int[e-s+1];

        while(i <= m && j <= e) {
            if(arr[i] <= arr[j]) {
                res[k++] = arr[i++];
            } else {
                res[k++] = arr[j++];
            }
        }
        while(i <= m) {
            res[k++] = arr[i++];
        }
        while(j <= e) {
            res[k++] = arr[j++];
        }
        return res;
    }

    private void mergeSort(int arr[], int s, int e) {
        if(s == e) return;
        int m = s + (e-s)/2;
        mergeSort(arr, s, m);
        mergeSort(arr, m+1, e);
        merge2SortedPartOfArray(arr, s, m, e);
    }
}
