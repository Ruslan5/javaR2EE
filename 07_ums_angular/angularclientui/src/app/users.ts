import {Role} from "./role";

export class Users {
  id: number | undefined;
  login: string | undefined;
  password: string | undefined;
  email: string | undefined;
  firstName: string | undefined;
  lastName: string | undefined;
  birthday: string | undefined;
  role: Role[] = [];
}
