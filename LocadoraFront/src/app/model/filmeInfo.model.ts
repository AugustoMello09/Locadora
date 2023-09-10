import { EstoqueId } from "./estoqueId.model";

export interface Filmeinfo {
  id?: string;
  nome: string;
  estoque: EstoqueId;
}