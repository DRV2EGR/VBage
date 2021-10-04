package ru.vbage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vbage.entity.Message;
import ru.vbage.entity.User;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByReciever(User user);
}
