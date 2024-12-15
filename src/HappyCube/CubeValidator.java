package HappyCube;

import java.util.ArrayList;
import java.util.List;

import static HappyCube.Utils.copyList;

public class CubeValidator {
    final List<CubeFace> fixedFaces;
    final List<CubeFace> UnmatchedFaces;
    
    public CubeValidator(List<CubeFace> faces) {
        fixedFaces = new ArrayList<CubeFace>();
        fixedFaces.add(faces.getFirst());
        UnmatchedFaces = new ArrayList<CubeFace>(faces.subList(1, faces.size()));
    }
    
    public CubeValidator(List<CubeFace> fixedFaces, List<CubeFace> UnmatchedFaces) {
        this.fixedFaces = fixedFaces;
        this.UnmatchedFaces = UnmatchedFaces;
    }
    
    public CubeValidator getClone() {
        try {
            return (CubeValidator) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public CubeFace getUnMatched(int index) {
        return UnmatchedFaces.get(index);
    }
    
    public void moveToFixed(CubeFace toMove) {
        UnmatchedFaces.remove(toMove);
        fixedFaces.add(toMove);
    }
    
    public void moveToUnMatched(CubeFace toRemove, int idx) {
        fixedFaces.remove(toRemove);
        UnmatchedFaces.add(idx, toRemove);
    }
    
    public boolean IsCubeDone() {
        return !UnmatchedFaces.isEmpty();
    }
    
    public int getToMatchSize() {
        return UnmatchedFaces.size();
    }
    
    public boolean isMatch(CubeFace newFace) {
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
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CubeValidator(copyList(fixedFaces), copyList(UnmatchedFaces));
    }
    
    public List<CubeFace> getFixed() {
        return fixedFaces;
    }
}
