import { Todo } from "./todo.model";

export class TodoRecord {
  id!:number
  todoDay!: string;
  todoRecordItems!: Array<Todo>;
}
