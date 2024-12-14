package HappyCube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CubeFace {
    
    public List<Boolean> up;
    public List<Boolean> right = new ArrayList<>();
    public List<Boolean> down;
    public List<Boolean> left = new ArrayList<>();
    private int flips;
    
    
    public CubeFace(List<Boolean> up, List<Boolean> right, List<Boolean> down, List<Boolean> left, int flips) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
        this.flips = flips;
    }
    
    CubeFace(List<List<Boolean>> rowsList) {
        up = rowsList.get(0);
        down = rowsList.get(rowsList.size() - 1);
        for(List<Boolean> row : rowsList) {
            left.add(row.get(0));
            right.add(row.get(row.size() - 1));
        }
        this.flips = isFlippable() ? 2 : 1;
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
        return new CubeFace(up, right, down, left, flips);
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
        StringBuilder res = new StringBuilder();
        for(Boolean aBoolean : up) {
            res.append(aBoolean ? "o" : " ");
        }
        res.append("\n");
        
        for(int i = 1; i < left.size() - 1; i++) {
            res.append(Boolean.TRUE.equals(left.get(i)) ? "oooo" : " ooo");
            res.append(Boolean.TRUE.equals(right.get(i)) ? "o\n" : " \n");
        }
        
        for(int i = 0; i < down.size(); i++) {
            res.append(Boolean.TRUE.equals(down.get(i)) ? "o" : " ");
        }
        res.append("\n");
        
        return res.toString();
    }
    
    public int getFlips() {
        return flips;
    }
   
    @Override
    public boolean equals(Object o) {
        return this.up.hashCode() == ((CubeFace) o).up.hashCode() &&
                this.right.hashCode() == ((CubeFace) o).right.hashCode() &&
                this.down.hashCode() == ((CubeFace) o).down.hashCode() &&
                this.left.hashCode() == ((CubeFace) o).left.hashCode();
    }
    
    @Override
    public int hashCode() {
        List<List<Boolean>> a = Arrays.asList(up, right, down, left);
        var result = "";
        for(int i = 0; i < a.size(); i++) {
            for(int j = 0; j < a.get(i).size(); j++) {
                Boolean b = a.get(i).get(j);
                result += (b? "1" : "0");
            }
        }
        return result.hashCode();
    }
    
    private List<Boolean> rotateArray(List<Boolean> down) {
        List<Boolean> downRotated = new ArrayList<>();
        for(int i = 0; i < down.size(); i++) {
            downRotated.add(down.get(down.size() - i - 1));
        }
        return downRotated;
    }
    
    private boolean isFlippable() {
        CubeFace clone = clone();
        clone.flip();
        if(clone.hashCode() == this.hashCode()) return false;
        clone.rotate();
        return clone.hashCode() != this.hashCode();
    }
}
