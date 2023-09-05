import { RolId } from "./roleId.model";

export interface User {
  id?: string;
  name: string;
  email: string;
  cpf: string;
  password: string;
  roles: RolId[];
}