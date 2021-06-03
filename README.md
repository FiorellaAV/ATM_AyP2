# ATM_AyP2
Este proyecto es un cajero automático, realizado en Java. El mismo realiza operaciones por consola, entre ellas trasferencias, depositos, retiros, entre otros. 
Decisiones de diseño tomadas:

Se ha optado por utilizar un diseño informado
en el cual, al inciar el programa
el usuario se encontrara con una interface
la cual le pedira su numero de tarjeta
y pin ,en el caso de que la misma este en la ´
base de datos, se procedera a un menu interactivo
en donde se debera elegir la cuenta con la que se
desea operar. Luego de elegir, se le presentara
un nuevo menu con operaciones bancarias para 
realizar (Consultas/solicitudes, 
Extracciones/adelantos y Transferencia/depositos).

Descripcion de cada archivo *.java

Cajero.java:
Clase principal, en donde se ejecuta la interfaz
y los metodos para realizar las operaciones entre
las cuentas.

Cuenta.java:
Clase en la cual se crea la cuenta padre a partir
de su alias, saldo y movimientos. En ella se encuentran sus
respectivos getters y setters, y con un array de movimientos

CuentaCorriente.java:
Clase hija de la clase padre Cuenta. La misma se crea 
con el constructor herado del padre y con un Descubierto, ademas
de un metodo getter para conocerlo.

CajaDeAhorroPesos.java:
Clase hija de la clase Cuenta, creada con el constructor 
heredado.

CajaDeAhorroDolares.java:
Clase hija de la clase Cuenta, creada con el constructor 
heredado.

Cliente.java:
Clase en donde se crea un cliente a partir
de su cuit, alias y tarjeta. En ella se encuentran
sus respectivos getters y setters. 

Tarjeta.java:
Clase creada a partir de un numero, pin y cuit. la misma 
cuenta con getters y setters

Movimiento.java:
Clase creada a partir de una fecha, motivo, alias e importe.
la misma cuenta con getters y setters

ErrorDeValidacion.java:
Clase hija de la clase Exception, se crea a partir de un String,
la misma cuenta con un getter.

ErrorDeTransaccion.java:
Clase hija de la clase Exception, se crea a partir de un String,
la misma cuenta con un getter.

Test.java:
Clase utilizada para el testeo de las diversas funciones
del programa.

Pantalla.java:
Interfaz que utiliza la clase main Cajero. En ella se encuentran
los metodos de la interfaz gráfica.

Transaccion.java:
Clase padre de todas las operaciones que se realizan en el
cajero, cuenta con dos constructores, para que se utilice uno
u otro segun la operacion. El primero se crea a partir de dos
cuentas (una de origen y otra de destino), un monto y el cajero.
El segundo se crea a partir de una cuenta, un monto y el cajero.

Revertible.java:
Interfaz que utiliza la clase transaccion, para revertir
la operacion.

Retiro.java:
Clase hija de transaccion, se crea a partir de una cuenta,
el monto y el cajero.En ella se encuentra el metodo para
retirar de la cuenta.

Impresora.java:
Clase que crea los tiquets de las operaciones realizadas
en la sesion.

Deposito.java:
Clase hija de transaccion, se crea a partir de una cuenta,
el monto y el cajero. En ella se encuenta el metodo para 
depositar en la cuenta.

CompraDeDolares.java:
Clase hija de transaccion, se crea a partir de dos cuentas
(una de origen y otra de destino), el monto y el cajero.
En ella se encuentra el metodo para comprar dolares.

Transferencia.java:
Clase hija de transaccion,se crea a partir de dos cuentas
(una de origen y otra de destino), el monto y el cajero.
En ella se encuenta el metodo para realizar una transferencia

Conclusiones
Nos parecio una consigna interesante para abordar todos los 
temas de la cursada.
