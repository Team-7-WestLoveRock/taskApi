create table if not exists Projects
(
    id          int auto_increment,
    name        varchar(60) not null,
    description text        not null,
    state       varchar(4)  not null,
    create_at   timestamp   not null,
    primary key (id)
    );

create table if not exists Milestones
(
    id         int auto_increment,
    project_id int         not null,
    name       varchar(45) not null,
    start_date date        null,
    end_date   date        null,
    primary key (id),
    foreign key (project_id) references Projects (id)
    );

create table if not exists Project_Authorities
(
    project_id int         not null,
    user_id    varchar(45) not null,
    authority  varchar(45) not null,
    primary key (project_id, user_id),
    foreign key (project_id) references Projects (id)
    );

create table if not exists Tags
(
    id         int auto_increment,
    project_id int         not null,
    name       varchar(45) not null,
    color      varchar(45) not null,
    primary key (id),
    foreign key (project_id) references Projects (id)
    );

create table if not exists Tasks
(
    id               int auto_increment,
    project_id       int         not null,
    title            text        not null,
    register_user_id varchar(45) not null,
    expiration_date  timestamp   null,
    content          text        null,
    priority         varchar(10) null,
    milestone_id     int         null,
    created_at       timestamp   null,
    primary key (id),
    foreign key (milestone_id) references Milestones (id),
    foreign key (project_id) references Projects (id)
    );

create table if not exists Comments
(
    id          int auto_increment,
    task_id     int         not null,
    user_id     varchar(45) not null,
    content     varchar(45) not null,
    written_date timestamp   not null,
    primary key (id),
    foreign key (task_id) references Tasks (id)
    );

create table if not exists Task_Authorities
(
    task_id   int         not null,
    user_id   varchar(45) not null,
    authority varchar(45) not null,
    primary key (task_id, user_id),
    foreign key (task_id) references Tasks (id)
    );

create table if not exists Task_Logs
(
    update_date timestamp not null,
    task_id     int       not null,
    primary key (update_date, task_id),
    foreign key (task_id) references Tasks (id)
    );

create table if not exists Tasks_Tags
(
    task_id int not null,
    tag_id  int not null,
    primary key (task_id, tag_id),
    foreign key (tag_id) references Tags (id),
    foreign key (task_id) references Tasks (id)
    );
