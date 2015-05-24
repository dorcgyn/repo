/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shudu;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Sudo {
    public int[][] sudo = new int[9][9];
    
    public Sudo(){
        init();
    }
    
    public void init() {
        for (int i = 0; i < 9; i++ ) {
            for (int j = 0; j < 9; j++ ) {
                sudo[i][j] = 0;
            }
        }
    }
    
    public boolean isFullEdit() {
        for (int i = 0; i < 9; i++ ) {
            for (int j = 0; j < 9; j++ ) {
                if (sudo[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isValid() {
        // check all lines and columns
        for (int i = 0; i < 9; i++ ) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            ArrayList<Integer> column = new ArrayList<Integer>();
            for (int j = 0; j < 9; j++) {
                row.add(sudo[i][j]);
                column.add(sudo[j][i]);
            }
            if (!isNumbersValid(row) || !isNumbersValid(column)) {
                return false;
            }
        }
        // check all block
        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                ArrayList<Integer> blocks = new ArrayList<Integer>();
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        blocks.add(sudo[i+x][j+y]);
                    }
                }
                if (!isNumbersValid(blocks)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public PossibleNumbers getPossiblesForCell(int x, int y) {
        PossibleNumbers possibles = new PossibleNumbers();
        for (int number = 1; number < 10; number++) {
            // check row and column
            ArrayList<Integer> row = new ArrayList<Integer>();
            ArrayList<Integer> column = new ArrayList<Integer>();
            for (int i = 0; i < 9; i++) {
                row.add(sudo[x][i]);
                column.add(sudo[i][y]);
            }
            row.add(number);
            column.add(number);
            if (!isNumbersPossibleValid(row) || !isNumbersPossibleValid(column)) {
                continue; // continue number loop
            }
            // check block
            int offset_x = x/3;
            int offset_y = y/3;
            ArrayList<Integer> block = new ArrayList<Integer>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    block.add(sudo[ i + offset_x*3 ][ j + offset_y*3 ]);
                }
            }
            block.add(number);
            if (!isNumbersPossibleValid(block)) {
                continue;
            }
            // if all possible, add this number into possible list
            possibles.add(number);
        }
        return possibles;
    }
    
    /*
    public boolean isPossibleValid() {
        // check all lines and columns
        for (int i = 0; i < 9; i++ ) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            ArrayList<Integer> column = new ArrayList<Integer>();
            for (int j = 0; j < 9; j++) {
                row.add(sudo[i][j]);
                column.add(sudo[j][i]);
            }
            if (!isNumbersPossibleValid(row) || !isNumbersPossibleValid(column)) {
                return false;
            }
        }
        // check all block
        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                ArrayList<Integer> blocks = new ArrayList<Integer>();
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        blocks.add(sudo[i+x][j+y]);
                    }
                }
                if (!isNumbersPossibleValid(blocks)) {
                    return false;
                }
            }
        }
        return true;
    }
    */
    
    // should be 9 number to exactly fit
    private boolean isNumbersValid(ArrayList<Integer> numbers) {
        if (numbers.size() != 9) {
            return false;
        }
         for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) < 1 || numbers.get(i) > 9) {
                return false;
            }
            for (int j = i-1; j >= 0; j--) {
                if (numbers.get(i) == numbers.get(j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // allow 0 in to be fulfilled valid in future
    private boolean isNumbersPossibleValid(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == 0) {
                continue;
            }
            if (numbers.get(i) < 0 || numbers.get(i) > 9) {
                return false;
            }
            for (int j = i-1; j >= 0; j--) {
                if (numbers.get(i) == numbers.get(j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Sudo copy () {
        Sudo copy = new Sudo();
        for (int i = 0; i < 9; i++ ) {
            for (int j = 0; j < 9; j++ ) {
                copy.sudo[i][j] = sudo[i][j];
            }
        }
        return copy;
    }
}
