package hiber.service;

import hiber.dao.CarDaoImp;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImp implements CarService {

    @Autowired
    private CarDaoImp carDaoImp;

    @Transactional
    @Override
    public void add(Car car) {
        carDaoImp.add(car);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> listCars() {
        return carDaoImp.listCars();
    }


}
