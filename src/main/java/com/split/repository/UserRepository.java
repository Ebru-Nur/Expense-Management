package com.split.repository;

import com.split.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {//User sınıfı içerisinde id ile işlem yapacağı için Integer alır.

    //HQL: hibernate query language, veritabanı sorgularını sınıfın ismi ve içindeki değişken isimlerini kullanarak yazarız
    //SQL: veritabanındaki tablo ismi ve sütunların isimleriyle yazarız
    //nativeQuery=true->sql
    //@Query(value="",nativeQuery=true)
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);

}

