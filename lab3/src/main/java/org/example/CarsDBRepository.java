package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository{

    private JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();

    public CarsDBRepository(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        Connection con=dbUtils.getConnection();
        List<Car> cars=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Cars where brand=?")){
            preStmt.setString(1,manufacturerN);
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    int id=result.getInt("id");
                    String brand=result.getString("brand");
                    String model=result.getString("model");
                    int year=result.getInt("year");
                    Car car=new Car(brand,model,year);
                    car.setId(id);
                    cars.add(car);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
	    Connection con=dbUtils.getConnection();
        List<Car> cars=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Cars where year between ? and ?")){
            preStmt.setInt(1,min);
            preStmt.setInt(2,max);
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    int id=result.getInt("id");
                    String brand=result.getString("brand");
                    String model=result.getString("model");
                    int year=result.getInt("year");
                    Car car=new Car(brand,model,year);
                    car.setId(id);
                    cars.add(car);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        return cars;
    }

    @Override
    public void add(Car elem) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Cars (brand,model,year) values (?,?,?)")){
            preStmt.setString(1,elem.getBrand());
            preStmt.setString(2,elem.getModel());
            preStmt.setInt(3,elem.getYear());
            int result=preStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
    }

    @Override
    public void update(Integer id, Car elem) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Cars set brand=?, model=?, year=? where id=?")){
            preStmt.setString(1,elem.getBrand());
            preStmt.setString(2,elem.getModel());
            preStmt.setInt(3,elem.getYear());
            preStmt.setInt(4,id);
            int result=preStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
    }

    @Override
    public Iterable<Car> findAll() {
        Connection con=dbUtils.getConnection();
        List<Car> cars=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Cars")){
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    int id=result.getInt("id");
                    String brand=result.getString("brand");
                    String model=result.getString("model");
                    int year=result.getInt("year");
                    Car car=new Car(brand,model,year);
                    car.setId(id);
                    cars.add(car);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        return cars;
    }
}
