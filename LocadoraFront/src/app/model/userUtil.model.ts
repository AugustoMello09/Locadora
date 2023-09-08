import { RolId } from "./roleId.model";

export interface UserU {
  id?: string;
  name: string;
  email: string;
  roles: RolId[];
}