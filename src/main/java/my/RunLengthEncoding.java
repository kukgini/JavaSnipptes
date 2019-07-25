package my;

public class RunLengthEncoding {
    static public String runLengthEncoding(String text) {
        String encodedString = "";

        for (int i = 0, next = 1, count = 1; next < text.length(); i++, next++) {
            if (text.charAt(i) == text.charAt(next)) {
                count++;
            } else {
                encodedString = encodedString
                	.concat(Integer.toString(count))
                    .concat(Character.toString(text.charAt(i)));
                count = 1;
            }
        }
        return encodedString;
    }
    
    static public void main(String[] args) {
        String in = "AAAAABCC";
        String out = runLengthEncoding(in);
        System.out.printf("%s -> %s",in,out);   
    }
}
