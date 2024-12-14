import HappyCube.HappyCube;
import HappyCube.TestingData;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Character[][][] a = TestingData.getCharacters();
        Character[][][] b = TestingData.getCharacters2();
        Character[][][] c = TestingData.getCharacters3();
        Character[][][] d = TestingData.getCharacters4();
        Character[][][] failure = TestingData.getCharacters5();
    
        System.out.println("\n----------------------------\nTamir: Here: 1: \n");
        new HappyCube().Run(a);
        System.out.println("\n----------------------------\nTamir: Here: 2: \n");
        new HappyCube().Run(b);
        System.out.println("\n----------------------------\nTamir: Here: 3: \n");
        new HappyCube().Run(c);
        System.out.println("\n----------------------------\nTamir: Here: 4: \n");
        new HappyCube().Run(d);
        System.out.println("\n----------------------------\nTamir: Here: failure: \n");
        new HappyCube().Run(failure);
    }
}