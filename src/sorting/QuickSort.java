package sorting;

import helper.Helper;

public class QuickSort {

    private int rearrange(int arr[]) {
        int i = 1, j = arr.length-1;
        while( i <= j) {
            if(arr[i] <=  arr[0])   i++;
            else if(arr[j] > arr[0])    j--;
            else {
                Helper.swap(arr, i, j);
                i++; j--;
            }
        }
        Helper.swap(arr, i-1, 0);
        return i-1;
    }
    private int rearrangeAPartOfArray(int arr[], int l, int r) {
        int i = l+1, j = r;
        while( i <= j) {
            if(arr[i] <=  arr[l])   i++;
            else if(arr[j] > arr[l])    j--;
            else {
                Helper.swap(arr, i, j);
                i++; j--;
            }
        }
        Helper.swap(arr, l, i-1);
        return i-1;
    }
    private void quickSort(int arr[], int l, int r) {
        if(l>=r)    return;
        int idx = rearrangeAPartOfArray(arr, l, r);
        quickSort(arr, l, idx-1);
        quickSort(arr, idx+1, r);
    }
}
