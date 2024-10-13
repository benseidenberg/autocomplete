import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null) {
            throw new NullPointerException("array can't be null");
        }
        else if (key == null) {
            throw new NullPointerException("key can't be null");
        }
        else if (comparator == null) {
            throw new NullPointerException(" comparator can't be null.");
        }
        // initial bounds
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Calculate the middle index
            int mid = lo + (hi - lo) / 2;

            // Compare mid element with the key
            if (comparator.compare(a[mid], key) > 0) {
                // If mid element > key, search left half
                hi = mid - 1;
            }
            else if (comparator.compare(a[mid], key) < 0) {
                // If mid element < key, search right half
                lo = mid + 1;
            }
            else {
                // If mid element == key, find first occurrence
                while (mid >= 0 && comparator.compare(a[mid], key) == 0) {
                    mid--;
                }
                // return index of first occurrence (+1 because mid went one step too far)
                return mid + 1;
            }
        }
        return -1; // if key not found
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException("Array, key, or comparator cannot be null.");
        }

        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // Adjust search space based on comparison
            if (comparator.compare(a[mid], key) > 0) hi = mid - 1;
            else if (comparator.compare(a[mid], key) < 0) lo = mid + 1;
            else {
                // Move forward to find the last occurrence
                while (mid < a.length && comparator.compare(a[mid], key) == 0) {
                    mid++;
                }
                return mid - 1; // Return last occurrence index
            }
        }
        return -1; // if key not found
    }

    public static void main(String[] args) {
        // Define a sample array of strings
        String[] terms = {
                "apple", "banana", "banana", "cherry", "date", "date", "date", "orange"
        };

        // Create a Comparator for comparing strings lexicographically
        Comparator<String> stringComparator = Comparator.naturalOrder();

        System.out.println("Testing firstIndexOf:");
        System.out.println(testFirstIndexOf(terms, "banana", stringComparator, 1));
        System.out.println(testFirstIndexOf(terms, "date", stringComparator, 4));
        System.out.println(testFirstIndexOf(terms, "grape", stringComparator, -1));
        System.out.println(testFirstIndexOf(terms, "apple", stringComparator, 0));
        System.out.println(testFirstIndexOf(terms, "orange", stringComparator, 7));


        System.out.println("\nTesting lastIndexOf:");
        System.out.println(testLastIndexOf(terms, "banana", stringComparator, 2));
        System.out.println(testLastIndexOf(terms, "date", stringComparator, 6));
        System.out.println(testLastIndexOf(terms, "grape", stringComparator, -1));
        System.out.println(testLastIndexOf(terms, "apple", stringComparator, 0));
        System.out.println(testLastIndexOf(terms, "orange", stringComparator, 7));
    }

    // Helper function to test firstIndexOf
    public static boolean testFirstIndexOf(String[] array, String key,
                                           Comparator<String> comparator, int expected) {
        int result = firstIndexOf(array, key, comparator);
        System.out.printf("firstIndexOf('%s') = %d (Expected: %d) -> %s\n", key, result, expected,
                          result == expected ? "PASS" : "FAIL");
        return result == expected;
    }

    // Helper function to test lastIndexOf
    public static boolean testLastIndexOf(String[] array, String key, Comparator<String> comparator,
                                          int expected) {
        int result = lastIndexOf(array, key, comparator);
        System.out.printf("lastIndexOf('%s') = %d (Expected: %d) -> %s\n", key, result, expected,
                          result == expected ? "PASS" : "FAIL");
        return result == expected;
    }
}
