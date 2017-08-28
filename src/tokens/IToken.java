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
public interface IToken {
    public int getValue();
    public void setValue(int value);
    public int calculate(int a, int b);
    @Override
    public String toString();
}
