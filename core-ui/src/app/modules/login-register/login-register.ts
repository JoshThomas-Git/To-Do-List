import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, FormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login-register',
  imports: [ CommonModule,
    ReactiveFormsModule,
    FormsModule ],
  templateUrl: './login-register.html',
  styleUrl: './login-register.scss',
})
export class LoginRegister {
  isLogin = true;  // Toggle between login & register

  loginForm: FormGroup;
  registerForm: FormGroup;

  constructor(private fb: FormBuilder) {

    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });

    this.registerForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });
  }

  signIn(){
  }

  signUp(){    
  }

  toggleForm() {
    this.isLogin = !this.isLogin;
  }

  loginSubmit() {
    console.log(this.loginForm.value);
  }

  registerSubmit() {
    console.log(this.registerForm.value);
  }
}
