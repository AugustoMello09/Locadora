import { Estadoid } from "./estoqueId.model";
import { UserId } from "./userId.model";

export interface ReservaOnlineInsert {
  id?: string;
  qtdReservada: string;
  dataReserva: string;
  user: UserId;
  estoque: Estadoid;
}