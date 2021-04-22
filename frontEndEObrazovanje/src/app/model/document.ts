import { Student } from "./student";
import { TypeDocumentt } from "./typeDocument";

export class Documentt{
    public id: number;
    public title: string;
    public url: string;
    public typeDocumentDTO: TypeDocumentt;
    public studentDTO: Student;

    constructor(documentCfg: Documentt)
    {
        this.id = documentCfg.id;
        this.title= documentCfg.title;
        this.url = documentCfg.url;
        this.typeDocumentDTO = documentCfg.typeDocumentDTO;
        this.studentDTO = documentCfg.studentDTO;
    }
}