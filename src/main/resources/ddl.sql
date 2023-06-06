create table IF NOT EXISTS Projects
(
    id          int primary key auto_increment,
    name        varchar(30) not null,
    description text        not null,
    state       varchar(2)  not null,
    create_at   timestamp   not null
    );

create table IF NOT EXISTS Milestones
(
    id         int primary key auto_increment,
    project_id int         not null,
    name       varchar(45) not null,
    start_date date        null,
    end_date   date        null,
    unique (name),
    foreign key (project_id) references Projects (id)
    );

create table IF NOT EXISTS Project_Authorities
(
    project_id int         not null,
    user_id    varchar(45) not null,
    authority  varchar(45) not null,
    primary key (project_id, user_id),
    foreign key (project_id) references Projects (id)
    );

create table IF NOT EXISTS Tags
(
    id         int primary key auto_increment,
    project_id int         not null,
    name       varchar(45) not null,
    color      varchar(45) not null,
    unique (name),
    foreign key (project_id) references Projects (id)
    );

create table IF NOT EXISTS Tasks
(
    id               int primary key auto_increment,
    project_id       int         not null,
    title            text        not null,
    register_user_id varchar(45) not null,
    expiration_date  timestamp   null,
    content          text        null,
    priority         varchar(10) null,
    milestone_id     int         null,
    created_at       timestamp   null,
    unique (register_user_id),
    foreign key (milestone_id) references Milestones (id),
    foreign key (project_id) references Projects (id)
    );

create table IF NOT EXISTS Comments
(
    id         int primary key auto_increment,
    task_id    int         not null,
    user_id    varchar(45) not null,
    content    varchar(45) not null,
    created_at timestamp   not null,
    updated_at timestamp   null,
    foreign key (task_id) references Tasks (id)
    );

create table IF NOT EXISTS Task_Authorities
(
    task_id   int         not null,
    user_id   varchar(45) not null,
    authority varchar(45) not null,
    primary key (task_id, user_id),
    foreign key (task_id) references Tasks (id)
    );

create table IF NOT EXISTS Task_Logs
(
    update_date int not null,
    task_id     int not null,
    primary key (update_date, task_id),
    foreign key (task_id) references Tasks (id)
    );

create table IF NOT EXISTS Tasks_Tags
(
    task_id int not null,
    tag_id  int not null,
    primary key (task_id, tag_id),
    foreign key (tag_id) references Tags (id),
    foreign key (task_id) references Tasks (id)
    );
