package naivelcs;

/**
 *
 * @author Aina Aggarwal
 * @author Amrut Patil
 * 
 */
public class NaiveLCS {

        private String str1,str2;
        private long recursivecalls;
        private int[][] c;
        
        public NaiveLCS(String a, String b) {
                str1 = a;
                str2 = b;
                c = new int[str1.length()][str2.length()];
                recursivecalls = 0;
        }
        
         //Method to compute LCS by recursion
          public int LCS(int i, int j){
                ++recursivecalls; //count number of recursive calls
                if(i == -1 || j == -1) {
                        return 0;
                }
                else if (str1.charAt(i) == str2.charAt(j)){
                        c[i][j] = 1 + LCS(i-1,j-1);
                }
                else {
                        c[i][j] = Math.max(LCS(i-1,j), LCS(i,j-1));
                }
                
                return c[i][j];
        }
        
        //Method to return number of recursive calls
        public long getRecursiveCalls(){
                return recursivecalls;
        }       
        
        public static void main(String[] args) { 
            String x = "CAGTA";
            String y = "GTACT";
            NaiveLCS lcs = new NaiveLCS(x, y);
            System.out.println("Length of LCS: " + lcs.LCS(x.length()-1, y.length()-1)); 
            System.out.println("Number of Recursive Calls:  " + lcs.getRecursiveCalls());

        }
}
