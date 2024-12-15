package HappyCube;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HappyCube {
    
    public void Run(Character[][][] faces) {
        CubeCalculator cubeBuilder = new CubeCalculator();
        List<CubeFace> m_facesObj = Arrays.stream(faces).map(CubeFace::new).collect(Collectors.toList());
        cubeBuilder.calculate(m_facesObj);
        
        System.out.println("Tamir: total recursion: " + cubeBuilder.c);
        System.out.println("Tamir: total cubes: " + cubeBuilder.getResults().size());
        
        cubeBuilder.getResults().forEach(r -> {
            r.forEach(System.out::println);
            System.out.println("\n-----------------------------------------------------------\n");
        });
    }
}
