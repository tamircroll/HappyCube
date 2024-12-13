package HappyCube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HappyCube {
    
    
    private static final int ROTATIONS = 4;
    private static final int FLIP_COUNT = 2;
    private final List<CubeFace> m_facesObj;
    private List<List<CubeFace>> result = new ArrayList<>();
    
    public HappyCube(Character[][][] faces) {
        m_facesObj = Arrays.stream(faces).map(CubeFace::new).collect(Collectors.toList());
    }
    
    public void calculate() {
        calculate(m_facesObj);
        result.forEach(r -> {
            r.forEach(a -> System.out.println(a));
            System.out.println("\n-----------------------------------------------------------\n");
        });
    }
    
    private void calculate(List<CubeFace> facesObj) {
        ArrayList currFace = new ArrayList<>();
        List<CubeFace> facesClone = copyList(facesObj);
        currFace.add(facesClone.remove(0));
        calculate(currFace, facesClone);
    }
    
    private boolean calculate(List<CubeFace> fixedFaces2, List<CubeFace> facesRest2) {
        if(facesRest2.size() == 0) {
            result.add(fixedFaces2);
            return true;
        }
        for(int j = 0; j < facesRest2.size(); j++) {
            List<CubeFace> fixedFaces = copyList(fixedFaces2);
            List<CubeFace> facesRest = copyList(facesRest2);
            CubeFace currentFace = facesRest.get(j);
            for(int k = 0; k < FLIP_COUNT; k++) {
                for(int r = 0; r < ROTATIONS; r++) {
                    if(isMatch(fixedFaces, currentFace)) {
                        facesRest.remove(currentFace);
                        fixedFaces.add(currentFace);
                        if(!calculate(fixedFaces, facesRest)) {
                            fixedFaces.remove(currentFace);
                            facesRest.add(j, currentFace);
                        } else {
                            return true;
                        }
                    }
                    currentFace.rotate();
                }
                currentFace.flip();
            }
        }
        return false;
    }
    
    private boolean isMatch(List<CubeFace> fixedFaces, CubeFace newFace) {
        int idx = fixedFaces.size();
        if(idx == 1) {
            return fixedFaces.get(0).isRightMatchLeft(newFace);
        } else if(idx == 2) {
            return fixedFaces.get(1).isRightMatchLeft(newFace);
        } else if(idx == 3) {
            return fixedFaces.get(0).isDownMatchLeft(newFace) &&
                    fixedFaces.get(1).isDownMatchUp(newFace) &&
                    fixedFaces.get(2).isDownMatchRight(newFace);
        } else if(idx == 4) {
            return fixedFaces.get(0).isLeftMatchLeft(newFace) &&
                    fixedFaces.get(2).isRightMatchRight(newFace) &&
                    fixedFaces.get(3).isDownMatchUp(newFace);
        } else if(idx == 5) {
            return fixedFaces.get(0).isUpMatchLeft(newFace) &&
                    fixedFaces.get(1).isUpMatchDown(newFace) &&
                    fixedFaces.get(2).isUpMatchRight(newFace) &&
                    fixedFaces.get(4).isDownMatchUp(newFace);
        }
        
        return false;
    }
    
    private List<CubeFace> copyList(List<CubeFace> facesObj) {
        return facesObj.stream().map(CubeFace::clone).collect(Collectors.toList());
    }
}
