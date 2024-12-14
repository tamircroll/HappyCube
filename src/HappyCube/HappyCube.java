package HappyCube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HappyCube {
    
    
    private static final int ROTATIONS = 4;
    private static final int FLIP_COUNT = 2;
    private List<List<CubeFace>> result = new ArrayList<>();
    
    public void Run(Character[][][] faces) {
        CubeValidator cubeBuilder = new CubeValidator();
        List<CubeFace> m_facesObj = Arrays.stream(faces).map(CubeFace::new).collect(Collectors.toList());
        cubeBuilder.calculate(m_facesObj);
        cubeBuilder.getResults().forEach(r -> {
            r.forEach(System.out::println);
            System.out.println("\n-----------------------------------------------------------\n");
        });
    }
}
