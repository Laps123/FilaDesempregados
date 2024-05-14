import java.util.Scanner;

class Main {
    static class Pessoa {
        int id;
        Pessoa prev;
        Pessoa next;

        public Pessoa(int id) {
            this.id = id;
        }
    }

    static Pessoa preencher(int tamanho) {
        Pessoa inicio = null;
        Pessoa noAnterior = null;

        for (int i = 1; i <= tamanho; ++i) {
            Pessoa no = new Pessoa(i);

            if (inicio == null) {
                inicio = no;
            } else {
                noAnterior.next = no;
                no.prev = noAnterior;
            }

            noAnterior = no;
        }

        inicio.prev = noAnterior;
        noAnterior.next = inicio;

        return inicio;
    }

    static Pessoa deletar(Pessoa lista, Pessoa reg) {
        Pessoa noAnterior = reg.prev;
        Pessoa noProximo = reg.next;

        if (reg == lista) {
            lista = lista.next;
            noAnterior.next = lista;
            lista.prev = reg.prev;
        } else {
            noAnterior.next = noProximo;
            noProximo.prev = noAnterior;
        }

        return lista;
    }

    static int contar(Pessoa lista) {
        int i = 1;
        Pessoa no = lista;
        while (lista != no.next) {
            no = no.next;
            i++;
        }
        return i;
    }

    static Pessoa percorrer(Pessoa lista, int n, int direcao) {
        Pessoa no = lista;
        if (direcao == 0) {
            while (--n > 0) {
                no = no.next;
            }
        } else {
            while (--n > 0) {
                no = no.prev;
            }
        }
        return no;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int m = scanner.nextInt();
            if (n == 0) break;

            Pessoa lista = preencher(n);
            Pessoa K = lista;
            Pessoa M = lista.prev;

            while (contar(lista) > 2) {
                K = percorrer(K, k, 0);
                M = percorrer(M, m, 1);

                Pessoa auxK, auxM;
                if (K.next == M) {
                    auxK = M.next;
                } else {
                    auxK = K.next;
                }

                if (M.prev == K) {
                    auxM = K.prev;
                } else {
                    auxM = M.prev;
                }

                if (K == M) {
                    System.out.printf("%3d,", M.id);
                    lista = deletar(lista, K);
                } else {
                    System.out.printf("%3d%3d,", K.id, M.id);
                    lista = deletar(lista, M);
                    lista = deletar(lista, K);
                }

                K = auxK;
                M = auxM;
            }

            if (contar(lista) == 2) {
                K = percorrer(K, k, 0);
                M = percorrer(M, m, 1);

                if (K == M) {
                    System.out.printf("%3d,%3d\n", K.id, K.next.id);
                } else {
                    System.out.printf("%3d%3d\n", K.id, K.next.id);
                }
            } else {
                System.out.printf("%3d\n", lista.id);
            }
        }
        scanner.close();
    }
}
