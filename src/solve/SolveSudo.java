/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solve;

import static generate.Generator.recursive;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import shudu.PossibleNumbers;
import shudu.Sudo;

/**
 *
 * @author Administrator
 */
public class SolveSudo {
    public static ArrayList<Sudo> solveSudo( Sudo problem) {
        ArrayList<Sudo> solutions = new ArrayList<>();
        Sudo problemCopy = problem.copy();
        
        ArrayList<Map<String,Integer>> emptyPos = findEmptyPosition(problem);
        
        findAllRecursive(emptyPos, 0,problemCopy,solutions);
        
        return solutions;
    }
    
    private static ArrayList<Map<String,Integer>> findEmptyPosition(Sudo sudo) {
        ArrayList<Map<String,Integer>> emptyPositions = new ArrayList<Map<String,Integer>>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudo.sudo[i][j] == 0) {
                    Map<String, Integer> map = new HashMap();
                    map.put("x", i);
                    map.put("y", j);
                    emptyPositions.add(map);
                }
            }
        }
        return emptyPositions;
    }
    
    
    public static boolean findAllRecursive ( ArrayList<Map<String,Integer>> emptyPos
            , int index, Sudo problem
            , ArrayList<Sudo> solutions) {
        // end condition for recursive
        if (index == emptyPos.size()) {
            solutions.add(problem.copy());
            return false;
        } 
        int x = emptyPos.get(index).get("x");
        int y = emptyPos.get(index).get("y");
        PossibleNumbers possibles = problem.getPossiblesForCell(x, y);
        do {
            if (possibles.notPossible()) {
                return false;
            }
            // generate current value
            //TODO: try one by one maybe more effecitent
            problem.sudo[x][y] = possibles.randomGenerate();
            
            if (findAllRecursive(emptyPos, index+1, problem, solutions)) {
                return true;
            }
            
            // reset value to 0
            problem.sudo[x][y] = 0;
        } while (true);
    }
}
