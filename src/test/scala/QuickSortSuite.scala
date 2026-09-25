import QuickSort.*

class QuickSortSuite extends munit.FunSuite:

  // ---------- menores ----------
  test("menores: lista vacía") {
    assertEquals(menores(Nil, 5), Nil)
  }

  test("menores: filtra los elementos menores que el pivote") {
    assertEquals(menores(List(3, 8, 1, 5, 2), 4), List(3, 1, 2))
  }

  test("menores: no incluye los iguales al pivote") {
    assertEquals(menores(List(4, 2, 4, 1), 4), List(2, 1))
  }

  test("menores: ningún elemento es menor") {
    assertEquals(menores(List(7, 9, 10), 5), Nil)
  }

  test("menores: con números negativos") {
    assertEquals(menores(List(-3, 0, -1, 2), 0), List(-3, -1))
  }

  // ---------- mayores ----------
  test("mayores: lista vacía") {
    assertEquals(mayores(Nil, 5), Nil)
  }

  test("mayores: filtra los elementos mayores o iguales que el pivote") {
    assertEquals(mayores(List(3, 8, 1, 5, 2), 4), List(8, 5))
  }

  test("mayores: incluye los iguales al pivote") {
    assertEquals(mayores(List(4, 2, 4, 6), 4), List(4, 4, 6))
  }

  test("mayores: ningún elemento es mayor o igual") {
    assertEquals(mayores(List(1, 2, 3), 5), Nil)
  }

  // ---------- appendTR ----------
  test("appendTR: ambas listas vacías") {
    assertEquals(appendTR(Nil, Nil), Nil)
  }

  test("appendTR: primera lista vacía") {
    assertEquals(appendTR(Nil, List(1, 2)), List(1, 2))
  }

  test("appendTR: segunda lista vacía") {
    assertEquals(appendTR(List(1, 2), Nil), List(1, 2))
  }

  test("appendTR: concatena conservando el orden") {
    assertEquals(appendTR(List(1, 2, 3), List(4, 5)), List(1, 2, 3, 4, 5))
  }

  test("appendTR: lista grande (no desborda la pila)") {
    def rango(desde: Int, hasta: Int, acc: List[Int]): List[Int] =
      if hasta < desde then acc else rango(desde, hasta - 1, hasta :: acc)
    val grande = rango(1, 100000, Nil)
    assertEquals(appendTR(grande, List(0)).length, 100001)
  }

  // ---------- separar ----------
  test("separar: lista vacía retorna los acumuladores") {
    assertEquals(separar(Nil, 4, Nil, Nil), (Nil, Nil))
  }

  test("separar: separa menores y mayores o iguales (orden invertido)") {
    assertEquals(
      separar(List(3, 8, 1, 5, 2), 4, Nil, Nil),
      (List(2, 1, 3), List(5, 8))
    )
  }

  test("separar: los iguales al pivote van a mayores") {
    assertEquals(separar(List(4, 1, 4), 4, Nil, Nil), (List(1), List(4, 4)))
  }

  test("separar: todos menores") {
    assertEquals(separar(List(1, 2, 3), 10, Nil, Nil), (List(3, 2, 1), Nil))
  }

  test("separar: todos mayores") {
    assertEquals(separar(List(11, 12), 10, Nil, Nil), (Nil, List(12, 11)))
  }

  test("separar: respeta acumuladores iniciales no vacíos") {
    assertEquals(
      separar(List(1, 9), 5, List(0), List(7)),
      (List(1, 0), List(9, 7))
    )
  }

  // ---------- quickSort ----------
  test("quickSort: lista vacía") {
    assertEquals(quickSort(Nil), Nil)
  }

  test("quickSort: lista de un elemento") {
    assertEquals(quickSort(List(42)), List(42))
  }

  test("quickSort: lista ya ordenada") {
    assertEquals(quickSort(List(1, 2, 3, 4, 5)), List(1, 2, 3, 4, 5))
  }

  test("quickSort: lista en orden inverso") {
    assertEquals(quickSort(List(5, 4, 3, 2, 1)), List(1, 2, 3, 4, 5))
  }

  test("quickSort: lista con elementos repetidos") {
    assertEquals(quickSort(List(3, 1, 3, 2, 1, 3)), List(1, 1, 2, 3, 3, 3))
  }

  test("quickSort: todos los elementos iguales") {
    assertEquals(quickSort(List(7, 7, 7, 7)), List(7, 7, 7, 7))
  }

  test("quickSort: lista desordenada con negativos") {
    assertEquals(
      quickSort(List(10, -2, 0, 7, -8, 3)),
      List(-8, -2, 0, 3, 7, 10)
    )
  }