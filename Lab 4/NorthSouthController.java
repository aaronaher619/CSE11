public class NorthSouthController extends CarController{
    public NorthSouthController(CoordInfo oracle) {
        super(oracle);
    }

    public void setDefaultDirection(){
        direction = NORTH;
    }

    // return the direction when roaming
    public Coord roam(Coord current){
	if (getDirection().equals(WEST) || getDirection().equals(EAST)){
	    setDefaultDirection();
	}
	if(getDirection().equals(NORTH)){
            if (!(oracle.coordFree((current.add(NORTH))))){
                direction = SOUTH;
                return SOUTH;
            }
            direction = NORTH;
            return NORTH;
        }

        if(getDirection().equals(SOUTH)){
            if (!(oracle.coordFree((current.add(SOUTH))))){
                direction = NORTH;
                return NORTH;
            }
            direction = SOUTH;
            return SOUTH;
	}
        return ORIGIN;
    }

    // return the direction when driving
    public Coord drive(Coord current, Coord goal){
	if((current.row == goal.row) && (current.col == goal.col)){
	    return ORIGIN;
	}
	if(current.row != goal.row){
	    if(current.row < goal.row){
		if ((oracle.coordFree((current.add(SOUTH))))){
		    direction = SOUTH;	
		    return SOUTH;
		}
	    }
	    else if(current.row > goal.row){
		if ((oracle.coordFree((current.add(NORTH))))){
		    direction = NORTH;
		    return NORTH;
		}
	    }
	}
	if(current.col != goal.col){
	    if(current.col < goal.col){
		if ((oracle.coordFree((current.add(EAST))))){
		    direction = EAST;
		    return EAST;
		}
            }
            else if(current.col > goal.col){
		if ((oracle.coordFree((current.add(SOUTH))))){
		    direction = WEST;
		    return WEST;
		}
            }
	}
	return ORIGIN;
    }
}
