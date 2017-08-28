/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countdownnumbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import tokens.*;

/**
 *
 * @author Joeri
 */
public class CountdownTree extends MTree<IToken>{

    public CountdownTree(ArrayList<Integer> question) {
        super(null);
        Map<Node, ArrayList<Integer>> layer = createFirstLayer(question);
        layer = createSecondLayer(layer);
        for (int i = 0; i < 7; i++) {
            layer = createMiddleLayer(layer,i);
        }
        System.out.println("");
        
    }
    
    private Map<Node, ArrayList<Integer>> createFirstLayer(ArrayList<Integer> question){
        Map<Node, ArrayList<Integer>> firstlayer = new HashMap();
        for (int i = 0; i < question.size(); i++) {
            tokens.Number t = new tokens.Number();
            t.setValue(question.get(i));
            ArrayList<Integer> questioncopy = new ArrayList();
            questioncopy.addAll(question);
            questioncopy.remove(i);
            firstlayer.put(this.getRoot().addChild(t),questioncopy);
        }
        
        return firstlayer;
    }
    
    private Map<Node, ArrayList<Integer>> createSecondLayer(Map<Node, ArrayList<Integer>> firstlayer){
        Map<Node, ArrayList<Integer>> secondlayer = new HashMap();
        
        for(Node n:firstlayer.keySet()){
            ArrayList<Integer> question = firstlayer.get(n);
            for (int i = 0; i < question.size(); i++) {
                tokens.Number t = new tokens.Number();
                t.setValue(question.get(i));
                ArrayList<Integer> questioncopy = new ArrayList();
                questioncopy.addAll(question);
                questioncopy.remove(question.get(i));
                secondlayer.put(n.addChild(t),questioncopy);
            }
        }
        
        
        return secondlayer;
    }
    
    private Map<Node, ArrayList<Integer>> createMiddleLayer(Map<Node, ArrayList<Integer>> previouslayer, int itteration){
        Map<Node, ArrayList<Integer>> middlelayer = new HashMap();
        
        previouslayer.keySet().stream().forEach((n) -> {
            ArrayList<Integer> question = previouslayer.get(n);
            for (int i = 0; i < question.size(); i++) {
                tokens.Number t = new tokens.Number();
                t.setValue(question.get(i));
                ArrayList<Integer> questioncopy = new ArrayList();
                questioncopy.addAll(question);
                questioncopy.remove(question.get(i));
                middlelayer.put(n.addChild(t),questioncopy);
            }
            if(!((6-question.size())<itteration/2)){
                IToken t = new Plus();
                middlelayer.put(n.addChild(t), question);
                t = new Minus();
                middlelayer.put(n.addChild(t), question);
                t = new Multiply();
                middlelayer.put(n.addChild(t), question);
                t = new Divide();
                middlelayer.put(n.addChild(t), question);
            }
        });
        
        
        return middlelayer;
    }

    public ArrayList<IToken> calcClosest(int target){
        return new ArrayList();
    }
}
