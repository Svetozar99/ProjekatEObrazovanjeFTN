import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Documentt } from 'src/app/model/document';
import { User } from 'src/app/model/user';
import { DocumentsService } from './documents.service';

@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.css']
})
export class DocumentsComponent implements OnInit {

  user: User = { id:0, firstName:"", lastName:"", userName:"",password:"", roles:[]};
  documentss : Documentt[] | null = [];

  subscription: Subscription;

  constructor(private documentsService:DocumentsService, private router: Router,private route: ActivatedRoute) { 
    this.subscription = documentsService.RegenerateData$.subscribe(() =>
    this.geStudentDocuments());
  }

  ngOnInit():void { 
        this.documentsService.getStudentDocuments()
        .subscribe(res => {
          this.documentss = res.body;
        });
  }

  geStudentDocuments(){
    console.log("Get documents!");
    this.documentsService.getStudentDocuments().subscribe(
      response => {
        this.documentss = response.body;
      });
  }

  goToViewDocument(doc: Documentt): void{
    this.router.navigate(['/document', doc.id]);
  }
}