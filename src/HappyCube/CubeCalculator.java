package HappyCube;

import java.util.List;
import java.util.Set;

public class CubeCalculator {
    private HappyCubeSet results = new HappyCubeSet();
    int c = 0;
    
    public void calculate(List<CubeFace> facesObj) {
        calculate(new CubeBuilder(facesObj));
    }
    
    boolean calculate(CubeBuilder builder) {
        c++;
        if(!builder.IsCubeDone()) {
            results.add(builder.getMatched());
            return true;
        }
        for(int faceIdx = 0; faceIdx < builder.getUnMatchSize(); faceIdx++) {
            CubeBuilder builderClone = builder.getClone();
            CubeFace faceToMatch = builderClone.getUnMatched(faceIdx);
            for(int k = 0; k < faceToMatch.flipsCount(); k++) {
                for(int r = 0; r < faceToMatch.rotationCount(); r++) {
                    if(builderClone.isMatch(faceToMatch)) {
                        builderClone.moveToMatched(faceToMatch);
                        calculate(builderClone);
                        builderClone.moveToUnMatched(faceToMatch, faceIdx);
                    }
                    faceToMatch.rotate();
                }
                faceToMatch.flip();
            }
        }
        return false;
    }
    
    public Set<List<CubeFace>> getResults() {
        return results;
    }
}
