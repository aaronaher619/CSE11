public class Train{
    private int totalNumCars = 0;
    private TrainCar[] train = new TrainCar [0];
    private Loader loadMaster;
    /** Constructor
     * @param loadMaster - any object that implements the Loader interface.
     *                     a null reference results in runtime errors and 
     *                     does not need to be checked.
     */
    public Train(Loader loadMaster){
	this.loadMaster = loadMaster;
    }

    /** Getter - Return the number of TrainCars in this Train
     * @return  how many TrainCars have been added to this Train
     */
    public int getNumCars(){
        return totalNumCars;
    }


    /** Getter -  Return the total weight of all cargo in all TrainCars 
     * @return  total weight of cargo, summed over all TrainCars
     */ 
    public double getWeight(){
	double totalWeightOfTrain = 0;
	for (int i=0; i<train.length; ++i){
	    totalWeightOfTrain = totalWeightOfTrain + train[i].getWeight();
	}
        return totalWeightOfTrain;
    }

    /** Getter - Return the weight of all cargo in a particular TrainCar 
     * @param car  car number starting from 0
     * @return  weight of TrainCar indicated by car. Return 0 if car 
     *           number does not exist	
     */ 
    public double getWeight(int car){
	if ((car > train.length) || (car < 0)){
            return 0;
        }
        return train[car].getWeight();
    }

    /** Getter - 2D array of Items that lists all cargo
     * @return 2D array of Items contained in each TrainCar. Row index
     *         is the car#.
     *    
     */
    public Item [][] getContents(){
	Item[][] manifestOfItems = new Item[train.length][10000];
	for (int i=0; i<train.length; ++i){
	    for (int j=0; j<train[i].getContents().length; ++j){
		manifestOfItems[i][j] = train[i].getContents()[j];
	    }
	}
	    
        return manifestOfItems;
    }

    /** Getter - 1D array of Items that lists all cargo of a particular 
     *           TrainCar
     * @param car car number starting from 0 
     * @return 1D array of Items contained in the specif TrainCar. 
     *         If car number does not exist, return null.
     */
    public Item [] getContents(int car){
	if ((car > train.length) || (car < 0)){
	    return null;
	}

	Item[] contentsOfTrainCar = train[car].getContents();
	return contentsOfTrainCar;
    }	

    /** Getter - Return TrainCar reference for specific car #
     * @param car car number starting from 0 
     * @return reference to the TrainCar instance at car index 
     *         If car number does not exist, return null.
     */
    public TrainCar getTrainCar(int car) {
        return train[car];
    }

    /** Add TrainCars to the Train, all with identical capacity
     * @param nCars  how many to add. Must be positive
     * @param maxWeight cargo capacity of the TrainCars to be added. 
     *                  Must be nonnegative
     *
     * <p> cars are numbered sequentially from 0.  The Nth car added has
     * index (N-1). (array ordering). addCars may be invoked any number 
     * of times.
     */
    public void addCars(int nCars, double maxWeight) {
	if ((maxWeight<0) || (nCars<0)){
	    return;
	}
	TrainCar[] newTrain = new TrainCar[nCars + train.length];
	
	for(int i=0; i<train.length; ++i){
	    newTrain[i] = train[i];
	}
	
	train = newTrain;
	
	for (int i = (train.length-nCars); i<train.length; ++i){
	    train[i] =  new TrainCar(maxWeight);
	}
	totalNumCars = totalNumCars + nCars;  
    }

    /** load item onto train
     *  @param item any object that implements Item
     *  @return 0/1. 0 if it could not load, 1 if it loads. 
     *          returns 0 if item is null. 
     *  
     *  <p>
     *  This method uses the Loader to determine which car in which
     *  to load an item. The loader might return Loader.NOCAR 
     *  If a valid car index is returned by the Loader, then this 
     *  method loads the item into the specific car.
     *   
     */
    public int load(Item item)
    {
	if (item == null){
	    return 0;
	}
	int car = loadMaster.chooseCar(train, item);
	if (car == -1){
	    return 0;
	}
	return train[car].load(item);
    }
    /** Convenience wrapper method around load
     *  @param qty number of items to load
     *  @param item any object that implements Item
     *  @return number of items successfully loaded [0..qty]
     *          returns 0 if item is null
     */
    public int load(int qty, Item item) {
	if (item == null){
	    return 0;
	}

	int numLoaded = 0;
	for (int i = 1; i<=qty; ++i){
	    if (load(item) == 1){
		++numLoaded;
	    }
	}
	return numLoaded;
    }
}
// vim: ts=4:sw=4:et
