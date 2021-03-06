package com.web.blog.dao.care;

import java.util.Optional;

import javax.transaction.Transactional;

import java.util.List;

import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Interest;

import org.hibernate.annotations.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;



public interface InterestDao extends JpaRepository<Interest, String> {
    // List<Interest> findByUidAndDesertionno(String uid, String desertionNo);
    //아래도 써줘야 하는데 이름이 같아서 쓸 수 없음...
    // Interest findByUidAndDesertionno(String uid, String desertionNo);
    @Transactional
    void deleteByInterestno(int interestno);
    Interest save(Interest interest);
    List<Interest> findByUid(String uid);
    Optional<Interest> findByUidAndDesertionno(String uid, String desertionno);
}