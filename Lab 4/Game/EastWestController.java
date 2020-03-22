public class EastWestController extends CarController{
    public EastWestController(CoordInfo oracle) {
        super(oracle);
    }

    public void setDefaultDirection(){
        direction = EAST;
    }

    // return the direction when roaming
    public Coord roam(Coord current){
	if (getDirection().equals(SOUTH) || getDirection().equals(NORTH)){
            setDefaultDirection();
        }
	if(getDirection().equals(EAST)){
            if (!(oracle.coordFree((current.add(EAST))))){
                direction = WEST;
                return WEST;
            }
            direction = EAST;
            return EAST;
        }

        if(getDirection().equals(WEST)){
            if (!(oracle.coordFree((current.add(WEST))))){
                direction = EAST;
                return EAST;
            }
            direction = WEST;
            return WEST;
            }
	return ORIGIN;
    }

    // return the direction when driving
    public Coord drive(Coord current, Coord goal){
	if((current.row == goal.row) && (current.col == goal.col)){
            return ORIGIN;
        }
        if(current.col != goal.col){
            if(current.col < goal.col){
		if ((oracle.coordFree((current.add(EAST))))){
		    direction = EAST;
		    return EAST;
		}
            }
            else if(current.col > goal.col){
		if ((oracle.coordFree((current.add(WEST))))){
                    direction = WEST;
		    return WEST;
		}
            }
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
	return ORIGIN;
    }
}
