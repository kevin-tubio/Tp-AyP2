# Tp-AyP2

Reconquistando la Tierra de Fantasia

En esta aventura acompañaras a una raza de guerreros en su mision por reconquistar la tierra que le ha sido arrebatada en anteriores batallas. Estos guerreros han recobrado fuerzas y disponen de un ejercito que consideran suficientemente poderoso para emprender esta epopeya.

De camino a la tierra deseada se encontraran con diversos poblados, algunos de los cuales son hostiles y deberan vencer para poder continuar el camino. Otros, son aliados, y permitiran que las tropas descansen. Es importantisimo no desperdiciar recursos y, aun a riesgo de no encontrar poblados aliados, recorrer el camino mas rapido entre su ubicacion actual y la tierra destino: el tiempo apremia. Cada batalla o descanso, dura un dia.

Se te busca, hechicero del codigo, para que prepares unos conjuros algoritmicos que predigan si esta mision es factible, y si asi lo fuera, cuantos guerreros llegaran hasta el final del camino y en cuanto tiempo.

Datos para resolver el problema
-------------------------------
Razas
-----

Un Wrives tiene una salud inicial de 108. Utiliza magia, y su rango de ataque es de 14 a 28 metros. Ocasiona un daño basico de 113 puntos. Cuando ataca, lo hace con 2 veces su daño, cada 2 ataques. Al recibir un ataque recibe 2 veces el daño, ya que no lleva armadura. Cuando descansa, medita, y como considera la violencia como algo malo, se rehusa a atacar hasta que lo ataquen. Gracias a esto, aumenta su salud y su salud maxima en 50.

Una Reralopes tiene una salud inicial de 53. Utiliza una catapulta, y su rango de ataque es de 5 a 46 metros. Ocasiona un daño basico de 27 puntos. Cuando ataca, erra 2 de cada 4 ataques. Al recibir un ataque se desconcentra y sus ataques vuelven al valor normal inicial. Cuando descansa, se concentra y sus proximos 3 ataques dañan el doble del valor correspondiente.

Una Radaiteran tiene una salud inicial de 36. Utiliza shurikens, y su rango de ataque es de 17 a 41 metros. Ocasiona un daño basico de 56 puntos. Cuando ataca, lo hace cada vez con mas fuerza (3 de daño extra x la cantidad de ataques dados). Al recibir un ataque lo hace normalmente. Cuando descansa, no le sucede nada.

Un Nortaichian tiene una salud inicial de 66. Utiliza un arco, y su rango de ataque es de 16 a 22 metros. Ocasiona un daño basico de 18 puntos. Cuando ataca, se cura un 4 por ciento de su salud. Al recibir un ataque se enfurece y sus ataques multiplican por 2 su daño (dura 2 turnos). Cuando descansa, recupera toda su salud, pero se vuelve de piedra por 2 turnos, lo que hace que no pueda atacar, pero reduce el daño entrante en 1/2.

Batallas
--------

Las batallas en la Tierra de Fantasia se realizan de una manera muy ordenada:

1) Se forman ambos ejercitos en linea
2) Siempre comienza a atacar nuestro ejercito
3) Se turnan ambos ejercitos para atacarse
4) Al quedarse con la salud en cero, la unidad se desmaya y queda fuera de combate
5) Termina el combate cuando un ejercito se queda sin contendientes de pie

Poblados
--------

Se suministrara un archivo con la informacion de los caminos que interconectan a los poblados, y los datos de dicho poblado. Por ejemplo:

- 4
- 1 100 Wrives propio
- 2 30 Reralopes aliado
- 3 40 Nortaichian enemigo
- 4 60 Nortaichian enemigo
- 1 -> 4
- 1 2 10
- 1 3 20
- 2 3 5
- 3 4 7


En ese archivo figura toda la informacion necesaria para la prediccion:
- Una linea con la cantidad de pueblos (n, 4 en el ejemplo)
- n lineas autonumeradas, que representan cada pueblo, con el total de habitantes, la raza, y si es propio / aliado / enemigo. "Propio" sera un unico pueblo.
- Una linea que indica el pueblo inicial, y el pueblo final (1 -> 4)
- x lineas que indican el costo de trasladarse entre cada par de pueblos, siendo estos datos pueblo de origen, pueblo destino, costo del traslado en dias.

Condiciones
-----------

1) Las razas deberan programarse utilizando la tecnica de TDD. A cada prueba escrita deberan asignarle un numero consecutivo, que evidencie el desarrollo incremental. Por ejemplo, prueba001, prueba002, etc. Por supuesto, separar las pruebas en distintas clases, segun que raza se esta probando. Se verificara una cobertura mayor al 92%.

2) El camino mas corto debera calcularse utilizando un algoritmo de grafos apropiado. El mismo debera programarse en terminos de un grafo y no de los terrenos, para poder ser reutilizado en futuros usos.

3) El mapa es unico para todo el problema, y debe poder accederse a la misma instancia desde cualquier clase que lo requiera. Utilizar un patron de diseño para este comportamiento.

4) Tanto el ejercito como la unidad individual deben poder tratarse de manera uniforme para el ataque, la recepcion de golpes y el descanso. Utilizar un patron de diseño para este comportamiento.

Puntos extra (opcional)
-----------------------

El grupo que dada una configuracion que resulte en derrota, pueda dar programaticamente una alternativa deterministica que acabe con la victoria, teniendo el menor costo posible, con una complejidad computacional aceptable.

Sobre las entregas
------------------

Cada equipo tiene asignado un/a tutor/a, el cual los acompañara durante el desarrollo del trabajo practico, y sera quien lo corrija. Es importante que aprovechen esta relacion para evacuar dudas, discutir implementaciones, y coordinar las entregas. Todo esto debe ser coordinado con su tutor previamente, con suficiente antelacion.

Habra una preentrega, para confirmar que el rumbo que tomaron es el correcto, mas alla de las consultas que puedan coordinarse. Esta preentrega debera tener al menos el diagrama de clases del problema, para poder discutir la solucion.

La fecha de esta primera aproximacion se coordinara con cada tutor.

Para la entrega final, el 22 y 23 de Junio (dependiendo su dia de cursada), se debera disponer de los siguientes entregables:
- Diagrama de clases actualizado (.jpg)
- Pruebas unitarias del codigo
- El codigo debidamente comentado, formateado y sin errores de compilacion
- Un main que evidencie el funcionamiento del programa
- Al menos cuatro archivos de entrada de prueba significativamente diferentes, para poder ejecutar distintas versiones del problema.
- Un pequeño informe que explique el trabajo realizado (.pdf). Este informe no debe explicar el codigo, sino como se penso la solucion y como se distribuyo el trabajo.
