import { CourseInstance } from "./courseInstance";

export class Enrollment{
    public id: number;
    public studentDTO: string;
    public courseInstanceDTO: CourseInstance;

    constructor(enrollmentCfg:Enrollment)
    {
        this.id = enrollmentCfg.id;
        this.studentDTO= enrollmentCfg.studentDTO;
        this.courseInstanceDTO = enrollmentCfg.courseInstanceDTO;
    }
}

