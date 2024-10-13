import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public final class Autocomplete {
    private Term[] terms;

    // Constructor to initialize and sort terms array
    public Autocomplete(Term[] terms) {
        if (terms == null) throw new NullPointerException("array can't be null");
        for (Term term : terms) {
            if (term == null) throw new IllegalArgumentException("each term cannot be null");
        }
        this.terms = terms;
        Arrays.sort(this.terms);  // Sort terms in lexicographical order
    }

    // Finds and returns all matching terms for the given prefix
    public Term[] allMatches(String prefix) {
        if (prefix == null) throw new NullPointerException("prefix can't be null");

        // Use binary search to find the first and last indices of matching terms
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0),
                                                         Term.byPrefixOrder(prefix.length()));
        if (firstIndex == -1) return new Term[0];  // No matches

        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0),
                                                       Term.byPrefixOrder(prefix.length()));

        // Copy matching terms into a new array
        Term[] matchTerms = Arrays.copyOfRange(terms, firstIndex, lastIndex + 1);

        // Sort matches in descending order by weight
        Arrays.sort(matchTerms, Term.byReverseWeightOrder());

        return matchTerms;  // Return sorted matches
    }

    // Returns the number of matching terms for the given prefix
    public int numberOfMatches(String prefix) {
        if (prefix == null) throw new NullPointerException("Prefix can't be null");

        // Compute and return the number of matches using first and last index
        return 1 + BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0),
                                                  Term.byPrefixOrder(prefix.length())) -
                BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0),
                                                Term.byPrefixOrder(prefix.length()));
    }

    // Main function for unit testing
    public static void main(String[] args) {

        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}

