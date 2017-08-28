/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countdownnumbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import tokens.*;

/**
 *
 * @author Joeri
 */
public class CountdownRecursive {

    
    // map of all permutations on the amount of numbers used
    // example: {1: [[100],[75],[50],[25]]}
    private final Map<Integer, ArrayList<ArrayList<Integer>>> permutations = new HashMap();
    
    // map of all permutations on the amount of operations used
    // example: {1: [[+],[-],[*],[/]]}
    private final Map<Integer, ArrayList<ArrayList<IToken>>> operationpermutations = new HashMap();
    
    // stats for amount of solutions run through
    int solutioncount = 0;

    public CountdownRecursive(ArrayList<Integer> question) {
        permutateIntegers(new ArrayList(), question);
        operationpermutations.put(0,new ArrayList());
        for (int i = 1; i < 6; i++) {
            permutateOperations(new ArrayList(),i);            
        }

        System.out.println("");
    }

    // fills permutations according to the question
    private void permutateIntegers(ArrayList<Integer> prefix, ArrayList<Integer> rest) {
        if (!permutations.containsKey(prefix.size())) {
            permutations.put(prefix.size(), new ArrayList());
        }
        permutations.get(prefix.size()).add(prefix);
        if (!rest.isEmpty()) {
            for (int i = 0; i < rest.size(); i++) {
                ArrayList<Integer> prefixcopy = new ArrayList();
                prefixcopy.addAll(prefix);
                prefixcopy.add(rest.get(i));
                ArrayList<Integer> restcopy = new ArrayList();
                restcopy.addAll(rest);
                restcopy.remove(i);
                permutateIntegers(prefixcopy, restcopy);
            }
        }
    }

    // fills operationpermutations
    private void permutateOperations(ArrayList<IToken> prefix, int size) {
        if (prefix.size() < size) {
            ArrayList<IToken> prefixcopy = new ArrayList();
            prefixcopy.addAll(prefix);
            prefixcopy.add(new Plus());
            permutateOperations(prefixcopy,size);
            prefixcopy.set(prefixcopy.size() - 1, new Minus());
            permutateOperations(prefixcopy,size);
            prefixcopy.set(prefixcopy.size() - 1, new Multiply());
            permutateOperations(prefixcopy,size);
            prefixcopy.set(prefixcopy.size() - 1, new Divide());
            permutateOperations(prefixcopy,size);
        } else {
            if (!operationpermutations.containsKey(size)) {
                operationpermutations.put(size, new ArrayList());
            }
            operationpermutations.get(size).add(prefix);
        }
    }

    //tries to get as close to the target as possible using all the permutations
    public SolutionStep solve(int target) {
        
        SolutionStep closestsolution = new SolutionStep();
        
        // check if solution is 1 of the numbers in the question
        for (Integer number : permutations.get(1).get(0)) {
            
            SolutionStep solution = new SolutionStep();
            solution.addNumber(number);
            
            if(checkIfFound(solution, closestsolution, target)){
                return closestsolution;
            }
        }
        
        // check all permutations from 2 numbers and operation until 6 numbers and 5 operations
        for (int i = 2; i <= 6; i++) {
            for (ArrayList<Integer> permutation : permutations.get(i)) {
                for (ArrayList<IToken> operations : operationpermutations.get(i-1)) {

                    Stack<IToken> tokens = new Stack();

                    for (int j = 0; j < permutation.size() - 1; j++) {
                        tokens.Number number = new tokens.Number();
                        number.setValue(permutation.get(j));
                        tokens.add(number);
                        tokens.add(operations.get(j));
                    }
                    tokens.Number number = new tokens.Number();
                    number.setValue(permutation.get(permutation.size() - 1));
                    tokens.add(number);
                    
                    ArrayList<SolutionStep> solutions = new ArrayList();                    
                    SolutionStep predecessorstokens = new SolutionStep();
                    
                    predecessorstokens.add(tokens);
                    permutateBrackets(predecessorstokens,solutions);
                    
                    if(checkIfFound(solutions, closestsolution, target)){
                        return closestsolution;
                    }
                    
                }
            }
        }

        return closestsolution;
    }

    // solve the calculations of all number and operation permutations
    // using bracket permutations to calculate
    private void permutateBrackets(SolutionStep predecessor, ArrayList<SolutionStep> solutions) {
        
        // using the last step of the predecessor solution
        Stack<IToken> tosolve = predecessor.get(predecessor.size()-1);
        if (tosolve.size() == 1) {
            solutions.add(predecessor);
        } else {
            for (int i = 1; i < tosolve.size() - 1; i += 2) {
                int a = tosolve.get(i - 1).getValue();
                int b = tosolve.get(i + 1).getValue();
                
                // eliminate symmetry
                if(a<b || tosolve.get(i).getClass() == Minus.class || tosolve.get(i).getClass() == Divide.class){
                    int solution = tosolve.get(i).calculate(a, b);
                    
                    // eliminate negative numbers
                    if(solution > 0){
                        tokens.Number n = new tokens.Number();
                        n.setValue(solution);

                        Stack<IToken> tosolvecopy = new Stack();
                        tosolvecopy.addAll(tosolve);

                        tosolvecopy.set(i - 1, n);
                        tosolvecopy.remove(i);
                        tosolvecopy.remove(i);

                        SolutionStep predecessorcopy = new SolutionStep();
                        predecessorcopy.addAll(predecessor);
                        predecessorcopy.add(tosolvecopy);

                        permutateBrackets(predecessorcopy,solutions);
                    }
                }
            }
        }
    }
    
    // check if one of the solutions is equal to the target
    // either way sets closestsolution if closer
    private boolean checkIfFound(ArrayList<SolutionStep> solutions, SolutionStep closestsolution, int target){
        for(SolutionStep solution:solutions){
            if(checkIfFound(solution, closestsolution, target)){
                return true;
            }
        }
        
        return false;
    }
    
    // check if the solution is equal to the target
    // either way sets closestsolution if closer
    private boolean checkIfFound(SolutionStep solution, SolutionStep closestsolution, int target){
                
        IToken lasttoken = solution.getResult();

        solutioncount++;
        
        if(Math.abs(lasttoken.getValue()-target)<Math.abs(closestsolution.getResult().getValue()-target)){
            closestsolution.clear();
            closestsolution.addAll(solution);
            System.out.println("closest: " + lasttoken.getValue() + " " + solution + " #solution: " + solutioncount);

            if(closestsolution.getResult().getValue()==target){
                return true;
            }
        }
        
        return false;
    }
}
