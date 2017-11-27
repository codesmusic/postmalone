import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Hackerrank {

    public static void merge(int[] arr, int left, int right, int mid)
    {
        int arr1 = mid - left + 1;
        int arr2 = right - mid;
        int[] leftArr = new int[arr1];
        int[] rightArr = new int[arr2];
        for(int i = 0; i < arr1; i++)
            leftArr[i] = arr[left + i];
        for(int i = 0; i < arr2; i++)
            rightArr[i] = arr[mid + 1 + i];
        int i = 0, j = 0;
        int k = left;
        while(i < arr1 && j < arr2)
        {
            if(leftArr[i] <= rightArr[j])
            {
                arr[k] = leftArr[i];
                i++;
            }
            else
            {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while(i < arr1)
        {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while(j < arr2)
        {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] arr, int left, int right)
    {
        if(left < right)
        {
            int mid = (left + right ) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, right, mid);
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
        {
            arr[i] = in.nextInt();
        }
        mergeSort(arr, 0, arr.length - 1);
        int min = arr[1] - arr[0];
        for(int i = 1; i < n; i++)
        {
            if(arr[i] - arr[i-1] < min)
            {
                min = arr[i] - arr[i-1];
            }
        }
        for(int i = 1; i < n; i++)
        {
            if(arr[i] - arr[i-1] == min)
            {
                System.out.print(arr[i-1] + " " + arr[i] + " ");
            }
        }
    }
}