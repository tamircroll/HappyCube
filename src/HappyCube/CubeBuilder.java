package HappyCube;

import java.util.ArrayList;
import java.util.List;

import static HappyCube.Utils.copyList;

public class CubeBuilder {
    final List<CubeFace> matchedFaces;
    final List<CubeFace> UnmatchedFaces;
    
    public CubeBuilder(List<CubeFace> faces) {
        matchedFaces = new ArrayList<CubeFace>();
        matchedFaces.add(faces.getFirst());
        UnmatchedFaces = new ArrayList<CubeFace>(faces.subList(1, faces.size()));
    }
    
    public CubeBuilder(List<CubeFace> matchedFaces, List<CubeFace> UnmatchedFaces) {
        this.matchedFaces = matchedFaces;
        this.UnmatchedFaces = UnmatchedFaces;
    }
    
    public CubeBuilder getClone() {
        try {
            return (CubeBuilder) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public CubeFace getUnMatched(int index) {
        return UnmatchedFaces.get(index);
    }
    
    public void moveToMatched(CubeFace toMove) {
        UnmatchedFaces.remove(toMove);
        matchedFaces.add(toMove);
    }
    
    public void moveToUnMatched(CubeFace toRemove, int idx) {
        matchedFaces.remove(toRemove);
        UnmatchedFaces.add(idx, toRemove);
    }
    
    public boolean IsCubeDone() {
        return !UnmatchedFaces.isEmpty();
    }
    
    public int getUnMatchSize() {
        return UnmatchedFaces.size();
    }
    
    public List<CubeFace> getMatched() {
        return matchedFaces;
    }
    
    public boolean isMatch(CubeFace newFace) {
        int idx = matchedFaces.size();
        if(idx == 1) {
            return matchedFaces.get(0).isRightMatchLeft(newFace);
        } else if(idx == 2) {
            return matchedFaces.get(1).isRightMatchLeft(newFace);
        } else if(idx == 3) {
            return matchedFaces.get(0).isDownMatchLeft(newFace) &&
                    matchedFaces.get(1).isDownMatchUp(newFace) &&
                    matchedFaces.get(2).isDownMatchRight(newFace);
        } else if(idx == 4) {
            return matchedFaces.get(0).isLeftMatchLeft(newFace) &&
                    matchedFaces.get(2).isRightMatchRight(newFace) &&
                    matchedFaces.get(3).isDownMatchUp(newFace);
        } else if(idx == 5) {
            return matchedFaces.get(0).isUpMatchLeft(newFace) &&
                    matchedFaces.get(1).isUpMatchDown(newFace) &&
                    matchedFaces.get(2).isUpMatchRight(newFace) &&
                    matchedFaces.get(4).isDownMatchUp(newFace);
        }
        
        return false;
        
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CubeBuilder(copyList(matchedFaces), copyList(UnmatchedFaces));
    }
}
