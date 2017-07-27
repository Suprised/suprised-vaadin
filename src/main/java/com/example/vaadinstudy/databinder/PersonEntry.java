package com.example.vaadinstudy.databinder;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户Bean
 * 
 * @author AmyChen
 *
 */
public class PersonEntry implements Serializable {

	private static final long serialVersionUID = -1611378847264385837L;

	private int id ;
	private String name;
	private String password;
	private int age;
	private Date birthDay;
	private float salary;
	
	public PersonEntry() {
	}
	
	public PersonEntry(int id, String name) {
		this.id = id ;
		this.name = name ;
	}

	public PersonEntry(int id, String name, int age, Date birthDay, float salary) {
		this(id, name);
		this.age = age;
		this.birthDay = birthDay;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result
				+ ((birthDay == null) ? 0 : birthDay.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(salary);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonEntry other = (PersonEntry) obj;
		if (age != other.age)
			return false;
		if (birthDay == null) {
			if (other.birthDay != null)
				return false;
		} else if (!birthDay.equals(other.birthDay))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(salary) != Float.floatToIntBits(other.salary))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PersonEntry [id=" + id + ", name=" + name + "password" + password +  ", age=" + age
				+ ", birthDay=" + birthDay + ", salary=" + salary + "]";
	}
}
