# TeamFit

## 설명
파일을 이용한 자바 기반 프로젝트입니다.
TeamFit는 사용자가 피트니스 루틴을 관리할 수 있도록 도와주는 자바 기반 프로젝트입니다. 
사용자는 운동을 검색하고 선택한 운동을 장바구니에 담아 관리할 수 있습니다.
사용자는 운동 강사와 1대1 매칭을 할 수 있습니다.

## 기능
- 사용자 관리
- 강의 검색
- 강의 신청 기능

## 프로젝트 구조
```
teamfit/
├── .classpath
├── .project
├── cart.txt
├── exercise.txt
├── users.txt
├── bin/
│   └── teamfit/
│       ├── Exercise.class
│       ├── ExerciseCart.class
│       ├── TeamFit.class
│       └── User.class
├── src/
│   └── teamfit/
│       ├── Exercise.java
│       ├── ExerciseCart.java
│       ├── TeamFit.java
│       └── User.java
└── images/
    ├── instructor_matching.png
    ├── lecture_registration1.png
    ├── lecture_registration2.png
    └── Membership_registration.png
```

## 시작

### 필수 조건
- Java Development Kit (JDK) 8 이상
- Git

### 설치
1. repository 복사:
    ```sh
    git clone https://github.com/yourusername/teamfit.git
    ```
2. project directory로 이동:
    ```sh
    cd teamfit
    ```

### 실행
1. 자바 소스 파일 컴파일:
    ```sh
    javac -d bin src/teamfit/*.java
    ```
2. 실행:
    ```sh
    java -cp bin teamfit.TeamFit
    ```

## 파일
- `src/teamfit/`: 프로젝트의 소스 코드가 포함된 디렉토리입니다.
- `bin/teamfit/`: 컴파일된 클래스 파일이 포함된 디렉토리입니다.
- `cart.txt`: 장바구니 데이터를 저장하는 데 사용되는 텍스트 파일입니다.
- `exercise.txt`: 운동 데이터를 저장하는 데 사용되는 텍스트 파일입니다.
- `users.txt`: 사용자 데이터를 저장하는 데 사용되는 텍스트 파일입니다.

## 스크린샷
콘솔에서 실행 중인 애플리케이션의 예시입니다:

### 회원 등록 및 로그인
![Membership_registration](https://github.com/gksqls9735/TeamFit/assets/166088728/0ca402d2-f0ea-4727-9ef6-199a55dd10f9)

### 강의 등록
![lecture_registration1](https://github.com/gksqls9735/TeamFit/assets/166088728/83a5c15d-949a-4fef-a08d-d72cc174163f)
![lecture_registration2](https://github.com/gksqls9735/TeamFit/assets/166088728/b5a729ff-de5a-4197-855d-bf59eb54f864)

### 강사 매칭
![instructor_matching](https://github.com/gksqls9735/TeamFit/assets/166088728/2703ee31-a082-4a83-a041-e1b73a45cff2)

## Contributing
1. Fork the repository.
2. Create your feature branch:
    ```sh
    git checkout -b feature/YourFeature
    ```
3. Commit your changes:
    ```sh
    git commit -m 'Add some feature'
    ```
4. Push to the branch:
    ```sh
    git push origin feature/YourFeature
    ```
5. Open a pull request.

## 라이센스
이 프로젝트는 MIT 라이센스 하에 라이센스가 부여됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

## 연락처
이메일 - [gksqls9735@gmail.com](mailto:gksqls9735@gmail.com)

프로젝트 링크: [https://github.com/gksqls9735/teamfit](https://github.com/gksqls9735/teamfit)
