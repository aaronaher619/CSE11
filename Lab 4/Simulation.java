import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.event.ChangeListener;

public class Simulation extends JFrame implements Runnable, ActionListener, KeyListener, ChangeListener{
    public Grid grid = new Grid();
    public GraphicsGrid gGrid;
    public TimeTick timer = new TimeTick(100, grid, this);
    public Thread thread;
    public GridSetup set;

    public JButton newGame = new JButton("New Game");
    public JButton pause = new JButton("Pause");
    private JLabel score = new JLabel();
    public JPanel tPanel = new JPanel();
    public JPanel mPanel = new JPanel();
    public JPanel bPanel = new JPanel();
    public JSlider timeSlider = new JSlider(10, 100, 10);

    public int row;
    public int col;
    public int playerRiders = 0;
    public int robocarRiders = 0;
    public int totalRiders = 0;
    public int x = 100;
    public boolean once = true;

    public Simulation(GridSetup set){
	setMinimumSize(new Dimension(500,500));
	this.set = set;
	grid.setDimension(this,set);
	grid.addRoboCars();
	grid.addObstacles();
	grid.addRider();

	run();
    }

    public Simulation(int row, int col){
	setMinimumSize(new Dimension(500,500));
	//this.set = set;
	this.row = row;
	this.col = col;
	grid.setDimension(this, row, col);
	run();
    }

    
    public void run(){
	thread = new Thread(timer);
	thread.start();
    }
    
    public void increaseTimeTick(){
	x = x-10;
	if(x == 0){
	    return;
	}
	timer.setTicks(x);
    }
    public void setGGrid(GraphicsGrid gGrid){
	this.gGrid = gGrid;
	
	tPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	mPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	bPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

	String text = String.format("Riders Loaded Player: %d Robots: %d", playerRiders, robocarRiders);
	score.setText(text);
	newGame.addActionListener(this);
	pause.addActionListener(this);
	mPanel.addKeyListener(this);
	timeSlider.addChangeListener(this);

	tPanel.add(score);
	mPanel.add(gGrid);
	mPanel.setFocusable(true);
	bPanel.add(newGame);
	bPanel.add(pause);
	bPanel.add(timeSlider);
	
	add(tPanel, BorderLayout.NORTH);
	add(mPanel, BorderLayout.CENTER);
	add(bPanel, BorderLayout.SOUTH);	
    }

    public void newGame(){
	if(set != null){
	    //System.out.println("New Game");
	    this.thread.stop();
	    setVisible(false);
	    Simulation sim = new Simulation(set);
	    timer = new TimeTick(100, grid, sim);
	}
	if(set == null){
	    //System.out.println("New Game");
	    this.thread.stop();
	    setVisible(false);
	    Simulation sim = new Simulation(row, col);
	    timer = new TimeTick(100, grid, sim);
	}
    }
	
    public void actionPerformed(ActionEvent e) {
	if(e.getSource() == newGame){
	    //perform action when newGame clicked
	    newGame();
	}
	if(e.getSource() == pause){
	    timer.changeState();
	    //System.out.println("pause");
	}
    }
    
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){
	grid.checkLocation();
	//System.out.println(e.getKeyChar());
	if(e.getKeyChar() == 'h'){
	    grid.updatePlayerToGrid();
	    grid.player.newRider(new Coord(1,0));
	    grid.player.drive();
	    grid.updatePlayerToGrid();
	}

	if(e.getKeyChar() == 'i'){
	    grid.updatePlayerToGrid();
	    grid.player.newRider(new Coord(0,1));
	    grid.player.drive();
	    grid.updatePlayerToGrid();
	}

	if(e.getKeyChar() == ' '){
	    grid.updatePlayerToGrid();
	    grid.player.newRider(new Coord(0,0));
	    grid.player.drive();
	    grid.updatePlayerToGrid();
	}
	    
    }

    
   public void stateChanged(ChangeEvent event) {
       int sliderVal = sliderVal = timeSlider.getValue(); 
       timer.setTicks(sliderVal);
   }


    public void update(){
	String text = String.format("Riders Loaded Player: %d Robots: %d", playerRiders, robocarRiders);
	score.setText(text);
	tPanel.add(score);
	if (once && (set == null)){
	    grid.addNewRider();
	    once = false;
	}
	totalRiders = playerRiders + robocarRiders;
	gGrid.addGridObject(grid.player);
	grid.checkLocation();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
	GridSetup setup = null;
	if(args.length == 1){
	    setup = new GridSetup(args[0]);
	    Simulation sim = new Simulation(setup);
	}
	if(args.length == 2){
	    int row = Integer.parseInt(args[0]);
	    int col = Integer.parseInt(args[1]);
	    Simulation sim = new Simulation(row, col);
	}
    }
}
