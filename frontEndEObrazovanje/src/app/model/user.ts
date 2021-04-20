export class User{
    public id: number;
    public firstName: string;
    public lastName: string;
    public userName: string;
    public role: string;

    constructor(userCfg:User)
    {
        this.id = userCfg.id;
        this.firstName = userCfg.firstName;
        this.lastName = userCfg.lastName;
        this.userName = userCfg.userName;
        this.role = userCfg.role;
    }
}