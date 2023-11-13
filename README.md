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