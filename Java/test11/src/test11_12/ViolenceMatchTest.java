package test11_12;

public class ViolenceMatchTest {
    public static void main(String[] args) {
        ViolenceMatch violenceMatch = new ViolenceMatch("abacbcbababaca", "babac");
        System.out.println(violenceMatch.search());
    }
}

class ViolenceMatch {
    String str;
    String key;

    public ViolenceMatch(String str, String key) {
        this.str = str;
        this.key = key;
    }

    public int search() {
        int i = 0;
        int j = 0;
        while (i < str.length() && j < key.length()) {
            if (str.charAt(i) == key.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j==key.length()) {
            return i - j;
        } else {
            return -1;
        }
    }
}
