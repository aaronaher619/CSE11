import java.util.Scanner;

public class DummyGrid{
    public Grid main1;
    public Simulation sim;
    public void printGrid(String[][] grid){
	for (int i = 0; i<main1.row; ++i){
	    System.out.print("=");
	}
	System.out.println("==");
	for (int i = 0; i<main1.row; ++i){
	    System.out.print("|");
	    for (int j = 0; j<main1.col; ++j){
		System.out.print(grid[i][j]);
	    }
	
	System.out.println("|");
	}
	for (int i = 0; i<main1.row; ++i){
	    System.out.print("=");
	}
	System.out.println("==");
    }

    public void runInterpreter(){
	main1 = new Grid();
	main1.setDimensionSetup();
	main1.addRoboCarsSetup();
	main1.addObstaclesSetup();
	main1.addRiderSetup();
	
	/*
	Scanner scan = new Scanner(System.in);
	String cmd = "";
	
	while(true) {
	    System.err.print("> ");
	    if(!scan.hasNext()) { return; }
	    cmd = scan.nextLine();
	    String[] parts = cmd.split("\\s+");
	    if(parts.length == 0) { continue; }

	    switch(parts[0]) {
	    case "g":
		main1 = new Grid(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
		printGrid(main1.getGrid());
		sim = main1.getSimulation();
		sim.printGraphics();
		continue;

	    case "d":
		main1.update();
		printGrid(main1.getGrid());
		sim.printGraphics();
                continue;

	    case "c":
		main1.addCar(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);
		printGrid(main1.getGrid());
		sim.printGraphics();
                continue;

	    case "o":
		main1.addObstacle(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
		printGrid(main1.getGrid());
		sim.printGraphics();
                continue;

	    case "r":
		main1.addRider(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
		printGrid(main1.getGrid());
		sim.printGraphics();
                continue;

	    default:
		continue;
	    }
	*/
	
    }
    public static void main(String[] args) {
	DummyGrid myMain = new DummyGrid();
	myMain.runInterpreter();
    }
}

/*
    public boolean addRider(int row, int col){
        riders.add(new Rider());
        Coord coord = new Coord(row,col);
        riders.get(riders.size()-1).setLocation(coord);
        addRiderToGrid(riders.get(riders.size()-1));
	
	for(int i = 0; i<cars.size(); ++i){
	    cars.get(i).newRider(coord);
	}
	sim.gGrid.addGridObject(riders.get(riders.size()-1));
	addRider = true;
	return true;
    }

   

    public boolean addCar(int row, int col, String ctrl1){
	CarController ctrl = new RandomController(info);
	
	if (ctrl1.equals("e")){
	    ctrl = new EastWestController(info);
	}
	if (ctrl1.equals("n")){
	    ctrl = new NorthSouthController(info);
	}
	if (ctrl1.equals("r")){
            ctrl = new RandomController(info);
        }
	if (ctrl1.equals("m")){
            ctrl = new ManualController(info);
        }


	cars.add(new SharedCar(ctrl, grid));
	Coord coord = new Coord(row,col);
	cars.get(cars.size()-1).setLocation(coord);
	addCarToGrid(cars.get(cars.size()-1));
	sim.gGrid.addGridObject(cars.get(cars.size()-1));
	return true;
    }
   

    public boolean addObstacle(int row, int col){
        obstacles.add(new Obstacle());
        Coord coord = new Coord(row,col);
        obstacles.get(obstacles.size()-1).setLocation(coord);
        addObstacleToGrid(coord, obstacles.get(obstacles.size()-1));
	sim.gGrid.addGridObject(obstacles.get(obstacles.size()-1));
	return true;
    }

   

    public Grid getGridMain(){
	return this;
    }

    public void setup(String s){
	setup = new GridSetup(s);
	setDimensionSetup();
	addRoboCarsSetup();
	addObstaclesSetup();
	addRiderSetup();
    }

	    public Grid(int row, int col){
	this.row = row;
	this.col = col;
	dimension = new Coord(this.row, this.col);
	gridMain = new String[row][col];
	for (int i = 0; i<row; ++i){
	    for(int j = 0; j<col; ++j){
		gridMain[i][j] = " ";
	    }
	}
	gGrid = new GraphicsGrid(row, col, 20);
	sim = new Simulation(this);
	sim.setGGrid(gGrid);
    }

   
*/

