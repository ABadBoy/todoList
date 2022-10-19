import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../core/auth.service';
import { Result } from '../core/result';
import { NotificationService } from '../service/notification.service';
import { RegisterService } from '../service/register.service';
import { TokenStorageService } from '../service/tokenStorage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup = new FormGroup({
    name: new FormControl('',Validators.required),
    password: new FormControl('', Validators.required),
  });

  constructor(private registerService:RegisterService, private notificationService: NotificationService,
    private router: Router,private tokenStorageService: TokenStorageService) { }


  ngOnInit(): void {
  }


  login() {
    if (this.loginForm.valid) {
      this.registerService.login(this.loginForm.value).subscribe((response:Result)=>{
        console.log(response);
        if(response.code == 0){
          this.tokenStorageService.saveUser(response.data);
          this.notificationService.success('login successfully');
          this.goTodo();
        }else{
          this.notificationService.success('login fail');
        }

      });
    }
  }

  goTodo() {
    this.router.navigate(['app']).then(()=>{
      window.location.reload();
    });
    this.router.navigate(['todo']);
  }

}
