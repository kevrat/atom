CREATE TABLE user (
                       id          INTEGER NOT NULL AUTO_INCREMENT,
                       password  VARCHAR(100)    ,
                       nickname   VARCHAR(100)    NOT NULL,
                       lastLogin   DATE            ,
#                        UNIQUE KEY (id),
                       PRIMARY KEY(id)
);
CREATE TABLE message (
                       id          INTEGER NOT NULL AUTO_INCREMENT,
                       body   VARCHAR(200)    NOT NULL,
                       created_at   DATE            ,
#                        UNIQUE KEY (id),
                       PRIMARY KEY(id)
);