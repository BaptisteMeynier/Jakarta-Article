package com.meynier.jakarta.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FishStockKey implements Serializable {

    @Column(name = "st_fish_id")
    private Long studentId;

    @Column(name = "st_shop_id")
    private Long courseId;

    public FishStockKey() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
