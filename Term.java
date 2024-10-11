import java.util.Comparator;

public class Term implements Comparable<Term> {
    private String query;
    private double weight;

    public Term(String query, long weight) {
        // Check if query is null or weight is negative (invalid cases)
        if (query == null || weight < 0) {
            throw new IllegalArgumentException(
                    "Illegal argument: query must be non-null, weight must be non-negative.");
        }
        this.query = query;
        this.weight = weight;
    }

    public static Comparator<Term> byReverseWeightOrder() {
        return new Comparator<Term>() {
            @Override
            public int compare(Term term1, Term term2) {
                return (int) (term2.weight - term1.weight);  // Higher weight comes first
            }
        };
    }

    // Returns a comparator that compares terms by their first r characters
    public static Comparator<Term> byPrefixOrder(final int r) {
        if (r < 0)
            throw new IllegalArgumentException("Prefix length must be non-negative.");
        return new Comparator<Term>() {
            public int compare(final Term term1, final Term term2) {
                // Subset each query to the first r characters, or less if query is shorter
                String subQuery1;
                if (term1.query.length() > r) {
                    subQuery1 = term1.query.substring(0, r);
                }
                else {
                    subQuery1 = term1.query;
                }
                String subQuery2;
                if (term2.query.length() > r) {
                    subQuery2 = term2.query.substring(0, r);
                }
                else {
                    subQuery2 = term2.query;
                }
                return subQuery1.compareTo(subQuery2);
            }
        };
    }

    @Override
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    @Override
    public String toString() {
        return String.format("%.2f\t%s", this.weight, this.query); // "%.2f" = 2 decimal places
    }
}
