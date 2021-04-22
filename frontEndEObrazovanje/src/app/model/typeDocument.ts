export class TypeDocumentt{
    public id: number;
    public name: string;
    public code: string;

    constructor(typeDocumentCfg: TypeDocumentt)
    {
        this.id = typeDocumentCfg.id;
        this.name= typeDocumentCfg.name;
        this.code = typeDocumentCfg.code;
    }
}