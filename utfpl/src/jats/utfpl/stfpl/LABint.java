package jats.utfpl.stfpl;


public class LABint implements Ilabel {
    public int m;
    public LABint(int i) {
        m = i;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof LABint) {
            LABint right = (LABint)o;
            return m == right.m;
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "m" + m;
    }

}
