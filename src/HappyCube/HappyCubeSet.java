package HappyCube;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static HappyCube.Utils.copyList;

public class HappyCubeSet extends HashSet<List<CubeFace>> {
    
    @Override
    public boolean add(List<CubeFace> b) {
        List<CubeFace> cubeFaces = copyList(b);
        if(contains(cubeFaces)) {
            return false;
        }
        super.add(cubeFaces);
        return true;
    }
    
    @Override
    public boolean contains(Object o) {
        if(size() == 0) return false;
        return Arrays.stream(this.toArray()).anyMatch(c -> isTheSame((List<CubeFace>) c, (List<CubeFace>) o));
    }
    
    private boolean isTheSame(List<CubeFace> next, List<CubeFace> o1) {
        for(int i = 0; i < next.size(); i++) {
            if(next.get(i).hashCode() != o1.get(i).hashCode()) {
                return false;
            }
        }
        return true;
    }
}
