import java.util.Random;
public class ManualController extends CarController{

    public ManualController(CoordInfo oracle) {
        super(oracle);
    }

    public void setDefaultDirection(){
        direction = EAST;
    }

    // return the direction when roaming
    public Coord roam(Coord current){
	return ORIGIN;
    }

    // return the direction when driving
    public Coord drive(Coord current, Coord goal){
        if((goal.row == 1) && (goal.col == 0)){
	    if(direction == EAST){
		direction = NORTH;
		return NORTH;
	    }
	    if(direction == NORTH){
		direction = WEST;
		return WEST;
	    }
	    if(direction == WEST){
		direction = SOUTH;
		return SOUTH;
	    }
	    else{
		direction = EAST;
		return EAST;
	    }
	}

	if((goal.row == 0) && (goal.col == 1)){
	    if(direction == EAST){
		direction = SOUTH;
		return SOUTH;
	    }
	    if(direction == SOUTH){
		direction = WEST;
		return WEST;
	    }
	    if(direction == WEST){
		direction = NORTH;
		return NORTH;
	    }
	    else{
		direction = EAST;
		return EAST;
	    }	
	}
	
	if((goal.row == 0) && (goal.col == 0)){
	    if(direction == SOUTH){
		direction = SOUTH;
		return SOUTH;
	    }
	    if(direction == NORTH){
		direction = NORTH;
		return NORTH;
	    }
	    if(direction == EAST){
		direction = EAST;
		return EAST;
	    }
	    else{
		direction = WEST;
		return WEST;
	    }	
        }
        return ORIGIN;
    }
}
