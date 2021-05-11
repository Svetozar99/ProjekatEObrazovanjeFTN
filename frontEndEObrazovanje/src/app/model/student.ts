import { User } from "./user";

export class Student{
    public id: number;
    public cardNumber: string;
    public userDTO: User;
    public roles: 'ROLE_STUDENT';

    constructor(studentCfg:Student)
    {
        this.id = studentCfg.id;
        this.cardNumber = studentCfg.cardNumber;
        this.userDTO = studentCfg.userDTO;
        this.roles = studentCfg.roles;
    }
}