# Sixtagram
6조 만의 SNS

## 화면 상세 내역

### 공통
value/strings를 이용해 다국어 적용하기
value/themes를 이용하여 폰트 사이즈와 스타일 그룹 만들어 관리하기
BottomNavigationBar를 사용하여 화면을 전환시킴, 전환 시 화면이동 애니메이션 동작함  


### SplashActivity - 서수현
LottieAnimation를 사용하여 움직이는 화면 구현
Handler()를 통해 특정 시간 후 로그인 액티비티로 이동  


### LoginActivity - 조원준
이메일을 입력하면 HashMap에 저장된 키값과 비교하여 저장된 인덱스 값을 리턴하고 인덱스 번째의 비밀번호와 입력된 비밀번호 비교하여 로그인 실행
isNullOrEmpty를 사용하여 입력란이 null인지 아닌지 체크함
circleImageView를 사용하여 메인 아이콘 이미지를 동그랗게 만듦
MaterialDesign의 EditText를 사용하여 비밀번호 표시 + 아이콘 넣기 기능을 적용함
회원가입 한 데이터를 ActivityReesultLauncher를 사용해 로그인화면 입력란에 자동으로 입력해줌  


### SignUpActivity - 조원준
싱글턴 패턴인 object를 사용하여 정보를 저장해 어디서든 더미 데이터 회원정보에 접근할 수 있게 만듦
dataClass를 사용하여 데이터를 관리함
hashMap을 사용하여 아이디 중복체크 기능 구현
isNullOrEmpty를 사용하여 입력란이 null인지 아닌지 체크함
MaterialDesign의 EditText를 사용하여 비밀번호 표시 + 아이콘 넣기 기능을 적용함
matches를 이용하여 작성된 비밀번호 값이 패턴과 일치하는지 체크함
addTextChangedListener가 EditText값이 변경되는 것을 인식하고 비밀번호, 비밀번호 확인란이 일치하는지 알려줌
ScrollView 사용  


### Splash2Activity - 서수현
ObjectAnimation을 사용하여 imageView가 정해진 위치 + 시간만큼 움직임
Handler()를 통해 특정 시간 후 멤버 액티비티로 이동  


### MemberActivity - 권민석
listView를 이용한 메인화면 내 멤버 정렬 및 스크롤 기능 구현
아이템 클릭 시 디테일 액티비티로 이동
update 메소드를 이용해 멤버 정보 수정
hashmap을 이용해 멤버들의 이메일 정보 받아오기
glide를 사용하여 원형 이미지 만들기  


### CalendarActivity - 이성진
setText()를 사용하여 토요일,일요일 일때 색상 변경
Intent를 이용한 화면 전환과 startActivityForResult를 이용한 결과값 가져오기
isnullorempty()를 활용하여 비어있는 공간 있을 시 이벤트 만들기
Spinner을 활용하여 카테고리 만들기
AlertDialog를 활용하여 삭제버튼 누를시 "삭제하시겠습니까"창 띄우기  


### CommunityActivity - 서수현
listView를 사용하여 화면에 데이터 리스트 보여주기
각 화면 클릭 시 디테일 화면으로 넘어감
Glide로 이미지 비율과 모서리 값 주기
디테일 화면 좋아요 클릭 시 하트 애니메이션 작동함
addView를 사용하여 모든 댓글 데이터 보여줌  


### GameActivity - 이동규
select를 이용해 버튼 클릭 시 텍스트나 이미지,버튼이 잠시 변하도록 하기
overridePendingTransition 함수를 이용해 Activity 전환 시 animation 구현하기
Runnable,Handler 객체를 통해 주기적으로 실행문 반복하기
Chronometer 위젯을 사용해 Activity에 시간 표시하기
Handler 객체를 사용해 Toast 메세지를 내가 원하는 시간만큼 띄워보기
SharedPreferences 패키지를 JSON 문자열과 함께 이용해 리스트 데이터 저장하기
Glide 사용하여 gif파일 Activity에서 표현하기  


----------------------------------------

## 앱 화면 별 시연 영상 과 자료
![Screenshot_20230824_103502](https://github.com/joye-seo/Sixtagram/assets/104261048/9dd7c367-1854-419e-8a3d-de240d45e6ca width="250" height="600")  


[앱 시연 영상](https://drive.google.com/file/d/1RESDeSCdvA2vTVAWodx0KBXNFz0f5-TU/view?usp=drive_link)  

[자료] (https://drive.google.com/drive/folders/1B3d2Y0tOb9OoQzbAcYND0qohmnDpI0ZS?usp=sharing)  


## 와이어프레임
[와이어프레임] (https://file.notion.so/f/s/3443d7e2-9b46-4715-9d1c-027b6bd0e04c/Untitled.png?id=dbf697e1-7d87-4c24-ab09-6d5309dd6301&table=block&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&expirationTimestamp=1692928800000&signature=MZ4OtoB7nLthYok6s9QMntFJdfrFPcHbkJvjtHu6pyA&downloadName=Untitled.png)  


## 개발자
권민석 mindori9097@gmail.com  

서수현 missu0119@gmail.com  

이동규 dklee1619@naver.com  

이성진 asdsad86642@gmail.com  

조원준 wonjun3026@naver.com  

