public class FairLoader implements Loader{
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
	
	int index = 0;
	while (index<cars.length){
	    if (cars[index].getWeight() == cars[index].getMaxWeight()){ //If car already full then skip car
		++index;
		continue;
	    }

	    else if(!(cars[index].canLoad(item))){ //If item cannnot fit in car then skip over it
                ++index;
		continue;
            }
	    else if((index==cars.length-1) && (cars[index].getWeight() == cars[index].getMaxWeight()) && (!(cars[index].canLoad(item)))){
		return Loader.NOCAR;
	    }
	    else{
		break;
	    }
	}

	if (index >= cars.length){ //Prevents index from going out of bounds
	    return Loader.NOCAR;
	}

	double minWeight = cars[index].getWeight();
	int minCarIndex = index;

	for(int car =0; car<cars.length; ++car){
	    if(cars[car].getWeight() == cars[car].getMaxWeight()){ //If car already full then skip car
		continue;
	    }

	    if(!(cars[car].canLoad(item))){ //If item cannnot fit in car then skip over it
	    	continue;
	    }
	    
	    if(cars[car].getWeight()<minWeight){ //checks for minimum weight of cars
		minWeight = cars[car].getWeight();
		minCarIndex = car;
	    }
	}
	
	if (cars[minCarIndex].canLoad(item)){
	    return minCarIndex;
	}
        return Loader.NOCAR; // No car found
    }
}