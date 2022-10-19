import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './service/tokenStorage.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddTodoComponent } from './add-todo/add-todo.component';
import { TodoService } from './service/todo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {


  title = 'hello-angular';
  user:any;
  constructor(private tokenStorageService:TokenStorageService,public dialog: MatDialog,private router: Router) { }

  ngOnInit(): void {
    this.user = this.tokenStorageService.getUser();
  }

  btnClick() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose=true;
    dialogConfig.autoFocus=true;
    dialogConfig.width="40%";
    dialogConfig.height="80%"
    this.dialog.open(AddTodoComponent,dialogConfig).afterClosed().subscribe(()=>{
      this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
        this.router.navigate(['todo']);
    });
    });
  }

  logOut(){
    this.tokenStorageService.signOut();
    this.user = this.tokenStorageService.getUser();
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate(['login']);
    })
  }
}
