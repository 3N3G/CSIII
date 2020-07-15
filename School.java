import java.util.*;
import java.io.*;


public class School {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);    
		Student s1 = getStudent(input);
		System.out.println();
		Student s2 = getStudent(input);
		System.out.println();
		System.out.println("Student " + s1.getFullName() + " is taking courses " + Arrays.toString(s1.getCourses()));
		System.out.println("Student " + s2.getFullName() + " is taking courses " + Arrays.toString(s2.getCourses()));
		
		
		String[] inCommon = s1.inCommon(s2);
		if (inCommon.length == 0) {
			System.out.println("The students have no shared classes.");
		} else if (inCommon.length == 1) {
			System.out.println("The students have the following class in common:");
			System.out.println("   " + (1) + " - " + inCommon[0]);
		}
		else {
			System.out.println("The students have the following classes in common:");
			for (int i = 0; i < 8; i++) {
				if (inCommon[i] != null) {
					System.out.println("   " + (i+1) + " - " + inCommon[i]);
				}
			}
		}
	}
	
	public static Student getStudent(Scanner input) {
		String[] fullName = getName(input).split(" ", 2);
		String firstName = fullName[0];
		String lastName = "";
		if (fullName.length == 2) {
			lastName = fullName[1];
		} else {
			lastName = "";
		}
		int grade = MyUtils.getNumber(input, "What is the student's grade (9-12)? ", 9, 12);
		int courses = MyUtils.getNumber(input, "How many courses is this student taking (1-8)? ", 1, 8);
		Student s = new Student(firstName, lastName, grade, 8);
		String[] courseList = new String[8];
		for (int i = 1; i <= courses; i++) {
			String thisCourse = getCourseName(input, i);
			int periodIndex = getCoursePeriod(input, courseList, thisCourse, firstName);
			// should only return 1 to 8, so if it returns 10, I know that it got a bad response
			while (periodIndex == 10) { 
				thisCourse = getCourseName(input, i);
				periodIndex = getCoursePeriod(input, courseList, thisCourse, firstName);
			}
			courseList[periodIndex] = thisCourse;
		}
		for (int i = 0; i < 8; i++) {
			if (courseList[i] == null) {
				courseList[i] = "Free";
			}
		}
		for (int i = 0; i < 8; i++) {
			s.addCourse(courseList[i], i+1);
		}
		return s;
	}
	
	public static String getName (Scanner input) {
		
		while (true) {
			System.out.print("What is the student's name (firstname lastname)? ");
			String theLine = input.nextLine();
			if (theLine.equals("")) {
				System.out.println("Input is not valid, you need to enter some text");
			} else {
				return theLine;
			}
		}
	}
	
	public static String getCourseName (Scanner input, int i) {
		while (true) {
			System.out.print("Course " + i + " name? ");
			String theLine = input.nextLine();
			if (theLine.equals("")) {
				System.out.println("Input is not valid, you need to enter some text");
			} else {
				return theLine;
			}
		}
	}
	
	public static int getCoursePeriod(Scanner input, String[] courseList, String thisCourse, String firstName) {
		while (true) {
			int periodindex = MyUtils.getNumber(input, "Period for " + thisCourse + " (1-8)? ", 1, 8) - 1;
			if (courseList[periodindex] != null) {
				System.out.println(firstName + " is already taking a course in period " + (periodindex+1));
				return 10; 
				/*
				 * means that they errored. I only put getCoursePeriod seperately because I assumed 
				 * that if you had PE but chose a period the same as a different class, you would
				 * only have to redo the period, but apparently you have to end up rechoosing the name of 
				 * the class too, which makes no sense which is why i'm fixing it like this
				 * it should only return 1-8, so 10 can't be returned unless I want it to
				*/
			} else {
				return periodindex;
			}
		}
		
	}
	
	

}

