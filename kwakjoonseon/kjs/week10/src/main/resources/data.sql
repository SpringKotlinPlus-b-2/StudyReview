insert into to_do_card(title, description, member_id, created_date_time)
values ('제목1', '할 일 상세1', 1, CURRENT_TIMESTAMP - 25),
       ('두번째 글 제목', '상세한 할 일 2', 1, CURRENT_TIMESTAMP - 20),
       ('3제목', '일3 상세 내용', 2, CURRENT_TIMESTAMP - 15),
       ('4제목', '4내용', 1, CURRENT_TIMESTAMP - 10),
       ('제목5', '내용5', 2, CURRENT_TIMESTAMP- 5);

insert into comment(content, member_id, created_date_time, to_do_card_id)
values ('댓글이야', '1', CURRENT_TIMESTAMP - 20, 1),
       ('2댓글', '1', CURRENT_TIMESTAMP - 15, 1),
       ('댓글 내용입니다', '1', CURRENT_TIMESTAMP - 10, 2),
       ('아무 댓글', '2', CURRENT_TIMESTAMP - 5, 2);
