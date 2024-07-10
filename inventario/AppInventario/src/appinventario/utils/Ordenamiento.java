package appinventario.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class Ordenamiento {

    public static <T> List<T> burbuja(List<T> lista, BiPredicate<T,T> predicado){
        
        int tamaño = lista.size();
        
        for(int i = 0; i < tamaño - 1; i++){
            for(int j = 0; j < tamaño - i - 1; j++){
                if(predicado.test(lista.get(j), lista.get(j+1))){
                    //Si es verdadero intercambiamos
                    T tmp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, tmp);
                }
            }
        }

        return lista;
    }

    public static <T> List<T> quicksort(List<T> lista, BiPredicate<T,T> predicado){
        
        //Verificamos si la lista es menor o igual a 1
        if (lista.size() <= 1) {
            return lista;
        }

        //Definimos el pivote
        T pivot = lista.get(0);
        
        List<T> menores = new ArrayList<>();
        List<T> iguales = new ArrayList<>();
        List<T> mayores = new ArrayList<>();
        
        for (T elemento : lista) {
            if (!predicado.test(elemento, pivot)) {
                menores.add(elemento);
            } else if (!predicado.test(pivot, elemento)) {
                mayores.add(elemento);
            } else {
                iguales.add(elemento);
            }
        }
        
        menores = quicksort(menores, predicado);
        mayores = quicksort(mayores, predicado);
        
        menores.addAll(iguales);
        menores.addAll(mayores);
        
        return menores;
    }
}
