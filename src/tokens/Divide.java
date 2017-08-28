/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

/**
 *
 * @author Joeri
 */
public class Divide implements IToken{
    
    @Override
    public int calculate(int a, int b) {
        if(b==0){
            return 0;
        }
        else if((a/b)*b==a){
            return a/b;
        }
        else{
            return 0;            
        }
    }

    @Override
    public int getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValue(int value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "/";
    }
    
}
