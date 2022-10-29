package by.grsu.dzhivushko.cars;

import java.sql.Timestamp;
import java.util.Date;

import by.grsu.dzhivushko.cars.db.model.Car;

public class Main {

	public static void main(String[] args) {
		Car car = new Car();
		car.setCreated(new Timestamp(new Date().getTime()));
		car.setId(1);
		car.setModelId(2);
		car.setVin("4Y1SL65848Z411439");
		System.out.println(car.toString());
	}
}
