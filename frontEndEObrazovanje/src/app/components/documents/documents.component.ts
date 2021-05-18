import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Document } from 'src/app/model/document';
import { User } from 'src/app/model/user';
import { DocumentsService } from './documents.service';
import { saveAs } from 'file-saver';
import { StudentDetailComponent } from '../student-detail/student-detail.component';
import { Student } from 'src/app/model/student';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.css']
})
export class DocumentsComponent implements OnInit {

  user: User = { id:0, firstName:"", lastName:"", userName:"",password:"", roles:[]};
  documents : Document[] | null = [];

  subscription: Subscription;

  @Input() student:Student = new Student({
    id:0,
    cardNumber: '',
    userDTO:{
      id:0,
      firstName:'',
      lastName:'',
      userName:'',
      password:'',
      roles:[]
    }
  })

  constructor(private documentsService:DocumentsService, private router: Router,private route: ActivatedRoute, private auths: AuthenticationService ,private studentDetail: StudentDetailComponent) { 
    if(auths.getRole() === 'ROLE_ADMINISTRATOR'){
      this.student = studentDetail.student;
    }
    this.subscription = documentsService.RegenerateData$.subscribe(() =>
    this.geStudentDocuments());
  }

  ngOnInit():void { 
        this.documentsService.getStudentDocuments(this.student.userDTO.userName)
        .subscribe(res => {
          console.log(JSON.stringify(res.body));
          this.documents = res.body;
        });
  }

  geStudentDocuments(){
    console.log("Get documents!");
    this.documentsService.getStudentDocuments(this.student.userDTO.userName).subscribe(
      response => {
        this.documents = response.body;
      });
  }

  deleteDocument(document: Document): void {
    console.log("Brisem: "+JSON.stringify(document));
    this.documentsService.deleteDocument(document.id).subscribe(
      () => this.geStudentDocuments()
    );
  }

  downloadFile(document:Document): void {
    this.documentsService
      .downloadFile(document.url).subscribe(blob => {
        const splitPath = document.url.split("\\");
        const fileName = splitPath[splitPath.length-1];
        saveAs(blob, fileName);
      })
  }

  goToViewDocument(doc: Document): void{
    this.router.navigate(['/document', doc.id]);
  }
}