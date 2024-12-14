package HappyCube;

import org.junit.jupiter.api.Test;

class CubeFaceTest {
    
    @Test
    public void test1() {
        CubeFace cubeFace = new CubeFace(TestingData.getCharacters()[0]);
        CubeFace cubeFace2 = new CubeFace(TestingData.getCharacters11()[0]);
        System.out.println("Tamir: Here: " + cubeFace.hashCode());
        System.out.println("Tamir: Here2: " + cubeFace2.hashCode());
    }
}