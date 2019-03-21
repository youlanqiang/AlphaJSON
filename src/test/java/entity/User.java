package entity;

import java.util.List;
import java.util.Set;

/**
 * @author youlanqiang
 * @version 1.0
 * @since 1.8
 */
public class User {

    private String name;

    private boolean man;

    private int age;

    private List<Autor> autors;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        man = man;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Autor> getAutors() {
        return autors;
    }

    public void setAutors(List<Autor> autors) {
        this.autors = autors;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", man=" + man +
                ", age=" + age +
                ", autors=" + autors +
                '}';
    }
}
