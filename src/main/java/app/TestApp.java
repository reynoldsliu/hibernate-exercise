package app;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.emp.entity.Dept;
import web.emp.entity.Emp;
import web.member.entity.Member;

public class TestApp {
	public static void main(String[] args) {
//		HibernateUtil h = new HibernateUtil();
//		SessionFactory sessionFactory = h.getSessionFactory();
//		
		SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
		Session session = sessionFactory.openSession();
//		Member member = session.get(Member.class, 1);
//		System.out.println(member.getNickname());
//		
//		HibernateUtil.shutdown();
		
		TestApp ta = new TestApp();
		//insert
//		Member member = new Member();
//		member.setUsername("NewMember文");
//		member.setPassword("IMpassword文");
//		member.setNickname("IMnickname文");
//		ta.insert(member);
		
//		//deleteById
//		System.out.println(member.getId());
//		System.out.println(ta.deleteById(6));
		
//		//updateById
//		Member newMember = new Member();
//		newMember.setId(1);
//		newMember.setPass(false);
//		newMember.setRoleId(2);
//		System.out.println(ta.updateById(newMember));
		
		//selectById
//		System.out.println(ta.selectById(1));
		
//		ta.selectAll().forEach(member -> System.out.println(member));
//		for(Member member:ta.selectAll()) {
//			System.out.println(member);
//		}
		
		
//		//select USERNAAME,NICKNAME
//		//from MEMBER
//		//where USERNAME = ?, and PASSWORD = ?
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
//		//from MEMBER
//		Root<Member> root = criteriaQuery.from(Member.class);
//		//where USERNAME = ?, and PASSWORD = ?
//		criteriaQuery.where(criteriaBuilder.and(
//				criteriaBuilder.equal(root.get("username"), "admin"),
//				criteriaBuilder.equal(root.get("password"), "P@ssw0rd")
//				));
//		//select USERNAAME,NICKNAME
//		criteriaQuery.multiselect(root.get("username"),root.get("nickname"));
//		//order by ID
//		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
//		
//		Member member =  session.createQuery(criteriaQuery).uniqueResult();
//		System.out.println(member);
		
//		Dept dept = session.get(Dept.class,30);
//		var emps = dept.getEmps();
//		for(var emp:emps) {
//			System.out.println(emp.getEname());
//		}
		
		Emp emp = session.get(Emp.class,7369);
		Dept dept = emp.getDept();
		System.out.println(dept.getDeptno());
		List<Emp> emps = dept.getEmps();
		for(Emp tmp : emps) {
			System.out.println(tmp.getEname());
		}
		
		
	}
	
	public Integer insert(Member member) {
		SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(member);
			transaction.commit();
			return member.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	public Integer deleteById(Integer id) {
		SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class,id);
			int mem_id = member.getId();
			session.remove(member);
			
			transaction.commit();
			return mem_id;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
		
	}
	
	public Integer updateById(Member newMember) {

		SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member oldMember = session.get(Member.class,newMember.getId());
			
			final Boolean pass = newMember.getPass(); 
			if(pass != null) {
				oldMember.setPass(pass);
			}
			
			final Integer roleid = newMember.getRoleId();
			if(roleid != null) {
				oldMember.setRoleId(roleid);
			}
			
			transaction.commit();
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
		
	}
	
	public Member selectById(Integer id) {
		SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class,id);
			
			transaction.commit();
			return member;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
		
	}
	
	public List<Member> selectAll() {
		SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<Member> query =  session.createQuery("select new web.member.pojo.Member(username,nickname) from Member",Member.class);
			List<Member> list = query.getResultList();
			
			transaction.commit();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
		
	}


}
