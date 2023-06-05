# taskApi

Task-api는 프로젝트, 작업, 코멘트, 태그, 마일스톤 정보를 관리합니다. 이 서비스에서는 Project, Task, Comment, Tag, Milestone이라는 객체들을 중심으로 도메인을 모델링합니다.

## 요구사항

- Project, Task, Comment, Tag, Milestone 정보를 관리하는 API를 제공합니다.

- 인증 처리는 Gateway 서비스에 위임합니다.

- 이러한 각각의 서비스는 독립적으로 배포되고 확장될 수 있어야 하며, 서로의 독립성을 유지하면서 효과적으로 통신할 수 있어야 합니다.
