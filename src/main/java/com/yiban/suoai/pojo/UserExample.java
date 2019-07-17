package com.yiban.suoai.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andHead_imgIsNull() {
            addCriterion("head_img is null");
            return (Criteria) this;
        }

        public Criteria andHead_imgIsNotNull() {
            addCriterion("head_img is not null");
            return (Criteria) this;
        }

        public Criteria andHead_imgEqualTo(String value) {
            addCriterion("head_img =", value, "head_img");
            return (Criteria) this;
        }

        public Criteria andHead_imgNotEqualTo(String value) {
            addCriterion("head_img <>", value, "head_img");
            return (Criteria) this;
        }

        public Criteria andHead_imgGreaterThan(String value) {
            addCriterion("head_img >", value, "head_img");
            return (Criteria) this;
        }

        public Criteria andHead_imgGreaterThanOrEqualTo(String value) {
            addCriterion("head_img >=", value, "head_img");
            return (Criteria) this;
        }

        public Criteria andHead_imgLessThan(String value) {
            addCriterion("head_img <", value, "head_img");
            return (Criteria) this;
        }

        public Criteria andHead_imgLessThanOrEqualTo(String value) {
            addCriterion("head_img <=", value, "head_img");
            return (Criteria) this;
        }

        public Criteria andHead_imgLike(String value) {
            addCriterion("head_img like", value, "head_img");
            return (Criteria) this;
        }

        public Criteria andHead_imgNotLike(String value) {
            addCriterion("head_img not like", value, "head_img");
            return (Criteria) this;
        }

        public Criteria andHead_imgIn(List<String> values) {
            addCriterion("head_img in", values, "head_img");
            return (Criteria) this;
        }

        public Criteria andHead_imgNotIn(List<String> values) {
            addCriterion("head_img not in", values, "head_img");
            return (Criteria) this;
        }

        public Criteria andHead_imgBetween(String value1, String value2) {
            addCriterion("head_img between", value1, value2, "head_img");
            return (Criteria) this;
        }

        public Criteria andHead_imgNotBetween(String value1, String value2) {
            addCriterion("head_img not between", value1, value2, "head_img");
            return (Criteria) this;
        }

        public Criteria andBg_imgIsNull() {
            addCriterion("bg_img is null");
            return (Criteria) this;
        }

        public Criteria andBg_imgIsNotNull() {
            addCriterion("bg_img is not null");
            return (Criteria) this;
        }

        public Criteria andBg_imgEqualTo(String value) {
            addCriterion("bg_img =", value, "bg_img");
            return (Criteria) this;
        }

        public Criteria andBg_imgNotEqualTo(String value) {
            addCriterion("bg_img <>", value, "bg_img");
            return (Criteria) this;
        }

        public Criteria andBg_imgGreaterThan(String value) {
            addCriterion("bg_img >", value, "bg_img");
            return (Criteria) this;
        }

        public Criteria andBg_imgGreaterThanOrEqualTo(String value) {
            addCriterion("bg_img >=", value, "bg_img");
            return (Criteria) this;
        }

        public Criteria andBg_imgLessThan(String value) {
            addCriterion("bg_img <", value, "bg_img");
            return (Criteria) this;
        }

        public Criteria andBg_imgLessThanOrEqualTo(String value) {
            addCriterion("bg_img <=", value, "bg_img");
            return (Criteria) this;
        }

        public Criteria andBg_imgLike(String value) {
            addCriterion("bg_img like", value, "bg_img");
            return (Criteria) this;
        }

        public Criteria andBg_imgNotLike(String value) {
            addCriterion("bg_img not like", value, "bg_img");
            return (Criteria) this;
        }

        public Criteria andBg_imgIn(List<String> values) {
            addCriterion("bg_img in", values, "bg_img");
            return (Criteria) this;
        }

        public Criteria andBg_imgNotIn(List<String> values) {
            addCriterion("bg_img not in", values, "bg_img");
            return (Criteria) this;
        }

        public Criteria andBg_imgBetween(String value1, String value2) {
            addCriterion("bg_img between", value1, value2, "bg_img");
            return (Criteria) this;
        }

        public Criteria andBg_imgNotBetween(String value1, String value2) {
            addCriterion("bg_img not between", value1, value2, "bg_img");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andShaIsNull() {
            addCriterion("sha is null");
            return (Criteria) this;
        }

        public Criteria andShaIsNotNull() {
            addCriterion("sha is not null");
            return (Criteria) this;
        }

        public Criteria andShaEqualTo(String value) {
            addCriterion("sha =", value, "sha");
            return (Criteria) this;
        }

        public Criteria andShaNotEqualTo(String value) {
            addCriterion("sha <>", value, "sha");
            return (Criteria) this;
        }

        public Criteria andShaGreaterThan(String value) {
            addCriterion("sha >", value, "sha");
            return (Criteria) this;
        }

        public Criteria andShaGreaterThanOrEqualTo(String value) {
            addCriterion("sha >=", value, "sha");
            return (Criteria) this;
        }

        public Criteria andShaLessThan(String value) {
            addCriterion("sha <", value, "sha");
            return (Criteria) this;
        }

        public Criteria andShaLessThanOrEqualTo(String value) {
            addCriterion("sha <=", value, "sha");
            return (Criteria) this;
        }

        public Criteria andShaLike(String value) {
            addCriterion("sha like", value, "sha");
            return (Criteria) this;
        }

        public Criteria andShaNotLike(String value) {
            addCriterion("sha not like", value, "sha");
            return (Criteria) this;
        }

        public Criteria andShaIn(List<String> values) {
            addCriterion("sha in", values, "sha");
            return (Criteria) this;
        }

        public Criteria andShaNotIn(List<String> values) {
            addCriterion("sha not in", values, "sha");
            return (Criteria) this;
        }

        public Criteria andShaBetween(String value1, String value2) {
            addCriterion("sha between", value1, value2, "sha");
            return (Criteria) this;
        }

        public Criteria andShaNotBetween(String value1, String value2) {
            addCriterion("sha not between", value1, value2, "sha");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("salt is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("salt is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("salt =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("salt <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("salt >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("salt >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("salt <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("salt <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("salt like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("salt not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("salt in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("salt not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("salt between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("salt not between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Boolean value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Boolean value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Boolean value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Boolean value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Boolean value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Boolean value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Boolean> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Boolean> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Boolean value1, Boolean value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Boolean value1, Boolean value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andSchool_idIsNull() {
            addCriterion("school_id is null");
            return (Criteria) this;
        }

        public Criteria andSchool_idIsNotNull() {
            addCriterion("school_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchool_idEqualTo(Integer value) {
            addCriterion("school_id =", value, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idNotEqualTo(Integer value) {
            addCriterion("school_id <>", value, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idGreaterThan(Integer value) {
            addCriterion("school_id >", value, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("school_id >=", value, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idLessThan(Integer value) {
            addCriterion("school_id <", value, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idLessThanOrEqualTo(Integer value) {
            addCriterion("school_id <=", value, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idIn(List<Integer> values) {
            addCriterion("school_id in", values, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idNotIn(List<Integer> values) {
            addCriterion("school_id not in", values, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idBetween(Integer value1, Integer value2) {
            addCriterion("school_id between", value1, value2, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idNotBetween(Integer value1, Integer value2) {
            addCriterion("school_id not between", value1, value2, "school_id");
            return (Criteria) this;
        }

        public Criteria andAcademy_idIsNull() {
            addCriterion("academy_id is null");
            return (Criteria) this;
        }

        public Criteria andAcademy_idIsNotNull() {
            addCriterion("academy_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcademy_idEqualTo(Integer value) {
            addCriterion("academy_id =", value, "academy_id");
            return (Criteria) this;
        }

        public Criteria andAcademy_idNotEqualTo(Integer value) {
            addCriterion("academy_id <>", value, "academy_id");
            return (Criteria) this;
        }

        public Criteria andAcademy_idGreaterThan(Integer value) {
            addCriterion("academy_id >", value, "academy_id");
            return (Criteria) this;
        }

        public Criteria andAcademy_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("academy_id >=", value, "academy_id");
            return (Criteria) this;
        }

        public Criteria andAcademy_idLessThan(Integer value) {
            addCriterion("academy_id <", value, "academy_id");
            return (Criteria) this;
        }

        public Criteria andAcademy_idLessThanOrEqualTo(Integer value) {
            addCriterion("academy_id <=", value, "academy_id");
            return (Criteria) this;
        }

        public Criteria andAcademy_idIn(List<Integer> values) {
            addCriterion("academy_id in", values, "academy_id");
            return (Criteria) this;
        }

        public Criteria andAcademy_idNotIn(List<Integer> values) {
            addCriterion("academy_id not in", values, "academy_id");
            return (Criteria) this;
        }

        public Criteria andAcademy_idBetween(Integer value1, Integer value2) {
            addCriterion("academy_id between", value1, value2, "academy_id");
            return (Criteria) this;
        }

        public Criteria andAcademy_idNotBetween(Integer value1, Integer value2) {
            addCriterion("academy_id not between", value1, value2, "academy_id");
            return (Criteria) this;
        }

        public Criteria andStu_numIsNull() {
            addCriterion("stu_num is null");
            return (Criteria) this;
        }

        public Criteria andStu_numIsNotNull() {
            addCriterion("stu_num is not null");
            return (Criteria) this;
        }

        public Criteria andStu_numEqualTo(String value) {
            addCriterion("stu_num =", value, "stu_num");
            return (Criteria) this;
        }

        public Criteria andStu_numNotEqualTo(String value) {
            addCriterion("stu_num <>", value, "stu_num");
            return (Criteria) this;
        }

        public Criteria andStu_numGreaterThan(String value) {
            addCriterion("stu_num >", value, "stu_num");
            return (Criteria) this;
        }

        public Criteria andStu_numGreaterThanOrEqualTo(String value) {
            addCriterion("stu_num >=", value, "stu_num");
            return (Criteria) this;
        }

        public Criteria andStu_numLessThan(String value) {
            addCriterion("stu_num <", value, "stu_num");
            return (Criteria) this;
        }

        public Criteria andStu_numLessThanOrEqualTo(String value) {
            addCriterion("stu_num <=", value, "stu_num");
            return (Criteria) this;
        }

        public Criteria andStu_numLike(String value) {
            addCriterion("stu_num like", value, "stu_num");
            return (Criteria) this;
        }

        public Criteria andStu_numNotLike(String value) {
            addCriterion("stu_num not like", value, "stu_num");
            return (Criteria) this;
        }

        public Criteria andStu_numIn(List<String> values) {
            addCriterion("stu_num in", values, "stu_num");
            return (Criteria) this;
        }

        public Criteria andStu_numNotIn(List<String> values) {
            addCriterion("stu_num not in", values, "stu_num");
            return (Criteria) this;
        }

        public Criteria andStu_numBetween(String value1, String value2) {
            addCriterion("stu_num between", value1, value2, "stu_num");
            return (Criteria) this;
        }

        public Criteria andStu_numNotBetween(String value1, String value2) {
            addCriterion("stu_num not between", value1, value2, "stu_num");
            return (Criteria) this;
        }

        public Criteria andSignatureIsNull() {
            addCriterion("signature is null");
            return (Criteria) this;
        }

        public Criteria andSignatureIsNotNull() {
            addCriterion("signature is not null");
            return (Criteria) this;
        }

        public Criteria andSignatureEqualTo(String value) {
            addCriterion("signature =", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotEqualTo(String value) {
            addCriterion("signature <>", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureGreaterThan(String value) {
            addCriterion("signature >", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureGreaterThanOrEqualTo(String value) {
            addCriterion("signature >=", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureLessThan(String value) {
            addCriterion("signature <", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureLessThanOrEqualTo(String value) {
            addCriterion("signature <=", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureLike(String value) {
            addCriterion("signature like", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotLike(String value) {
            addCriterion("signature not like", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureIn(List<String> values) {
            addCriterion("signature in", values, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotIn(List<String> values) {
            addCriterion("signature not in", values, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureBetween(String value1, String value2) {
            addCriterion("signature between", value1, value2, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotBetween(String value1, String value2) {
            addCriterion("signature not between", value1, value2, "signature");
            return (Criteria) this;
        }

        public Criteria andIs_particularIsNull() {
            addCriterion("is_particular is null");
            return (Criteria) this;
        }

        public Criteria andIs_particularIsNotNull() {
            addCriterion("is_particular is not null");
            return (Criteria) this;
        }

        public Criteria andIs_particularEqualTo(Boolean value) {
            addCriterion("is_particular =", value, "is_particular");
            return (Criteria) this;
        }

        public Criteria andIs_particularNotEqualTo(Boolean value) {
            addCriterion("is_particular <>", value, "is_particular");
            return (Criteria) this;
        }

        public Criteria andIs_particularGreaterThan(Boolean value) {
            addCriterion("is_particular >", value, "is_particular");
            return (Criteria) this;
        }

        public Criteria andIs_particularGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_particular >=", value, "is_particular");
            return (Criteria) this;
        }

        public Criteria andIs_particularLessThan(Boolean value) {
            addCriterion("is_particular <", value, "is_particular");
            return (Criteria) this;
        }

        public Criteria andIs_particularLessThanOrEqualTo(Boolean value) {
            addCriterion("is_particular <=", value, "is_particular");
            return (Criteria) this;
        }

        public Criteria andIs_particularIn(List<Boolean> values) {
            addCriterion("is_particular in", values, "is_particular");
            return (Criteria) this;
        }

        public Criteria andIs_particularNotIn(List<Boolean> values) {
            addCriterion("is_particular not in", values, "is_particular");
            return (Criteria) this;
        }

        public Criteria andIs_particularBetween(Boolean value1, Boolean value2) {
            addCriterion("is_particular between", value1, value2, "is_particular");
            return (Criteria) this;
        }

        public Criteria andIs_particularNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_particular not between", value1, value2, "is_particular");
            return (Criteria) this;
        }

        public Criteria andIs_matchIsNull() {
            addCriterion("is_match is null");
            return (Criteria) this;
        }

        public Criteria andIs_matchIsNotNull() {
            addCriterion("is_match is not null");
            return (Criteria) this;
        }

        public Criteria andIs_matchEqualTo(Boolean value) {
            addCriterion("is_match =", value, "is_match");
            return (Criteria) this;
        }

        public Criteria andIs_matchNotEqualTo(Boolean value) {
            addCriterion("is_match <>", value, "is_match");
            return (Criteria) this;
        }

        public Criteria andIs_matchGreaterThan(Boolean value) {
            addCriterion("is_match >", value, "is_match");
            return (Criteria) this;
        }

        public Criteria andIs_matchGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_match >=", value, "is_match");
            return (Criteria) this;
        }

        public Criteria andIs_matchLessThan(Boolean value) {
            addCriterion("is_match <", value, "is_match");
            return (Criteria) this;
        }

        public Criteria andIs_matchLessThanOrEqualTo(Boolean value) {
            addCriterion("is_match <=", value, "is_match");
            return (Criteria) this;
        }

        public Criteria andIs_matchIn(List<Boolean> values) {
            addCriterion("is_match in", values, "is_match");
            return (Criteria) this;
        }

        public Criteria andIs_matchNotIn(List<Boolean> values) {
            addCriterion("is_match not in", values, "is_match");
            return (Criteria) this;
        }

        public Criteria andIs_matchBetween(Boolean value1, Boolean value2) {
            addCriterion("is_match between", value1, value2, "is_match");
            return (Criteria) this;
        }

        public Criteria andIs_matchNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_match not between", value1, value2, "is_match");
            return (Criteria) this;
        }

        public Criteria andIs_rankIsNull() {
            addCriterion("is_rank is null");
            return (Criteria) this;
        }

        public Criteria andIs_rankIsNotNull() {
            addCriterion("is_rank is not null");
            return (Criteria) this;
        }

        public Criteria andIs_rankEqualTo(Boolean value) {
            addCriterion("is_rank =", value, "is_rank");
            return (Criteria) this;
        }

        public Criteria andIs_rankNotEqualTo(Boolean value) {
            addCriterion("is_rank <>", value, "is_rank");
            return (Criteria) this;
        }

        public Criteria andIs_rankGreaterThan(Boolean value) {
            addCriterion("is_rank >", value, "is_rank");
            return (Criteria) this;
        }

        public Criteria andIs_rankGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_rank >=", value, "is_rank");
            return (Criteria) this;
        }

        public Criteria andIs_rankLessThan(Boolean value) {
            addCriterion("is_rank <", value, "is_rank");
            return (Criteria) this;
        }

        public Criteria andIs_rankLessThanOrEqualTo(Boolean value) {
            addCriterion("is_rank <=", value, "is_rank");
            return (Criteria) this;
        }

        public Criteria andIs_rankIn(List<Boolean> values) {
            addCriterion("is_rank in", values, "is_rank");
            return (Criteria) this;
        }

        public Criteria andIs_rankNotIn(List<Boolean> values) {
            addCriterion("is_rank not in", values, "is_rank");
            return (Criteria) this;
        }

        public Criteria andIs_rankBetween(Boolean value1, Boolean value2) {
            addCriterion("is_rank between", value1, value2, "is_rank");
            return (Criteria) this;
        }

        public Criteria andIs_rankNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_rank not between", value1, value2, "is_rank");
            return (Criteria) this;
        }

        public Criteria andViolatorIsNull() {
            addCriterion("violator is null");
            return (Criteria) this;
        }

        public Criteria andViolatorIsNotNull() {
            addCriterion("violator is not null");
            return (Criteria) this;
        }

        public Criteria andViolatorEqualTo(Boolean value) {
            addCriterion("violator =", value, "violator");
            return (Criteria) this;
        }

        public Criteria andViolatorNotEqualTo(Boolean value) {
            addCriterion("violator <>", value, "violator");
            return (Criteria) this;
        }

        public Criteria andViolatorGreaterThan(Boolean value) {
            addCriterion("violator >", value, "violator");
            return (Criteria) this;
        }

        public Criteria andViolatorGreaterThanOrEqualTo(Boolean value) {
            addCriterion("violator >=", value, "violator");
            return (Criteria) this;
        }

        public Criteria andViolatorLessThan(Boolean value) {
            addCriterion("violator <", value, "violator");
            return (Criteria) this;
        }

        public Criteria andViolatorLessThanOrEqualTo(Boolean value) {
            addCriterion("violator <=", value, "violator");
            return (Criteria) this;
        }

        public Criteria andViolatorIn(List<Boolean> values) {
            addCriterion("violator in", values, "violator");
            return (Criteria) this;
        }

        public Criteria andViolatorNotIn(List<Boolean> values) {
            addCriterion("violator not in", values, "violator");
            return (Criteria) this;
        }

        public Criteria andViolatorBetween(Boolean value1, Boolean value2) {
            addCriterion("violator between", value1, value2, "violator");
            return (Criteria) this;
        }

        public Criteria andViolatorNotBetween(Boolean value1, Boolean value2) {
            addCriterion("violator not between", value1, value2, "violator");
            return (Criteria) this;
        }

        public Criteria andExpress_timeIsNull() {
            addCriterion("express_time is null");
            return (Criteria) this;
        }

        public Criteria andExpress_timeIsNotNull() {
            addCriterion("express_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpress_timeEqualTo(Integer value) {
            addCriterion("express_time =", value, "express_time");
            return (Criteria) this;
        }

        public Criteria andExpress_timeNotEqualTo(Integer value) {
            addCriterion("express_time <>", value, "express_time");
            return (Criteria) this;
        }

        public Criteria andExpress_timeGreaterThan(Integer value) {
            addCriterion("express_time >", value, "express_time");
            return (Criteria) this;
        }

        public Criteria andExpress_timeGreaterThanOrEqualTo(Integer value) {
            addCriterion("express_time >=", value, "express_time");
            return (Criteria) this;
        }

        public Criteria andExpress_timeLessThan(Integer value) {
            addCriterion("express_time <", value, "express_time");
            return (Criteria) this;
        }

        public Criteria andExpress_timeLessThanOrEqualTo(Integer value) {
            addCriterion("express_time <=", value, "express_time");
            return (Criteria) this;
        }

        public Criteria andExpress_timeIn(List<Integer> values) {
            addCriterion("express_time in", values, "express_time");
            return (Criteria) this;
        }

        public Criteria andExpress_timeNotIn(List<Integer> values) {
            addCriterion("express_time not in", values, "express_time");
            return (Criteria) this;
        }

        public Criteria andExpress_timeBetween(Integer value1, Integer value2) {
            addCriterion("express_time between", value1, value2, "express_time");
            return (Criteria) this;
        }

        public Criteria andExpress_timeNotBetween(Integer value1, Integer value2) {
            addCriterion("express_time not between", value1, value2, "express_time");
            return (Criteria) this;
        }

        public Criteria andExperienceIsNull() {
            addCriterion("experience is null");
            return (Criteria) this;
        }

        public Criteria andExperienceIsNotNull() {
            addCriterion("experience is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceEqualTo(Integer value) {
            addCriterion("experience =", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotEqualTo(Integer value) {
            addCriterion("experience <>", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceGreaterThan(Integer value) {
            addCriterion("experience >", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceGreaterThanOrEqualTo(Integer value) {
            addCriterion("experience >=", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceLessThan(Integer value) {
            addCriterion("experience <", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceLessThanOrEqualTo(Integer value) {
            addCriterion("experience <=", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceIn(List<Integer> values) {
            addCriterion("experience in", values, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotIn(List<Integer> values) {
            addCriterion("experience not in", values, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceBetween(Integer value1, Integer value2) {
            addCriterion("experience between", value1, value2, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotBetween(Integer value1, Integer value2) {
            addCriterion("experience not between", value1, value2, "experience");
            return (Criteria) this;
        }

        public Criteria andTitle_idIsNull() {
            addCriterion("title_id is null");
            return (Criteria) this;
        }

        public Criteria andTitle_idIsNotNull() {
            addCriterion("title_id is not null");
            return (Criteria) this;
        }

        public Criteria andTitle_idEqualTo(Integer value) {
            addCriterion("title_id =", value, "title_id");
            return (Criteria) this;
        }

        public Criteria andTitle_idNotEqualTo(Integer value) {
            addCriterion("title_id <>", value, "title_id");
            return (Criteria) this;
        }

        public Criteria andTitle_idGreaterThan(Integer value) {
            addCriterion("title_id >", value, "title_id");
            return (Criteria) this;
        }

        public Criteria andTitle_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("title_id >=", value, "title_id");
            return (Criteria) this;
        }

        public Criteria andTitle_idLessThan(Integer value) {
            addCriterion("title_id <", value, "title_id");
            return (Criteria) this;
        }

        public Criteria andTitle_idLessThanOrEqualTo(Integer value) {
            addCriterion("title_id <=", value, "title_id");
            return (Criteria) this;
        }

        public Criteria andTitle_idIn(List<Integer> values) {
            addCriterion("title_id in", values, "title_id");
            return (Criteria) this;
        }

        public Criteria andTitle_idNotIn(List<Integer> values) {
            addCriterion("title_id not in", values, "title_id");
            return (Criteria) this;
        }

        public Criteria andTitle_idBetween(Integer value1, Integer value2) {
            addCriterion("title_id between", value1, value2, "title_id");
            return (Criteria) this;
        }

        public Criteria andTitle_idNotBetween(Integer value1, Integer value2) {
            addCriterion("title_id not between", value1, value2, "title_id");
            return (Criteria) this;
        }

        public Criteria andPaperIsNull() {
            addCriterion("paper is null");
            return (Criteria) this;
        }

        public Criteria andPaperIsNotNull() {
            addCriterion("paper is not null");
            return (Criteria) this;
        }

        public Criteria andPaperEqualTo(Integer value) {
            addCriterion("paper =", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperNotEqualTo(Integer value) {
            addCriterion("paper <>", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperGreaterThan(Integer value) {
            addCriterion("paper >", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperGreaterThanOrEqualTo(Integer value) {
            addCriterion("paper >=", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperLessThan(Integer value) {
            addCriterion("paper <", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperLessThanOrEqualTo(Integer value) {
            addCriterion("paper <=", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperIn(List<Integer> values) {
            addCriterion("paper in", values, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperNotIn(List<Integer> values) {
            addCriterion("paper not in", values, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperBetween(Integer value1, Integer value2) {
            addCriterion("paper between", value1, value2, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperNotBetween(Integer value1, Integer value2) {
            addCriterion("paper not between", value1, value2, "paper");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}