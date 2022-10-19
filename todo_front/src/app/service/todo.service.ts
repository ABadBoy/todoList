import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Result } from '../core/result';
import { Todo } from '../todo/todo.model';
import { TokenStorageService } from './tokenStorage.service';

const baseUrl = 'http://www.leyiwangyou.top:8081/';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  todos: Todo[] = [];

  constructor(private http: HttpClient,private tokenStorageService:TokenStorageService) { }

  addTodo(todo: any){
    const user = this.tokenStorageService.getUser();
    const headers= new HttpHeaders({'TENANT-ID': user.id.toString()});
    return this.http.post(baseUrl + 'add-todoRecord', todo,{'headers':headers}).subscribe(response=>{
      console.log(response);
    });
  }

  getTodos():Observable<Result>{
    const user = this.tokenStorageService.getUser();
    const headers= new HttpHeaders({'TENANT-ID': user.id.toString()});
    return this.http.get<Result>(baseUrl + 'getAllTodoRecord/',{'headers':headers});
  }

  delteTodos(id:number){
    return this.http.delete(baseUrl + 'todoRecord/' + id);
  }

  updateCompleted(todo: Todo) {
    return this.http.post(baseUrl + 'update-todoRecordItem', todo);
  }
}
