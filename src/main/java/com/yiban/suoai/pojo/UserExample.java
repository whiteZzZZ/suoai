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

        public Criteria andTurenameIsNull() {
            addCriterion("tureName is null");
            return (Criteria) this;
        }

        public Criteria andTurenameIsNotNull() {
            addCriterion("tureName is not null");
            return (Criteria) this;
        }

        public Criteria andTurenameEqualTo(String value) {
            addCriterion("tureName =", value, "turename");
            return (Criteria) this;
        }

        public Criteria andTurenameNotEqualTo(String value) {
            addCriterion("tureName <>", value, "turename");
            return (Criteria) this;
        }

        public Criteria andTurenameGreaterThan(String value) {
            addCriterion("tureName >", value, "turename");
            return (Criteria) this;
        }

        public Criteria andTurenameGreaterThanOrEqualTo(String value) {
            addCriterion("tureName >=", value, "turename");
            return (Criteria) this;
        }

        public Criteria andTurenameLessThan(String value) {
            addCriterion("tureName <", value, "turename");
            return (Criteria) this;
        }

        public Criteria andTurenameLessThanOrEqualTo(String value) {
            addCriterion("tureName <=", value, "turename");
            return (Criteria) this;
        }

        public Criteria andTurenameLike(String value) {
            addCriterion("tureName like", value, "turename");
            return (Criteria) this;
        }

        public Criteria andTurenameNotLike(String value) {
            addCriterion("tureName not like", value, "turename");
            return (Criteria) this;
        }

        public Criteria andTurenameIn(List<String> values) {
            addCriterion("tureName in", values, "turename");
            return (Criteria) this;
        }

        public Criteria andTurenameNotIn(List<String> values) {
            addCriterion("tureName not in", values, "turename");
            return (Criteria) this;
        }

        public Criteria andTurenameBetween(String value1, String value2) {
            addCriterion("tureName between", value1, value2, "turename");
            return (Criteria) this;
        }

        public Criteria andTurenameNotBetween(String value1, String value2) {
            addCriterion("tureName not between", value1, value2, "turename");
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

        public Criteria andHeadImgIsNull() {
            addCriterion("head_img is null");
            return (Criteria) this;
        }

        public Criteria andHeadImgIsNotNull() {
            addCriterion("head_img is not null");
            return (Criteria) this;
        }

        public Criteria andHeadImgEqualTo(String value) {
            addCriterion("head_img =", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgNotEqualTo(String value) {
            addCriterion("head_img <>", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgGreaterThan(String value) {
            addCriterion("head_img >", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("head_img >=", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgLessThan(String value) {
            addCriterion("head_img <", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgLessThanOrEqualTo(String value) {
            addCriterion("head_img <=", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgLike(String value) {
            addCriterion("head_img like", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgNotLike(String value) {
            addCriterion("head_img not like", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgIn(List<String> values) {
            addCriterion("head_img in", values, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgNotIn(List<String> values) {
            addCriterion("head_img not in", values, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgBetween(String value1, String value2) {
            addCriterion("head_img between", value1, value2, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgNotBetween(String value1, String value2) {
            addCriterion("head_img not between", value1, value2, "headImg");
            return (Criteria) this;
        }

        public Criteria andBgImgIsNull() {
            addCriterion("bg_img is null");
            return (Criteria) this;
        }

        public Criteria andBgImgIsNotNull() {
            addCriterion("bg_img is not null");
            return (Criteria) this;
        }

        public Criteria andBgImgEqualTo(String value) {
            addCriterion("bg_img =", value, "bgImg");
            return (Criteria) this;
        }

        public Criteria andBgImgNotEqualTo(String value) {
            addCriterion("bg_img <>", value, "bgImg");
            return (Criteria) this;
        }

        public Criteria andBgImgGreaterThan(String value) {
            addCriterion("bg_img >", value, "bgImg");
            return (Criteria) this;
        }

        public Criteria andBgImgGreaterThanOrEqualTo(String value) {
            addCriterion("bg_img >=", value, "bgImg");
            return (Criteria) this;
        }

        public Criteria andBgImgLessThan(String value) {
            addCriterion("bg_img <", value, "bgImg");
            return (Criteria) this;
        }

        public Criteria andBgImgLessThanOrEqualTo(String value) {
            addCriterion("bg_img <=", value, "bgImg");
            return (Criteria) this;
        }

        public Criteria andBgImgLike(String value) {
            addCriterion("bg_img like", value, "bgImg");
            return (Criteria) this;
        }

        public Criteria andBgImgNotLike(String value) {
            addCriterion("bg_img not like", value, "bgImg");
            return (Criteria) this;
        }

        public Criteria andBgImgIn(List<String> values) {
            addCriterion("bg_img in", values, "bgImg");
            return (Criteria) this;
        }

        public Criteria andBgImgNotIn(List<String> values) {
            addCriterion("bg_img not in", values, "bgImg");
            return (Criteria) this;
        }

        public Criteria andBgImgBetween(String value1, String value2) {
            addCriterion("bg_img between", value1, value2, "bgImg");
            return (Criteria) this;
        }

        public Criteria andBgImgNotBetween(String value1, String value2) {
            addCriterion("bg_img not between", value1, value2, "bgImg");
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

        public Criteria andSchoolIdIsNull() {
            addCriterion("school_id is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNotNull() {
            addCriterion("school_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdEqualTo(Integer value) {
            addCriterion("school_id =", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotEqualTo(Integer value) {
            addCriterion("school_id <>", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThan(Integer value) {
            addCriterion("school_id >", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("school_id >=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThan(Integer value) {
            addCriterion("school_id <", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThanOrEqualTo(Integer value) {
            addCriterion("school_id <=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIn(List<Integer> values) {
            addCriterion("school_id in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotIn(List<Integer> values) {
            addCriterion("school_id not in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdBetween(Integer value1, Integer value2) {
            addCriterion("school_id between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotBetween(Integer value1, Integer value2) {
            addCriterion("school_id not between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdIsNull() {
            addCriterion("academy_id is null");
            return (Criteria) this;
        }

        public Criteria andAcademyIdIsNotNull() {
            addCriterion("academy_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcademyIdEqualTo(Integer value) {
            addCriterion("academy_id =", value, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdNotEqualTo(Integer value) {
            addCriterion("academy_id <>", value, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdGreaterThan(Integer value) {
            addCriterion("academy_id >", value, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("academy_id >=", value, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdLessThan(Integer value) {
            addCriterion("academy_id <", value, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdLessThanOrEqualTo(Integer value) {
            addCriterion("academy_id <=", value, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdIn(List<Integer> values) {
            addCriterion("academy_id in", values, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdNotIn(List<Integer> values) {
            addCriterion("academy_id not in", values, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdBetween(Integer value1, Integer value2) {
            addCriterion("academy_id between", value1, value2, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("academy_id not between", value1, value2, "academyId");
            return (Criteria) this;
        }

        public Criteria andStuNumIsNull() {
            addCriterion("stu_num is null");
            return (Criteria) this;
        }

        public Criteria andStuNumIsNotNull() {
            addCriterion("stu_num is not null");
            return (Criteria) this;
        }

        public Criteria andStuNumEqualTo(String value) {
            addCriterion("stu_num =", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumNotEqualTo(String value) {
            addCriterion("stu_num <>", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumGreaterThan(String value) {
            addCriterion("stu_num >", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumGreaterThanOrEqualTo(String value) {
            addCriterion("stu_num >=", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumLessThan(String value) {
            addCriterion("stu_num <", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumLessThanOrEqualTo(String value) {
            addCriterion("stu_num <=", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumLike(String value) {
            addCriterion("stu_num like", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumNotLike(String value) {
            addCriterion("stu_num not like", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumIn(List<String> values) {
            addCriterion("stu_num in", values, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumNotIn(List<String> values) {
            addCriterion("stu_num not in", values, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumBetween(String value1, String value2) {
            addCriterion("stu_num between", value1, value2, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumNotBetween(String value1, String value2) {
            addCriterion("stu_num not between", value1, value2, "stuNum");
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

        public Criteria andIsParticularIsNull() {
            addCriterion("is_particular is null");
            return (Criteria) this;
        }

        public Criteria andIsParticularIsNotNull() {
            addCriterion("is_particular is not null");
            return (Criteria) this;
        }

        public Criteria andIsParticularEqualTo(Boolean value) {
            addCriterion("is_particular =", value, "isParticular");
            return (Criteria) this;
        }

        public Criteria andIsParticularNotEqualTo(Boolean value) {
            addCriterion("is_particular <>", value, "isParticular");
            return (Criteria) this;
        }

        public Criteria andIsParticularGreaterThan(Boolean value) {
            addCriterion("is_particular >", value, "isParticular");
            return (Criteria) this;
        }

        public Criteria andIsParticularGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_particular >=", value, "isParticular");
            return (Criteria) this;
        }

        public Criteria andIsParticularLessThan(Boolean value) {
            addCriterion("is_particular <", value, "isParticular");
            return (Criteria) this;
        }

        public Criteria andIsParticularLessThanOrEqualTo(Boolean value) {
            addCriterion("is_particular <=", value, "isParticular");
            return (Criteria) this;
        }

        public Criteria andIsParticularIn(List<Boolean> values) {
            addCriterion("is_particular in", values, "isParticular");
            return (Criteria) this;
        }

        public Criteria andIsParticularNotIn(List<Boolean> values) {
            addCriterion("is_particular not in", values, "isParticular");
            return (Criteria) this;
        }

        public Criteria andIsParticularBetween(Boolean value1, Boolean value2) {
            addCriterion("is_particular between", value1, value2, "isParticular");
            return (Criteria) this;
        }

        public Criteria andIsParticularNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_particular not between", value1, value2, "isParticular");
            return (Criteria) this;
        }

        public Criteria andIsMatchIsNull() {
            addCriterion("is_match is null");
            return (Criteria) this;
        }

        public Criteria andIsMatchIsNotNull() {
            addCriterion("is_match is not null");
            return (Criteria) this;
        }

        public Criteria andIsMatchEqualTo(Boolean value) {
            addCriterion("is_match =", value, "isMatch");
            return (Criteria) this;
        }

        public Criteria andIsMatchNotEqualTo(Boolean value) {
            addCriterion("is_match <>", value, "isMatch");
            return (Criteria) this;
        }

        public Criteria andIsMatchGreaterThan(Boolean value) {
            addCriterion("is_match >", value, "isMatch");
            return (Criteria) this;
        }

        public Criteria andIsMatchGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_match >=", value, "isMatch");
            return (Criteria) this;
        }

        public Criteria andIsMatchLessThan(Boolean value) {
            addCriterion("is_match <", value, "isMatch");
            return (Criteria) this;
        }

        public Criteria andIsMatchLessThanOrEqualTo(Boolean value) {
            addCriterion("is_match <=", value, "isMatch");
            return (Criteria) this;
        }

        public Criteria andIsMatchIn(List<Boolean> values) {
            addCriterion("is_match in", values, "isMatch");
            return (Criteria) this;
        }

        public Criteria andIsMatchNotIn(List<Boolean> values) {
            addCriterion("is_match not in", values, "isMatch");
            return (Criteria) this;
        }

        public Criteria andIsMatchBetween(Boolean value1, Boolean value2) {
            addCriterion("is_match between", value1, value2, "isMatch");
            return (Criteria) this;
        }

        public Criteria andIsMatchNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_match not between", value1, value2, "isMatch");
            return (Criteria) this;
        }

        public Criteria andIsRankIsNull() {
            addCriterion("is_rank is null");
            return (Criteria) this;
        }

        public Criteria andIsRankIsNotNull() {
            addCriterion("is_rank is not null");
            return (Criteria) this;
        }

        public Criteria andIsRankEqualTo(Boolean value) {
            addCriterion("is_rank =", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankNotEqualTo(Boolean value) {
            addCriterion("is_rank <>", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankGreaterThan(Boolean value) {
            addCriterion("is_rank >", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_rank >=", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankLessThan(Boolean value) {
            addCriterion("is_rank <", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankLessThanOrEqualTo(Boolean value) {
            addCriterion("is_rank <=", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankIn(List<Boolean> values) {
            addCriterion("is_rank in", values, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankNotIn(List<Boolean> values) {
            addCriterion("is_rank not in", values, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankBetween(Boolean value1, Boolean value2) {
            addCriterion("is_rank between", value1, value2, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_rank not between", value1, value2, "isRank");
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

        public Criteria andExpressTimeIsNull() {
            addCriterion("express_time is null");
            return (Criteria) this;
        }

        public Criteria andExpressTimeIsNotNull() {
            addCriterion("express_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpressTimeEqualTo(Integer value) {
            addCriterion("express_time =", value, "expressTime");
            return (Criteria) this;
        }

        public Criteria andExpressTimeNotEqualTo(Integer value) {
            addCriterion("express_time <>", value, "expressTime");
            return (Criteria) this;
        }

        public Criteria andExpressTimeGreaterThan(Integer value) {
            addCriterion("express_time >", value, "expressTime");
            return (Criteria) this;
        }

        public Criteria andExpressTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("express_time >=", value, "expressTime");
            return (Criteria) this;
        }

        public Criteria andExpressTimeLessThan(Integer value) {
            addCriterion("express_time <", value, "expressTime");
            return (Criteria) this;
        }

        public Criteria andExpressTimeLessThanOrEqualTo(Integer value) {
            addCriterion("express_time <=", value, "expressTime");
            return (Criteria) this;
        }

        public Criteria andExpressTimeIn(List<Integer> values) {
            addCriterion("express_time in", values, "expressTime");
            return (Criteria) this;
        }

        public Criteria andExpressTimeNotIn(List<Integer> values) {
            addCriterion("express_time not in", values, "expressTime");
            return (Criteria) this;
        }

        public Criteria andExpressTimeBetween(Integer value1, Integer value2) {
            addCriterion("express_time between", value1, value2, "expressTime");
            return (Criteria) this;
        }

        public Criteria andExpressTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("express_time not between", value1, value2, "expressTime");
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

        public Criteria andTitleIdIsNull() {
            addCriterion("title_id is null");
            return (Criteria) this;
        }

        public Criteria andTitleIdIsNotNull() {
            addCriterion("title_id is not null");
            return (Criteria) this;
        }

        public Criteria andTitleIdEqualTo(Integer value) {
            addCriterion("title_id =", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotEqualTo(Integer value) {
            addCriterion("title_id <>", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdGreaterThan(Integer value) {
            addCriterion("title_id >", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("title_id >=", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdLessThan(Integer value) {
            addCriterion("title_id <", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdLessThanOrEqualTo(Integer value) {
            addCriterion("title_id <=", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdIn(List<Integer> values) {
            addCriterion("title_id in", values, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotIn(List<Integer> values) {
            addCriterion("title_id not in", values, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdBetween(Integer value1, Integer value2) {
            addCriterion("title_id between", value1, value2, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("title_id not between", value1, value2, "titleId");
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

        public Criteria andYibanidIsNull() {
            addCriterion("yibanId is null");
            return (Criteria) this;
        }

        public Criteria andYibanidIsNotNull() {
            addCriterion("yibanId is not null");
            return (Criteria) this;
        }

        public Criteria andYibanidEqualTo(Integer value) {
            addCriterion("yibanId =", value, "yibanid");
            return (Criteria) this;
        }

        public Criteria andYibanidNotEqualTo(Integer value) {
            addCriterion("yibanId <>", value, "yibanid");
            return (Criteria) this;
        }

        public Criteria andYibanidGreaterThan(Integer value) {
            addCriterion("yibanId >", value, "yibanid");
            return (Criteria) this;
        }

        public Criteria andYibanidGreaterThanOrEqualTo(Integer value) {
            addCriterion("yibanId >=", value, "yibanid");
            return (Criteria) this;
        }

        public Criteria andYibanidLessThan(Integer value) {
            addCriterion("yibanId <", value, "yibanid");
            return (Criteria) this;
        }

        public Criteria andYibanidLessThanOrEqualTo(Integer value) {
            addCriterion("yibanId <=", value, "yibanid");
            return (Criteria) this;
        }

        public Criteria andYibanidIn(List<Integer> values) {
            addCriterion("yibanId in", values, "yibanid");
            return (Criteria) this;
        }

        public Criteria andYibanidNotIn(List<Integer> values) {
            addCriterion("yibanId not in", values, "yibanid");
            return (Criteria) this;
        }

        public Criteria andYibanidBetween(Integer value1, Integer value2) {
            addCriterion("yibanId between", value1, value2, "yibanid");
            return (Criteria) this;
        }

        public Criteria andYibanidNotBetween(Integer value1, Integer value2) {
            addCriterion("yibanId not between", value1, value2, "yibanid");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openId is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openId is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openId =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openId <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openId >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openId >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openId <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openId <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openId like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openId not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openId in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openId not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openId between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openId not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andUnionidIsNull() {
            addCriterion("unionId is null");
            return (Criteria) this;
        }

        public Criteria andUnionidIsNotNull() {
            addCriterion("unionId is not null");
            return (Criteria) this;
        }

        public Criteria andUnionidEqualTo(String value) {
            addCriterion("unionId =", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidNotEqualTo(String value) {
            addCriterion("unionId <>", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidGreaterThan(String value) {
            addCriterion("unionId >", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidGreaterThanOrEqualTo(String value) {
            addCriterion("unionId >=", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidLessThan(String value) {
            addCriterion("unionId <", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidLessThanOrEqualTo(String value) {
            addCriterion("unionId <=", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidLike(String value) {
            addCriterion("unionId like", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidNotLike(String value) {
            addCriterion("unionId not like", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidIn(List<String> values) {
            addCriterion("unionId in", values, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidNotIn(List<String> values) {
            addCriterion("unionId not in", values, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidBetween(String value1, String value2) {
            addCriterion("unionId between", value1, value2, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidNotBetween(String value1, String value2) {
            addCriterion("unionId not between", value1, value2, "unionid");
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