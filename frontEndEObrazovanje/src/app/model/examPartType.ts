export class ExamPartType{
    public id: number;
    public name: string;
	public code: number;

    constructor(examPartTypeCfg:ExamPartType)
    {
        this.id = examPartTypeCfg.id;
        this.name = examPartTypeCfg.name;
        this.code = examPartTypeCfg.code;
    }
}
