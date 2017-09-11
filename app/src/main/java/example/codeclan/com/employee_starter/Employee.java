package example.codeclan.com.employee_starter;

/**
 * Created by user on 30/08/2017.
 */

import java.sql.ResultSet;

import db.SqlRunner;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private Department department;

    public Employee(String name, Department department, double salary) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void save() {
        String sql = String.format("INSERT INTO employees (name, department_id, salary) VALUES ('%s', %d, %7.2f);", this.name, this.department.getId(), this.salary);
        this.id = SqlRunner.executeUpdate(sql);
        SqlRunner.closeConnection();
    }

    public void delete() {
        String sql = String.format("DELETE FROM employees WHERE id = %d;", this.id);
        SqlRunner.executeUpdate( sql );
        SqlRunner.closeConnection();
    }

    public static void deleteAll() {
        String sql = "DELETE FROM employees;";
        SqlRunner.executeUpdate( sql );
        SqlRunner.closeConnection();
    }
    public void update() {
        String sql = String.format("UPDATE employees SET name = '%s', department_id = %d, salary = %7.2f WHERE id = %d;", this.name, this.department.getId(), this.salary, this.id);
        SqlRunner.executeUpdate( sql );
        SqlRunner.closeConnection();
    }

    public static void all() {
        String sql = "SELECT * FROM employees;";
        ResultSet rs = SqlRunner.executeQuery(sql);
        try{
            while( rs.next() ){
                String name = rs.getString("name");
                int department_id = rs.getInt("department_id");
                double salary = rs.getDouble("salary");
                System.out.println(name);
                System.out.println(department_id);
                System.out.println(salary);
            }
        } catch( Exception e ){
            System.err.println( e.getClass().getName() + " : " + e.getMessage() );
            System.exit(0);
        }finally{
            SqlRunner.closeConnection();
        }


    }
}
