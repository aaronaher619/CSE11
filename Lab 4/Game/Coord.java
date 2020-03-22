import java.lang.Math;

public class Coord implements Comparable<Coord>{

    public final int row;
    public final int col;
    
    public Coord(){
	this.row = 0;
	this.col = 0;
    }

    public Coord(Coord other){
	this.row = other.row;
	this.col = other.col;
    }

    public Coord(int row1, int col1){
	this.row = row1;
	this.col = col1;
    }

    private int sign(int num){
	if (num<0){
	    return -1;
	}
	else{
	    return 1;
	}
    }

    public Coord dist(Coord b){
	if (b == null){
	    return null;
	}
	else {
	    return new Coord(Math.abs(row - b.row), Math.abs(col - b.col));
	}
    }

    public Coord diff(Coord b){
	if (b == null){
	    return null;
	}
	else {
	    return new Coord(row - b.row, col - b.col);
	}
    }

    public int dist2(Coord b){
	if (b == null){
	    return Integer.MAX_VALUE;
	}
	else {
	    return ((dist(b).row)^2 + (dist(b).col)^2);
	}
    }

    public Coord unit(){
	return new Coord(sign(row), sign(col));
    }

    public Coord add(Coord b){
	if (b == null){
	    return null;
	}
	else{
	    return new Coord(row + b.row,col + b.col);
	}
    }

    @Override
    public int compareTo(Coord other){
	Coord origin = new Coord();
	int comparisonVal = 0;
	Integer x = dist2(origin);
	Integer y = other.dist2(origin);
	comparisonVal = x.compareTo(y);
	return comparisonVal;
    }

    public boolean equals(Coord other){
	if ((this.row == other.row) && (this.col == other.col)){
		return true;
	    }
	return false;
    }

    public String toString(){
	return "".format("Coord:(row= %d ,col= %d )", this.row, this.col);
    }
}