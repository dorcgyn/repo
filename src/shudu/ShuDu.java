/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shudu;

import generate.Generator;

/**
 *
 * @author Administrator
 */
public class ShuDu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sudo sudo = Generator.sudoGenerator();
        print(sudo);
    }
    
    public static void print(Sudo sudo) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudo.sudo[i][j] + "  ");
            }
            System.out.print("\n");
        }
    }
}
