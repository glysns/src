import type { Pedido } from "~/interfaces/pedido";

const pedido = {} as Pedido;
pedido.total = 0;
export const usePedidoState = () => useState<Pedido>('pedido', () => pedido )