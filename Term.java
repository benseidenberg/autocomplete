import java.util.ArrayList;
import java.util.Comparator;

public class Term implements Comparable<Term> {
    private String SearchQuery;
    private double SearchWeight;

    public Term(String SearchQuery, long SearchWeight) {
        // Check if SearchQuery is null or SearchWeight is negative (invalid cases)
        if (SearchQuery == null || SearchWeight < 0) {
            throw new IllegalArgumentException(
                    "Illegal argument: SearchQuery must be non-null, SearchWeight must be non-negative.");
        }
        this.SearchQuery = SearchQuery;
        this.SearchWeight = SearchWeight;
    }

    public static Comparator<Term> byReverseWeightOrder() {
        return new Comparator<Term>() {
            @Override
            public int compare(Term term1, Term term2) {
                return (int) (term2.SearchWeight
                        - term1.SearchWeight);  // Higher SearchWeight comes first
            }
        };
    }

    // Returns a comparator that compares terms by their first r characters
    public static Comparator<Term> byPrefixOrder(final int r) {
        if (r < 0) throw new IllegalArgumentException("Prefix length can't be negative.");
        return new Comparator<Term>() {
            public int compare(final Term term1, final Term term2) {
                // Subset each SearchQuery to the first r characters, or less if SearchQuery is shorter
                String subQuery1;
                if (term1.SearchQuery.length() > r) {
                    subQuery1 = term1.SearchQuery.substring(0, r);
                }
                else {
                    subQuery1 = term1.SearchQuery;
                }
                String subQuery2;
                if (term2.SearchQuery.length() > r) {
                    subQuery2 = term2.SearchQuery.substring(0, r);
                }
                else {
                    subQuery2 = term2.SearchQuery;
                }
                return subQuery1.compareTo(subQuery2);
            }
        };
    }

    @Override
    public int compareTo(Term that) {
        return this.SearchQuery.compareTo(that.SearchQuery);
    }

    @Override
    public String toString() {
        return String.format("%.2f\t%s", this.SearchWeight,
                             this.SearchQuery); // "%.2f" = 2 decimal places
    }

    public static void main(String[] args) {
        Term t1 = new Term("benebnebne", 8);
        Term t2 = new Term("csciscsicsisc", 11);
        Term t3 = new Term("abab", 20);
        Term t4 = new Term("baab", 9);
        ArrayList<Term> arr = new ArrayList<>();
        arr.add(t1);
        arr.add(t2);
        arr.add(t3);
        arr.add(t4);
        arr.sort(byPrefixOrder(2));
        for (Term tmp : arr) {
            System.out.println(tmp.SearchQuery);
        }
        arr.sort(byReverseWeightOrder());
        for (Term tmp : arr) {
            System.out.println(tmp.SearchQuery);
        }
    }
}

