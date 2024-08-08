package test12_4;

import java.util.Scanner;
import java.util.Stack;

public class parenMatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Stack<Character> characters = new Stack<>();
        boolean flag = true;
        for(int i = 0; i<str.length(); i++){
            if(str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{'){
                characters.push(str.charAt(i));
            }else if(str.charAt(i) == ')' || str.charAt(i) == ']' || str.charAt(i) == '}'){
                if(characters.empty()){
                    flag = false;
                    break;
                }

                if(characters.peek() == '(' && str.charAt(i) == ')'){
                    characters.pop();
                }else if(characters.peek() == '[' && str.charAt(i) == ']'){
                    characters.pop();
                }else if(characters.peek() == '{' && str.charAt(i) == '}'){
                    characters.pop();
                }else{
                    flag = false;
                    break;
                }
            }
        }
        if(!characters.empty()){
            flag = false;
        }
        if(flag){
            System.out.println("yes\n");
        }else{
            System.out.println("no\n");
        }
    }
}
