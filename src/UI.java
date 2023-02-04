import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class UI {
    Scanner in = new Scanner(System.in);

    public String getUserInput(String prompt){
        System.out.print(prompt);
        String userInput = in.nextLine();
        return userInput;
    }

    public boolean isUserInputValid(String userInput, int size){
        if (!isUserInputInteger(userInput) || !isUserInputInRange(userInput, size)) {
            System.out.println("[X] Incorrect input");
            return false;
        }
        return true;
    }

    private boolean isUserInputInteger(String userInput){
        try {
            Integer.parseInt(userInput);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    private boolean isUserInputInRange(String userInput, int size){
        int uInput = Integer.parseInt(userInput);
        if(uInput < 0 || uInput > size - 1) return false;
        return true;
    }
    public void printMenu(ArrayList<String> options){
        for(int i = 0; i < options.size(); i++){
            System.out.println(String.format("[%d] %s", i, options.get(i)));
        }
    }

    public int getInputAndValidate(String prompt, int size){
        String userInput;
        do {
            userInput = getUserInput(prompt);
        } while (!isUserInputValid(userInput, size));
        System.out.println();
        return Integer.parseInt(userInput);
    }

}
