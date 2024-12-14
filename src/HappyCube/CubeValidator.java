package HappyCube;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static HappyCube.Utils.copyList;

public class CubeValidator {
    
    private static final int ROTATIONS = 4;
    private MySet results = new MySet();
    
    
    public void calculate(List<CubeFace> facesObj) {
        ArrayList fixedFace = new ArrayList<>();
        List<CubeFace> facesClone = copyList(facesObj);
        CubeFace remove = facesClone.remove(0);
        fixedFace.add(remove);
        calculate(fixedFace, facesClone);
    }
    
    boolean calculate(List<CubeFace> fixedFaces2, List<CubeFace> facesRest2) {
        if(facesRest2.isEmpty()) {
            results.add(fixedFaces2);
            return true;
        }
        for(int faceIdx = 0; faceIdx < facesRest2.size(); faceIdx++) {
            List<CubeFace> fixedFaces = copyList(fixedFaces2);
            List<CubeFace> facesRest = copyList(facesRest2);
            CubeFace currentFace = facesRest.get(faceIdx);
            for(int k = 0; k < currentFace.getFlips(); k++) {
                for(int r = 0; r < ROTATIONS; r++) {
                    if(isMatch(fixedFaces, currentFace)) {
                        facesRest.remove(currentFace);
                        fixedFaces.add(currentFace);
                        if(!calculate(fixedFaces, facesRest)) {
                            fixedFaces.remove(currentFace);
                            facesRest.add(faceIdx, currentFace);
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
    
    public Set<List<CubeFace>> getResults() {
        return results;
    }
}
