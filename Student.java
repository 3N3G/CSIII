import java.util.*;

//This class runs the behavior of a School object
//Code written by Gene Yang

public class Student {
	public static final String FREE = "Free";
	private String first;
	private String last;
	private int grade;
	private int max;
	private String[] courses;
	
	/* 
	 * Creates a Student given the first and last names, the grade, and the max number of courses
	 * Throws exception if no first name, no last name, grade outside the 1-12 range, 
	 * and when max is nonpositive.
	 * Also creates a String array for the courses
	 */
	public Student(String first, String last, int grade, int max) {
		if (first == null || last == null || grade < 1 || grade > 12 || max <= 0) {
			throw new IllegalArgumentException();
		}
		this.first = first;
		this.last = last;
		this.grade = grade;
		this.max = max;
		this.courses = new String[this.max];
		Arrays.fill(this.courses, FREE);
	}
	
	public String getFirstName() {
		return this.first;
	}
	
	public String getLastName() {
		return this.last;
	}
	

	//Joins the first and last name together, including if they have no first/last name
	
	public String getFullName() {
		if (this.first == "") {
			return this.last;
		} else if (this.last == "") {
			return this.first;
		} else {
			return this.first + " " + this.last;
		}
	}
	
	public int getGrade() {
		return this.grade;
	}
	
	//Sets the grade of a student, checking to make sure it's nonnegative
	public void setGrade(int grade) {
		if (grade <= 0) {
			throw new IllegalArgumentException("No negative grades!");
		} else {
			this.grade = grade;
		}
	}
	
	public String[] getCourses() {
		return this.courses;
	}
	
	//counts the number of classes that aren't a free
	public int getCourseCount() {
		int count = 0;
		for (String subj : this.courses) {
			if (subj != FREE) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public String toString() {
		return this.grade + ": " + this.getFullName() + " " + Arrays.toString(this.courses);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean[] equalities = new boolean[5]; // firstname, lastname, grade, max, and courses
		// did this for easier checking
		if (obj instanceof Student) {
			Student s2 = (Student)obj;
			equalities[0] = s2.getFirstName().equals(this.getFirstName());
			equalities[1] = s2.getLastName().equals(this.getLastName());
			equalities[2] = (s2.grade == this.grade);
			equalities[3] = (s2.max == this.max);
			equalities[4] = Arrays.equals(s2.getCourses(), this.getCourses());
			
			return (equalities[0] && equalities[1] && equalities[2] && equalities[3] && equalities[4]);
		}
		return false;
	}
	
	//Adds a course to a period, ensuring that the period has no other class, and is from 1 to 8
	public boolean addCourse(String course, int period) {
		if (course.equals("") || course == null || period < 1 || period > this.max) {
			return false;
		} else if (this.courses[period-1].equals(FREE)) {
			this.courses[period - 1] = course;
			return true;		
		}
		return false;
	}
	
	//Gives a String array of classes in common between two students
	public String[] inCommon(Student s2) {
		String[] courses1 = this.getCourses();
		String[] courses2 = s2.getCourses();
		String[] inCommon = new String[8];
		for (int i = 0; i < 8; i++) {
			if (courses1[i].equals(courses2[i])) {
				inCommon[i] = courses1[i];
			}
		}
		return inCommon;
	}
	
}
