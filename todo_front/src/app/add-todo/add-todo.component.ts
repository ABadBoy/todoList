import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { NotificationService } from '../service/notification.service';
import { TodoService } from '../service/todo.service';

@Component({
  selector: 'app-add-todo',
  templateUrl: './add-todo.component.html',
  styleUrls: ['./add-todo.component.css']
})
export class AddTodoComponent implements OnInit {

  constructor(private dialogRef:MatDialogRef<AddTodoComponent>,private todoService:TodoService,
    private notificationService: NotificationService) { }

  ngOnInit(): void {
  }

  form: FormGroup = new FormGroup({
    todoDay: new FormControl('', Validators.required),
    todoRecordItems: new FormArray([new FormControl('', Validators.required)])
    });

  public get todoRecordItems() {
    return this.form.get('todoRecordItems') as FormArray;
  }

  onSubmit() {
    if (this.form.valid) {
      const d = new Date(this.form.get('todoDay')?.value);
      console.log('d:'+d);
      var convertDate = new Date(d.getFullYear(), d.getMonth(), d.getDate(), d.getHours(), d.getMinutes() - d.getTimezoneOffset()).toISOString().slice(0, 10);
      console.log('convertDate:'+convertDate);
      this.form.get('todoDay')?.setValue(convertDate);
      this.todoService.addTodo(this.form.value);
      console.log(this.form.value);
      this.form.reset();
      this.notificationService.success('Submitted successfully');
      this.onClose();
    }
  }

  onClear() {
    this.form.reset();
  }

  onClose() {
    this.dialogRef.close();
  }

  addDescription(){
    const description = new FormControl('', Validators.required);
    this.todoRecordItems.push(description);
  }

  removeDescription(){
    this.todoRecordItems.removeAt(this.todoRecordItems.length-1);
  }
}
