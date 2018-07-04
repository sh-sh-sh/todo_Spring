★★★1.MYSQL 설정★★★

DB URL = jdbc:mysql://192.168.0.213/todo_db?useSSL=no&characterEncoding=utf8
사용자명 = sh
비밀번호 = 12341234

테이블 생성문
create table todo_user(
    id varchar(20) not null,
	password varchar(128) not null,
    name varchar(20) not null,
    email varchar(40) not null,
    primary key(id)
);

create table todo_category(
    cat_id int not null,
    name varchar(20) not null,
    primary key(cat_id)
);

create table todo (
	idx int not null auto_increment,
    user_id varchar(20) not null,
    category int not null,
    title varchar(20) not null,
    content text,
    start_date datetime default now() not null,
    target_date datetime not null,
    create_date  datetime default now() not null,
    done boolean not null default false,
    
    primary key(idx),
    foreign key(user_id) references `todo_user`(id) on delete cascade,
    foreign key(category) references `todo_category`(cat_id)
);


★★★실행 관련 내용★★★
1.VMware를 실행해 데이터가 저장되어 있는 가상 서버를 구동한다.
2.이클립스에서 이 프로젝트를 우클릭해 실행 도구-Maven build를 누른다.
(Goals가 설정되어 있지 않으면 tomcat7:run으로 설정한다.)
3.서버가 구동된 것을 확인했으면,
http://localhost:8099/에 접속한다.
만약 구동한 컴퓨터가 아닌 다른 컴퓨터에서 접속해야 할 경우
윈도우 방화벽의 고급 설정-인바운드 규칙-새 규칙을 눌러 8099 포트 연결 허용 규칙을 추가한다.


4.로그인을 누르고, 로그인을 하거나 해당 페이지에서 회원가입을 눌러 회원가입을 한다.
로그인 시 Remember me를 누르면 id를 유효기간 한달인 쿠키로 저장되고,
다음에 로그인 페이지로 들어오면 remember me가 체크되어 있게 바뀐다.
체크를 풀고 로그인할 시 저장되어 있던 쿠키를 삭제(maxage를 0으로 만듬)하고,
다음 로그인 시 체크되어 있지 않게 된다.

로그인시 세션에 유저의 아이디가 저장되고, 로그아웃시 무효화 시킨다.

로그인 필터를 사용해 로그인 페이지와 메인 페이지 외의 모든 페이지를 로그인하지 않고 접속할 경우 경고 메세지가 포함된 로그인 페이지로 이동된다.

테스트용 아이디:user1
비밀번호:asdf


로그인 페이지의 디자인은
http://www.blueb.co.kr/?c=2/34&uid=4093
이곳에서 가져와 알맞게 변경하였다.

★로그인 후 접속 가능한 페이지

1.프로필
프로필을 볼 수 있으며, 유저 정보 수정과 탈퇴가 가능하다.

2.할일 보기
등록한 할일 목록을 볼 수 있다. 
오늘, 이번주, 이번달, 완료, 미완료 별로 나뉘어져 있으며,
(패러미터 값별로 일부분만 다른 페이지이고, jsp 파일 자체는 동일하다.)
완료, 미완료를 제외한 나머지 페이지에서는 진행도가 바로 표시된다.
제목을 누르면 해당 할일의 상세 페이지로 이동할 수 있다.
완료 버튼을 누르면 바로 DB의 해당 할일의 달성 여부가 수정되고 페이지를 다시 불러온다.
달성은 O, X, 지남의 세 가지 상태로 표시되는데,
O=달성한 경우, X=기한을 지나지 않았고 달성하지 않은 경우, 지남=기한을 지나고 달성하지 않은 경우이다.
그 외, 수정, 삭제 버튼을 눌러 수정과 삭제가 가능하다.

3.할일 추가
내용을 제외한 모든 항목을 입력하고 등록을 누르면 데이터베이스에 등록되어 할일 보기에서 등록한 할일을 볼 수 있다.
시작일 또는 종료일을 입력하는 부분을 누르면 날짜와 시간을 선택하는 것이 나오는데,
이것은 외부 Bootstrap-Material DateTimePicker라는 외부 파일을 적용한 것이다.
출처:https://github.com/T00rk/bootstrap-material-datetimepicker

★★★기타★★★

반응형 웹 디자인

로그인 페이지를 제외한 페이지들은 반응형 웹으로 만들어져 있어 작은 창에서도 무리없이 볼 수 있다.
할일 보기 페이지의 경우, 창의 크기가 가로 768픽셀 이하가 되면 테이블의 모양이 바뀌고,
테이블이 변형된 뒤 생긴 +모양 버튼을 클릭하면 기존에 표시되어 있던 내용이 창에 맞게 나타난다.

해당 기능은 FooTable이라는 외부 파일을 적용한 것이다.
출처 : https://fooplugins.github.io/FooTable/index.html