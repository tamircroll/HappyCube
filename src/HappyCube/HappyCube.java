package HappyCube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HappyCube {
    
    
    private static final int ROTATIONS = 4;
    private static final int FLIP_COUNT = 2;
    private List<List<CubeFace>> result = new ArrayList<>();
    
    public void calculate(Character[][][] faces) {
        CubeValidator cubeValidator = new CubeValidator();
        List<CubeFace> m_facesObj = Arrays.stream(faces).map(CubeFace::new).collect(Collectors.toList());
        cubeValidator.calculate(m_facesObj);
        cubeValidator.getResults().forEach(r -> {
            r.forEach(System.out::println);
            System.out.println("\n-----------------------------------------------------------\n");
        });
    }
    
//    private void calculate(List<CubeFace> facesObj, CubeValidator cubeValidator) {
//        ArrayList currFace = new ArrayList<CubeFace>();
//        currFace.add(facesObj.remove(0));
//        cubeValidator.calculate(currFace, facesObj);
//    }
}
