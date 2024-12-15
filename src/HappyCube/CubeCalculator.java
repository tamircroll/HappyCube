package HappyCube;

import java.util.List;
import java.util.Set;

public class CubeCalculator {
    private HappyCubeSet results = new HappyCubeSet();
    
    public void calculate(List<CubeFace> facesObj) {
        calculate(new CubeBuilder(facesObj));
    }
    
    boolean calculate(CubeBuilder builder) {
        if(!builder.IsCubeDone()) {
            results.add(builder.getMatched());
            return true;
        }
        for(int faceIdx = 0; faceIdx < builder.getUnMatchSize(); faceIdx++) {
            CubeBuilder validatorClone = builder.getClone();
            CubeFace faceToMatch = validatorClone.getUnMatched(faceIdx);
            for(int k = 0; k < faceToMatch.getFlipsCount(); k++) {
                for(int r = 0; r < faceToMatch.getRotationCount(); r++) {
                    if(validatorClone.isMatch(faceToMatch)) {
                        validatorClone.moveToMatched(faceToMatch);
                        if(!calculate(validatorClone)) {
                            validatorClone.moveToUnMatched(faceToMatch, faceIdx);
                        }
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
