import { CategoriaId } from "./categoriaId.model";
import { EstoqueId } from "./estoqueId.model";

export interface FilmeInsert {
  id?: string;
  nome: string;
  descricao: string;
  diretor: string;
  valorAluguel: string;
  estoque: EstoqueId;
  categoria: CategoriaId;
}