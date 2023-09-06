import { EstoqueInfo } from "./estoqueinfo.model";

export interface Filme {
  id?: string;
  nome: string;
  descricao: string;
  diretor: string;
  valorAluguel: string;
  estoque: EstoqueInfo;
}