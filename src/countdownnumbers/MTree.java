/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countdownnumbers;

/**
 *
 * @author Joeri
 */
public class MTree<T> {

    private final Node<T> root;

    public MTree(T rootData) {

        root = new Node<>(null);
        root.setData(rootData);
    }

    public Node<T> getRoot() {
        return root;
    }
    
    
    
//    public void make4WayTree(ArrayList<T> question){
//        //root.data = question.get(0);
//            
//        Stack<Node<T>> parents = new Stack();
//        Stack<Node<T>> children = new Stack();
//        parents.add(root);
//        
//        for (int i = 0; i < question.size()-1; i++) {
//            
//            for(Node<T> p:parents){
//                p.setData(question.get(i));
//                
//                for (int j = 0; j < 4; j++) {
//                    p.getChildren().add(new Node<>(p));
//                }
//                
//                children.addAll(p.getChildren());
//            }
//            parents.clear();
//            parents.addAll(children);
//            children.clear();
//        }
//        for(Node<T> p:parents){
//            p.setData(question.get(question.size()-1));
//        }
//    }
    
}
