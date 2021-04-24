import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { LoginComponent } from '../login/login.component';
import { ViewUserComponent } from '../view-user/view-user.component';
import { ExamsComponent } from '../exams/exams.component';
import { ExamDetailComponent } from '../exam-detail/exam-detail.component';
import { AccountComponent } from '../account/account.component';
import { UsersComponent } from '../users/users.component';
import { DocumentsComponent } from '../documents/documents.component';
import { TeachingsComponent } from '../teachings/teachings.component';
import { DocumentComponent } from '../document/document.component';
import { TeacherComponent } from '../teacher/teacher.component';
import { CourseInstanceComponent } from '../course-instance/course-instance.component';
import { AddExamComponent } from '../add-exam/add-exam.component';
import { AddPaymentComponent } from '../add-payment/add-payment.component';
import { AddExamPartComponent } from '../add-exam-part/add-exam-part.component';

const routes: Routes = [
  { path: 'addUser', component: ViewUserComponent },
  { path: 'exams', component: ExamsComponent },
  { path: 'exam-detail/student/:examId', component: ExamDetailComponent },
  { path: 'account', component: AccountComponent },
  { path: 'users', component: UsersComponent },
  { path: 'documents', component: DocumentsComponent },
  { path: 'teachings', component: TeachingsComponent },
  { path: 'document', component: DocumentComponent },
  { path: 'teacher', component: TeacherComponent },
  { path: 'course-instance', component: CourseInstanceComponent },
  { path: 'add-exam', component: AddExamComponent },
  { path: 'add-payment', component: AddPaymentComponent },
  { path: 'add-exam-part', component: AddExamPartComponent },
  { path: 'view-user/:id', component: ViewUserComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class SiteContentRoutingModule { }
