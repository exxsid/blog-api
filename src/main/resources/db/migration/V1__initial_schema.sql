CREATE TABLE blogs (
    id int not null,
    title varchar not null,
    content  text not null,
    category varchar not null,
    tags varchar[],
    createdAt timestamp not null,
    updatedAt timestamp not null,
    PRIMARY KEY (id)
);