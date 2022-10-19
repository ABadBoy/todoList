import { Component, OnInit } from '@angular/core';
import { Todo } from './todo.model';
import { TodoService } from '../service/todo.service';
import { Result } from '../core/result';
import { TodoRecord } from './todos.model';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css'],
  providers: [TodoService]
})
export class TodoComponent implements OnInit {

  todoRecords?: TodoRecord[];
  desc = '';
  result!: Result;
  constructor(private todoService:TodoService) { }

  ngOnInit() {
    this.getTodos();
  }

  getTodos(){
    this.todoService.getTodos().subscribe((result)=>{
      console.log(result);
      this.todoRecords=result.data;
      console.log(this.todoRecords);
    })
  }

  deleteTodo(id: any){
    this.todoService.delteTodos(id).subscribe(response=>{
      this.getTodos();
    });
  }

  updateCompleted(todo: Todo) {
    this.todoService.updateCompleted(todo).subscribe(response=>{
      this.getTodos();
    });
  }
}
