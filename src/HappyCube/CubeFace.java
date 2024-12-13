package HappyCube;

import java.util.ArrayList;
import java.util.List;

public class CubeFace {
    
    public List<Boolean> up;
    public List<Boolean> right = new ArrayList<>();
    public List<Boolean> down;
    public List<Boolean> left = new ArrayList<>();
    
    
    public CubeFace(List<Boolean> up, List<Boolean> right, List<Boolean> down, List<Boolean> left) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
    }
    
    CubeFace(List<List<Boolean>> rowsList) {
        if(rowsList.size() != 5) {
            throw new RuntimeException("Wrong number of Faces ! ! !");
        }
        up = rowsList.get(0);
        down = rowsList.get(rowsList.size() - 1);
        for(List<Boolean> row : rowsList) {
            left.add(row.get(0));
            right.add(row.get(row.size() - 1));
        }
    }
    
    public CubeFace(Character[][] faces) {
        this(getLists(faces));
    }
    
    public void flip() {
        List<Boolean> temp = up;
        up = down;
        down = temp;
        right = rotateArray(right);
        left = rotateArray(left);
    }
    
    public void rotate() {
        List<Boolean> upRotated = rotateArray(up);
        up = right;
        right = rotateArray(down);
        down = left;
        left = upRotated;
    }
    
    @Override
    public CubeFace clone() {
        return new CubeFace(up, right, down, left);
    }
    
    public boolean isRightMatchLeft(CubeFace toMatch) {
        for(int i = 0; i < right.size(); i++) {
            if(right.get(i) && toMatch.left.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isUpMatchDown(CubeFace toMatch) {
        for(int i = 0; i < up.size(); i++) {
            if(up.get(i) && toMatch.down.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isDownMatchUp(CubeFace toMatch) {
        for(int i = 0; i < down.size(); i++) {
            if(down.get(i) && toMatch.up.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isDownMatchRight(CubeFace toMatch) {
        for(int i = 0; i < down.size(); i++) {
            if(down.get(i) && toMatch.right.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isDownMatchLeft(CubeFace toMatch) {
        List<Boolean> leftToMatch = rotateArray(toMatch.left);
        for(int i = 0; i < down.size(); i++) {
            if(down.get(i) && leftToMatch.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isUpMatchLeft(CubeFace toMatch) {
        for(int i = 0; i < up.size(); i++) {
            if(up.get(i) && toMatch.left.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isRightMatchRight(CubeFace toMatch) {
        List<Boolean> rightToMatch = rotateArray(toMatch.right);
        for(int i = 0; i < right.size(); i++) {
            if(right.get(i) && rightToMatch.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isUpMatchRight(CubeFace toMatch) {
        List<Boolean> rightToMatch = rotateArray(toMatch.right);
        for(int i = 0; i < up.size(); i++) {
            if(up.get(i) && rightToMatch.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    
    public boolean isLeftMatchLeft(CubeFace toMatch) {
        List<Boolean> leftToMatch = rotateArray(toMatch.left);
        for(int i = 0; i < left.size(); i++) {
            Boolean b = leftToMatch.get(i);
            if(left.get(i) && b) {
                return false;
            }
        }
        return true;
    }
    
    private static List<List<Boolean>> getLists(Character[][] faces) {
        List<List<Boolean>> facesBool2 = new ArrayList<>();
        for(Character[] face : faces) {
            ArrayList<Boolean> newLst = new ArrayList<>();
            for(int j = 0; j < face.length; j++) {
                newLst.add(face[j] == 'o');
            }
            facesBool2.add(newLst);
        }
        return facesBool2;
    }
    
    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < up.size(); i++) {
            res += up.get(i) ? "o" : " ";
        }
        res += "\n";
        
        
        for(int i = 1; i < left.size() - 1; i++) {
            res += left.get(i) ? "oooo" : " ooo";
            res += right.get(i) ? "o\n" : " \n";
        }
        
        for(int i = 0; i < down.size(); i++) {
            res += down.get(i) ? "o" : " ";
        }
        res += "\n";
        
        return res;
    }
    
    private List<Boolean> rotateArray(List<Boolean> down) {
        List<Boolean> downRotated = new ArrayList<>();
        for(int i = 0; i < down.size(); i++) {
            downRotated.add(down.get(down.size() - i - 1));
        }
        return downRotated;
    }
}