# 23rd-Study-Todo-List
## 1주차: In-Memory 기반 Todo-List 구현
- 사용 언어: Kotlin
- Spring Boot Version: 3.1.5
### 구현 내용
- Todo Class 생성
  ```  
  id: Long  
  title: String  
  content: String  
  progress: Enum<Progress> //PROCESSING, CANCELLED, COMPLETED  
  ```
  - HashMap을 이용해 In-memory 기반 레포지토리 구현  
- CRUD 구현
  - GET /api/v1/todo/{id}
    - id에 해당하는 todo 반환
  - GET /api/v1/todo/list
    - 모든 todo 리스트 반환
  - POST /api/v1/todo
    - todo 생성
  - PATCH /api/v1/todo
    - todo progress 업데이트
  - DELETE /api/v1/todo?id=0
    - id에 해당하는 todo 삭제
## 2주차: DB 기반 Todo-List 구현
### 구현내용
- MySQL, Spring Data Jpa로 전환
- JdbcTemplate 이용 bulk insert 구현

### API 변경 사항
- 에러 핸들링 변경
  - Business Exception 추가
  - ErrorCode 및 ErrorResponseDto 추가
- POST /api/v1/todo/bulk
  - RequestBody로 count 받아, count만큼 mock data 생성
  - 생성에 성공한 데이터 개수 리턴

### 추가 예정
- 테스트코드,,, 빨리 작성해보겠습니다..!
### 궁금증
- BusinessException에서 에러 분기를 보통 ErrorCode(Enum)을 통해 하는 편인데
, BusinessException을 상속받아 exception 자체를 분리하는 방식의 장점이 무엇인지 궁금합니다..! 
생각했던건 exceptionHandler에서 에러 처리를 분기해서 할 수 있다..? 또다른 장점은 무엇이 있을까요??