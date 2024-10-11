import java.util.Comparator;

public class BinarySearchDeluxe {
    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        int lo = 0;
        int hi = a.length - 1;
        int result = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = comparator.compare(a[mid], key);

            if (cmp < 0) {
                lo = mid + 1;  // Key is in the right half
            } else if (cmp > 0) {
                hi = mid - 1;  // Key is in the left half
            } else {
                // Found the key, but continue searching to the left
                result = mid;  // Store the index
                hi = mid - 1;  // Move the upper bound left to find first occurrence
            }
        }
        return result;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        int lo = 0;                       // Start of the search range
        int hi = a.length - 1;            // End of the search range
        int result = -1;                    // Initialize the result to -1 (if key isn't found)

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;  // Calculate the middle index
            int cmp = comparator.compare(a[mid], key);  // Compare the middle element with the key

            if (cmp < 0) {
                lo = mid + 1;  // If key is larger, narrow search to the right half
            } else if (cmp > 0) {
                hi = mid - 1;  // If key is smaller, narrow search to the left half
            } else {
                // If the key matches, we have found one occurrence of the key
                result = mid;    // Store the current index
                lo = mid + 1;   // But continue searching to the right for the last occurrence
            }
        }
        return result;
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Example test case using Strings
        String[] terms = {"apple", "banana", "banana", "banana", "cherry", "date"};

        Comparator<String> comparator = String::compareTo;

        // Test firstIndexOf
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, "banana", comparator);
        System.out.println("First index of 'banana': " + firstIndex);  // Should print 1

        // Test lastIndexOf
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, "banana", comparator);
        System.out.println("Last index of 'banana': " + lastIndex);  // Should print 3
    }
}
