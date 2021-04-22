import { User } from "./user";

export class Student{
    public id: number;
    public firstName: string;
    public lastName: string;
    public userName: string;
    public cardNumber: string;
    public userDTO: User;
    public roles: 'ROLE_STUDENT';

    constructor(studentCfg:Student)
    {
        this.id = studentCfg.id;
        this.firstName = studentCfg.firstName;
        this.lastName = studentCfg.lastName;
        this.userName = studentCfg.userName;
        this.cardNumber = studentCfg.cardNumber;
        this.userDTO = studentCfg.userDTO;
        this.roles = studentCfg.roles;
    }
}