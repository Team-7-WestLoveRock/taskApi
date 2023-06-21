# taskApi

Task-api는 프로젝트, 작업, 코멘트, 태그, 마일스톤 정보를 관리합니다. 이 서비스에서는 Project, Task, Comment, Tag, Milestone이라는 객체들을 중심으로 도메인을 모델링합니다.

## 요구사항

- Project, Task, Comment, Tag, Milestone 정보를 관리하는 API를 제공합니다.

- 인증 처리는 Gateway 서비스에 위임합니다.

- 이러한 각각의 서비스는 독립적으로 배포되고 확장될 수 있어야 하며, 서로의 독립성을 유지하면서 효과적으로 통신할 수 있어야 합니다.

## ERD
![taskapi-erd](https://github.com/Team-7-WestLoveRock/taskApi/assets/76582376/a9e6b7cd-a0b1-48ae-9da9-4f333d221ff4)

# API SPEC

## Project  
**GET /project/api/projects/{userId}**   
ex) 사용자가 포함되어있는 프로젝트 조회  
Response
```json
[
{
    "projectId": 1,
    "name": "dooray 미니 프로젝트",
    "description": "김서현, 김정민, 추만석의 두레이 미니프로젝트",
    "state": "진행",
    "createdAt": "2023-04-28T05:12:33"
},
{
    "projectId": 2,
    "name": "미니 dooray 프로젝트",
    "description": "김정민, 김서현, 추만석의 미니두레이 프로젝트",
    "state": "진행",
    "createdAt": "2023-04-29T05:12:33"
}
]
```

**GET /project/api/projects/{projectId}**  
ex) 특정 프로젝트 조회  
Response
```json
{
    "projectId": 1
    "name": "dooray 미니 프로젝트",
    "description": "김서현, 김정민, 추만석의 두레이 미니프로젝트",
    "state": "진행",
    "createdAt": "2023-04-28T05:12:33"
}
```

**POST /project/api/projects**  
ex) 프로젝트 생성  
Request
```json
{
    "name": "Mini Dooray",
    "description": "awesome mini dooray project",
    "userId": "westloverock"
}
```
Response
```json
{
    "createdAt": "2023-04-28T05:12:33"
}
```

**POST /project/api/projects/{projectId}**  
ex) 프로젝트 멤버 추가  
Request
```json
[
    {"userId": "user1", "authority": "MEMBER"},
    {"userId": "user2", "authority": "MEMBER"},
    {"userId": "user3", "authority": "MEMBER"},
    {"userId": "user4", "authority": "MEMBER"}
]
```
Response
```json
{
    "count": 4
}

```

**POST /project/api/projects/{projectId}/{userId}**  
ex) 프로젝트 멤버 권한 변경  
Request
```json
{
    "authority": "ADMIN"
}
```
Response
```json
{
    "authority": "ADMIN"
}
```


## Tag
**GET /project/api/projects/{projectId}/tags**
ex) 태그 조회  
Response
```json
[
    {"name": "태그1", "color": "#FFEB45"},
    {"name": "태그2", "color": "#RFF471"},
    {"name": "태그3", "color": "#FFEB45"}
]
```

**POST /project/api/projects/{projectId}/tag**  
ex) 태그 생성  
Request
```json
{
    "name": "태그1",
    "color": "##FF47"
}
```


**PUT /project/api/projects/{projectId}/tag/{tagId}**  
ex) 태그 수정  
Request
```json
{
    "name": "수정된 태그",
    "color": "#FFEB45"
}
```


**DELETE /project/api/projects/{projectId}/tag/{tagId}**  
ex) 태그 삭제

---

## Milestone
**GET /project/api/projects/{projectId}/milestones**
ex) 마일스톤 조회  
Response
```json
[
    {
        "name": "두레이 이해하기",
        "startDate": "2023-06-06",
        "endDate": "2023-06-10"
    },
    {
        "name": "두레이 구현하기",
        "startDate": "2023-06-10",
        "endDate": "2023-12-31"
    }
]
```

**POST /project/api/projects/{projectId}/milestone**  
ex) 마일스톤 생성  
Request
```json
{
    "name": "두레이 이해하기",
    "startDate": "2023-06-06",
    "endDate": "2023-06-10"
}
```


**PUT /project/api/projects/{projectId}/milestone/{milestoneId}**  
ex) 마일스톤 수정  
Request
```json
{
    "name": "두레이 이해하기",
    "startDate": "2023-06-06",
    "endDate": "2023-06-25"
}
```


**DELETE /project/api/projects/{projectId}/milestone/{milestoneId}**  
ex) 마일스톤 삭제

---

## Task

**GET /project/api/projects/{projectId}/tasks**  
ex) 업무 조회  
Response
```json
[
    {
        "projectId": 1,
        "title": "Make Dooray",
        "registerUserId": "westloverock",
        "expirationDate": "2023-06-06T00:00:00",
        "content": "Implement ALL!",
        "priority": "매우 높음",
        "milestoneId": 1,
        "createdAt": "2023-06-06T00:00:00"
    },
    {
        "projectId": 2,
        "title": "Dooray 22",
        "registerUserId": "westloverock",
        "expirationDate": "2023-06-07T00:00:00",
        "content": "Implement ALL!",
        "priority": "높음",
        "milestoneId": null,
        "createdAt": "2023-06-07T00:00:00"
    }
]
```

**GET /project/api/projects/{projectId}/task/{taskId}**  
ex) 업무 조회  
Response
```json
{
    "projectId": 1,
    "title": "Make Dooray",
    "registerUserId": "westloverock",
    "expirationDate": "2023-06-06T00:00:00",
    "content": "Implement ALL!",
    "priority": "매우 높음",
    "milestoneId": 1,
    "createdAt": "2023-06-06T00:00:00"
}
```

**POST /project/api/projects/{projectId}/task**  
ex) 업무 생성 (Option: tags, milestone, expirationDate, priority, managerUsers, referenceUsers)      
Request
```json
{
    "projectId": 1,
    "managerUsers": [{"userId": "user1", "userId": "user2"}],
    "referenceUsers": [{"userId": "user3", "userId": "user4"}],
    "title": "Make Dooray",
    "registerUserId": "westloverock",
    "expirationDate": "2023-06-06T00:00:00",
    "content": "Implement ALL!",
    "priority": "매우 높음",
    "tags": [{"tagId": "1", "tagId": "2"}],
    "milestoneId": 1
}
```
Response
```json
{
    "createdAt": "2023-06-06T00:00:00"
}
```


**POST /project/api/projects/{projectId}/task/{taskId}**  
ex) 업무 수정 (+TaskLogs 데이터 추가 필요, + TaskAuthority 변경)  
Request
```json
{
    "projectId": 1,
    "managerUsers": [{"userId": "user21", "userId": "user22"}],
    "referenceUsers": [{"userId": "user31", "userId": "user41"}],
    "title": "Dooray Make",
    "registerUserId": "westloverock",
    "expirationDate": "2023-06-07T00:00:00",
    "content": "Implement ALL!",
    "priority": "매우 높음",
    "tags": [{"tagId": "1"}],
    "milestoneId": null
}
```
Response
```json
{
    "update_date": "2023-06-07T00:00:00"
}
```

**DELETE /project/api/projects/{projectId}/task/{taskId}**  
ex) 업무 삭제 (+TaskAuthority까지 같이 삭제)

---
## Comment

**GET /project/api/projects/{projectId}/task/{taskId}/comments**  
ex) 댓글 조회  
Response
```json
[
    {
        "userId": "user1",
        "content": "hello",
        "writtenDate": "2023-06-07T00:00:00",

    },
    {
        "userId": "user2",
        "content": "hello",
        "writtenDate": "2023-06-08T00:00:00",
    }
]
```
**POST /project/api/projects/{projectId}/task/{taskId}/comment**  
ex) 댓글 등록  
Request
```json
{
    "content": "good bye"
}
```

Response
```json
{
    "writtenDate": "2023-06-08T00:00:00"
}
```


**POST /project/api/projects/{projectId}/task/{taskId}/comment/{commentId}**  
ex) 댓글 수정  
Request
```json
{
    "content": "good bye"
}
```

Response
```json
{
    "writtenDate": "2023-06-08T00:00:00"
}
```

**DELETE /project/api/projects/{projectId}/task/{taskId}/comment/{commentId}**  
ex) 댓글 삭제 




















