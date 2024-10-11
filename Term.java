import java.util.Comparator;

public class Term implements Comparable<Term> {

    private final String searchTerm;
    private final long searchWeight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null || weight < 0) throw new IllegalArgumentException();

        this.searchTerm = query;
        this.searchWeight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new Comparator<Term>() {
            public int compare(Term o1, Term o2) {
                if (o1.searchWeight < o2.searchWeight) {
                    return 1;
                } else if (o1.searchWeight > o2.searchWeight) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) throw new IllegalArgumentException();
        return new Comparator<Term>() {
            public int compare(Term o1, Term o2) {
                String prefix1 = o1.searchTerm.substring(0, Math.min(r, o1.searchTerm.length()));
                String prefix2 = o2.searchTerm.substring(0, Math.min(r, o2.searchTerm.length()));

                for (int i = 0; i < Math.min(prefix1.length(), prefix2.length()); i++) {
                    char c1 = prefix1.charAt(i);
                    char c2 = prefix2.charAt(i);
                    if (c1 < c2) {
                        return -1;
                    } else if (c1 > c2) {
                        return 1;
                    }
                }

                // If both prefixes are identical up to this point, compare by length
                if (prefix1.length() < prefix2.length()) {
                    return -1;
                } else if (prefix1.length() > prefix2.length()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        int minLength = Math.min(this.searchTerm.length(), that.searchTerm.length());

        for (int i = 0; i < minLength; i++) {
            char c1 = this.searchTerm.charAt(i);
            char c2 = that.searchTerm.charAt(i);

            if (c1 < c2) {
                return -1;  // this.searchTerm is lexicographically smaller
            } else if (c1 > c2) {
                return 1;   // this.searchTerm is lexicographically larger
            }
        }

        // If all characters so far are equal, the shorter string should come first
        if (this.searchTerm.length() < that.searchTerm.length()) {
            return -1;
        } else if (this.searchTerm.length() > that.searchTerm.length()) {
            return 1;
        } else {
            return 0;
        }
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return this.searchWeight + "\t" + searchTerm;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Term term1 = new Term("apple", 100);
        Term term2 = new Term("banana", 200);
        Term term3 = new Term("appricot", 50);

        // Test compareTo (lexicographic order)
        System.out.println(term1.compareTo(term2));
        /* should be negative since "apple" < "banana" */

        // Test byReverseWeightOrder
        Comparator<Term> reverseOrder = Term.byReverseWeightOrder();
        System.out.println(reverseOrder.compare(term1, term2));
        /* should be positive since 200 > 100 */

        // Test byPrefixOrder (first 3 characters)
        Comparator<Term> prefixOrder = Term.byPrefixOrder(3);
        System.out.println(prefixOrder.compare(term1, term3));
        /* should be 0 since "app" == "app" */
    }

}
