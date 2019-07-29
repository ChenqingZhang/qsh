drop table if exists `resource`;
create table `resource` (
  `id` bigint(36) not null,
  `name` varchar(100) not null,
  `remark` varchar(200) default null,
  `seq` int(11) default null,
  `url` varchar(200) default null,
  `method` varchar(20) default null,
  `pid` bigint(36) default null,
  `type` int(11) not null,
  primary key (`id`),
  constraint `resource_ibfk_1` foreign key (`pid`) references `resource` (`id`) on delete cascade

) engine=innodb default charset=utf8mb4;


drop table if exists `role`;
create table `role` (
  `id` bigint(36) not null,
  `name` varchar(100) not null,
  `remark` varchar(200) default null,
  `seq` int(11) default null,
  `pid` bigint(36) default null,
  primary key (`id`),
  constraint `role_ibfk_1` foreign key (`pid`) references `role` (`id`) on delete cascade
) engine=innodb default charset=utf8mb4;

insert into `role` values ('0', 'admin', '超级管理员角色，拥有系统中所有的资源访问权限', '0', null);


drop table if exists `role_resource`;
create table `role_resource` (
  `role_id` bigint(36) not null,
  `resource_id` bigint(36) not null,
  primary key (`resource_id`,`role_id`),
  key `resource_id` (`resource_id`),
  key `role_resource_ibfk_1` (`role_id`),
  constraint `role_resource_ibfk_1` foreign key (`role_id`) references `role` (`id`) on delete cascade,
  constraint `role_resource_ibfk_2` foreign key (`resource_id`) references `resource` (`id`) on delete cascade
) engine=innodb default charset=utf8mb4;


drop table if exists `user`;
create table `user` (
  `id` bigint(36) not null,
  `create_time` datetime default current_timestamp,
  `modify_time` datetime default current_timestamp,
  `name` varchar(100) not null,
  `password` varchar(100) not null,
  `username` varchar(100) not null,
  primary key (`id`)
) engine=innodb default charset=utf8mb4;

insert into `user` values ('0', '2018-07-25 17:50:05', '2018-07-25 17:50:05', '管理员', 'e10adc3949ba59abbe56e057f20f883e', 'admin');


drop table if exists `user_role`;
create table `user_role` (
  `user_id` bigint(20) not null,
  `role_id` bigint(20) not null,
  primary key (`user_id`,`role_id`),
  constraint `user_role_ibfk_1` foreign key (`user_id`) references `user` (`id`) on delete cascade,
  constraint `user_role_ibfk_2` foreign key (`role_id`) references `role` (`id`) on delete cascade
) engine=innodb default charset=utf8mb4;

insert into `user_role` values ('0', '0');


drop table if exists `member`;
create table `member` (
  `id` bigint(36) not null,
  `create_time` datetime default current_timestamp,
  `modify_time` datetime default current_timestamp,
  `member_time` datetime default current_timestamp,
  `company` varchar(100) not null,
  `principal` varchar(100) not null,
  `mobile` varchar(20) not null,
  `address` varchar(100) not null,
  `member_status` int not null,
  primary key (`id`)
) engine=innodb default charset=utf8mb4;


drop table if exists `business`;
create table `business` (
  `id` bigint(36) not null,
  `create_time` datetime default current_timestamp,
  `modify_time` datetime default current_timestamp,
  `signin_time` datetime default current_timestamp,
  `signin_status` int not null,
  `mobile` varchar(20) not null,
  `item` text not null,
  `business_type` int not null,
  primary key (`id`)
) engine=innodb default charset=utf8mb4;


