package test11_12;

import java.util.Arrays;

public class KMPTest {
    public static void main(String[] args) {
        KMP kmp = new KMP("abac", "ad");
        kmp.getNext();
        System.out.println(Arrays.toString(kmp.next));
        System.out.println(kmp.kmpSearch());
    }
}

class KMP {
    String str;
    String key;
    int[] next;

    public KMP(String str, String key) {
        this.str = str;
        this.key = key;
        this.next = new int[key.length()];
    }

    public void getNext() {
        next[0] = -1;
        int i = -1;
        int j = 0;
        while (j < next.length - 1) {
            if (i == -1 || key.charAt(i) == key.charAt(j)) {
                i++;
                j++;
                if (key.charAt(i) != key.charAt((j))) {
                    next[j] = i;
                } else {
                    next[j] = next[i];
                }
            } else {
                i = next[i];
            }
        }
    }

    public int kmpSearch() {
        int i = 0;
        int j = 0;
        while (i < str.length() && j < key.length()) {
            if (j == -1 || str.charAt(i) == key.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (i == str.length() && j != key.length()) {
            return -1;
        } else {
            return i - j;
        }
    }
}
