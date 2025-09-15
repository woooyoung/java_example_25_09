package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoController {

    private Scanner sc;
    private ArrayList<Todo> todos;
    private long todosLastId;

    public TodoController(){
        sc = new Scanner(System.in);
        todos = new ArrayList<>();
        todosLastId = 0;
    }

    public void add() {
        long id = todosLastId + 1;
        System.out.print("할 일 : ");
        String content = sc.nextLine().trim();

        Todo todo = new Todo(id, content);
        todos.add(todo);
        todosLastId++;

        System.out.printf("%d번 todo 생성됨\n", id);
    }

    public void list() {
        System.out.println("번호     /     내용  ");

        todos.forEach(todo -> System.out.printf("%d  /   %s  \n", todo.getId(), todo.getContent()));
    }

    public void delete() {
        System.out.print("삭제할 할일 번호 : ");
        long id = Long.parseLong(sc.nextLine().trim());

        boolean isRemoved = todos.removeIf(todo -> todo.getId() == id);

        if (!isRemoved) {
            System.out.printf("%d번 할일은 없어\n", id);
            return;
        }

        System.out.printf("%d번 할 일이 삭제됨\n", id);
    }

    public void modify() {
        System.out.print("수정할 할일 번호 : ");
        long id = Long.parseLong(sc.nextLine().trim());

        Todo foundTodo = todos.stream()
                .filter(t -> t.getId() == id)
                .findFirst().orElse(null);

        if (foundTodo == null) {
            System.out.printf("%d번 할일은 없어\n", id);
            return;
        }

        System.out.printf("기존 할 일 : %s\n", foundTodo.getContent());
        System.out.print("새 할 일 : ");
        foundTodo.setContent(sc.nextLine().trim());

        System.out.printf("%d번 할 일이 수정됨\n", id);
    }
}
