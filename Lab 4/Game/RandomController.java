import java.util.Random;
public class RandomController extends CarController{
    public boolean diagUp = false;
    
    public RandomController(CoordInfo oracle) {
        super(oracle);
    }

    public void setDefaultDirection(){
        direction = NORTH;
    }

    // return the direction when roaming
    public Coord roam(Coord current){
        Random randGen = new Random();
	int randNum = randGen.nextInt(4);
	if (randNum == 0){
	    return NORTH;
	}
	if (randNum == 1){
	    return SOUTH;
	}
	if (randNum == 2){
	    return EAST;
	}
	if (randNum == 3){
	    return WEST;
	}
	return ORIGIN;
    }

    // return the direction when driving
    public Coord drive(Coord current, Coord goal){
	/*
	Coord coord = current.dist(goal);
	if((current.row == goal.row) && (current.col == goal.col)){
	    return ORIGIN;
	}

	if(coord.row > coord.col){
	    if(current.row < goal.row){
		if ((oracle.coordFree((current.add(EAST))))){
		    direction = EAST;
		    return EAST;
		}
	    }
	    else if(current.row > goal.row){
		if ((oracle.coordFree((current.add(WEST))))){
		    direction = WEST;
		    return WEST;
		}  
	    }
	}

	if(coord.row < coord.col){
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
	*/

	//if(coord.row == coord.col){
	    if(diagUp){
		if(current.col < goal.col){
		    if ((oracle.coordFree((current.add(EAST))))){
			direction = EAST;
			diagUp = false;
			return EAST;
		    }
		}
		else if(current.col > goal.col){
		    if ((oracle.coordFree((current.add(WEST))))){
			direction = WEST;
			diagUp = false;
			return WEST;
		    }
		}
	    }
	    if(!diagUp){
		if(current.row < goal.row){
		    if ((oracle.coordFree((current.add(SOUTH))))){
			direction = SOUTH;
			diagUp = true;
			return SOUTH;
		    }
		}
		else if(current.row > goal.row){
		    if ((oracle.coordFree((current.add(NORTH))))){
			direction = NORTH;
			diagUp = true;
			return NORTH;
		    }
		}
	    }
	    
	    //}
	return ORIGIN;
    }
}
