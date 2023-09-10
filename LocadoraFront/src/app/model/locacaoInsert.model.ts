import { FilmeId } from "./filmeId.model";
import { UserId } from "./userId.model";

export interface LocacaoInsert {
  id?: string;
  formaPagamento: string;
  qtd: string;
  user: UserId;
  filme: FilmeId;
}