package web.emp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Emp {
	@Id
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Timestamp hiredate;
    private BigDecimal sal;
    private BigDecimal comm;
    private Integer deptno;
//    @ManyToOne
//    @JoinColumn(name = "DEPTNO",
//    insertable = false,
//    updatable = false)
    @ManyToOne
    @JoinColumn(name = "DEPTNO",//"⾃⽅關聯欄位"
    	    insertable = false,
    	    updatable = false)
    private Dept dept;


}

