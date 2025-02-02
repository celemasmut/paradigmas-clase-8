package paradigms.clase8;


import io.reactivex.rxjava3.core.Observable;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Resuelve cada uno de los ejercicios según el enunciado del test. Imprime cada ejecución de forma que se pueda
 * corroborar el correcto funcionamiento.
 *
 */
public class Ejercicios {

    /** TODO:
     *    - Multiplica todos los elementos por 2.
     */
    @Test
    void multiplicaTodoPor2() {
        final var source = Observable.fromIterable(List.of(1, 2, 3, 4, 5, 6));

        source.map(integer -> integer * 2)
                .subscribe(System.out::println);

    }

    /** TODO:
     *    - Quédate solo con los elementos pares y multiplícalos por 2.
     */
    @Test
    void multiplicaLosParesPor2() {
        final var source = Observable.fromIterable(List.of(1, 2, 3, 4, 5, 6));

        source.filter(integer -> integer % 2 != 0)
                .map(integer -> integer * 2)
                .subscribe(System.out::println);

    }

    /** TODO:
     *    - Repite 3 veces cada elemento.
     */
    @Test
    void repite3VecesCadaElemento() {
        final var source = Observable.fromIterable(List.of(1, 2, 3, 4, 5, 6));

        source.flatMap(element -> Observable.fromIterable(Collections.nCopies(3, element)))
                .subscribe(System.out::println);

    }

    /** TODO:
     *    - Repite 3 veces cada elemento par y 1 sola vez los elementos impares.
     */
    @Test
    void repite3VecesLosParesY1VezLosImpares() {
        final var source = Observable.fromIterable(List.of(1, 2, 3, 4, 5, 6));

        StringBuilder result = new StringBuilder();

        source.groupBy(i -> 0 == (i % 2) ? "PAR" : "IMPAR")
                .subscribe(group -> group
                        .map(number ->
                                Optional.of("PAR".equals(group.getKey()))
                                        .filter(isEven -> isEven)
                                        .map(i -> number.toString().repeat(3))
                                        .orElse(number.toString()))
                        .subscribe(result::append));

        // Flatmap approach
        /*source.flatMap(element -> Optional.of(element % 2 == 0)
                        .filter(isEven -> isEven)
                        .map(isEven -> Observable.fromIterable(Collections.nCopies(3, element)))
                        .orElseGet(() -> Observable.just(element)))
                .subscribe(result::append);*/

        assertEquals("122234445666", result.toString());
    }

    /** TODO:
     *    - Emite elementos hasta que encuentres un elemento impar, entonces lanza un error.
     */
    @Test
    void emiteElementosHastaQueEncuentresUnElementoImpar() {
        final var source = Observable.fromIterable(List.of(1, 2, 3, 4, 5, 6));
    }
}
