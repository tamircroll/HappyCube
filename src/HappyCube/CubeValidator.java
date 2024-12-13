package HappyCube;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CubeValidator {
    
    private static final int ROTATIONS = 4;
    private static final int FLIP_COUNT = 2;
    private List<List<CubeFace>> results = new ArrayList<>();
    
    public boolean calculate(List<CubeFace> fixedFacesOrig, List<CubeFace> facesRestOrig) {
//        fixedFacesOrig.add(facesRestOrig.remove(0));
        return calculate(fixedFacesOrig, facesRestOrig, 1);
    }
    
    public boolean calculate(List<CubeFace> fixedFacesOrig, List<CubeFace> facesRestOrig, int rotations) {
        if(facesRestOrig.isEmpty()) {
            results.add(fixedFacesOrig);
            return true;
        }
        for(int j = 0; j < facesRestOrig.size(); j++) {
            List<CubeFace> fixedFaces = copyList(fixedFacesOrig);
            List<CubeFace> facesRest = copyList(facesRestOrig);
            CubeFace currentFace = facesRest.get(j);
            for(int k = 0; k < FLIP_COUNT; k++) {
                for(int r = 0; r < rotations; r++) {
                    if(!isMatch(fixedFaces, currentFace)) continue;
                    facesRest.remove(currentFace);
                    fixedFaces.add(currentFace);
                    if(calculate(fixedFaces, facesRest, ROTATIONS)) {
                        return true;
                    }
                    fixedFaces.remove(currentFace);
                    facesRest.add(j, currentFace);
                    currentFace.rotate();
                }
                currentFace.flip();
            }
        }
        return false;
    }
    
    private boolean isMatch(List<CubeFace> fixedFaces, CubeFace newFace) {
        int idx = fixedFaces.size();
        if(idx == 0) {
            return true;
        } else if(idx == 1) {
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
    
    public List<List<CubeFace>> getResults() {
        return results;
    }
}
