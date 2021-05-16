package com.klyuev.prokhodkaztelegrambot.repository;
import com.klyuev.prokhodkaztelegrambot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
