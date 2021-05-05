import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './components/account/account.component';
import { AddExamPartComponent } from './components/add-exam-part/add-exam-part.component';
import { AddExamComponent } from './components/add-exam/add-exam.component';
import { AddPaymentComponent } from './components/add-payment/add-payment.component';
import { CourseInstanceComponent } from './components/course-instance/course-instance.component';
import { DocumentComponent } from './components/document/document.component';
import { DocumentsComponent } from './components/documents/documents.component';
import { ExamDetailComponent } from './components/exam-detail/exam-detail.component';
import { ExamsComponent } from './components/exams/exams.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { TeacherComponent } from './components/teacher/teacher.component';
import { TeachingsComponent } from './components/teachings/teachings.component';
import { UsersComponent } from './components/users/users.component';
import { ViewUserComponent } from './components/view-user/view-user.component';
import { LoginGuard } from './guards/login-guard';
import { RoleGuard } from './guards/role-guard';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [LoginGuard]},
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard]},
  { path: 'home', component: HomeComponent, canActivate: [RoleGuard]},
  { path: 'addUser', component: ViewUserComponent, canActivate: [RoleGuard] },
  { path: 'exams', component: ExamsComponent, canActivate: [RoleGuard] },
  { path: 'exam-detail/student/:examId', component: ExamDetailComponent, canActivate: [RoleGuard] },
  { path: 'account', component: AccountComponent, canActivate: [RoleGuard] },
  { path: 'users', component: UsersComponent, canActivate: [RoleGuard] },
  { path: 'documents', component: DocumentsComponent, canActivate: [RoleGuard] },
  { path: 'teachings', component: TeachingsComponent, canActivate: [RoleGuard] },
  { path: 'document', component: DocumentComponent, canActivate: [RoleGuard] },
  { path: 'teacher', component: TeacherComponent, canActivate: [RoleGuard] },
  { path: 'course-instance', component: CourseInstanceComponent, canActivate: [RoleGuard] },
  { path: 'add-exam', component: AddExamComponent, canActivate: [RoleGuard] },
  { path: 'add-payment', component: AddPaymentComponent, canActivate: [RoleGuard] },
  { path: 'add-exam-part', component: AddExamPartComponent, canActivate: [RoleGuard] },
  { path: 'view-user/:id', component: ViewUserComponent, canActivate: [RoleGuard] },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
