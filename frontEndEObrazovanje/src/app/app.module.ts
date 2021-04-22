import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ExamsComponent } from './components/exams/exams.component';
import { ExamDetailComponent } from './components/exam-detail/exam-detail.component';
import { AccountComponent } from './components/account/account.component';
import { HomeComponent } from './components/home/home.component';
import { UsersComponent } from './components/users/users.component';
import { DocumentsComponent } from './components/documents/documents.component';
import { TeachingsComponent } from './components/teachings/teachings.component';
import { DocumentComponent } from './components/document/document.component';
import { TeacherComponent } from './components/teacher/teacher.component';
import { CourseInstanceComponent } from './components/course-instance/course-instance.component';
import { AddExamComponent } from './components/add-exam/add-exam.component';
import { AddPaymentComponent } from './components/add-payment/add-payment.component';
import { AddExamPartComponent } from './components/add-exam-part/add-exam-part.component';
import { UserService } from './components/users/users.service';
import { ViewUserComponent } from './components/view-user/view-user.component';
import { ExamsService } from './components/exams/exams.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ExamsComponent,
    ExamDetailComponent,
    AccountComponent,
    HomeComponent,
    UsersComponent,
    DocumentsComponent,
    TeachingsComponent,
    DocumentComponent,
    TeacherComponent,
    CourseInstanceComponent,
    AddExamComponent,
    AddPaymentComponent,
    AddExamPartComponent,
    ViewUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    CommonModule
  ],
  providers: [UserService, ExamsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
