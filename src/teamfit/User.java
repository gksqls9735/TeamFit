package teamfit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class User implements Comparable<User>, Serializable {
	private String name;
	private String phone;
	private String id;
	private String password;
	private String matchId;
	private String instructor;

	public User() {
		this(null, null, null, null, null, null);
	}

	public User(String name, String phone, String id, String password, String matchId, String isInstructor) {
		super();
		this.name = name;
		this.phone = phone;
		this.id = id;
		this.password = password;
		this.matchId = matchId;
		this.instructor = isInstructor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passWord) {
		this.password = passWord;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public static boolean showInstructor(ArrayList<User> users) {
		boolean found = false;
		System.out.println("-----------------------------------------------");
		for (User data : users) {
			if (data.getInstructor().equals("강사")) {
				System.out.println(data.getName() + ", " + data.getId());
				found = true;
			}
		}
		System.out.println("-----------------------------------------------");
		if (!found) {
			System.out.println("강사 목록이 비었습니다.");
		}
		return found;
	}

	@Override
	public int compareTo(User o) {
		return this.id.compareToIgnoreCase(o.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		User people = null;
		if (obj instanceof User) {
			people = (User) obj;
			return this.id.equals(people.id);
		}
		return false;
	}

	@Override
	public String toString() {
		return "이름\t|" + name + "\n전화 번호\t|" + phone + "\n아이디\t|" + id +
				"\n비밀 번호\t|" + password + "\n매칭 ID\t|" + matchId + "\n강사 여부\t|" + instructor;
		
	}
	
//관리자 파일을 따로 생성
}
