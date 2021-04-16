import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './components/account/account.component';
import { DocumentsComponent } from './components/documents/documents.component';
import { ExamDetailComponent } from './components/exam-detail/exam-detail.component';
import { ExamsComponent } from './components/exams/exams.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UsersComponent } from './components/users/users.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'exams', component: ExamsComponent },
  { path: 'exam-detail', component: ExamDetailComponent },
  { path: 'account', component: AccountComponent },
  { path: 'users', component: UsersComponent },
  { path: 'documents', component: DocumentsComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
