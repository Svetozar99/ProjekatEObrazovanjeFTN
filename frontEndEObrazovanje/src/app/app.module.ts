import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
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
import { DocumentsService } from './components/documents/documents.service';
import { TeachingsService } from './components/teachings/teachings.service';
import { ExamDetailService } from './components/exam-detail/exam-detail.service';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { RoleGuard } from './guards/role-guard';
import { LoginGuard } from './guards/login-guard';
import { AdminNavbarComponent } from './components/navbar/admin-navbar/admin-navbar.component';
import { StudentNavbarComponent } from './components/navbar/student-navbar/student-navbar.component';
import { CoursesComponent } from './components/courses/courses.component';
import { CoursesService } from './components/courses/courses.service';
import { ViewCourseInstanceComponent } from './components/view-course-instance/view-course-instance.component';
import { CoursesSpecificationsComponent } from './components/courses-specifications/courses-specifications.component';
import { ViewCourseSpecificationComponent } from './components/view-course-specification/view-course-specification.component';

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
    ViewUserComponent,
    AdminNavbarComponent,
    StudentNavbarComponent,
    CoursesComponent,
    ViewCourseInstanceComponent,
    CoursesSpecificationsComponent,
    ViewCourseSpecificationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    CommonModule
  ],
  providers: [CoursesService, UserService, ExamsService, DocumentsService, TeachingsService, ExamDetailService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },],
  bootstrap: [AppComponent]
})
export class AppModule { }
