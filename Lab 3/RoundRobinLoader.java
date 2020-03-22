public class RoundRobinLoader implements Loader{
    private int chooseCar = 0;
    /** choose which car can accommodate the item
     *  @param cars Array of TrainCars
     *  @param item What to load
     *  @return car index in range [0..cars.size) if item fits
     *         Loader.NOCAR if item cannot be loaded.
     *         Loader.NOCAR if cars is null
     *         Loader.NOCAR if item is null
     */
    public int chooseCar(TrainCar [] cars, Item item){

        if (cars == null) return Loader.NOCAR;
        if (item == null) return Loader.NOCAR;
	
	int i=1;
        for (int car = chooseCar; car <cars.length; ++car){
	    if (i == cars.length){
		break;
	    }

	    if (car == cars.length-1){
     		chooseCar = 0;
		if (cars[cars.length-1].canLoad(item)){
		    return car;
		}
		car=0;
		++i;
	    }

	    if (cars[car].canLoad(item)){
		++chooseCar;
		return car;
	    }

	    ++i;
	}
        return Loader.NOCAR; // No car found
    }
}
