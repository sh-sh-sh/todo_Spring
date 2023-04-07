�ڡڡ�1.MYSQL �����ڡڡ�

���̺� ������
create table todo_user
(
    id        varchar(20)                     not null        primary key,
    password  varchar(128)                    not null,
    name      varchar(20)                     not null,
    email     varchar(40)                     not null,
    enabled   tinyint(1)  default 1           not null,
    authority varchar(45) default 'ROLE_USER' not null
);

create table todo_category(
    cat_id int not null,
    name varchar(20) not null,
    primary key(cat_id)
);

insert into todo_db.todo_category (cat_id, name)
values  (1, '��Ȱ'),
        (2, '�о�'),
        (3, '����'),
        (4, '����'),
        (5, '����'),
        (6, '��Ÿ');

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




4.�α����� ������, �α����� �ϰų� �ش� ���������� ȸ�������� ���� ȸ�������� �Ѵ�.
�α��� �� Remember me�� ������ id�� ��ȿ�Ⱓ �Ѵ��� ��Ű�� ����ǰ�,
������ �α��� �������� ������ remember me�� üũ�Ǿ� �ְ� �ٲ��.
üũ�� Ǯ�� �α����� �� ����Ǿ� �ִ� ��Ű�� ����(maxage�� 0���� ����)�ϰ�,
���� �α��� �� üũ�Ǿ� ���� �ʰ� �ȴ�.

�α��ν� ���ǿ� ������ ���̵� ����ǰ�, �α׾ƿ��� ��ȿȭ ��Ų��.

�α��� ���͸� ����� �α��� �������� ���� ������ ���� ��� �������� �α������� �ʰ� ������ ��� ��� �޼����� ���Ե� �α��� �������� �̵��ȴ�.

�׽�Ʈ�� ���̵�:user1
��й�ȣ:asdf


�α��� �������� ��������
http://www.blueb.co.kr/?c=2/34&uid=4093
�̰����� ������ �˸°� �����Ͽ���.

�ڷα��� �� ���� ������ ������

1.������
�������� �� �� ������, ���� ���� ������ Ż�� �����ϴ�.

2.���� ����
����� ���� ����� �� �� �ִ�.
����, �̹���, �̹���, �Ϸ�, �̿Ϸ� ���� �������� ������,
(�з����� ������ �Ϻκи� �ٸ� �������̰�, jsp ���� ��ü�� �����ϴ�.)
�Ϸ�, �̿ϷḦ ������ ������ ������������ ���൵�� �ٷ� ǥ�õȴ�.
������ ������ �ش� ������ �� �������� �̵��� �� �ִ�.
�Ϸ� ��ư�� ������ �ٷ� DB�� �ش� ������ �޼� ���ΰ� �����ǰ� �������� �ٽ� �ҷ��´�.
�޼��� O, X, ������ �� ���� ���·� ǥ�õǴµ�,
O=�޼��� ���, X=������ ������ �ʾҰ� �޼����� ���� ���, ����=������ ������ �޼����� ���� ����̴�.
�� ��, ����, ���� ��ư�� ���� ������ ������ �����ϴ�.

3.���� �߰�
������ ������ ��� �׸��� �Է��ϰ� ����� ������ �����ͺ��̽��� ��ϵǾ� ���� ���⿡�� ����� ������ �� �� �ִ�.
������ �Ǵ� �������� �Է��ϴ� �κ��� ������ ��¥�� �ð��� �����ϴ� ���� �����µ�,
�̰��� �ܺ� Bootstrap-Material DateTimePicker��� �ܺ� ������ ������ ���̴�.
��ó:https://github.com/T00rk/bootstrap-material-datetimepicker

�ڡڡڱ�Ÿ�ڡڡ�

������ �� ������

�α��� �������� ������ ���������� ������ ������ ������� �־� ���� â������ �������� �� �� �ִ�.
���� ���� �������� ���, â�� ũ�Ⱑ ���� 768�ȼ� ���ϰ� �Ǹ� ���̺��� ����� �ٲ��,
���̺��� ������ �� ���� +��� ��ư�� Ŭ���ϸ� ������ ǥ�õǾ� �ִ� ������ â�� �°� ��Ÿ����.

�ش� ����� FooTable�̶�� �ܺ� ������ ������ ���̴�.
��ó : https://fooplugins.github.io/FooTable/index.html
