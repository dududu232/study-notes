# 外键

## 添加外键约束

```sql
# ① 先创建主表
create table dept1(
	dept_id int,
	dept_name varchar(15)
);

# ② 再创建从表
create table emp1(
	emp_id int PRIMARY KEY auto_increment,
	emp_name varchar(15),
	department_id int,
	
	
	#表级约束
	CONSTRAINT fk_emp1_dept1_id foreign key(department_id) REFERENCES dept1(dept_id)
);


#上述操作报错，因为主表中的dept_id上没有主键约束或唯一约束。
#   Failed to add the foreign key constraint. Missing index for constraint 'fk_emp1_dept1_id' in the referenced table 'dept1'

# ③ 添加
Alter table dept1 add PRIMARY key (dept_id)

desc dept1

# 再创建一次
create table emp1(
	emp_id int PRIMARY KEY auto_increment,
	emp_name varchar(15),
	department_id int,
	
	
	#表级约束
	CONSTRAINT fk_emp1_dept1_id foreign key(department_id) REFERENCES dept1(dept_id)
);

desc emp1

# 演示外键的效果
# 添加失败
insert into emp1 values(1001,'Tom',10)

#在主表中添加10号部门
insert into dept1 values(10,'IT')
#在主表中添加了10号部门之后,我们可以在从表中添加10号部门的员工
insert into emp1 values(1001,'Tom',10)
# 删除主表中的10号部门会失败
delete from dept1 where dept_id = 10
#更新主表中的10号部门的ID会失败
update dept1 set dept_id = 1 where  dept_id = 10
#新增11号部门
insert into dept1 values(11,'测试')
#更新主表中的11号部门的ID  更新成功
update dept1 set dept_id = 12 where dept_id= 11
#删除12号部门  删除成功
delete from dept1 where dept_id = 12


# 7.3 在alter table 时添加外键约束
# 创建部门表2
create table dept2(
	dept_id int primary key,
	dept_name varchar(15)
);
#创建员工表2
create table emp2(
	emp_id int primary key auto_increment,
	emp_name varchar(15),
	department_id int
)
# 查询约束  只查询出一个主键约束
select * from information_schema.table_constraints where table_name = 'emp2';
# 添加约束
alter table emp2 add constraint fk_emp2_dept1_id FOREIGN key(department_id) REFERENCES dept2(dept_id)
#查询约束  查询到了两个约束
select * from information_schema.table_constraints where table_name = 'emp2';


```

