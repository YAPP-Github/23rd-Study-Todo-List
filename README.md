# YAPP TODO-LIST STUDY

> Made By 극락코딩, 궁금하신 내용이 있다면 언제든 연락주세요!!

<hr>

### Based Dependency
* Java: 11
* Springboot: 2.7
<hr>
### ERD
![Untitled](https://github.com/minseo300/spring_cloud_demo/assets/76048180/1f762168-444f-4231-b17c-52332a77b798)

### API
* 회원가입: /signup [POST]
  {
    "name":"aaa",
    "email":"aaa@gmail.com",
    "password":"aaapw"
  }
* 로그인: /signin [POST]
  {
    "email":"aaa@gmail.com",
    "password":"aaapw"
  }
* todo 등록: /todo [POST]
  {
    "title":
    "contents":
    "memberId":
  }
* todo 수정: /todo/{todoId} [PATCH]
  {
    "itemId":
    "memberId":
    "title":
    "contents":
  }
* todo 삭제: /todo [DELETE]
  {
    "itemId":
    "memberId":
  }
* todo 단건 조회: /todo/{todoId} [GET]
* todo 다건 조회: /member/{memberId}/todo [GET]
