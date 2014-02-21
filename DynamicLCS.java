package DynamicLCS;

/** 
* Implementation of Dynamic Programming version of the LCS algorithm. 
* 
* @authors Aina Aggarwal, Amrut Patil
* 
*/ 
public class DynamicLCS {
         private String str1;
         private String str2;
    
    public DynamicLCS(String a, String b) {
        this.str1 = a;
        this.str2 = b;
        intialize();
    }
    // ls(i,j) - maximum length of common subsequence that end at a[i] and b[j].
    private int ls[][];
    
    private enum Path {
        L, U, D;
        
        public String toString() {
        	if(this.equals(L))
        		return "<";
        	else if(this.equals(U))
        		return "^";
        	else if(this.equals(D))   //Corresponds to member of LCS
        		return "\\";
            throw new IllegalStateException("Wrong Direction");
        }
    }
    
    // specifies the neighboring cell ls(i,j) from which value is obtained
    Path[][] p;
   
    private void intialize() {
        // initialize 1st row and 1st column with 0
        ls = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++)
            ls[i][0] = 0;
        for (int j = 0; j <= str2.length(); j++)
            ls[0][j] = 0;
   
        p = new Path[str1.length() + 1][str2.length() + 1];
   
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                //Comparisons
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {   //If the input element of the two sequences are equal, add 1 to the diagonal value 
                    ls[i][j] = ls[i - 1][j - 1] + 1;
                    p[i][j] = Path.D;
                } 
                else if (ls[i - 1][j] >= ls[i][j - 1]) {  //Choose the greater value
                    ls[i][j] = ls[i - 1][j];
                    p[i][j] = Path.U;
                } 
                else {
                    ls[i][j] = ls[i][j - 1];
                    p[i][j] = Path.L;
                }
            }
        }
    }
   
    public void printCell() {
        System.out.printf("%1$2c ", ' ');
        for (int j = 0; j <= str2.length(); j++){
            System.out.printf("%1$2c ", (j == 0) ? ' ' : str2.charAt(j - 1));
        }
        System.out.println();
   
        for (int i = 0; i <= str1.length(); i++) {
            System.out.printf("%1$2c ", (i == 0) ? ' ' : str1.charAt(i - 1));
   
        for (int j = 0; j <= str2.length(); j++)
                System.out.printf("%1$2d ", ls[i][j]);
        System.out.println();
        }
    }
   
    public void printPath() {
        System.out.printf("%1$2c ", ' ');
        for (int j = 0; j <= str2.length(); j++)
            System.out.printf("%1$2c ", (j == 0) ? ' ' : str2.charAt(j - 1));
        System.out.println();
   
        for (int i = 0; i <= str1.length(); i++) {
            System.out.printf("%1$2c ", (i == 0) ? ' ' : str1.charAt(i - 1));
   
            for (int j = 0; j <= str2.length(); j++)
                System.out.printf("%1$2s ", (p[i][j] == null)? '-' : p[i][j]);
            System.out.println();
        }
    }
   
    //Construct LCS
    public String deriveLCS() {
        StringBuilder lcs = new StringBuilder();
        int i = str1.length();
        int j = str2.length();
        while (i > 0 && j > 0) {
            if (p[i][j] == Path.D) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (p[i][j] == Path.U)
                i--;
            else
                j--;
        }
        return lcs.reverse().toString();
    }
    
    public static void main(String[] args) {
        
        DynamicLCS lcs;
        lcs = new DynamicLCS("BDCABA", "ABCBDAB");
        lcs.printCell();
        System.out.println();
        lcs.printPath();
        System.out.printf("LCS using Dynamic Version of the algorithm = %1$s\n\n", lcs.deriveLCS());
   
        //Required Section of the project.
        //Input sequence {0,1} Length = 5
        lcs = new DynamicLCS("01101", "10101");
        lcs.printCell();
        System.out.println();
        lcs.printPath();
        System.out.printf("LCS using Dynamic Version of the algorithm = %1$s\n\n", lcs.deriveLCS());
   
        //Input sequence {A,C,G,T} Length = 5
        lcs = new DynamicLCS("CAGTA", "GTACT");
        lcs.printCell();
        System.out.println();
        lcs.printPath();
        System.out.printf("LCS using Dynamic Version of the algorithm = %1$s\n\n", lcs.deriveLCS());
        
        //Input sequence {0,1} Length = 10
        lcs = new DynamicLCS("1101000110", "0101011010");
        lcs.printCell();
        System.out.println();
        lcs.printPath();
        System.out.printf("LCS using Dynamic Version of the algorithm = %1$s\n\n", lcs.deriveLCS());
   
        //Input sequence {A,C,G,T} Length = 10
        lcs = new DynamicLCS("AGTTCGGCCT", "GTTAGGCTAC");
        lcs.printCell();
        System.out.println();
        lcs.printPath();
        System.out.printf("LCS using Dynamic Version of the algorithm = %1$s\n\n", lcs.deriveLCS());
    
        //Input sequence {0,1} Length = 15
        lcs = new DynamicLCS("110100011001101", "110100101011010");
        lcs.printCell();
        System.out.println();
        lcs.printPath();
        System.out.printf("LCS using Dynamic Version of the algorithm = %1$s\n\n", lcs.deriveLCS());
   
        //Input sequence {A,C,G,T} Length = 15
        lcs = new DynamicLCS("ACTGTTAAGGCCGT", "AACTTGGATCGTCAT");
        lcs.printCell();
        System.out.println();
        lcs.printPath();
        System.out.printf("LCS using Dynamic Version of the algorithm = %1$s\n\n", lcs.deriveLCS());
   
        //Optional Section of the project. 
        //Addressing Exercise 15.4-1 on page 396/397 of CLRS	
      	lcs = new DynamicLCS("10010101","010110110");
        lcs.printCell();
        System.out.println();
        lcs.printPath();
        System.out.printf("LCS using Dynamic Version of the algorithm = %1$s\n\n", lcs.deriveLCS());
   
    }
}