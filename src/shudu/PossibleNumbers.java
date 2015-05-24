/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shudu;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class PossibleNumbers {
    private ArrayList<Integer> possibles = new ArrayList<Integer>();
    private Random random = new Random();
    
    public void add(int i) {
        possibles.add(i);
    }
    
    public boolean notPossible() {
        return possibles.size() == 0;
    }
    
    public int randomGenerate() {
        int randomNumber = random.nextInt(possibles.size());
        int returnValue = possibles.get(randomNumber);
        possibles.remove(randomNumber);
        return returnValue;
    }
}
