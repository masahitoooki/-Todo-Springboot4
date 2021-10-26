package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	TodoRepository todoRepository;

	// todolistを全件取得
	public List<Todo> searchAll(){
		return todoRepository.findAll();
	}

	//Todoを新たにDBに登録する
	public void addTodo(Todo todo) {
		todoRepository.save(todo);
	}

	//idに応じたTodoを返却する
	public Todo findById(Integer id) {
		Optional<Todo> updateTodo = todoRepository.findById(id);
	    return updateTodo.get();
	}

	// Todoの全件削除
	public void deleteAllTodo() {
		List<Todo> allTodo = todoRepository.findAll();
		List<Todo> doneList = new ArrayList<>();
		//doneがtrueかどうかを判定。trueのものをdoneListに追加する。
		for(Todo todo : allTodo) {
			if(todo.isDone()) {
				doneList.add(todo);
			}
		}
		todoRepository.deleteAll(doneList);
	}
}
