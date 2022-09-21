package comNopcommerceData;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class UserDataMapper {
	public static UserDataMapper getUserData() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.RESOURCES_SOURCE_FOLDER + "UserData.json"), UserDataMapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@JsonProperty("register")
	private Register register;
	
	static class Register {
		@JsonProperty("firstName")
		private String firstName;
		
		@JsonProperty("lastName")
		private String lastName;
		
		@JsonProperty("emailAddress")
		private String emailAddress;
		
		@JsonProperty("password")
		private String password;
		
		@JsonProperty("day")
		private String day;
		
		@JsonProperty("month")
		private String month;
		
		@JsonProperty("year")
		private String year;
		
		@JsonProperty("gender")
		private String gender;
	}

	public String getFirstName() {
		return register.firstName;
	}
	public String getLastName() {
		return register.lastName;
	}
	public String getEmailAddress() {
		return register.emailAddress;
	}
	public String getPassword() {
		return register.password;
	}
	public String getDay() {
		return register.day;
	}
	public String getMonth() {
		return register.month;
	}
	public String getYear() {
		return register.year;
	}
	public String getGender() {
		return register.gender;
	}
	
	@JsonProperty("login")
	private Login login;
	
	static class Login {
		@JsonProperty("username")
		private String username;
		
		@JsonProperty("password")
		private String password;
	}

	public String getLoginUsername() {
		return login.username;
	}
	
	public String getLoginPassword() {
		return login.password;
	}

	@JsonProperty("subjects")
	private List<Subject> subjects;
	
	public List<Subject> getSubjects(){
		return subjects;
	}
	
	public static class Subject{
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("point")
		private float point;

		public String getName() {
			return name;
		}

		public float getPoint() {
			return point;
		}
		
	}
}
