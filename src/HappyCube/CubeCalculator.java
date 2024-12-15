package HappyCube;

import java.util.List;
import java.util.Set;

public class CubeCalculator {
    
    private static final int ROTATIONS = 4;
    private HappyCubeSet results = new HappyCubeSet();
    
    
    public void calculate(List<CubeFace> facesObj) {
        calculate(new CubeValidator(facesObj));
    }
    
    boolean calculate(CubeValidator validator) {
        if(!validator.IsCubeDone()) {
            results.add(validator.getMatched());
            return true;
        }
        for(int faceIdx = 0; faceIdx < validator.getUnMatchSize(); faceIdx++) {
            CubeValidator validatorClone = validator.getClone();
            CubeFace faceToMatch = validatorClone.getUnMatched(faceIdx);
            for(int k = 0; k < faceToMatch.getFlips(); k++) {
                for(int r = 0; r < ROTATIONS; r++) {
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
