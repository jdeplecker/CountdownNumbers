/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countdownnumbers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joeri
 */
public class Node<T> {

    private T data;
    private Integer intermediateresult;
    private final Node<T> parent;
    private List<Node<T>> children;

    public Node(Node<T> parent) {
        this.parent = parent;
        children = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node addChild(T data){
        Node n = new Node(this);
        n.data = data;
        children.add(n);
        
        return n;
    }
    
    public List<Node<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }

    public Integer getIntermediateresult() {
        return intermediateresult;
    }

    public void setIntermediateresult(Integer intermediateresult) {
        this.intermediateresult = intermediateresult;
    }
}
