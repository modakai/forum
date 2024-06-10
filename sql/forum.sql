# 系统部门
drop table if exists sys_dept;
create table sys_dept
(
    id          bigint unsigned primary key auto_increment,
    parent_id   bigint unsigned not null default 0 comment '父部门ID',
    ancestors   varchar(100)             default '0' comment '祖级列表',
    dept_name   varchar(20)              default '' comment '部门名称',
    leader      varchar(20)              default '' comment '部门负责人',
    phone       varchar(18)              default '' comment '联系电话',
    email       varchar(50)              default '' comment '邮箱',
    sort        int             not null default 0 comment '顺序',
    status      tinyint(1)      not null default 0 comment '状态 0正常 1停用',
    remark      varchar(100)             default '' comment '备注',
    create_by   varchar(20)              default 0 comment '创建人',
    create_time datetime comment '创建时间',
    update_by   varchar(20)              default 0 comment '更新人',
    update_time datetime comment '更新时间'
) engine = InnoDB
  default charset = utf8mb4 comment '系统部门表';

-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into sys_dept
values (null, 0, '0', '论坛总部', 'admin', '15222222222', '22@qq.com', 0, 0, '', 'admin', sysdate(), '', null);


# 系统用户表
drop table if exists sys_user;
create table sys_user
(
    id          bigint unsigned primary key auto_increment,
    dept_id     bigint unsigned not null comment '部门ID',
    username    varchar(20)     not null comment '用户名',
    password    varchar(100)    not null comment '密码',
    nickname    varchar(30)  default '' comment '用户昵称',
    avatar      varchar(255) default '' comment '头像',
    phone       varchar(18)  default '' comment '手机号',
    gender      tinyint(1)   default 0 comment '性别 (0 男 1 女 -1 未知)',
    status      tinyint(1)   default '0' comment '状态 0正常 1禁用',
    remark      varchar(100) default '' comment '备注',
    create_by   varchar(20)  default '' comment '创建人',
    create_time datetime comment '创建时间',
    update_by   varchar(20)  default '' comment '更新人',
    update_time datetime comment '更新时间'
) engine = InnoDB
  default charset = utf8mb4 comment '系统用户表';
-- ----------------------------
-- 初始化-系统用户表数据
-- ----------------------------
insert into sys_user
values (null, 1, 'admin', '123abc', '超级管理员', 'https://vue.ruoyi.vip/static/img/profile.473f5971.jpg',
        '15222222222', 0, 0, '', 'admin', sysdate(), '', null);

# 角色表
drop table if exists sys_role;
create table sys_role
(
    id                  bigint unsigned primary key auto_increment,
    role_name           varchar(30) not null comment '角色名称',
    role_key            varchar(30) not null comment '角色权限字符串',
    sort                int         not null default 0 comment '角色顺序',
    menu_check_strictly tinyint(1)           default 1 comment '菜单树选择项是否关联显示',
    status              tinyint(1)           default '0' comment '状态 0正常 1禁用',
    remark              varchar(100)         default '' comment '备注',
    create_by           varchar(20)          default '' comment '创建人',
    create_time         datetime comment '创建时间',
    update_by           varchar(20)          default '' comment '更新人',
    update_time         datetime comment '更新时间'
) engine = InnoDB
  default charset = utf8mb4 comment '系统角色表';
-- ----------------------------
-- 初始化-系统角色表数据
-- ----------------------------
insert into sys_role
values (null, '超级管理员', 'admin', 1, 1, 0, '', 'admin', sysdate(), '', null);

# 用户角色关系表
drop table if exists sys_user_role;
create table sys_user_role
(
    user_id bigint unsigned not null comment '用户id',
    role_id bigint unsigned not null comment '角色id',
    primary key (user_id, role_id)
)
    engine = InnoDB
    default charset = utf8mb4 comment '用户角色关系表';
-- ----------------------------
-- 初始化-系统用户和角色关系数据
-- ----------------------------
insert into sys_user_role
values (1, 1);

# 菜单表
drop table if exists sys_menu;
create table sys_menu
(
    id               bigint unsigned primary key auto_increment,
    menu_name        varchar(50)     not null comment '菜单名称',
    parent_id        bigint unsigned not null default 0 comment '父菜单ID',
    path             varchar(200)             default '' comment '路由地址',
    component        varchar(255)             default '' comment '组件路径',
    perms            varchar(100)             default '' comment '权限标识',
    icon             varchar(100)             default '#' comment '图标',
    menu_type        tinyint(1)               default 0 comment '菜单类型 0目录 1菜单 2按钮',
    visible          tinyint(1)               default 0 comment '菜单状态 0显示 1隐藏',
    status           tinyint(1)               default 0 comment '菜单状态 0正常 1停用',
    sort             int             not null default 0 comment '顺序',
    is_external_link tinyint(1)               default 0 comment '是否外链 0否 1是',
    remark           varchar(100)             default '' comment '备注',
    create_by        varchar(20)              default '' comment '创建人',
    create_time      datetime comment '创建时间',
    update_by        varchar(20)              default '' comment '更新人',
    update_time      datetime comment '更新时间'
) engine = InnoDB
  default charset = utf8mb4 comment '系统菜单表';


# 角色菜单关系表
drop table if exists sys_role_menu;
create table sys_role_menu
(
    role_id bigint unsigned not null comment '角色id',
    menu_id bigint unsigned not null comment '菜单id',
    primary key (role_id, menu_id)
) engine = InnoDB
  default charset = utf8mb4 comment '角色菜单关系表';

