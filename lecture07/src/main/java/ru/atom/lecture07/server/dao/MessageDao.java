package ru.atom.lecture07.server.dao;

import ru.atom.lecture07.server.model.Message;

import java.util.List;

public interface MessageDao {
    List<Message> findAll();

    void save(Message msg);
}
