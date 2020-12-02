//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Sort.java
//  Sorting algorithms.
//  Uses merge sort from textbook chapter 18.
//  Visual Studio Code

import java.util.Comparator;

public class Sort
{
    /**
     * Returns a sorted array of elements from a Query.
     * @param <T> element type
     * @param query the query collection of elements to sort
     * @param comparator used to compare elements during sort
     * @return
     */
    public static <T> T[] mergeSort(
        Class<T> clazz,
        Query<T> query,
        Comparator<T> comparator
    ) {
        T[] a = query.toArrayT(clazz);
        mergeSort(a, comparator);
        return a;
    }

    /**
     * Sorts an array of elements using a Comparator.
     * @param <T> element type
     * @param query the query collection of elements to sort
     * @param comparator used to compare elements during sort
     */
    public static <T> void mergeSort(
        T[] data,
        Comparator<T> comparator
    ) {
        mergeSort(data, comparator, 0, data.length - 1);
    }

    /**
     * Recursive merge sort.
     * @param <T>
     * @param data
     * @param comparator
     * @param min
     * @param max
     */
    private static <T> void mergeSort(
        T[] data,
        Comparator<T> comparator,
        int min,
        int max
    ) {
        if (min < max) {
            int mid = (min + max) / 2;
            mergeSort(data, comparator, min, mid);
            mergeSort(data, comparator, mid + 1, max);
            merge(data, comparator, min, mid, max);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void merge(
        T[] data,
        Comparator<T> comparator,
        int first,
        int mid,
        int last
    ) {
        T[] temp = (T[])(new Object[data.length]);

        int first1 = first;
        int last1  = mid;
        int first2 = mid + 1;
        int last2  = last;
        int index  = first1;

        while (first1 <= last1 && first2 <= last2) {
            if (comparator.compare(data[first1], data[first2]) < 0) {
                temp[index] = data[first1];
                ++first1;
            } else {
                temp[index] = data[first2];
                ++first2;
            }

            ++index;
        }

        //  Copy remaining elements from first sub-array
        while (first1 <= last1) {
            temp[index] = data[first1];
            ++first1;
            ++index;
        }

        //  Copy remaining elements from second sub-array
        while (first2 <= last2) {
            temp[index] = data[first2];
            ++first2;
            ++index;
        }

        //  Copy merged data to original array
        for (index = first; index <= last; index++) {
            data[index] = temp[index];
        }
    }
}
