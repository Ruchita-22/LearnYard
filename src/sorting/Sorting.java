package sorting;

import helper.Helper;

public class Sorting {

    private void bubblesort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                Helper.swap(arr, j, j+1);
            }
        }
    }

}
