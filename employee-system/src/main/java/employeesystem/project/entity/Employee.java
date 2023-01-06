package employeesystem.project.entity;

//import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "employees")
public class Employee {
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long Id;
	    @Column (name= "first_name", nullable = false)
		private String firstName;
	    
	    @Column (name= "middle_name", nullable = false)
		private String middleName;
	    
	    @Column (name= "last_name", nullable = false)
		private String lastName;
	    
	    @Column (name= "birth_date", nullable = false)
		private String birthDate;
	    
	    @Column (name= "position", nullable = false)
		private String position;
		
	    public Employee() {
	    	
	    }
	    
		public Employee(String firstName, String middleName, String lastName, String birthDate, String position) {
			super();
			this.firstName = firstName;
			this.middleName = middleName;
			this.lastName = lastName;
			this.birthDate = birthDate;
			this.position = position;
		}
		public Long getId() {
			return Id;
		}
		public void setId(Long id) {
			Id = id;
		}
		public String getFirstName() {
			return firstName;
		}
	
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getBirthDate() {
			return birthDate;
		}
		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		
		
		

}
