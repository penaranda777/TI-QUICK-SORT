import scala.annotation.tailrec

object QuickSort:

  /** Actividad 1.1: elementos de inputList estrictamente menores que p. */
  def menores(inputList: List[Int], p: Int): List[Int] =
    inputList match
      case Nil => Nil
      case head :: tail =>
        if head < p then head :: menores(tail, p)
        else menores(tail, p)

  /** Actividad 1.2: elementos de inputList mayores o iguales que p.
   * Se usa >= (como en la definición matemática) para no perder
   * los elementos repetidos iguales al pivote.
   */
  def mayores(inputList: List[Int], p: Int): List[Int] =
    inputList match
      case Nil => Nil
      case head :: tail =>
        if head >= p then head :: mayores(tail, p)
        else mayores(tail, p)

  /** Concatena inputL1 seguida de inputL2 con recursión de cola.
   * Primero invierte inputL1 y luego pasa sus elementos, uno a uno,
   * al frente de inputL2.
   */
  def appendTR(inputL1: List[Int], inputL2: List[Int]): List[Int] =
    @tailrec
    def pasar(origen: List[Int], acc: List[Int]): List[Int] =
      origen match
        case Nil          => acc
        case head :: tail => pasar(tail, head :: acc)

    pasar(pasar(inputL1, Nil), inputL2)

  /** Actividad 2: separa en un solo recorrido los menores (< p)
   * y los mayores o iguales (>= p), usando dos acumuladores.
   * Nota: los elementos quedan en orden inverso al de aparición,
   * lo cual no afecta al ordenamiento.
   */
  @tailrec
  def separar(
               inputList: List[Int],
               p: Int,
               menoresAcc: List[Int],
               mayoresAcc: List[Int]
             ): (List[Int], List[Int]) =
    inputList match
      case Nil => (menoresAcc, mayoresAcc)
      case head :: tail =>
        if head < p then separar(tail, p, head :: menoresAcc, mayoresAcc)
        else separar(tail, p, menoresAcc, head :: mayoresAcc)

  /** Actividad 3: QuickSort según la definición matemática:
   *   qsort([])    = []
   *   qsort([x])   = [x]
   *   qsort(p :: t) = append(qsort(menores(t, p)), [p], qsort(mayores(t, p)))
   */
  def quickSort(inputList: List[Int]): List[Int] =
    inputList match
      case Nil       => Nil
      case _ :: Nil  => inputList
      case p :: tail =>
        appendTR(
          quickSort(menores(tail, p)),
          p :: quickSort(mayores(tail, p))
        )