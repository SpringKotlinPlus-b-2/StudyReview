insert into to_do_card(created_at, last_modified_at, deleted_at, title, description, member_id)
values (CURRENT_TIMESTAMP - 25, CURRENT_TIMESTAMP - 25, null, '제목1', '할 일 상세1', 1),
       (CURRENT_TIMESTAMP - 20, CURRENT_TIMESTAMP - 20, null, '두번째 글 제목', '상세한 할 일 2', 1),
       (CURRENT_TIMESTAMP - 15, CURRENT_TIMESTAMP - 15, null, '3제목', '일3 상세 내용', 2),
       (CURRENT_TIMESTAMP - 10, CURRENT_TIMESTAMP - 10, null, '4제목', '4내용', 1),
       (CURRENT_TIMESTAMP - 5, CURRENT_TIMESTAMP - 5, null, '제목5', '내용5', 2);

insert into comment(created_at, last_modified_at, deleted_at, content, member_id, to_do_card_id)
values (CURRENT_TIMESTAMP - 20, CURRENT_TIMESTAMP - 20, null, '댓글이야', 1, 1),
       (CURRENT_TIMESTAMP - 15, CURRENT_TIMESTAMP - 15, null, '2댓글', 2, 1),
       (CURRENT_TIMESTAMP - 10, CURRENT_TIMESTAMP - 10, null, '댓글 내용입니다', 1, 2),
       (CURRENT_TIMESTAMP - 5, CURRENT_TIMESTAMP - 5, null, '아무 댓글', 2, 2);

-- member 로컬 테스트 데이터
-- 1. email: test, password: test
-- 2. email: test2, password: test
insert into member(created_at, last_modified_at, deleted_at, email, password, nickname)
values (CURRENT_TIMESTAMP - 20, CURRENT_TIMESTAMP - 20, null, 'test', '$2a$10$4SGkZKr9t8xKCVaxMc/o7ugMbfcxZA00AszP7Z/mFVOkdZD0xZNaK', 'test'),
       (CURRENT_TIMESTAMP - 15, CURRENT_TIMESTAMP - 15, null, 'test2', '$2a$10$4SGkZKr9t8xKCVaxMc/o7ugMbfcxZA00AszP7Z/mFVOkdZD0xZNaK', 'test2');
