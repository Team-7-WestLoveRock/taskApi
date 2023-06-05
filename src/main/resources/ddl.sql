create table if not exists Projects
(
    id          int auto_increment
    primary key,
    name        varchar(30) charset utf8mb3 not null,
    description text                        not null,
    state       varchar(2) charset utf8mb3  not null,
    create_at   timestamp                   not null
    );

create table if not exists Milestones
(
    id         int auto_increment,
    project_id int         not null,
    name       varchar(45) not null,
    start_date date        null,
    end_date   date        null,
    primary key (id, project_id),
    constraint name_UNIQUE
    unique (name),
    constraint fk_Milestones_Projects1
    foreign key (project_id) references Projects (id)
    );

create index fk_Milestones_Projects1_idx
    on Milestones (project_id);

create table if not exists Project_Authorities
(
    project_id int         not null,
    user_id    varchar(45) not null,
    authority  varchar(45) not null,
    primary key (project_id, user_id),
    constraint fk_Project_Authorities_Projects1
    foreign key (project_id) references Projects (id)
    );

create index fk_Project_Authorities_Projects1_idx
    on Project_Authorities (project_id);

create table if not exists Tags
(
    id         int auto_increment,
    project_id int         not null,
    name       varchar(45) not null,
    color      varchar(45) not null,
    primary key (id, project_id),
    constraint name_UNIQUE
    unique (name),
    constraint fk_Tags_Projects1
    foreign key (project_id) references Projects (id)
    );

create index fk_Tags_Projects1_idx
    on Tags (project_id);

create table if not exists Tasks
(
    id               int auto_increment
    primary key,
    project_id       int         not null,
    title            text        not null,
    register_user_id varchar(45) not null,
    expiration_date  timestamp   null,
    content          text        null,
    priority         varchar(10) null,
    milestone_id     int         null,
    created_at       timestamp   null,
    constraint register_user_id_UNIQUE
    unique (register_user_id),
    constraint fk_Tasks_Milestones1
    foreign key (milestone_id) references Milestones (id),
    constraint fk_Tasks_Projects1
    foreign key (project_id) references Projects (id)
    );

create table if not exists Comments
(
    id         int auto_increment
    primary key,
    tasks_id   int         not null,
    user_id    varchar(45) not null,
    content    varchar(45) not null,
    created_at timestamp   not null,
    updated_at timestamp   null,
    constraint fk_Comments_Tasks1
    foreign key (tasks_id) references Tasks (id)
    );

create index fk_Comments_Tasks1_idx
    on Comments (tasks_id);

create table if not exists Task_Authorities
(
    task_id   int         not null
    primary key,
    user_id   varchar(45) not null,
    authority varchar(45) not null,
    constraint fk_Task_Authorities_Tasks1
    foreign key (task_id) references Tasks (id)
    );

create table if not exists Task_Logs
(
    update_date int not null,
    Tasks_id    int not null,
    primary key (update_date, Tasks_id),
    constraint fk_Task_Logs_Tasks1
    foreign key (Tasks_id) references Tasks (id)
    );

create index fk_Task_Logs_Tasks1_idx
    on Task_Logs (Tasks_id);

create index fk_Tasks_Milestones1_idx
    on Tasks (milestone_id);

create index fk_Tasks_Projects1_idx
    on Tasks (project_id);

create table if not exists Tasks_Tags
(
    task_id int not null,
    tag_id  int not null,
    primary key (task_id, tag_id),
    constraint fk_Tasks_has_Tags_Tags1
    foreign key (tag_id) references Tags (id),
    constraint fk_Tasks_has_Tags_Tasks1
    foreign key (task_id) references Tasks (id)
    );

create index fk_Tasks_has_Tags_Tags1_idx
    on Tasks_Tags (tag_id);

create index fk_Tasks_has_Tags_Tasks1_idx
    on Tasks_Tags (task_id);