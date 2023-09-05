import { CidadeId } from "./cidadeId.model";
import { UserId } from "./userId.model";

export interface Endereco {
  id?: String;
  logradouro: String;
  numero: String;
  complemento: String;
	bairro: String;
  cep: String;
  cidade: CidadeId;
  user: UserId;
}