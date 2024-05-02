package teamfit;

import java.io.Serializable;
import java.util.Objects;

public class Exercise implements Comparable<Exercise>, Serializable {
	private String code;
	private String exerciseName;
	private String instructorName;
	private String location;
	private String date;
	private String startTime;
	private int price;
	private int maxCapacity;
	private int applicantCount;

	public Exercise() {
		this(null, null, null, null, null, null, 0, 0);
	}

	public Exercise(String code, String exerciseName, String instructorName, String location, String date,
			String startTime, int price, int maxCapacity) {
		super();
		this.code = code;
		this.exerciseName = exerciseName;
		this.instructorName = instructorName;
		this.location = location;
		this.date = date;
		this.startTime = startTime;
		this.price = price;
		this.maxCapacity = maxCapacity;
		this.applicantCount = 0;		//!!
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getApplicantCount() {
		return applicantCount;
	}

	public void setApplicantCount(int applicantCount) {
		this.applicantCount = applicantCount;
	}
	
	@Override
	public int compareTo(Exercise o) {
		return this.date.compareToIgnoreCase(o.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		Exercise exercise = null;
		if (obj instanceof Exercise) {
			exercise = (Exercise) obj;
			return this.code.equals(exercise.code);
		}
		return false;
	}

	@Override
	public String toString() {
		return "강의 코드\t|" + code + "\n운동 종목\t|" + exerciseName + "\n강사 이름\t|" + instructorName +
				"\n위치\t|" + location + "\n날짜\t|" + date + "\n시작 시간\t|" + startTime +
				"\n가격\t|" + price + "\n신청 인원\t|" + applicantCount + "/" + maxCapacity;
	}
	
	

}