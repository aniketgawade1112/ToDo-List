package com.example.ToDoApp.service;

import com.example.ToDoApp.repository.ToDoRepository;
import com.example.ToDoApp.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    @Autowired
    ToDoRepository toDoRepository;

    public List<ToDo> getAllToDoItems() {
        return new ArrayList<>(toDoRepository.findAll());
    }

    public ToDo getToDoItemById(Long id) {
        return toDoRepository.findById(id).get();
    }

    public boolean updateStatus(Long id) {
        ToDo toDo = getToDoItemById(id);
        toDo.setStatus("Completed");
        return saveUpdateToDoItem(toDo);
    }

    public boolean saveUpdateToDoItem(ToDo toDo) {
        ToDo updatedObj = toDoRepository.save(toDo);

        if (getToDoItemById(updatedObj.getId()) != null) {
            return true;
        }
        return false;
    }

    public boolean deleteToDoItem(Long id) {
        toDoRepository.deleteById(id);

        if (getToDoItemById(id) == null) {
            return true;
        }
        return false;
    }
}
