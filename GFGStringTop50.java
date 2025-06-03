https://www.geeksforgeeks.org/top-50-string-coding-problems-for-interviews/


public class GFG_ReverseStringWords {
    public static void main(String[] args) {
       String str = "i.like.this.program.very.much";
       String [] s1=  str.split("\\.");
       StringBuilder stringBuilder = new StringBuilder();
        for(int i=s1.length-1;i>=0;i--){
            stringBuilder.append(s1[i]);
            if (i != 0) {
                stringBuilder.append(".");
            }
        }
        System.out.println(stringBuilder.toString());
    }
}

--------------------------
  
