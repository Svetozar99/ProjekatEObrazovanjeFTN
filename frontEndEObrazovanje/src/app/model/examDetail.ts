import { Exam } from "./exam";
import { ExamPartType } from "./examPartType";
import { ExamPartStatus } from "./examPartStatus";
export class ExamDetail{
    public id: number;
    public date: Date;
    public location: String;
    public points: number;
    public wonPoints: number;
    public examDTO: Exam;
    public examPartTypeDTO: ExamPartType;
    public statusDTO: ExamPartStatus;

    constructor(examDetailCfg:ExamDetail)
    {
        this.id = examDetailCfg.id;
        this.date= examDetailCfg.date;
        this.location = examDetailCfg.location;
        this.points = examDetailCfg.points;
        this.wonPoints= examDetailCfg.wonPoints;
        this.examDTO = examDetailCfg.examDTO;
        this.examPartTypeDTO= examDetailCfg.examPartTypeDTO;
        this.statusDTO = examDetailCfg.statusDTO;
    }
}