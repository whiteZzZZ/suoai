package com.yiban.suoai.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WordReviewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WordReviewExample() {
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

        public Criteria andWord_idIsNull() {
            addCriterion("word_id is null");
            return (Criteria) this;
        }

        public Criteria andWord_idIsNotNull() {
            addCriterion("word_id is not null");
            return (Criteria) this;
        }

        public Criteria andWord_idEqualTo(Integer value) {
            addCriterion("word_id =", value, "word_id");
            return (Criteria) this;
        }

        public Criteria andWord_idNotEqualTo(Integer value) {
            addCriterion("word_id <>", value, "word_id");
            return (Criteria) this;
        }

        public Criteria andWord_idGreaterThan(Integer value) {
            addCriterion("word_id >", value, "word_id");
            return (Criteria) this;
        }

        public Criteria andWord_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("word_id >=", value, "word_id");
            return (Criteria) this;
        }

        public Criteria andWord_idLessThan(Integer value) {
            addCriterion("word_id <", value, "word_id");
            return (Criteria) this;
        }

        public Criteria andWord_idLessThanOrEqualTo(Integer value) {
            addCriterion("word_id <=", value, "word_id");
            return (Criteria) this;
        }

        public Criteria andWord_idIn(List<Integer> values) {
            addCriterion("word_id in", values, "word_id");
            return (Criteria) this;
        }

        public Criteria andWord_idNotIn(List<Integer> values) {
            addCriterion("word_id not in", values, "word_id");
            return (Criteria) this;
        }

        public Criteria andWord_idBetween(Integer value1, Integer value2) {
            addCriterion("word_id between", value1, value2, "word_id");
            return (Criteria) this;
        }

        public Criteria andWord_idNotBetween(Integer value1, Integer value2) {
            addCriterion("word_id not between", value1, value2, "word_id");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUser_idEqualTo(Integer value) {
            addCriterion("user_id =", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThan(Integer value) {
            addCriterion("user_id >", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThan(Integer value) {
            addCriterion("user_id <", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idIn(List<Integer> values) {
            addCriterion("user_id in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andLike_timeIsNull() {
            addCriterion("like_time is null");
            return (Criteria) this;
        }

        public Criteria andLike_timeIsNotNull() {
            addCriterion("like_time is not null");
            return (Criteria) this;
        }

        public Criteria andLike_timeEqualTo(Integer value) {
            addCriterion("like_time =", value, "like_time");
            return (Criteria) this;
        }

        public Criteria andLike_timeNotEqualTo(Integer value) {
            addCriterion("like_time <>", value, "like_time");
            return (Criteria) this;
        }

        public Criteria andLike_timeGreaterThan(Integer value) {
            addCriterion("like_time >", value, "like_time");
            return (Criteria) this;
        }

        public Criteria andLike_timeGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_time >=", value, "like_time");
            return (Criteria) this;
        }

        public Criteria andLike_timeLessThan(Integer value) {
            addCriterion("like_time <", value, "like_time");
            return (Criteria) this;
        }

        public Criteria andLike_timeLessThanOrEqualTo(Integer value) {
            addCriterion("like_time <=", value, "like_time");
            return (Criteria) this;
        }

        public Criteria andLike_timeIn(List<Integer> values) {
            addCriterion("like_time in", values, "like_time");
            return (Criteria) this;
        }

        public Criteria andLike_timeNotIn(List<Integer> values) {
            addCriterion("like_time not in", values, "like_time");
            return (Criteria) this;
        }

        public Criteria andLike_timeBetween(Integer value1, Integer value2) {
            addCriterion("like_time between", value1, value2, "like_time");
            return (Criteria) this;
        }

        public Criteria andLike_timeNotBetween(Integer value1, Integer value2) {
            addCriterion("like_time not between", value1, value2, "like_time");
            return (Criteria) this;
        }

        public Criteria andReview_timeIsNull() {
            addCriterion("review_time is null");
            return (Criteria) this;
        }

        public Criteria andReview_timeIsNotNull() {
            addCriterion("review_time is not null");
            return (Criteria) this;
        }

        public Criteria andReview_timeEqualTo(Integer value) {
            addCriterion("review_time =", value, "review_time");
            return (Criteria) this;
        }

        public Criteria andReview_timeNotEqualTo(Integer value) {
            addCriterion("review_time <>", value, "review_time");
            return (Criteria) this;
        }

        public Criteria andReview_timeGreaterThan(Integer value) {
            addCriterion("review_time >", value, "review_time");
            return (Criteria) this;
        }

        public Criteria andReview_timeGreaterThanOrEqualTo(Integer value) {
            addCriterion("review_time >=", value, "review_time");
            return (Criteria) this;
        }

        public Criteria andReview_timeLessThan(Integer value) {
            addCriterion("review_time <", value, "review_time");
            return (Criteria) this;
        }

        public Criteria andReview_timeLessThanOrEqualTo(Integer value) {
            addCriterion("review_time <=", value, "review_time");
            return (Criteria) this;
        }

        public Criteria andReview_timeIn(List<Integer> values) {
            addCriterion("review_time in", values, "review_time");
            return (Criteria) this;
        }

        public Criteria andReview_timeNotIn(List<Integer> values) {
            addCriterion("review_time not in", values, "review_time");
            return (Criteria) this;
        }

        public Criteria andReview_timeBetween(Integer value1, Integer value2) {
            addCriterion("review_time between", value1, value2, "review_time");
            return (Criteria) this;
        }

        public Criteria andReview_timeNotBetween(Integer value1, Integer value2) {
            addCriterion("review_time not between", value1, value2, "review_time");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
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

        public Criteria andIs_deleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIs_deleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIs_deleteEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteLessThan(Boolean value) {
            addCriterion("is_delete <", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "is_delete");
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