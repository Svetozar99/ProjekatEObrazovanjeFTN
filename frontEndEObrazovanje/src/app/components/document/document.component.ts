import { Component, OnInit } from '@angular/core';
import { Document } from 'src/app/model/document';
import { Student } from 'src/app/model/student';
import { TypeDocument } from 'src/app/model/typeDocument';
import { Url } from 'src/app/model/url';
import { User } from 'src/app/model/user';
import { DocumentTypeService } from 'src/app/services/document-type.service';
import { DocumentService } from './document.service';

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.css']
})
export class DocumentComponent implements OnInit {

  fileToUpload: File | null;
  fileName = '';
  document: Document;
  documentTypes:TypeDocument[] = [];

  constructor(private documentService:DocumentService,private documentTypeService:DocumentTypeService) {
    this.fileToUpload = new File(new Array<Blob>(), "Mock.zip", { type: 'application/zip' });
    this.document = new Document(
      {
        id:0,title:'',
        studentDTO:new Student(
          {
              id:0,
              cardNumber:'',
              userDTO:new User(
                {
                  id:0,
                  firstName:'',
                  lastName:'',
                  userName:'',
                  password:'',
                  roles:[],
                })
          }),
        url:'',
        typeDocumentDTO:new TypeDocument({id:0,code:'',name:''})
      }
    )
    this.documentTypeService.getDocumentTypes().subscribe(res=>{
      this.documentTypes = res.body == null ? []:res.body;
    })
  }

  ngOnInit(): void {
  }

  onFileSelected(event:Event) {

    const target= event.target as HTMLInputElement;

    this.fileToUpload = (target.files as FileList)[0];
  }

  submitDocument(){
    if (this.fileToUpload) {

      this.fileName = this.fileToUpload.name;

      const formData = new FormData();

      formData.append("file", this.fileToUpload);

      this.documentService.addFile(formData).subscribe(res=>{
        const url:Url = res.body == null ? new Url({url:''}):res.body;
        this.document.url = url.url;
        console.log("Document: "+JSON.stringify(this.document))
        this.documentService.addDocument(this.document).subscribe();
      });
  }
  }

}
