/* This program outputs a triangle as shown in problem description. There is no
   input file for this problem (see problem #12 in A+ January 2016 file for further
   description of problem).
 */

public class Tri {
    public static void main(String[] args) {
        for(int i=1; i<=10; i++) {
            for(int j=10-i; j<10; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}