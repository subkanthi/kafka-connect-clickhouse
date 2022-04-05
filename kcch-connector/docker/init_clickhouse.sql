CREATE TABLE employees
(
    `emp_no` Int8,
    `birth_date` Date64,
    `first_name` String,
    `last_name` String,
    `gender` String,
    `hire_date` Date64
)
ENGINE = MergeTree
PRIMARY KEY emp_no
ORDER BY emp_no