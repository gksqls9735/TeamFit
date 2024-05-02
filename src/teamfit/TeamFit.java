package teamfit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TeamFit {

	public static String[] menu = new String[] { "마이페이지", "강의 신청", "강의 검색", "강의 신청 취소", "강의 전체 취소", "강사 1대1 매칭", "종료" };
	public static String[] logIn = new String[] { "로그인", "회원 가입" };
	public static String[] myPageMenu = new String[] { "개인 정보 확인", "정보 수정", "운동 신청 목록 출력", "계정 삭제", "매칭된 강사 정보확인",
			"강의 개설" };
	public static String[] updateUser = new String[] { "이름", "전화 번호", "비밀 번호" };
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<Exercise> exercise = new ArrayList<Exercise>();
	public static ArrayList<ExerciseCart> cart = new ArrayList<ExerciseCart>();
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		if (loadUsersFromFile() && loadExercisesFromFile() && loadCartsFromFile()) {
			System.out.println("데이터를 성공적으로 불러왔습니다.");
		} else {
			System.out.println("데이터를 불러오는데 실패했습니다.");
			return;
		}

		String id = null;
		boolean exit = false;

		// 로그인 혹은 회원가입 진행
		while (!exit) {
			System.out.print("로그인(1) 혹은 회원가입(2)을 진행해주세요: ");
			int num = sc.nextInt();
			sc.nextLine();
			switch (logIn[num - 1]) {
			case "로그인":
				id = logInUser();
				exit = true;
				break;
			case "회원 가입":
				signUpUser();
				id = logInUser();
				exit = true;
				break;
			}
		}
		// 메인 메뉴
		while (true) {
			showMenu();
			System.out.print("메뉴를 선택해주세요(1~7): ");
			int menuNum = sc.nextInt();
			sc.nextLine();
			if (menuNum < 1 || menuNum > 7) {
				System.out.println("1부터 7까지의 숫자를 입력하세요.");
			} else {
				switch (menu[menuNum - 1]) {
				case "마이페이지":
					myPage(id);
					break;
				case "강의 신청":
					enrollCourse(id);
					break;
				case "강의 검색":
					search();
					break;
				case "강의 신청 취소":
					cancelIndividualCourse(id);
					break;
				case "강의 전체 취소":
					cancelAllCourses(id);
					break;
				case "강사 1대1 매칭":
					matchInstructor(id);
					break;
				case "종료":
					System.out.println("TeamFit 프로그램을 종료합니다.");
					return;
				}
			}
		}
	}

	// 회원가입
	public static void signUpUser() {
		String[] userFile = new String[6];
		System.out.println("다음 사항들을 입력해주세요.");
		// 이름 입력
		System.out.print("이름: ");
		userFile[0] = sc.nextLine();

		// 전화번호 입력, 기존에 가입한 번호일 경우 return
		System.out.print("전화번호: ");
		userFile[1] = sc.nextLine();
		for (User data : users) {
			if (data.getPhone().equals(userFile[1])) {
				System.out.println("기존에 가입한 전화번호입니다.");
				return;
			}
		}

		// 아이디 입력, 중복확인
		boolean flag = false;
		while (!flag) {
			System.out.print("아이디: ");
			userFile[2] = sc.nextLine();
			for (User data : users) {
				if (data.getId().equals(userFile[2])) {
					System.out.println("중복된 아이디입니다.");
					System.out.println("아이디를 다시 입력해주세요.");
					flag = false;
					break;
				}
				flag = true;
			}
		}
		// 비밀번호 입력
		System.out.print("비밀번호: ");
		userFile[3] = sc.nextLine();

		// 1대1로 매칭된 아이디, 존재하지 않을 시 X로 설정
		userFile[4] = "X";

		// 강사, 일반회원 결정
		System.out.print("강사 여부(Y/N): ");
		String isInstructor = sc.nextLine();
		if (isInstructor.equalsIgnoreCase("Y")) {
			userFile[5] = "강사";
		} else {
			userFile[5] = "회원";
		}

		User user = new User(userFile[0], userFile[1], userFile[2], userFile[3], userFile[4], userFile[5]);
		users.add(user);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true));) {
			for (String data : userFile) {
				bw.write(data);
				bw.newLine();
			}
			bw.flush(); // 모든 데이터를 파일에 쓰고 파일을 닫기 전에 버퍼에 있는 데이터를 강제로 출력하기 위함
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("프로그램을 이용하시려면 로그인을 진행해주세요.");
		return;
	}

	// 로그인
	public static String logInUser() {
		// 아이디 비밀번호 입력
		System.out.println("로그인");
		System.out.print("아이디: ");
		String id = sc.nextLine();
		System.out.print("비밀번호: ");
		String password = sc.nextLine();

		for (User data : users) {
			if (data.getId().equals(id) && data.getPassword().equals(password)) {
				System.out.println("로그인 성공!");
				return id; // 로그인한 아이디 반환
			}
		}
		System.out.println("아이디 또는 비밀번호가 올바르지 않습니다.");
		System.out.println("프로그램을 종료합니다.");
		System.exit(0);
		return null;
	}

	// 마이페이지 메뉴
	public static void myPage(String id) {
		while (true) {
			myPageMenu();
			System.out.print("메뉴를 선택해주세요(1~6): ");
			int menu = sc.nextInt();
			sc.nextLine();
			if (menu < 0 || menu > 7) {
				System.out.println("올바른 메뉴를 선택해주세요.");
				return;
			}

			switch (myPageMenu[menu - 1]) {
			case "개인 정보 확인":
				showMyInfo(id);
				return;
			case "정보 수정":
				updateUserInfo(id);
				return;
			case "운동 신청 목록 출력":
				showMyCart(id);
				return;
			case "계정 삭제":
				deleteUser(id);
				System.exit(0);
			case "매칭된 강사 정보확인":
				showMyInstructor(id);
				return;
			case "강의 개설":
				createExercise(id);
				return;
			}
		}

	}

	// 매칭된 강사
	public static void showMyInstructor(String id) {
		for (User data : users) {
			if (data.getId().equals(id)) {
				System.out.println("강사 아이디\t|" + data.getMatchId());
			}
		}
	}

	// 개인정보출력
	public static void showMyInfo(String id) {
		for (User data : users) {
			if (data.getId().equals(id)) {
				System.out.println("-----------------------------------------------");
				System.out.println(data);
				System.out.println("-----------------------------------------------");
			}
		}
	}

	// 정보수정
	public static void updateUserInfo(String id) {
		for (User data : users) {
			if (data.getId().equals(id)) {
				System.out.print("1. 이름 / 2. 전화 번호 / 3. 비밀 번호 중 변경하실 사항을 선택해주세요: ");
				int num = sc.nextInt();
				sc.nextLine();

				switch (updateUser[num - 1]) {
				case "이름":
					System.out.print("변경하실 이름을 입력해주세요: ");
					String name = sc.nextLine();
					data.setName(name);
					saveUsersToFile();
					break;
				case "전화 번호":
					System.out.print("변경하실 전화번호를 입력해주세요: ");
					String phone = sc.nextLine();
					data.setPhone(phone);
					saveUsersToFile();
					break;
				case "비밀 번호":
					System.out.print("변경하실 비밀번호 입력해주세요: ");
					String password = sc.nextLine();
					data.setPassword(password);
					saveUsersToFile();
					break;
				}
			}
		}
	}

	// 계정 삭제
	public static void deleteUser(String id) {
		boolean success = false;
		System.out.print("계정을 삭제하시겠습니까?(Y/N): ");
		String answer = sc.nextLine();
		String matchId = null;
		// 해당 계정 삭제
		if (answer.equalsIgnoreCase("Y")) {
			for (User data : users) {
				if (data.getId().equals(id)) {
					matchId = data.getMatchId();
					users.remove(data);
					success = true;
				}
			}
			// 장바구니에서 삭제
			for (ExerciseCart data : cart) {
				if (data.getCartId().equals(id)) {
					cart.remove(data);
					success = true;
				}
			}
		}
		// 매칭된 사람에게서 정보 삭제
		for (User data : users) {
			if (data.getId().equals(matchId)) {
				data.setMatchId("X");
			}
		}
		if (success) {
			saveCartsToFile();
			saveUsersToFile();
			System.out.println("계정삭제가 완료되었습니다.");
			System.exit(0);
		}
		// 강사가 계정을 삭제하면 강의도 삭제
	}

	// 강의 개설
	public static void createExercise(String id) {
		for (User data : users) {
			if (data.getId().equals(id) && (!data.getInstructor().equals("강사"))) {
				System.out.println("일반 회원은 강의 개설이 불가능합니다.");
				return;
			}
		}
		String[] exerciseFile = new String[8];
		System.out.print("강의 코드를 입력해주세요: ");
		exerciseFile[0] = sc.nextLine();

		System.out.print("운동 종류를 입력해주세요: ");
		exerciseFile[1] = sc.nextLine();

		exerciseFile[2] = id;

		System.out.print("강의 위치를 입력해주세요: ");
		exerciseFile[3] = sc.nextLine();

		System.out.print("강의 날짜를 입력해주세요: ");
		exerciseFile[4] = sc.nextLine();

		System.out.print("강의 시작 시간을 입력해주세요: ");
		exerciseFile[5] = sc.nextLine();

		System.out.print("가격를 입력해주세요: ");
		exerciseFile[6] = sc.nextLine();
		int price = Integer.parseInt(exerciseFile[6]);

		System.out.print("최대 인원을 입력해주세요: ");
		exerciseFile[7] = sc.nextLine();
		int maxCapacity = Integer.parseInt(exerciseFile[7]);
		Exercise exercises = new Exercise(exerciseFile[0], exerciseFile[1], exerciseFile[2], exerciseFile[3],
				exerciseFile[4], exerciseFile[5], price, maxCapacity);
		exercise.add(exercises);

		boolean success = false;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("exercise.txt", true));) {
			for (String data : exerciseFile) {
				bw.write(data);
				bw.newLine();
			}
			bw.flush();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
		}
		if (success) {
			System.out.println("강의 개설이 완료되었습니다.");
		} else {
			System.out.println("강의 개설에 실패하였습니다.");
		}
	}

	// 마이페이지 메뉴출력
	public static void myPageMenu() {
		System.out.println("***********************************************");
		System.out.println("1. 개인 정보 확인\t\t4. 계정 삭제");
		System.out.println("2. 정보 수정\t\t5. 매칭된 강사 정보확인");
		System.out.println("3. 운동 신청 목록 출력\t6. 강의 개설");
		System.out.println("***********************************************");
	}

	// 강의 신청
	public static void enrollCourse(String id) {
		// 강의 목록을 저장한 exercise 리스트가 비어있을 시 return
		if (exercise.isEmpty()) {
			System.out.println("등록된 강의가 없습니다.");
			return;
		}
		String[] strCart = new String[2];
		strCart[0] = id;
		System.out.println("*********************강의목록*********************");
		for (Exercise data : exercise) {
			System.out.println("-----------------------------------------------");
			System.out.println(data);
			System.out.println("-----------------------------------------------");
		}
		System.out.println("***********************************************");
		System.out.print("신청할 강의의 코드를 입력해주세요: ");
		strCart[1] = sc.nextLine();
		// 중복 신청 확인
		for (ExerciseCart data : cart) {
			if (data.getCartId().equals(strCart[0]) && data.getCartCode().equals(strCart[1])) {
				System.out.println("해당 강의는 이미 신청하였습니다.");
				return;
			}
		}

		boolean found = false;
		ExerciseCart exerciseCart = null;
		for (Exercise data : exercise) {
			if (data.getCode().equals(strCart[1])) {
				found = true;
				System.out.println("-----------------------------------------------");
				System.out.println(data);
				System.out.println("-----------------------------------------------");
				System.out.print("해당 강의를 신청하시겠습니까?(Y/N): ");
				String answer = sc.nextLine();
				if (answer.equalsIgnoreCase("Y")) {
					if (data.getApplicantCount() >= data.getMaxCapacity()) {
						System.out.println("해당 강의의 정원이 가득 찼습니다.");
						return;
					} else {
						exerciseCart = new ExerciseCart(strCart[0], strCart[1]);
						cart.add(exerciseCart);
						data.setApplicantCount(data.getApplicantCount() + 1);
						System.out.println("강의 신청이 완료되었습니다.");
						saveCartsToFile();
						return;
					}
				}
			}
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("cart.txt", true));) {
			for (String data : strCart) {
				bw.write(data);
				bw.newLine();
			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!found) {
			System.out.println("해당하는 강의가 없습니다.");
		}
	}

	// 강의 검색
	public static void search() {
		// 해당하는 운동 종목 강의 목록 출력
		System.out.print("검색할 운동 종목을 입력하세요: ");
		String exName = sc.nextLine();
		boolean found = false;
		for (Exercise data : exercise) {
			if (data.getExerciseName().contains(exName)) {
				System.out.println("-----------------------------------------------");
				System.out.println(data);
				System.out.println("-----------------------------------------------");

				found = true;
			}
		}
		if (!found) {
			System.out.println("검색 결과가 없습니다.");
		}
		// 강의 신청 추가하기
	}

	// 강의 개별 취소
	public static void cancelIndividualCourse(String id) {
		// 장바구니 비었을 시 return
		boolean exit = showMyCart(id);
		if (exit == false)
			return;
		// 신청 인원 감소 추가하기
		System.out.print("취소할 강의 코드를 입력해주세요: ");
		String code = sc.nextLine();
		boolean found = false;
		// 아이디와 코드가 같은 것 삭제
		for (ExerciseCart data : cart) {
			if (data.getCartId().equals(id) && data.getCartCode().equals(code)) {
				found = true;
				System.out.print("해당 강의를 삭제하시겠습니까?(Y/N): ");
				String answer = sc.nextLine();
				if (answer.equalsIgnoreCase("Y")) {
					cart.remove(data);
					saveCartsToFile();
					System.out.println("신청을 취소하였습니다.");
					return;
				}
			}
		}
		if (!found) {
			System.out.println("해당 강의를 신청하지 않았습니다.");
		}
	}

	// 강의 전체 취소
	public static void cancelAllCourses(String id) {
		boolean exit = showMyCart(id);
		if (exit == false)
			return;
		// 신청 인원 감소 추가하기
		System.out.print("신청목록을 비우시겠습니까?(Y/N): ");
		String answer = sc.nextLine();

		// 리스트에서 삭제(해당하는 id의 신청 목록 삭제)
		if (answer.equalsIgnoreCase("Y")) {
			Iterator<ExerciseCart> iterator = cart.iterator();
			while (iterator.hasNext()) {
				ExerciseCart data = iterator.next();
				if (data.getCartId().equals(id)) {
					iterator.remove();
				}
			}
			saveCartsToFile();
		}
		showMyCart(id);
	}

	// 1대1 강사 매칭
	public static void matchInstructor(String id) {
		// 강사 정보
		boolean exit = User.showInstructor(users);
		if (exit == false)
			return;

		// 매칭된 강사 유무 확인
		for (User data : users) {
			if (data.getId().equals(id)) {
				if (!data.getMatchId().equals("X")) {
					System.out.println("이미 강사가 매칭되었습니다.");
					return;
				}
			}
		}
		String instructorId = null;
		boolean found = false;
		while (true) {
			System.out.print("원하는 강사의 아이디를 입력해주세요: ");
			instructorId = sc.nextLine();

			found = false;

			for (User data : users) {
				if (data.getInstructor().equals("강사") && data.getId().equals(instructorId)) {
					if (!data.getMatchId().equals("X")) {
						System.out.println("해당 강사는 이미 다른 사용자와 매칭되어 있습니다.");
						return;
					}
					System.out.println("강사와 매칭되었습니다. 강사 ID: " + instructorId);
					for (User user : users) {
						if (user.getId().equals(id)) { // 회원의 matchIdp에 강사 Id 저장
							user.setMatchId(instructorId);
						}
						if (user.getId().equals(instructorId)) { // 강사의 matchId에 회원 Id 저장
							user.setMatchId(id);
						}
					}
					data.setMatchId(id);
					saveUsersToFile();
					return;
				}
			}
			if (!found) {
				System.out.println("존재하지 않는 강사이빈다.");
			}
		}
	}

	// 메인 메뉴 출력
	public static void showMenu() {
		System.out.println("***********************************************");
		System.out.println("\t\t    TeamFit");
		System.out.println("***********************************************");
		System.out.println("1. 마이페이지\t\t5. 강의 전체 취소");
		System.out.println("2. 강의 신청\t\t6. 강사 1대1 매칭");
		System.out.println("3. 강의 검색\t\t7. 종료");
		System.out.println("4. 강의 신청 취소\t\t");
		System.out.println("***********************************************");
	}

	// 강의 신청 목록 출력
	public static boolean showMyCart(String id) {
		boolean found = false;
		for (ExerciseCart data : cart) {
			if (data.getCartId().equals(id)) {
				System.out.println("강의 코드\t|" + data.getCartCode());
				found = true;
			}
		}
		if (!found) {
			System.out.println("신청목록이 비었습니다.");
		}
		return found;
		// Exercise for문으로 해당 강의 정보 출력
	}

	// 유저 정보 저장
	public static boolean saveUsersToFile() {
		try (FileWriter fw = new FileWriter("users.txt")) {
			for (User user : users) {
				fw.write(user.getName() + "\n");
				fw.write(user.getPhone() + "\n");
				fw.write(user.getId() + "\n");
				fw.write(user.getPassword() + "\n");
				fw.write(user.getMatchId() + "\n");
				fw.write(user.getInstructor() + "\n");
			}
			return true;
		} catch (IOException e) {
			System.out.println("파일 저장 실패: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("오류 발생: " + e.getMessage());
			return false;
		}
	}

	// 유저 정보 불러오기
	public static boolean loadUsersFromFile() {
		try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String name = line.trim(); // trim(): 문자열의 앞뒤 공백을 제거하여 데이터를 더 정확하게 처리
				String phone = br.readLine().trim();
				String id = br.readLine().trim();
				String password = br.readLine().trim();
				String matchId = br.readLine().trim();
				String instructor = br.readLine().trim();
				User user = new User(name, phone, id, password, matchId, instructor);
				users.add(user);
			}
			return true;
		} catch (IOException e) {
			System.out.println("파일 읽기 실패: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("오류 발생: " + e.getMessage());
			return false;
		}
	}

	// 강의 정보 저장
	public static boolean saveExercisesToFile() {
		// 신청 인원 추가하기
		try (FileWriter fw = new FileWriter("exercise.txt")) {
			for (Exercise data : exercise) {
				fw.write(data.getCode() + "\n");
				fw.write(data.getExerciseName() + "\n");
				fw.write(data.getInstructorName() + "\n");
				fw.write(data.getLocation() + "\n");
				fw.write(data.getDate() + "\n");
				fw.write(data.getStartTime() + "\n");
				fw.write(data.getPrice() + "\n");
				fw.write(data.getMaxCapacity() + "\n");
			}
			return true;
		} catch (IOException e) {
			System.out.println("파일 저장 실패: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("오류 발생: " + e.getMessage());
			return false;
		}
	}

	// 강의 정보 불러오기
	public static boolean loadExercisesFromFile() {
		try (BufferedReader br = new BufferedReader(new FileReader("exercise.txt"))) {
			// 신청 인원 추가하기
			String line = null;
			while ((line = br.readLine()) != null) {
				String code = line.trim();
				String exerciseName = br.readLine().trim();
				String instructorName = br.readLine().trim();
				String location = br.readLine().trim();
				String date = br.readLine().trim();
				String startTime = br.readLine().trim();
				int price = Integer.parseInt(br.readLine().trim());
				int maxCapacity = Integer.parseInt(br.readLine().trim());
				Exercise exercises = new Exercise(code, exerciseName, instructorName, location, date, startTime, price,
						maxCapacity);
				exercise.add(exercises);
			}
			return true;
		} catch (IOException e) {
			System.out.println("파일 읽기 실패: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("오류 발생: " + e.getMessage());
			return false;
		}
	}

	// 장바구니 저장
	public static boolean saveCartsToFile() {
		try (FileWriter fw = new FileWriter("cart.txt")) {
			for (ExerciseCart data : cart) {
				fw.write(data.getCartId() + "\n");
				fw.write(data.getCartCode() + "\n");
			}
			return true;
		} catch (IOException e) {
			System.out.println("파일 저장 실패: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("오류 발생: " + e.getMessage());
			return false;
		}
	}

	// 장바구니 불러오기..
	public static boolean loadCartsFromFile() {
		try (BufferedReader br = new BufferedReader(new FileReader("cart.txt"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String cartId = line.trim();
				String cartCode = br.readLine().trim();
				ExerciseCart carts = new ExerciseCart(cartId, cartCode);
				cart.add(carts);
			}
			return true;
		} catch (IOException e) {
			System.out.println("파일 읽기 실패: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("오류 발생: " + e.getMessage());
			return false;
		}
	}

}
