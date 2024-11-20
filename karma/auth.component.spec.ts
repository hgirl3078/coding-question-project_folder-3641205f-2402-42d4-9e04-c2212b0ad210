import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthComponent } from './auth.component';
import { AuthService } from '../services/auth.service';

describe('AuthComponent', () => {
  let component: AuthComponent;
  let fixture: ComponentFixture<AuthComponent>;
  let authService: jasmine.SpyObj<AuthService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('AuthService', ['login', 'register']);
    await TestBed.configureTestingModule({
      declarations: [ AuthComponent ],
      imports: [ ReactiveFormsModule, RouterTestingModule ],
      providers: [
        { provide: AuthService, useValue: spy }
      ]
    }).compileComponents();

    authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should validate login form', () => {
    const form = component.loginForm;
    expect(form.valid).toBeFalsy();
    form.controls['email'].setValue('test@test.com');
    form.controls['password'].setValue('password123');
    expect(form.valid).toBeTruthy();
  });

  it('should call auth service on login', () => {
    authService.login.and.returnValue(Promise.resolve(true));
    component.loginForm.setValue({
      email: 'test@test.com',
      password: 'password123'
    });
    component.onLogin();
    expect(authService.login).toHaveBeenCalled();
  });
});
