import java.lang.Object;
public class TrainCar{
	    
    private double currentWeight = 0;
    private double maxWeight;
    private Item[] contents = new Item[0];
    
    /** Constructor
     * @param maxW - max weight train car can hold
     */
    public TrainCar(double maxW){
	if (maxW<=0){
	    this.maxWeight=0;
	}
	else {
	    this.maxWeight = maxW;
	}
    }

    @Override
    /** Overrides toString in class java.lang.Object
     */
	public java.lang.String toString(){
        return null;
    }

    /** Getter - Returns the current weight of the train car
     * @return the current weight of the train car in KG
     */
    public double getWeight(){
	return currentWeight;
    }

    /** Getter - Returns the max weight of the train car
     * @return the max weight of the train car in KG
     */
    public double getMaxWeight(){
	return maxWeight;
    }

    /** Getter - Returns an array of item references that the train car contains
     * @return Array of Items currently loaded in this car. length of array indicates the number of items loaded.
     */
    public Item[] getContents(){
	Item[] contentsOfTrainCar = contents;
	return contentsOfTrainCar;
    }

    /** Getter - Returns a boolean of whether or not an item can be loaded on the train car
     * @param item - object to be tested for loading
     * @return true if item would not put this TrainCar over its maximum weight, false otherwise.
     * Return false if item is null. Return false if item weight is less then or equal to= 0.
     */
    public boolean canLoad(Item item){
	if (item == null){
	    return false;
	}
	else if (item.getWeight() <= 0){
	    return false;
	}
	else if ((currentWeight+item.getWeight()) <= maxWeight){
	    return true;
	}
	else{
	    return false;
	}
    }

    /** Getter - Either succesfully loads an item or it does not
     * @param item - any object that implements Item
     * @return 0/1. return 0 if item not loaded, 1 if loaded. returns 0 if item is null. returns 0 if canLoad would return false.
     */
    public int load(Item item){	if (item == null){
	    return 0;
	}
	else if (canLoad(item)==false){
	    return 0;
	}

	Item[] newContents = new Item[contents.length + 1];

	for(int i=0; i<contents.length; ++i){
	    newContents[i] = contents[i];
	}

	contents = newContents;

	contents[contents.length - 1] = item;

	if (contents[contents.length - 1] ==  item){
	    currentWeight = currentWeight + item.getWeight();
	    return 1;
	}

	else{
	    return 0;
	}
    }

    /** Getter - Determine how many of a particular Item is in this TrainCar
     * @param item - Reference to any object that implements Item
     * @return number of items in the TrainCar. null item should return 0.
     */
    public int getQuantity(Item item){
	if (item == null){
	    return 0;
	}
	int quantity = 0;

	for(int i=0; i<contents.length; ++i){
	    if (contents[i] == item){
		++quantity;
	    }
	}
	return quantity;
    }
}