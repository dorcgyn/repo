/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generate;

import java.util.Random;
import shudu.PossibleNumbers;
import shudu.ShuDu;
import shudu.Sudo;

/**
 *
 * @author Administrator
 */
public class Generator {
    public static Sudo sudoGenerator() {
        Sudo sudo = new Sudo();
        
        recursive(sudo, 0, 0);
        
        return sudo;
    } 
    
    // i is row number, j is column number
    public static boolean recursive(Sudo sudo, int i, int j) {
        // System.out.println(i + " , " + j);
        // end condition for recursive
        if (i == 9) {
            return true;
        } 
        boolean flag; 
        PossibleNumbers possibles = sudo.getPossiblesForCell(i, j);
        do {
            if (possibles.notPossible()) {
                return false;
            }
            
            // generate current value
            sudo.sudo[i][j] = possibles.randomGenerate();
            // prepare next
            if ( j != 8) {
                flag = recursive(sudo, i, j+1); // same line, next column
            } else {
                flag = recursive(sudo, i+1, 0);  // new line, from begin
            }
            
            if (flag) {
                return true;
            }
            
            // reset value to 0
            sudo.sudo[i][j] = 0;
        } while (true);
    }
}
