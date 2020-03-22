import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Grid implements GridInfo, CoordInfo{
    public ArrayList<SharedCar> cars = new ArrayList<SharedCar>();
    public ArrayList<Rider> riders = new ArrayList<Rider>();
    public ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>(); 
    public ArrayList<CarController> controllers = new ArrayList<CarController>();
    public SharedCar player;

    public GridInfo grid = this;
    public CoordInfo info = this;

    public Coord dimension;
    public int row = 0;
    public int col = 0;
    public String[][] gridMain;
    

    public GridSetup setup;
    public Simulation sim;
    public GraphicsGrid gGrid;
    public boolean addRider = false;
    public boolean once = true;
    public boolean once1 = true;
    public Random randGen = new Random();
    
    public Grid(){
    }

    public void setDimension(Simulation sim, GridSetup setup){
	this.sim = sim;
	this.setup = setup;
	dimension = new Coord(setup.getDimension());
	this.row = dimension.row;
	this.col = dimension.col;
	gGrid = new GraphicsGrid(row, col, 20);
	sim.setGGrid(gGrid);
	
	gridMain = new String[row][col];
	for (int i = 0; i<row; ++i){
	    for(int j = 0; j<col; ++j){
		gridMain[i][j] = " ";
	    }
	}
	addPlayer();
    }

    public void setDimension(Simulation sim, int row, int col){
	this.sim = sim;
	dimension = new Coord(row,col);
	this.row = dimension.row;
	this.col = dimension.col;
	gGrid = new GraphicsGrid(row, col, 20);
	sim.setGGrid(gGrid);

	gridMain = new String[row][col];
	for (int i = 0; i<row; ++i){
	    for(int j = 0; j<col; ++j){
		gridMain[i][j] = " ";
	    }
	}
	addPlayer();
    }

    public boolean addPlayer(){
	Coord playerLoc = new Coord(row/2, col/2);
	if (setup == null){
	    playerLoc = new Coord(row/2, col/2);
	}
	else{
	    playerLoc = setup.getPlayer();
	}

	player = new SharedCar(new ManualController(info), grid);
	player.setLocation(playerLoc);
	player.setColor(Color.RED);
	addPlayerToGrid(player);
	return true;
    }
	
	
    public boolean addRider(){
	if (setup == null){
	    return true;
	}
	Coord riderLoc = setup.getRider();
	Rider rider = new Rider();
	rider.setLocation(riderLoc);
	riders.add(rider);
	addRiderToGrid(riders.get(0));
	addRider=true;
	for(int i = 0; i<cars.size(); ++i){
	    cars.get(i).newRider(riderLoc);
	}
	return true;
    }

    public boolean addNewRider(){
	Coord riderLoc = new Coord (randGen.nextInt(row),randGen.nextInt(col));
	while((!coordFree(riderLoc))){
	    riderLoc = new Coord (randGen.nextInt(row),randGen.nextInt(col));
	}
	riders.add(new Rider());
	riders.get(riders.size()-1).setLocation(riderLoc);
	addRiderToGrid(riders.get(riders.size()-1));
	//System.out.println(riderLoc);
	for(int i = 0; i<cars.size(); ++i){
	    cars.get(i).newRider(riderLoc);
	}
	addRider = true;
	return true;
    }

    public void updatePlayerToGrid(){
	Coord loc = new Coord(this.player.getLocation());
	int y = loc.row;
	int x = loc.col;
	if(gridMain[y][x] == " "){
	    gridMain[y][x] = this.player.getSymbol();
	}
	if(gridMain[y][x] == this.player.getSymbol()){
	    gridMain[y][x] = " ";
	}
    }

    public boolean addRoboCars(){
	if (setup == null){
	    return true;
	}
	
	Coord coord;
	Coord[] robocars = setup.getRobocars();
	String[] ctrls = setup.getControllers();
	//Converts the string array of controllers to a CarController arraylist
	for(int i = 0; i<ctrls.length; ++i){
	    if (ctrls[i].equals("east")){
		controllers.add(new EastWestController(info));
	    }
	    if (ctrls[i].equals("north")){
		controllers.add(new NorthSouthController(info));
	    }
	    if (ctrls[i].equals("random")){
		controllers.add(new RandomController(info));
	    }
	}
	//Adds cars to cars arraylist and to grid
	for(int i = 0; i<robocars.length; ++i){
	    cars.add(new SharedCar(controllers.get(i), grid));
	    cars.get(i).setLocation(robocars[i]);
	    addCarToGrid(cars.get(i));
	}
        return true;
    }

    public boolean addNewRoboCar(){
	Coord coord = new Coord (randGen.nextInt(row),randGen.nextInt(col));
	while((!coordFree(coord))){
	    coord = new Coord (randGen.nextInt(row),randGen.nextInt(col));
	}

	CarController x = null;
	int control = randGen.nextInt(3);
	if (control == 0){
	    x = new EastWestController(info) ;
	}
	if (control == 1){
	    x = new NorthSouthController(info);
	}
	if (control == 2){
	    x = new RandomController(info);
	}
	
	cars.add(new SharedCar(x, grid));
	cars.get(cars.size()-1).setLocation(coord);
	addCarToGrid(cars.get(cars.size()-1));
	Coord riderLoc = new Coord(riders.get(riders.size()-1).getLocation());
	if (addRider){
	    for(int i = 0; i<cars.size(); ++i){
		cars.get(i).newRider(riderLoc);
	    }
	}
	return true;
    }
	
    public boolean addObstacles(){
	if (setup == null){
	    return true;
	}
        Coord coord;
	Coord[] ob = setup.getObstacles();
        for(int i = 0; i<ob.length; ++i){
	    obstacles.add(new Obstacle());
	    obstacles.get(i).setLocation(ob[i]);
            coord = new Coord(ob[i]);
            addObstacleToGrid(coord, obstacles.get(i));
        }
        return true;
    }
    
    public boolean addNewObstacle(){
	Coord coord = new Coord (randGen.nextInt(row),randGen.nextInt(col));
	while((!coordFree(coord))){
	    coord = new Coord (randGen.nextInt(row),randGen.nextInt(col));
	}
	obstacles.add(new Obstacle());
	obstacles.get(obstacles.size()-1).setLocation(coord);
	addObstacleToGrid(coord, obstacles.get(obstacles.size()-1));
	return true;
    }
	
    public boolean addObstacleToGrid(Coord coord, Obstacle o){
	int x = coord.col;
        int y = coord.row;
        gridMain[y][x] = o.getSymbol();
        return true;
    }
    
    public boolean addCarToGrid(SharedCar car){
	Coord coord = new Coord(car.getLocation());
	int x = coord.col;
	int y = coord.row;
	gridMain[y][x] = car.getSymbol();
        return true;
    }
    public boolean addPlayerToGrid(SharedCar player){
	Coord coord = new Coord(player.getLocation());
	int x = coord.col;
	int y = coord.row;
	gridMain[y][x] = player.getSymbol();
        return true;
    }
	
    
    public boolean addRiderToGrid(Rider rider){
	Coord coord = new Coord(rider.getLocation());
	int x = coord.col;
	int y = coord.row;
	gridMain[y][x] = rider.getSymbol();
	return true;
    }

    public String[][] getGrid(){
	return gridMain;
    }

    public void update(){
	for(int i=0; i<cars.size(); ++i){
	    
	    Coord coord = new Coord(cars.get(i).getLocation());
            gridMain[coord.row][coord.col] = " ";
	    sim.gGrid.clearObjects();
	    cars.get(i).drive();
	    coord =  (cars.get(i).getLocation());
            addCarToGrid(cars.get(i));
	    sim.gGrid.addGridObject(cars.toArray(new SharedCar[cars.size()]));
	}
	if (addRider){
	    if (riders.size() > 0){
		sim.gGrid.addGridObject(riders.get(riders.size()-1));
	    } 
	}
	if (obstacles.size() > 0){
	    sim.gGrid.addGridObject(obstacles.toArray(new Obstacle[obstacles.size()]));
	}
	/*
	if(player.getLocation().equals(riders.get(riders.size()-1).getLocation())){
	    for(int i = 0; i<cars.size(); ++i){
		cars.get(i).roam();
	    }
	    riders.get(riders.size()-1).pickUp(player);
	    addRider = false;
	    //sim.playerRiders = sim.playerRiders + 1;
	}
	*/
	checkRiders();
    }

    public void checkLocation(){
	if (riders.size() == 0){
	    return;
	}
	if(player.getLocation().equals(riders.get(riders.size()-1).getLocation())){
	    for(int i = 0; i<cars.size(); ++i){
		cars.get(i).roam();
	    }
	    riders.get(riders.size()-1).pickUp(player);
	    if(addRider){
		sim.playerRiders = sim.playerRiders + 1;
		once1 = true;
	    }
	    addRider = false;
	}
	
    }
    public void checkRiders(){
	
	if (riders.size() == 0){
	    addNewRider();
	}
	if (!(riders.get(riders.size()-1).waiting())){
	    addNewRider();
	}
	if(once1){
	    //System.out.println(sim.totalRiders);
	    if (sim.totalRiders == 1){
		addNewRoboCar();
		once1= false;
	    }
	    
	    if (sim.totalRiders <10){
		return;
	    }
	    
	    if ((sim.totalRiders % 11) == 0){
		addNewRoboCar();
		once1= false;
	    }
	    
	    if ((sim.totalRiders % 10) == 0){
		addNewObstacle();
		sim.increaseTimeTick();
		once1= false;
	    }
	}
    }

    // Return true if SharedCar succesfully claimed the location
    public boolean claim(SharedCar car, Coord loc){
	if ((loc.row >=0) && (loc.row < dimension.row)){
            if ((loc.col >= 0) && (loc.col < dimension.col)){
                if(gridMain[loc.row][loc.col].equals(" ") || gridMain[loc.row][loc.col].equals("R")){
		    return true;
		}
	    }
	}
	return false;
    }
    
    // Return true if SharedCar  successfully loaded rider
    public boolean riderLoaded(SharedCar car){
	if (car.getLocation().equals(riders.get(riders.size()-1).getLocation())){
	    for(int i = 0; i<cars.size(); ++i){
		cars.get(i).roam();
	    }
	    riders.get(riders.size()-1).pickUp(car);
	    addRider = false;
	    sim.robocarRiders = sim.robocarRiders + 1;
	    once1 = true;
	}   
	return !(riders.get(riders.size()-1).waiting());
    }

    /** Determine if a Coordinate is free
     * @param loc location to query
     * @return  true if loc is in bounds and available
     *          else false.
     *          return false if loc is null
     */
    public boolean coordFree(Coord loc){
	if(loc == null) {return false;}
	if ((loc.row >=0) && (loc.row < dimension.row)){
            if ((loc.col >= 0) && (loc.col < dimension.col)){
                if(gridMain[loc.row][loc.col].equals(" ") || gridMain[loc.row][loc.col].equals("R")){
		return true;
		}
            }
        }
	return false;
    }
}