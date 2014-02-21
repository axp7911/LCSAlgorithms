package memoizedlcs;

/**
 *
 * @author Aina Aggarwal
 * @author Amrut Patil
 * 
 */
public class MemoizedLCS {
	
	private String str1,str2;
        private long recursivecalls;
	private int[][] c;

        public MemoizedLCS(String a, String b) {
		str1 = a;
		str2 = b;
		c = new int[str1.length()][str2.length()];
		
		for(int i=0; i<str1.length(); i++) {
			for(int j=0; j<str2.length(); j++) {
				c[i][j] = -1;
			}
		}
		recursivecalls = 0;
	}
	
	/**
	 * This method computes the LLCS. It first checks to see if an
	 * answer to the specific subproblem exists and and skips recomputing
	 * it. If an answer has not been found, it computes one.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public int LCS(int i, int j){
		++recursivecalls; //count number of recursive recursivecalls
		if(i == -1 || j == -1) {
			return 0;
		}else if(c[i][j] == -1) {
			if (str1.charAt(i) == str2.charAt(j)){
				c[i][j] = 1 + LCS(i-1,j-1);
			}
			else {
				c[i][j] = Math.max(LCS(i-1,j), LCS(i,j-1));
			}
		}
		return c[i][j];
	}
	
	//Method to compute LCS by recursion
	public long getRecursiveCalls(){
		return recursivecalls;
	}
	
	public static void main(String[] args) {
            String x = "CAGTA";
            String y = "GTACT";
            MemoizedLCS lcs = new MemoizedLCS(x, y);
            System.out.println("Length of LCS: " + lcs.LCS(x.length()-1, y.length()-1)); 
            System.out.println("Number of Recursive Calls:  " + lcs.getRecursiveCalls());
	}

}

