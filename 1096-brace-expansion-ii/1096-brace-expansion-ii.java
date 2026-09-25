import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        // Stack holds elements for each nesting level.
        // Each element in stack is an array containing:
        // [0] -> Set<String> currentUnion
        // [1] -> Set<String> currentProduct
        Stack<Set<String>[]> stack = new Stack<>();
        
        // Initialize root level
        stack.push(createLevel());

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '{') {
                // Start a new nested level
                stack.push(createLevel());
            } else if (c == '}') {
                // Pop the current level and consolidate its result
                Set<String>[] current = stack.pop();
                Set<String> groupResult = new HashSet<>(current[0]);
                groupResult.addAll(current[1]); // union of current[0] and current[1]

                // Multiply into top level's product set
                Set<String>[] top = stack.peek();
                top[1] = multiply(top[1], groupResult);
            } else if (c == ',') {
                // Flush product set into union set, reset product set
                Set<String>[] top = stack.peek();
                top[0].addAll(top[1]);
                top[1] = new HashSet<>(Collections.singletonList(""));
            } else {
                // Lowercase letter: multiply directly into top level product set
                Set<String>[] top = stack.peek();
                Set<String> letterSet = new HashSet<>(Collections.singletonList(String.valueOf(c)));
                top[1] = multiply(top[1], letterSet);
            }
        }

        // Final result at root level
        Set<String>[] root = stack.peek();
        Set<String> resultSet = new HashSet<>(root[0]);
        resultSet.addAll(root[1]);

        // Convert to sorted list
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    // Helper to initialize [unionSet, productSet] where productSet starts with {""}
    @SuppressWarnings("unchecked")
    private Set<String>[] createLevel() {
        Set<String>[] level = new Set[2];
        level[0] = new HashSet<>();
        level[1] = new HashSet<>(Collections.singletonList(""));
        return level;
    }

    // Helper to perform Cartesian product (concatenation) between two sets
    private Set<String> multiply(Set<String> setA, Set<String> setB) {
        Set<String> result = new HashSet<>();
        for (String a : setA) {
            for (String b : setB) {
                result.add(a + b);
            }
        }
        return result;
    }
}