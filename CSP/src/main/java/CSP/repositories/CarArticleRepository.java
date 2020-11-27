package CSP.repositories;

import CSP.models.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarArticleRepository {
    private List<Car> carList = new ArrayList<>();

    public void addCar(Car car){
        carList.add(car);
    }

    public void removeCar(Car car){
        carList.remove(car);
    }

    public void updateCar(Car oldCar, Car newCar){
        carList.set(carList.indexOf(oldCar) , newCar);
    }

    public List<Car> getAllCars(){
        return carList;
    }
}
