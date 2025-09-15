package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run() {

        System.out.println("Todo App 시작");
        
        try(Scanner sc = new Scanner(System.in)) {

            List<Todo> todos = new ArrayList<>();
            long todosLastId = 0;

            while(true) {
                System.out.print("명령) ");
                String cmd = sc.nextLine().trim();

                if(cmd.equals("exit")) break;
                else if(cmd.equals("add")){
                    long id = todosLastId + 1;
                    System.out.print("할 일 : ");
                    String content = sc.nextLine().trim();

                    Todo todo = new Todo(id, content);
                    todos.add(todo);
                    todosLastId++;

                    System.out.printf("%d번 todo 생성됨\n",id);
                }else if(cmd.equals("list")){
                    System.out.println("번호     /     내용  ");

                    todos.forEach(todo -> System.out.printf("%d  /   %s  \n",todo.getId(), todo.getContent()));
                }else if(cmd.equals("del")){
                    System.out.print("삭제할 할일 번호 : ");
                    long id = Long.parseLong(sc.nextLine().trim());

                    boolean isRemoved = todos.removeIf(todo -> todo.getId() == id);
                    
                    if(!isRemoved){
                        System.out.printf("%d번 할일은 없어\n",id);
                        continue;
                    }

                    System.out.printf("%d번 할 일이 삭제됨\n",id);
                }else if(cmd.equals("modify")){
                    System.out.print("수정할 할일 번호 : ");
                    long id = Long.parseLong(sc.nextLine().trim());

                    Todo foundTodo = todos.stream()
                            .filter(t -> t.getId() == id)
                            .findFirst().orElse(null);

                    if(foundTodo == null){
                        System.out.printf("%d번 할일은 없어\n",id);
                        continue;
                    }

                    System.out.printf("기존 할 일 : %s\n",foundTodo.getContent());
                    System.out.print("새 할 일 : ");
                    foundTodo.setContent(sc.nextLine().trim());

                    System.out.printf("%d번 할 일이 수정됨\n",id);
                }
            }
        }

        System.out.println("Todo App 끝");
    }
}
