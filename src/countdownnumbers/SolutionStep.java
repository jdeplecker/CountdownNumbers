/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countdownnumbers;

import java.util.ArrayList;
import java.util.Stack;
import tokens.IToken;

/**
 *
 * @author Joeri
 */
public class SolutionStep extends ArrayList<Stack<IToken>>{
    
    // gives the endresult of this solution
    public IToken getResult(){
        if(size()>0 && get(size()-1).size()==1){
            return get(size()-1).get(0);
        }
        else{
            tokens.Number nultoken = new tokens.Number();
            nultoken.setValue(-1);
            return nultoken;
        }
    }
    
    // adds an integer as IToken to the solution
    public void addNumber(int n){
        Stack<IToken> newstack = new Stack();
        tokens.Number numtoken = new tokens.Number();
        numtoken.setValue(n);
        newstack.add(numtoken);
        add(newstack);
        System.out.println("");
    }

    @Override
    public String toString() {
        
        String result = "";
        
        for (int i = 0; i < size(); i++) {
            Stack<IToken> s = get(i);
            
            result += "(";
            for (int j = 0; j < s.size()-1; j++) {
                result += s.get(j) + " ";
            }
            result += s.get(s.size()-1) + ")";
            if(i<size()-1){
                result += " -> ";
            }
        }
        
        
        return result;
    }
    
    
}
