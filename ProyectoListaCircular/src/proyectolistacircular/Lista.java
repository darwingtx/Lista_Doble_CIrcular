package proyectolistacircular;
import javax.swing.JOptionPane;

public class Lista {
    // Atributos

    private Nodo punta, fin;

    // metodos
    public Lista() {
        punta = null;
        fin = null;
    }

    public void InsertarInicio(float d) {
        Nodo x = new Nodo(d);

        if (punta == null) {
            fin = x;
            fin.setLigaSig(fin);
            fin.setLigaAnt(fin);
        } else {
            x.setLigaSig(punta);
            x.setLigaAnt(fin);
            fin.setLigaSig(x);
            punta.setLigaAnt(x);
        }
        punta = x;// Avanza Punta
    }

    public void InsertarFinal(float d) {

        Nodo x = new Nodo(d);

        if (punta == null) {
            punta = x;
            fin = x;
            punta.setLigaSig(fin);
            punta.setLigaAnt(fin);
        } else {
            fin.setLigaSig(x);
            x.setLigaAnt(fin);
            fin = x;
            punta.setLigaAnt(fin);
            fin.setLigaSig(punta);
        }

    }

    public void InsertarOrdenadoAscendente(float d) {
        Nodo x = new Nodo(d);
        Nodo p = punta, a;
        if (punta == null) {
            punta = x;
            fin = x;
            punta.setLigaSig(fin);
            punta.setLigaAnt(fin);
            p = punta;
        } else {
            do {
                p = p.getLigaSig();
            } while (p != punta && p.getDato() <= d);

            if (p == punta && p.getDato() <= d) {
                this.InsertarFinal(d);
            } else if (punta.getDato() >= d) {
                this.InsertarInicio(d);
            } else if (p.getDato() >= d) {
                a = p.getLigaAnt();
                x.setLigaAnt(a);
                x.setLigaSig(p);
                a.setLigaSig(x);
                p.setLigaAnt(x);

            }
        }
    }

    public void InsertarOrdenadoDescendente(float d) {
        Nodo x = new Nodo(d);
        Nodo p = punta, a;
        if (punta == null) {
            punta = x;
            fin = x;
            punta.setLigaSig(fin);
            punta.setLigaAnt(fin);
            p = punta;
        } else {
            do {
                p = p.getLigaSig();
            } while (p != punta && p.getDato() >= d);

            if (p == punta && p.getDato() >= d) {
                this.InsertarFinal(d);
            } else if (punta.getDato() <= d) {
                this.InsertarInicio(d);
            } else if (p.getDato() <= d) {
                a = p.getLigaAnt();
                x.setLigaAnt(a);
                x.setLigaSig(p);
                a.setLigaSig(x);
                p.setLigaAnt(x);

            }
        }

    }

    public void Ordenar(boolean cond) {
        Nodo p = punta, a;
        float x;
        if (this.Vacio()) {
            JOptionPane.showMessageDialog(null, "Lista vacia ");
        } else {
            do {
                a = punta;
                do {
                    if (cond && a.getDato() > p.getDato()) {
                        x = p.getDato();
                        p.setDato(a.getDato());
                        a.setDato(x);
                    } else if (!cond && a.getDato() < p.getDato()) {
                        x = a.getDato();
                        a.setDato(p.getDato());
                        p.setDato(x);
                    }
                    a = a.getLigaSig();
                } while (a != punta);
                p = p.getLigaSig();
            } while (p != punta);
        }
    }

    public void MostrarLista() {
        if (!this.Vacio()) {
            String s = "";
            Nodo p = punta;

            do {
                s = s + "|" + p.getDato() + "|" + "<--->";
                p = p.getLigaSig();
            } while (p != punta);

            JOptionPane.showMessageDialog(null, s);
        } else {
            JOptionPane.showMessageDialog(null, "Lista vacia ");
        }
    }

    public void Buscar(float d) {
        Nodo p = punta;
        boolean ba = false;
        int op;
        float dato;
        if (this.Vacio()) {
            JOptionPane.showMessageDialog(null, "Lista vacia ");
        } else {

            op = Integer.parseInt(JOptionPane.showInputDialog("*** Menu de busqueda ***\n\n"
                    + "1. Eliminar\n"
                    + "2. Remplazar\n"));
            do {
                if (p != null && p.getDato() == d) {
                    ba = true;

                    switch (op) {
                        case 1 -> {
                            if (punta == fin) {
                                punta = null;
                                fin = null;
                                p = punta;
                            } else {
                                p = p.getLigaSig();
                                Eliminar(p.getLigaAnt());

                            }
                            // p = a.getLigaSig();
                        }
                        case 2 -> {
                            dato = Float.parseFloat(JOptionPane.showInputDialog("Escriba el dato a remplazar: "));
                            p.setDato(dato);
                        }
                        default ->
                            JOptionPane.showMessageDialog(null, "Error, floatete nuevamente");

                    }
                    while (p == punta && punta != fin && p.getDato() == d) {
                        p = p.getLigaSig();
                        Eliminar(p.getLigaAnt());
                    }
                    if (punta != fin && p == punta) {
                        p = p.getLigaSig();
                    }
                } else if (punta != fin) {
                    p = p.getLigaSig();
                }

            } while (p != punta);
            if (!ba) {
                JOptionPane.showMessageDialog(null, "No se encontro el dato");
            }
        }
    }

    public void Eliminar(Nodo x) {// x llega como p y q llega como q
        Nodo p = x;
        Nodo a, s;
        if (p != punta) {
            a = p.getLigaAnt();
            s = p.getLigaSig();
            a.setLigaSig(s);
            s.setLigaAnt(a);
            p.setLigaAnt(null);
            p.setLigaSig(null);

            if (fin.getLigaAnt() == null) {
                fin = a;
                punta.setLigaAnt(fin);
                fin.setLigaSig(punta);
            }

        } else {

            s = p.getLigaSig();
            punta = s;
            s.setLigaAnt(fin);
            p.setLigaSig(null);
            p.setLigaAnt(null);
            fin.setLigaSig(punta);

        }

    }

    public void MenuList(Lista l2, Lista l3, int a) {
        int opc = 0, opi = 0;
        float Dato;

        do {
            opc = Menu();
            switch (opc) {
                case 1:
                    Dato = Float.parseFloat(JOptionPane.showInputDialog("Insertar valor"));
                    this.InsertarInicio(Dato);

                    break;
                case 2:
                    Dato = Float.parseFloat(JOptionPane.showInputDialog("Insertar valor"));
                    this.InsertarFinal(Dato);

                    break;
                case 3:
                    opi = Integer.parseInt(JOptionPane.showInputDialog(null,
                            "****Seleccione el modo Ordenado de insercion****\n"
                                    + "1.Ascendente.\n"
                                    + "2.Descendente.\n"));
                    switch (opi) {
                        case 1:
                            Dato = Float.parseFloat(JOptionPane.showInputDialog("Insertar valor"));
                            if (punta != null) {
                                this.Ordenar(true);
                            }
                            this.InsertarOrdenadoAscendente(Dato);
                            break;
                        case 2:
                            Dato = Float.parseFloat(JOptionPane.showInputDialog("Insertar valor"));
                            if (punta != null) {
                                this.Ordenar(false);
                            }
                            this.InsertarOrdenadoDescendente(Dato);
                            break;
                        default:
                            JOptionPane.showInputDialog(null, "Valor invalido");
                            break;
                    }
                    break;
                case 4:
                    opi = Integer.parseInt(JOptionPane.showInputDialog(null,
                            "De manera Ascedente o descedente?\n 1) Ascedente\n 2) Descedente"));
                    switch (opi) {
                        case 1:
                            this.Ordenar(true);
                            break;
                        case 2:
                            this.Ordenar(false);
                        default:
                            break;
                    }
                    break;
                case 5:
                    Dato = Float.parseFloat(JOptionPane.showInputDialog("Insertar valor a buscar"));
                    ;
                    this.Buscar(Dato);

                    break;
                case 6:

                    this.MostrarLista();
                    break;
                case 7:

                    opi = Integer.parseInt(JOptionPane.showInputDialog(null,
                            "****Seleccione la operacion a efectuar****\n"
                                    + "1.Suma.\n"
                                    + "2.Resta.\n"
                                    + "3.Multiplicacion.\n"
                                    + "4.Division.\n"));
                    switch(a){
                        case 1:
                            if(this.Vacio())
                            {JOptionPane.showMessageDialog(null,"La lista l1 se encuentra vacia.");
                            } if(l2.Vacio())
                            {JOptionPane.showMessageDialog(null,"La lista l2 se encuentra vacia.");
                            }
                            break;
                            case 2:
                            if(this.Vacio())
                            {JOptionPane.showMessageDialog(null,"La lista l2 se encuentra vacia.");
                            }  if(l2.Vacio())
                            {JOptionPane.showMessageDialog(null,"La lista l1 se encuentra vacia.");
                            }
                            
                            break;
                            default:
                            break;
                    }
                    switch (opi) {
                        case 1:
                            l3.punta = null;
                            
                            this.Sumar(l2, l3);
                            l3.MostrarLista();
                            break;
                        case 2:
                            l3.punta = null;
                            this.Restar(l2, l3);
                            l3.MostrarLista();
                            break;
                        case 3:
                            l3.punta = null;
                            this.Multiplicar(l2, l3);
                            l3.MostrarLista();
                            break;
                        case 4:
                            l3.punta = null;
                            this.Dividision(l2, l3);
                            l3.MostrarLista();
                            break;
                        default:
                            JOptionPane.showInputDialog(null, "Valor invalido");
                            break;
                    }

                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "--Has salido al menu de selección de listas--");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "***Opcion incorresta***");
                    break;
            }

        } while (opc != 8);
    }

    public static int Menu() {
        int opc = Integer.parseInt(JOptionPane.showInputDialog(null, "****Menu principal****\n\n"
                + "1. Insertar Inicio\n"
                + // listo
                "2. Insertar Final\n"
                + // listo
                "3. Insertar Ordenado\n"// listo Ascendente and Descendente
                + "4. Ordenar Lista\n"// listo
                + "5. Buscar Dato\n"
                + // listo
                "6. Mostrar Lista\n"
                + // listo
                "7. Operacion de dos Listas\n"
                + "8. Salir al menu lista\n\n\n"
                + "Ingrese la opcion: "));
        return opc;
    }

    public void Sumar(Lista l2, Lista l3) {
        Nodo p, x;
        p = this.punta;
        x = l2.punta;

        if (this.Vacio() && l2.Vacio()) {
           
        } else {

            if (p == null) {
                l3.punta = x;
            } else if (x == null) {
                l3.punta = p;
            } else {
                if (p == punta && x == l2.punta) {
                    l3.InsertarFinal(p.getDato() + x.getDato());
                    p = p.getLigaSig();
                    x = x.getLigaSig();
                }

                do {
                    if (p != punta && x != l2.punta) {
                        l3.InsertarFinal(p.getDato() + x.getDato());
                        p = p.getLigaSig();
                        x = x.getLigaSig();
                    } else if (p != punta) {
                        l3.InsertarFinal(p.getDato());
                        p = p.getLigaSig();
                    } else {
                        l3.InsertarFinal(x.getDato());
                        x = x.getLigaSig();
                    }
                } while (p != punta || x != l2.punta);
            }

            
           
            }
        }

    public void Restar(Lista l2, Lista l3) {
        Nodo p, x;
        p = this.punta;
        x = l2.punta;
        if (this.Vacio() && l2.Vacio()) {
            
        } else {

            if (p != null && x != null && p == punta && x == l2.punta) {
                l3.InsertarFinal(p.getDato() - x.getDato());
                p = p.getLigaSig();
                x = x.getLigaSig();
                do {
                    if (p != punta && x != l2.punta) {
                        l3.InsertarFinal(p.getDato() - x.getDato());
                        p = p.getLigaSig();
                        x = x.getLigaSig();
                    } else if (p != punta) {
                        l3.InsertarFinal(p.getDato());
                        p = p.getLigaSig();
                    } else {
                        l3.InsertarFinal(x.getDato() * -1);
                        x = x.getLigaSig();
                    }

                } while (p != punta || x != l2.punta);
            }
            if (x == null) {
                l3.punta = p;
            } else if (p == null) {
                do {
                    l3.InsertarFinal(x.getDato() * -1);
                    x = x.getLigaSig();
                } while (x != l2.punta);
            }

           
           
        }
    }

    public void Multiplicar(Lista l2, Lista l3) {
        Nodo p, x;
        p = this.punta;
        x = l2.punta;
         
           
         if (!l2.Vacio() || !this.Vacio()) {

            if (p != null && x != null && p == punta && x == l2.punta) {
                l3.InsertarFinal(p.getDato() * x.getDato());
                p = p.getLigaSig();
                x = x.getLigaSig();
                do {
                    if (p != punta && x != l2.punta) {
                        l3.InsertarFinal(p.getDato() * x.getDato());
                        p = p.getLigaSig();
                        x = x.getLigaSig();
                    } else if (p != punta) {
                        l3.InsertarFinal(0);
                        p = p.getLigaSig();
                    } else {
                        l3.InsertarFinal(0);
                        x = x.getLigaSig();
                    }
                } while (p != punta || x != l2.punta);

            }
            if (x == null || p == null) {
                do {
                    l3.InsertarFinal(0);
                    if (p == null) {
                        x = x.getLigaSig();
                    } else {
                        p = p.getLigaSig();
                    }
                } while (x != l2.punta || p != punta);
            }
           
        }
    }

    public void Dividision(Lista l2, Lista l3) {
        Nodo p, x;
        p = this.punta;
        x = l2.punta;
       
         if (!this.Vacio() && !l2.Vacio()) {
            if (p == punta && x == l2.punta) {
                if (x.getDato() != 0) {
                    l3.InsertarFinal(p.getDato() / x.getDato());
                } else if (x.getDato() == 0) {
                    l3.InsertarFinal(11111111);
                }
                p = p.getLigaSig();
                x = x.getLigaSig();
            }
            do {
                if (x.getDato() != 0) {
                    if (p != punta && x != l2.punta) {
                        l3.InsertarFinal(p.getDato() / x.getDato());
                    }
                 else if ((x == l2.punta || p == punta) || x.getDato() == 0) {
                    l3.InsertarFinal(111111111);
                }
                }
                if ( p != punta) {
                    p = p.getLigaSig();
                }
                if (x != l2.punta) {
                    x = x.getLigaSig();
                }

            } while (x != l2.punta || p != punta);
             }
         else if (this.Vacio() || l2.Vacio())
         {       
        do{
         l3.InsertarFinal(111111111);
         if (!this.Vacio()) {
                    p = p.getLigaSig();
                }
          if (!l2.Vacio()) {
                    x = x.getLigaSig();}
         
         }while(p!=punta || x!=l2.punta);
         }
    }

    
    // retorna true si la lista esta vacia
    public boolean Vacio() {
        return punta == null ? true : false;
    }
}