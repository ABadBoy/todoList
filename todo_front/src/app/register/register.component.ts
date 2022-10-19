import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Result } from '../core/result';
import { NotificationService } from '../service/notification.service';
import { RegisterService } from '../service/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private registerService:RegisterService, private notificationService: NotificationService,
    private router: Router) { }

  ngOnInit(): void {
  }

  public form: FormGroup = new FormGroup({
    id : new FormControl(null),
    name: new FormControl('',Validators.required),
    email: new FormControl('', Validators.email),
    password: new FormControl('', Validators.required),
  });

  register(){
    if (this.form.valid) {
      this.registerService.register(this.form.value).subscribe((result:Result)=>{
        console.log(result);
        if(result.code == 0){
          this.notificationService.success('register successfully, please login');
          this.goToLogin();
        }else{
          this.notificationService.warn(result.msg);
        }

      });
    }
  }

  goToLogin() {
    this.router.navigate(['login']);
  }
}
