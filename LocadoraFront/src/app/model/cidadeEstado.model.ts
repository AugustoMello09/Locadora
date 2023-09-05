import { EstadoId } from "./estadoId.model";

export interface EstadoCidade {
  id?: string;
  name: string;
  estadoId: EstadoId;   
}