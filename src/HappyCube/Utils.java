package HappyCube;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static List<CubeFace> copyList(List<CubeFace> facesObj) {
        return facesObj.stream().map(CubeFace::clone).collect(Collectors.toList());
    }
}
