import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Document } from 'src/app/model/document';
import { User } from 'src/app/model/user';
import { DocumentsService } from './documents.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.css']
})
export class DocumentsComponent implements OnInit {

  user: User = { id:0, firstName:"", lastName:"", userName:"",password:"", roles:[]};
  documents : Document[] | null = [];

  subscription: Subscription;

  constructor(private documentsService:DocumentsService, private router: Router,private route: ActivatedRoute) { 
    this.subscription = documentsService.RegenerateData$.subscribe(() =>
    this.geStudentDocuments());
  }

  ngOnInit():void { 
        this.documentsService.getStudentDocuments()
        .subscribe(res => {
          this.documents = res.body;
        });
  }

  geStudentDocuments(){
    console.log("Get documents!");
    this.documentsService.getStudentDocuments().subscribe(
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