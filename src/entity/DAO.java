/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.List;
import java.util.Optional;
/**
 *


 */
public interface DAO<T> 
{
    //Optional is a container used to contain not-null objects.
    //https://www.tutorialspoint.com/java8/java8_optional_class.htm
    Optional<T> get(int id);
    List<T> getAll();
    void insert(T t);
    void update(T t);
    void delete(T t);
    List<String> getColumnNames();
}
