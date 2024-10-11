
import java.util.Comparator;

public class Term implements Comparable<Term> {
    String query = new String();
    long weight = new long;

    public static class byReverseWeightOrder implements Comparator<Term> {
        public int compare(Term o1, Term o2) {

            if (o1.weight > o2.weight) {
                return 1;
            }
            else if (o2.weight > o1.weight) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
    public static class byPrefixOrder implements Comparator<Term> {
        public Comparator<Term> thenComparing(Comparator<? super Term> other) {
            return Comparator.super.thenComparing(other);
        }

    }

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        Term newTerm = new Term(query, weight);

    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new byReverseWeightOrder();
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        return new byPrefixOrder();

    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        public Comparator<Term> thenComparing(Comparator<? super Term> that) {
            return Comparator.super.thenComparing(that);
        }
    }


    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        String weightStringRep = Long.toString(weight);
        String stringRep = weightStringRep + "\t" + query;
        return stringRep;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
